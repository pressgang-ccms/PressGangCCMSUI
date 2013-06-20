package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.resources.xsl.DocbookToHTML;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public class TopicRenderedView extends BaseTemplateView implements TopicRenderedPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicRenderedView.class.getName());

    private final IFrameElement frame = Document.get().createIFrameElement();

    private JavaScriptObject worker;

    public TopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.RenderedView());
        frame.setFrameBorder(0);
        frame.addClassName(CSSConstants.TopicRenderedView.RENDERED_IFRAME);
        this.getPanel().getElement().appendChild(frame);
    }

    private native void createWorker(@NotNull final AceEditor editor) /*-{
        this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::worker = new Worker("javascript/xsltproc/xsltproc.js");

        var worker = this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::worker;
		var pressGang = @org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::INSTANCE;

        worker.addEventListener('message', function(me) {
            return function(e) {
				if (e.data != null) {
					this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::fillIframe(Ljava/lang/String;)(
						"<html>" +
							"<head>" +
                                "<link rel=\"stylesheet\" type=\"text/css\" href=\"common.css\">" +
                                "<link rel=\"stylesheet\" type=\"text/css\" href=\"overrides.css\">" +
							"</head>" +
							"<body>" +
                                e.data +
							"</body>" +
                        "</html>");
				} else {
					me.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::fillIframe(Ljava/lang/String;)(
						"<html><head></head><body>" +
							pressGang.@org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::TopicCouldNotBeRendered()() +
						"</body></html>");
				}

				if (worker.running) {
					worker.postMessage(editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()());
				};
			}}(this));
        worker.running = true;
		worker.postMessage(editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()());
    }-*/;



    @Override
    public final void display(final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {
        throw new UnsupportedOperationException("TopicRenderedView.display() is not supported. Use TopicRenderedView.displayEditorRendered() instead.");
    }

    private native void fillIframe(final String content) /*-{
        var iframe = this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::frame;
        var doc = iframe.document;
        if (iframe.contentDocument) {
			// For NS6
            doc = iframe.contentDocument;
        } else if (iframe.contentWindow) {
			// For IE5.5 and IE6
            doc = iframe.contentWindow.document;
        }
        if (doc) {
            // Put the content in the iframe
            doc.open();
            doc.writeln(content);
            doc.close();
        }
    }-*/;

    /**
     * Starts a loop that will render the contents of the ACE editor in a background thread.
     * @param editor The editor that has the source XML
     */
    @Override
    public native void displayEditorRendered(@Nullable final AceEditor editor) /*-{

		this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::stop()();

        var pressGang = @org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::INSTANCE;

		this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::createWorker(Ledu/ycp/cs/dh/acegwt/client/ace/AceEditor;)(editor);
    }-*/;

    /**
     * Renders the supplied xml once
     * @param xml The source XML
     */
    @Override
    public native void displayStringRendered(@NotNull final String xml) /*-{

		var pressGang = @org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::INSTANCE;

		var html = convertDocbookToHTML(xml);

        this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::fillIframe(Ljava/lang/String;)(
            "<html>" +
                "<head>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"common.css\">" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"overrides.css\">" +
                "</head>" +
                "<body>" +
                html +
                "</body>" +
            "</html>");
	}-*/;

    @Override
    public native void stop() /*-{
		var worker = this.@org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView::worker;

		// cancel the exiting worker
		if (worker != null) {
			worker.running = false;
		}
	}-*/;

    @Override
    @NotNull
    public IFrameElement getFrame() {
        return frame;
    }
}