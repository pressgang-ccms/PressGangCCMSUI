package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;


import java.util.Arrays;
import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * This view maintains a kind of double buffer. IFrames are loaded into hidden table cells, and then
 * "flipped" out after a period of time to give a seamless appearance of a panel being updated.
 */
public class TopicRenderedView extends BaseTemplateView implements TopicRenderedPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicRenderedView.class.getName());
    private static final String LOADING_IFRAME = "LoadingIFrame";
    private static final String LOADED_IFRAME = "LoadedIFrame";
    private static final String HIDDEN_IFRAME = "HiddenIFrame";
    private final FlexTable iFrameParent = new FlexTable();
    private int displayingRow = 0;
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
                    this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::echoServer);
			}
		} catch (exception) {
			// probably a cross domain violation
		}
	}-*/;

    public TopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.RenderedView());
        this.getPanel().setWidget(iFrameParent);
        iFrameParent.addStyleName(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE);
        iFrameParent.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL);
        iFrameParent.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL);

        createEventListener();
        addEventListener();

        LOGGER.info("ENTER TopicRenderedView()");
    }

    /**
     * The listener hold a reference to this, which will prevent it from being reclaimed by the GC.
     * So here we remove the listener.
     */
    public native void removeListener() /*-{
		try {
			if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::listener != null) {
				$wnd.removeEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::listener);
				this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::listener = null;
			}
		} catch (ex) {
            console.log(ex);
            throw ex;
        }

	}-*/;

    private native void createEventListener() /*-{
		this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::listener =
			function (me) {
				return function displayAfterLoaded(event) {
					// Make sure the iframe sending the data is from an expected source
                    var server = @org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails::getSavedServer()();
                    var serverHost = server.@org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails::getRestUrl()();

                    try {
                        var eventObject = JSON.parse(event.data);

                        if (eventObject.event == 'loaded' && serverHost.indexOf(event.origin) == 0) {
                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::displayRendered()();
                        } else if (eventObject.event == 'scrolled') {
                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::scrollTopPosition = eventObject.scrollTop;
                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::scrollLeftPosition = eventObject.scrollLeft;
                        }
                    } catch (exception) {
                        // The events just used to be 'loaded', so fall back to that if the JSON parsing failed
                        if (event.data == 'loaded' && serverHost.indexOf(event.origin) == 0) {
                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::displayRendered()();
                        }
                    }
				};
			}(this);
	}-*/;

    private native void addEventListener() /*-{
		$wnd.addEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::listener);
	}-*/;

    private void displayRendered() {
        if (loadingiframe != null) {
            final int next = displayingRow == 0 ? 1 : 0;
            /*
                Hide the outgoing iframe, and display the incoming one.
            */
            if (Arrays.asList(iFrameParent.getFlexCellFormatter().getStyleName(displayingRow, 0).split(" ")).indexOf(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL) != -1) {
                iFrameParent.getFlexCellFormatter().removeStyleName(displayingRow, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL);
                iFrameParent.getFlexCellFormatter().addStyleName(displayingRow, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL);
            }

            if (Arrays.asList(iFrameParent.getFlexCellFormatter().getStyleName(next, 0).split(" ")).indexOf(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL)  != -1) {
                iFrameParent.getFlexCellFormatter().removeStyleName(next, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL);
                iFrameParent.getFlexCellFormatter().addStyleName(next, 0, CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL);
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
        final int next = displayingRow == 0 ? 1 : 0;
        iFrameParent.setWidget(next, 0, null);
    }

    @Override
    public boolean displayTopicRendered(@NotNull final Integer topicXMLHoldID, final boolean readOnly, final boolean showImages) {

        /*
            There is the potential for a race condition here. If a XML file takes longer to render than the next refresh
            (maybe a large XML file was rendered, followed by a small one) then when the large one finished it will
            trigger the small one to be displayed prematurely.

            All this means in this situation is that the user will see blank screen or a half rendered HTML page.
         */

        final ServerDetails serverDetails = ServerDetails.getSavedServer();
        echoServer = serverDetails.getRestUrl();

        loadingiframe = new Frame();
        loadingiframe.getElement().setId(LOADING_IFRAME);
        loadingiframe.setUrl(serverDetails.getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" + topicXMLHoldID + "&" + Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded());
        loadingiframe.addStyleName(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_IFRAME);
        iFrameParent.setWidget(displayingRow, 0, loadingiframe);

        return true;
    }

    @Override
    @NotNull
    public FlexTable getiFrameParent() {
        return iFrameParent;
    }
}