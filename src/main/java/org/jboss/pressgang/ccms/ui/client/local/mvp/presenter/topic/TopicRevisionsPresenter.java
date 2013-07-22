package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.mergelygwt.client.Mergely;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

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

        PushButton getHtmlOpenDiff();

        Mergely getMergely();

        void displayRevisions();

        void displayDiff(String lhs, boolean rhsReadOnly, String rhs);

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

    @Inject private FailOverRESTCall failOverRESTCall;

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

    /**
     * The URLs used to return the rendered XML
     */
    private String currentXMLHREF, comparedXMLHREF;

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
        bindExtended();

        /*
            When this presenter is used a sa standalone presenter to display the rendered diff
            view of a topic, the done and new window buttons are not displayed.
         */
        display.getHTMLDone().setVisible(false);
        display.getHtmlOpenDiff().setVisible(false);
        display.showWaitingFromRenderedDiff();

    }

    @Override
    public void close() {
        removeListener();
    }

    public void bindExtended() {
        super.bind(display);
        createEventListener();
        addEventListener();
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            final String fixedToken = removeHistoryToken(historyToken, HISTORY_TOKEN);
            final String[] topicDetails = fixedToken.split(";");
            if (topicDetails.length == 2 || topicDetails.length == 3) {
                final Integer topicID = Integer.parseInt(topicDetails[0]);
                final Integer firstRevision = Integer.parseInt(topicDetails[1]);
                final Integer secondRevision = topicDetails.length == 3 ? Integer.parseInt(topicDetails[2]) : null;

                loadTopics(topicID, firstRevision, secondRevision);
            }
        } catch (final NumberFormatException ex) {
            // invalid data supplied on the url. do nothing
        }
    }

    private void loadTopics(@NotNull final Integer topicId, @NotNull final Integer firstRevision, @Nullable final Integer secondRevision) {
        final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
            @Override
            public void success(@NotNull final RESTTopicV1 retValue1) {
                final RESTCallBack<RESTTopicV1> callback2 = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue2) {

                        final RESTCallBack<IntegerWrapper> hold1 = new RESTCallBack<IntegerWrapper>() {
                            @Override
                            public void success(@NotNull final IntegerWrapper holdValue1) {
                                final RESTCallBack<IntegerWrapper> hold2 = new RESTCallBack<IntegerWrapper>() {
                                    @Override
                                    public void success(@NotNull final IntegerWrapper holdValue2) {
                                        renderXML(holdValue1.value, holdValue2.value, display.getHiddenAttachmentArea());
                                    }
                                };

                                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.holdXML(Constants.DOCBOOK_DIFF_XSL_REFERENCE + retValue2.getXml()), hold2, display);
                            }
                        };

                        failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.holdXML(Constants.DOCBOOK_DIFF_XSL_REFERENCE + retValue1.getXml()), hold1, display);

                    }
                };

                if (secondRevision == null) {
                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopic(topicId), callback2, display);
                } else {
                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicRevision(topicId, secondRevision), callback2, display);
                }
            }
        };

        failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicRevision(topicId, firstRevision), callback, display);
    }

    public EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateListProvider(@NotNull final Integer id, @NotNull final BaseTemplateViewInterface waitDisplay) {

        getProviderData().reset();

        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTopicCollectionItemV1> list) {

                final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
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
                };

                final int start = list.getVisibleRange().getStart();
                getProviderData().setStartRow(start);
                final int length = list.getVisibleRange().getLength();
                final int end = start + length;

                this.resetProvider();

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicWithRevisions(id, start, end), callback, display);
            }
        };
        return provider;
    }

    /**
     * Creates some iFrames to render the XML.
     * @param echo1 The id to used when building the URL to the echo xml endpoint
     * @param echo2 The id to used when building the URL to the echo xml endpoint
     * @param hiddenAttach A panel where hidden iframes can be attached.
     */
    public void renderXML(@NotNull final Integer echo1, @NotNull final Integer echo2, @NotNull final Panel hiddenAttach) {
        display.showWaitingFromRenderedDiff();

        /*
            Clean up the temporary data if they weren't cleaned up in displayRenderedHTML() (which could happen
            if the user switches away from the revisions view before the rendered diff is displayed).
         */
        renderedHTML1 = renderedHTML2 = null;
        if (currentXML != null) {
            currentXML.removeFromParent();
        }

        if (comparedXML != null) {
            comparedXML.removeFromParent();
        }

        currentXML = new Frame();
        comparedXML = new Frame();

        /*
            iFrames have to be attached to the DOM to load their pages
         */
        hiddenAttach.add(currentXML);
        hiddenAttach.add(comparedXML);

        final IFrameElement currentXMLIFrameElement = currentXML.getElement().cast();
        final IFrameElement comparedXMLXMLIFrameElement = comparedXML.getElement().cast();

        ++tempIFrameCount;

        currentXMLIFrameElement.setId(CURRENT_FRAME_ID_PREFIX + tempIFrameCount);
        comparedXMLXMLIFrameElement.setId(COMPARE_FRAME_ID_PREFIX + tempIFrameCount);

        currentXMLHREF =  ServerDetails.getSavedServer().getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" + echo1 + "&" + Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded();
        comparedXMLHREF = ServerDetails.getSavedServer().getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" + echo2 + "&" + Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded();

        currentXML.setUrl(currentXMLHREF);
        comparedXML.setUrl(comparedXMLHREF);
    }

    private void displayRenderedHTML() {
        try {
            LOGGER.info("ENTER TopicRevisionsView.displayRenderedHTML()");

            /*
                Check isDisplayingRevisions() here because the user may have
                moved off the view.
             */
            if (renderedHTML1 != null && renderedHTML2 != null && !display.isDisplayingRevisions()) {
                final String diff = diffHTML(renderedHTML1, renderedHTML2);

                currentXML.removeFromParent();
                comparedXML.removeFromParent();

                renderedHTML1 = renderedHTML2 = null;
                currentXML = comparedXML = null;

                display.displayHtmlDiff(diff);
            }
        } finally {
            LOGGER.info("EXIT TopicRevisionsView.displayRenderedHTML()");
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
                    console.log("ENTER TopicRevisionsView.createEventListener() TopicRevisionsView.displayAfterLoaded()");

                    // Make sure the iframe sending the data is from an expected source
                    var server = @org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails::getSavedServer()();
					var serverHost = server.@org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails::getRestUrl()();
                    if (serverHost.indexOf(event.origin) == 0) {
                        // Match the ids we assigned to the temp rendering iframes to the ids of the source of the message.
                        // This ensures that the diff ordering is correct

                        var comparedXMLHREF = me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::comparedXMLHREF;
                        var currentXMLHREF = me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::currentXMLHREF;

                        try
                        {
                            var dataObject = JSON.parse(event.data);

                            if (dataObject.href == currentXMLHREF) {
                                me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::renderedHTML1 = dataObject.html;
                            } else if (dataObject.href == comparedXMLHREF) {
                                me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::renderedHTML2 = dataObject.html;
                            }
                        } catch (exception) {
                            // event.data used to be just the html. So if JSON.parse fails, fall back to reading the HTML
                            if (me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::renderedHTML1 == null) {
								me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::renderedHTML1 = event.data;
                            } else if (me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::renderedHTML2 == null) {
								me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::renderedHTML2 = event.data;
                            }
                        }

                        me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::displayRenderedHTML()();
                    }

					console.log("EXIT TopicRevisionsView.createEventListener() TopicRevisionsView.displayAfterLoaded()");
				};
			}(this);
	}-*/;

    private native void addEventListener() /*-{
		$wnd.addEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter::listener);
	}-*/;

}
