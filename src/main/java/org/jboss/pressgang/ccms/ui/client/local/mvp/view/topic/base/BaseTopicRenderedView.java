package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base;


import java.util.Arrays;
import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * This view maintains a kind of double buffer. IFrames are loaded into hidden table cells, and then
 * "flipped" out after a period of time to give a seamless appearance of a panel being updated.
 */
public abstract class BaseTopicRenderedView extends BaseTemplateView implements BaseTopicRenderedPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(BaseTopicRenderedView.class.getName());
    private static final String LOADING_IFRAME = "LoadingIFrame";
    private static final String LOADED_IFRAME = "LoadedIFrame";
    private static final String HIDDEN_IFRAME = "HiddenIFrame";
    private final FlexTable layoutPanel = new FlexTable();
    private final ListBox conditions = new ListBox(false);
    private final CheckBox remarks = new CheckBox(PressGangCCMSUI.INSTANCE.EnableRemarks());
    final FlexTable renderingOptions = new FlexTable();
    private int displayingRow = 1;
    private JavaScriptObject listener;

    private Frame loadingiframe;
    private Frame loadediframe;
    private int scrollTopPosition = 0, scrollLeftPosition = 0;
    private String echoServer;

    /**
     * The GWT scrolling functions don't work in Firefox in a window that contains
     * XSL transformed into HTML. So we use native code to get access to the scroll
     * position of the default view. Tested in Chrome and Firefox.
     *
     * @param id         The iframe id
     * @param scrollLeft The horizontal scroll position
     * @param scrollTop  The vertical scroll position
     */
    private native void setScroll(@NotNull final String id, final int scrollLeft, final int scrollTop) /*-{
		try {
			var iframe = $doc.getElementById(id);
			if (iframe != null &&
				iframe.contentWindow != null) {
				iframe.contentWindow.postMessage('{"event":"scroll","scrollLeft":' + scrollLeft + ',"scrollTop":' + scrollTop + '}',
                    this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::echoServer);
			}
		} catch (exception) {
			// probably a cross domain violation
		}
	}-*/;

    public BaseTopicRenderedView(final String pageName) {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), pageName);

        LOGGER.info("ENTER BaseTopicRenderedView()");

        this.getPanel().setWidget(layoutPanel);
        layoutPanel.addStyleName(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE);

        renderingOptions.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.Condition()));
        renderingOptions.setWidget(0, 1, conditions);
        renderingOptions.setWidget(1, 0, remarks);
        renderingOptions.getFlexCellFormatter().setColSpan(1, 0, 2);

        layoutPanel.setWidget(0, 0, renderingOptions);

        layoutPanel.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL);
        layoutPanel.getFlexCellFormatter().addStyleName(2, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL);

        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                createEventListener(serverDetails.getRestUrl());
                addEventListener();
            }
        });

        // Hide the action bar since it's not needed
        getTopActionGrandParentPanel().removeFromParent();
    }

    protected FlexTable getRenderingOptions() {
        return renderingOptions;
    }

    /**
     * The listener hold a reference to this, which will prevent it from being reclaimed by the GC.
     * So here we remove the listener.
     */
    public native void removeListener() /*-{
		try {
			if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::listener != null) {
				$wnd.removeEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::listener);
				this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::listener = null;
			}
		} catch (ex) {
            console.log(ex);
            throw ex;
        }

	}-*/;

    private native void createEventListener(final String serverHost) /*-{
		this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::listener =
			function (me) {
				return function displayAfterLoaded(event) {
                    try {
                        var eventObject = JSON.parse(event.data);

                        if (eventObject.event == 'loaded' && serverHost.indexOf(event.origin) == 0) {
                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::displayRendered()();
                        } else if (eventObject.event == 'scrolled') {
                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::scrollTopPosition = eventObject.scrollTop;
                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::scrollLeftPosition = eventObject.scrollLeft;
                        }
                    } catch (exception) {
                        // The events just used to be 'loaded', so fall back to that if the JSON parsing failed
                        if (event.data == 'loaded' && serverHost.indexOf(event.origin) == 0) {
                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::displayRendered()();
                        }
                    }
				};
			}(this);
	}-*/;

    private native void addEventListener() /*-{
		$wnd.addEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView::listener);
	}-*/;

    private void displayRendered() {
        if (loadingiframe != null) {
            final int next = displayingRow == 1 ? 2 : 1;
            /*
                Hide the outgoing iframe, and display the incoming one.
            */
            if (Arrays.asList(layoutPanel.getFlexCellFormatter().getStyleName(displayingRow, 0).split(" ")).indexOf(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL) != -1) {
                layoutPanel.getFlexCellFormatter().removeStyleName(displayingRow, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL);
                layoutPanel.getFlexCellFormatter().addStyleName(displayingRow, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL);
            }

            if (Arrays.asList(layoutPanel.getFlexCellFormatter().getStyleName(next, 0).split(" ")).indexOf(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL)  != -1) {
                layoutPanel.getFlexCellFormatter().removeStyleName(next, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL);
                layoutPanel.getFlexCellFormatter().addStyleName(next, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL);
            }

            setScroll(LOADING_IFRAME, scrollLeftPosition, scrollTopPosition);

            if (loadediframe != null) {
                loadediframe.getElement().setId(HIDDEN_IFRAME);
            }

            loadediframe = loadingiframe;
            loadediframe.getElement().setId(LOADED_IFRAME);
            loadingiframe = null;
            displayingRow = next;
        }
    }

    @Override
    public void display(final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {
        throw new UnsupportedOperationException("TopicRenderedView.display() is not supported. Use TopicRenderedView.displayTopicRendered() instead.");
    }

    @Override
    public void clear() {
        final int next = displayingRow == 1 ? 2 : 1;
        layoutPanel.setWidget(next, 0, null);
    }

    @Override
    public boolean displayTopicRendered(@NotNull final Integer topicXMLHoldID, final boolean readOnly, final boolean showImages) {

        /*
            There is the potential for a race condition here. If a XML file takes longer to render than the next refresh
            (maybe a large XML file was rendered, followed by a small one) then when the large one finished it will
            trigger the small one to be displayed prematurely.

            All this means in this situation is that the user will see blank screen or a half rendered HTML page.
         */

        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull ServerDetails serverDetails) {
                echoServer = serverDetails.getRestUrl();

                loadingiframe = new Frame();
                loadingiframe.getElement().setId(LOADING_IFRAME);
                loadingiframe.setUrl(serverDetails.getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" + topicXMLHoldID + "&" + Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded());
                loadingiframe.addStyleName(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME);
                layoutPanel.setWidget(displayingRow, 0, loadingiframe);
            }
        });

        return true;
    }

    @Override
    @NotNull
    public FlexTable getLayoutPanel() {
        return layoutPanel;
    }

    @Override
    @NotNull
    public ListBox getConditions() {
        return conditions;
    }

    @Override
    @NotNull
    public CheckBox getRemarks() {
        return remarks;
    }
}