package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.mergelygwt.client.Mergely;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TopicRevisionsPresenter extends BaseTemplatePresenter {

    public interface Display extends BaseTemplateViewInterface, BaseCustomViewInterface<RESTTopicV1> {

        EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider);

        CellTable<RESTTopicCollectionItemV1> getResults();

        SimplePager getPager();

        Column<RESTTopicCollectionItemV1, String> getViewButton();

        Column<RESTTopicCollectionItemV1, String> getDiffButton();

        Column<RESTTopicCollectionItemV1, String> getHTMLDiffButton();

        /**
         * @return The currently selected revision topic.
         */
        RESTTopicV1 getRevisionTopic();

        /**
         * @param revisionTopic The currently selected revision topic.
         */
        void setRevisionTopic(RESTTopicV1 revisionTopic);

        PushButton getDone();

        PushButton getCancel();

        PushButton getHTMLDone();

        Mergely getMergely();

        void displayRevisions();

        void displayDiff(String lhs, boolean lhsReadOnly, String rhs);

        void displayHtmlDiff(String htmlDiff);

        /**
         *
         * @return true if the view is displaying the list of revisions, and false if
         * it is in any other state (i.e. showing or in the process of showing a diff).
         */
        boolean isDisplayingRevisions();

        boolean isButtonsEnabled();

        void setButtonsEnabled(boolean buttonsEnabled);

        void showWaitingFromRenderedDiff();
    }

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TopicRevisionsPresenter.class.getName());

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "TopicHistoryView";

    /**
     * The start of the IDs assigned to the iFrames that are used to render the XML.
     */
    private static final String FRAME_ID_PREFIX = "TempRenderingFrame";
    private static final String CURRENT_FRAME_ID_PREFIX = "Current" + FRAME_ID_PREFIX;
    private static final String COMPARE_FRAME_ID_PREFIX = "Compare" + FRAME_ID_PREFIX;

    private static int tempIFrameCount = 0;

    /**
     * The message listener for HTML5 message passing
     */
    private JavaScriptObject listener;

    /**
     * Saves the html rendered by the XML frames when diffing the rendered html of two revisions
     */
    private String renderedHTML1, renderedHTML2;

    /**
     * The frames that will host the XML to be converted to HTML.
     */
    private Frame currentXML, comparedXML;


    private String topicId;

    @Inject
    private Display display;

    /**
     * Holds the data required to populate and refresh the tags list
     */
    private final ProviderUpdateData<RESTTopicCollectionItemV1> providerData = new ProviderUpdateData<RESTTopicCollectionItemV1>();

    @NotNull
    public ProviderUpdateData<RESTTopicCollectionItemV1> getProviderData() {
        return providerData;
    }


    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.TOPIC_REVISIONS_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void close() {
        removeListener();
    }

    public void bindExtended(final int topicId, @NotNull final String pageId) {
        super.bind(topicId, pageId, display);
        createEventListener();
        addEventListener();
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    public EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateListProvider(@NotNull final Integer id, @NotNull final BaseTemplateViewInterface waitDisplay) {

        getProviderData().reset();

        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTopicCollectionItemV1> list) {

                final BaseRestCallback<RESTTopicV1, Display> callback = new BaseRestCallback<RESTTopicV1, Display>(display, new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTTopicV1 retValue, @NotNull final Display display) {
                        checkArgument(retValue.getRevisions().getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getRevisions().getSize() != null, "Returned collection should have a valid size.");

                        if (retValue.getRevisions().getItems().size() != 0) {
                            checkArgument(retValue.getRevisions().getItems().get(0).getItem().getProperties() != null, "Returned collection should include items with a valid properties collection.");
                            checkArgument(retValue.getRevisions().getItems().get(0).getItem().getSourceUrls_OTM() != null, "Returned collection should include items with a valid source urls collection.");
                        }

                        getProviderData().setItems(retValue.getRevisions().getItems());
                        getProviderData().setSize(retValue.getRevisions().getSize());
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                    }
                });

                final int start = list.getVisibleRange().getStart();
                getProviderData().setStartRow(start);
                final int length = list.getVisibleRange().getLength();
                final int end = start + length;

                this.resetProvider();

                RESTCalls.getTopicWithRevisions(callback, id, start, end);
            }
        };
        return provider;
    }

    public void renderXML(@NotNull final Integer echo1, @NotNull final Integer echo2) {
        display.showWaitingFromRenderedDiff();

        currentXML = new Frame();
        comparedXML = new Frame();

        final IFrameElement currentXMLIFrameElement = currentXML.getElement().cast();
        final IFrameElement comparedXMLXMLIFrameElement = comparedXML.getElement().cast();

        ++tempIFrameCount;

        currentXMLIFrameElement.setId(CURRENT_FRAME_ID_PREFIX + tempIFrameCount);
        comparedXMLXMLIFrameElement.setId(COMPARE_FRAME_ID_PREFIX + tempIFrameCount);

        currentXML.setUrl(Constants.REST_SERVER + Constants.ECHO_ENDPOINT + "?id=" + echo1);
        comparedXML.setUrl(Constants.REST_SERVER + Constants.ECHO_ENDPOINT + "?id=" + echo2);

        /*
            iFrames have to be attached to the DOM to load their pages
         */
        display.getHiddenAttachmentArea().add(currentXML);
        display.getHiddenAttachmentArea().add(comparedXML);
    }

    private void displayRenderedHTML() {
        try {
            LOGGER.info("ENTER TopicRevisionsView.saveRenderedHTML()");

            /*
                Check isDisplayingRevisions() here because the user may have
                moved off the view.
             */
            if (renderedHTML1 != null && renderedHTML2 != null && !display.isDisplayingRevisions()) {
                final String diff = diffHTML(renderedHTML1, renderedHTML2);

                renderedHTML1 = renderedHTML2 = null;
                currentXML = comparedXML = null;
                display.getHiddenAttachmentArea().clear();

                display.displayHtmlDiff(diff);
            }
        } finally {
            LOGGER.info("EXIT TopicRevisionsView.saveRenderedHTML()");
        }
    }

    @NotNull
    private native String diffHTML(@NotNull final String html1, @NotNull final String html2) /*-{
		var diff = $wnd.htmldiff(html1, html2);
		return diff;
	}-*/;

    /**
     * The listener hold a reference to this, which will prevent it from being reclaimed by the GC.
     * So here we remove the listener.
     */
    public native void removeListener() /*-{
		if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::listener != null) {
			$wnd.removeEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::listener);
			this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::listener = null;
		}
	}-*/;

    private native void createEventListener() /*-{
		this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::listener =
			function (me) {
				return function displayAfterLoaded(event) {

                    // Match the ids we assigned to the temp rendering iframes to the ids of the source of the message.
                    // This ensures that the diff ordering is correct

                    var currentIFrameID = @org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::CURRENT_FRAME_ID_PREFIX + @org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::tempIFrameCount;
					var compareIFrameID = @org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::COMPARE_FRAME_ID_PREFIX + @org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::tempIFrameCount;

                    if (event.source.id == currentIFrameID) {
						me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::renderedHTML1 = event.data;
                    } else if (event.source.id == compareIFrameID) {
						me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::renderedHTML2 = event.data;
					}

					me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::displayRenderedHTML()();

				};
			}(this);
	}-*/;

    private native void addEventListener() /*-{
		$wnd.addEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::listener);
	}-*/;

}
