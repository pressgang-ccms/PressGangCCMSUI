package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;
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

    // Any number of processors can be created, they will behave
    // independently. Every stylesheet have to have its own processor.
    final XsltProcessor processor = new XsltProcessor();

    public TopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.RenderedView());
        frame.setFrameBorder(0);
        frame.addClassName(CSSConstants.TopicRenderedView.RENDERED_IFRAME);
        this.getPanel().getElement().appendChild(frame);

    }

    @Override
    public final void display(final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {
        throw new UnsupportedOperationException("TopicRenderedView.display() is not supported. Use TopicRenderedView.displayTopicRendered() instead.");
    }

    @Override
    public final void displayTopicRendered(@Nullable final String topicXML, @Nullable final String xsl, final boolean readOnly, final boolean showImages) {

        if (xsl == null) {
            fillIframe(frame, "<html><head></head><body>" + PressGangCCMSUI.INSTANCE.LoadingXSL() + "</body></html>");
        } else {
            try {
                processor.importStyleSheet(xsl);

                // define how the images are handled
                processor.setParameter("externalImages", showImages + "");

                processor.setParameter("class.prefix", "");

                // Setting the document to be transformed
                processor.importSource(topicXML == null ? "" : topicXML);

                // Getting the result
                final String resultString = processor.transform();
                fillIframe(frame,
                        "<html>" +
                            "<head>" +
                                "<link rel=\"stylesheet\" type=\"text/css\" href=\"common.css\">" +
                                "<link rel=\"stylesheet\" type=\"text/css\" href=\"overrides.css\">" +
                            "</head>" +
                            "<body>" +
                                resultString +
                            "</body>" +
                        "</html>");

            } catch (@NotNull final XsltProcessingException ex) {
                fillIframe(frame, "<html><head></head><body>" + PressGangCCMSUI.INSTANCE.TopicCouldNotBeRendered() + "</body></html>");
            }
        }
    }

    private native void fillIframe(final IFrameElement iframe, final String content) /*-{
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


    @Override
    @NotNull
    public IFrameElement getFrame() {
        return frame;
    }
}