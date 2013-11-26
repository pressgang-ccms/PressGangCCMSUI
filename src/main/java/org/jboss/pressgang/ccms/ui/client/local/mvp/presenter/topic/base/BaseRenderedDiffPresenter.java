package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Panel;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Contains the common logic to render two HTML pages and get the diff between them.
 */
abstract public class BaseRenderedDiffPresenter extends BaseTemplatePresenter {

    public interface Display extends BaseTemplateViewInterface {
        void showWaitingFromRenderedDiff();
        void showRenderedDiffError();
        void displayHtmlDiff(@NotNull final String htmlDiff);
    }

    /**
     * The start of the IDs assigned to the iFrames that are used to render the XML.
     */
    private static final String FRAME_ID_PREFIX = "TempRenderingFrame";
    private static final String CURRENT_FRAME_ID_PREFIX = "Current" + FRAME_ID_PREFIX;
    private static final String COMPARE_FRAME_ID_PREFIX = "Compare" + FRAME_ID_PREFIX;

    private static int tempIFrameCount = 0;

    @Inject
    private FailOverRESTCall failOverRESTCall;

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BaseRenderedDiffPresenter.class.getName());

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

    private Display display;

    @Override
    public void close() {
        removeListener();
    }

    /**
     * Unsupported method. Call bindRenderedDiff() instead.
     */
    public void bindExtended() {
        throw new UnsupportedOperationException("bindExtended() is not supported. Use bindRenderedDiff() instead.");
    }

    /**
     * @param display The display
     */
    public void bindRenderedDiff(@NotNull final Display display) {
        this.display = display;

        super.bind(display);
        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                createEventListener(serverDetails.getRestUrl());
                addEventListener();
            }
        });
    }

    public void loadTopics(@NotNull final Integer topicId, @NotNull final Integer firstRevision, @Nullable final Integer secondRevision, @NotNull final RenderedDiffFailedCallback failedCallback) {
        loadTopics(topicId, firstRevision, secondRevision, display.getHiddenAttachmentArea(), failedCallback);
    }

    public void loadTopics(@NotNull final Integer topicId, @NotNull final Integer firstRevision, @Nullable final Integer secondRevision, @NotNull final Panel hiddenAttach, @NotNull final RenderedDiffFailedCallback failedCallback) {
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
                                        renderXML(holdValue1.value, holdValue2.value, hiddenAttach);
                                    }

                                    @Override
                                    public void failed() {
                                        failedCallback.failed();
                                    }
                                };

                                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.holdXML(Constants.DOCBOOK_DIFF_XSL_REFERENCE + "\n" + DocbookDTD
                                        .getDtdDoctype() + "\n" + retValue2.getXml()), hold2, display, true);
                            }

                            @Override
                            public void failed() {
                                failedCallback.failed();
                            }

                        };

                        failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.holdXML(Constants.DOCBOOK_DIFF_XSL_REFERENCE + "\n" + DocbookDTD
                                .getDtdDoctype() + "\n" + retValue1.getXml()), hold1, display, true);

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

    /**
     * Creates some iFrames to render the XML. This method exists so presenters can supply their own hidden
     * attachment area, as the hidden area of the display will not be attached to the dom when this
     * presenter is used as a child of another presenter.
     *
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

        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                currentXMLHREF =  serverDetails.getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" + echo1 + "&" + Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded();
                comparedXMLHREF = serverDetails.getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" + echo2 + "&" + Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded();

                currentXML.setUrl(currentXMLHREF);
                comparedXML.setUrl(comparedXMLHREF);
            }
        });

    }

    protected void displayRenderedHTML() {
        try {
            LOGGER.info("ENTER BaseRenderedDiffPresenter.displayRenderedHTML()");

            if (renderedHTML1 != null && renderedHTML2 != null) {
                final String diff = diffHTML(renderedHTML1, renderedHTML2);

                if (currentXML != null) {
                    currentXML.removeFromParent();
                }

                if (comparedXML != null) {
                    comparedXML.removeFromParent();
                }

                renderedHTML1 = renderedHTML2 = null;
                currentXML = comparedXML = null;

                display.displayHtmlDiff(diff);
            }
        } finally {
            LOGGER.info("EXIT BaseRenderedDiffPresenter.displayRenderedHTML()");
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
		if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::listener != null) {
			$wnd.removeEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::listener);
			this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::listener = null;
		}
	}-*/;

    private native void createEventListener(final String serverHost) /*-{
		this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::listener =
			function (me) {
				return function displayAfterLoaded(event) {
					console.log("ENTER BaseRenderedDiffPresenter.createEventListener() BaseRenderedDiffPresenter.displayAfterLoaded()");

					// Make sure the iframe sending the data is from an expected source
					console.log("Event Origin: " + event.origin);
					console.log("Server Host: " + serverHost);
                    if (serverHost.indexOf(event.origin) == 0) {
						// Match the ids we assigned to the temp rendering iframes to the ids of the source of the message.
						// This ensures that the diff ordering is correct

						var comparedXMLHREF = me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::comparedXMLHREF;
						var currentXMLHREF = me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::currentXMLHREF;

						try
						{
							var dataObject = JSON.parse(event.data);

							if (dataObject.href) {
								if (dataObject.href == currentXMLHREF) {
									me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::renderedHTML1 = dataObject.html;
								} else if (dataObject.href == comparedXMLHREF) {
									me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::renderedHTML2 = dataObject.html;
								}
							}
						} catch (exception) {
							// event.data used to be just the html. If the old XSL files are in the browsers cache, the JSON.parse will fail.
							// In that case fall back to reading the HTML from the data directly.
							// Note that the live rendering used a data property called "loaded", so we check for that here.
							if (event.data != 'loaded') {
								if (me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::renderedHTML1 == null) {
									me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::renderedHTML1 = event.data;
								} else if (me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::renderedHTML2 == null) {
									me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::renderedHTML2 = event.data;
								}
							}
						}

						me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::displayRenderedHTML()();
					}

					console.log("EXIT BaseRenderedDiffPresenter.createEventListener() BaseRenderedDiffPresenter.displayAfterLoaded()");
				};
			}(this);
	}-*/;

    private native void addEventListener() /*-{
		$wnd.addEventListener('message', this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter::listener);
	}-*/;
}
