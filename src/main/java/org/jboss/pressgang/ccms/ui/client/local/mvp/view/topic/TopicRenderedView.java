package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

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

import java.util.logging.Logger;

public class TopicRenderedView extends BaseTemplateView implements TopicRenderedPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicRenderedView.class.getName());

    private final HTML div = new HTML();

    // Any number of processors can be created, they will behave
    // independently. Every stylesheet have to have its own processor.
    final XsltProcessor processor = new XsltProcessor();
    /**
     * true if the XSL was imported successfully, and false otherwise
     */
    boolean importSuccessful;

    public TopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.RenderedView());

        LOGGER.info("ENTER TopicRenderedView()");

        div.addStyleName(CSSConstants.TopicView.TOPIC_RENDERED_VIEW_DIV);
        try {
            // Setting the stylesheet to transform with
            processor.importStyleSheet(DocbookToHTML.XSL);
            importSuccessful = true;
        } catch (XsltProcessingException e) {
            importSuccessful = false;
            div.setHTML(PressGangCCMSUI.INSTANCE.TopicCouldNotBeRendered());
        }
    }

    @Override
    public final void display(final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {
        throw new UnsupportedOperationException("TopicRenderedView.display() is not supported. Use TopicRenderedView.displayTopicRendered() instead.");
    }

    @Override
    public final void displayTopicRendered(@NotNull final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly, final boolean showImages) {

        if (!importSuccessful) {
            return;
        }

        try {
            // define how the images are handled
            processor.setParameter("externalImages", showImages + "");

            // Setting the document to be transformed
            processor.importSource(topic.getXml());

            // Getting the result
            final String resultString = processor.transform();
            div.setHTML(resultString);

            this.getPanel().setWidget(div);
        } catch (@NotNull final XsltProcessingException ex) {
            div.setHTML(PressGangCCMSUI.INSTANCE.TopicCouldNotBeRendered());
        }
    }

}