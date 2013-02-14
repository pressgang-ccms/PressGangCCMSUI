package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HTML;
import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.resources.xsl.DocbookToHTML;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import java.util.List;

public class TopicRenderedView extends BaseTemplateView implements TopicRenderedPresenter.Display {

    private final HTML div = new HTML();

    public TopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.RenderedView());

        div.addStyleName(CSSConstants.TOPIC_RENDERED_VIEW_DIV);
    }

    @Override
    public final void display(final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {
        throw new UnsupportedOperationException("TopicRenderedView.display() is not supported. Use TopicRenderedView.displayTopicRendered() instead.");
    }

    @Override
    public final void displayTopicRendered(final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly, final boolean showImages) {

        try {
            // Any number of processors can be created, they will behave
            // independently. Every stylesheet have to have its own processor.
            final XsltProcessor processor = new XsltProcessor();

            // Setting the stylesheet to transform with

            processor.importStyleSheet(DocbookToHTML.XSL);
            processor.setParameter("externalImages", showImages + "");
            
            //processor.importSource(Constants.DOCBOOK_XSL_FILE);

            // Setting the document to be transformed
            processor.importSource(topic.getXml());

            // Order of setting the stylesheet and document is indifferent.

            // Optional, must be called after importStyleSheet
            // processor.setParameter(paramNameString, paramValueString);

            // Getting the result
            String resultString = processor.transform();
            div.setHTML(resultString);

            this.getPanel().setWidget(div);
        } catch (final XsltProcessingException ex) {
            div.setHTML(PressGangCCMSUI.INSTANCE.TopicCouldNotBeRendered());
        }
    }

}