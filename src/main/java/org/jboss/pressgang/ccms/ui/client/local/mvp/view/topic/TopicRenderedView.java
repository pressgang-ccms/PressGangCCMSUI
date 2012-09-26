package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.resources.xsl.DocbookToHTML;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HTML;

public class TopicRenderedView extends TopicViewBase implements TopicRenderedPresenter.Display {
    

    private final HTML div = new HTML("div");

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        // TODO Auto-generated method stub
        return null;
    }

    public TopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.RenderedView());

        div.addStyleName(CSSConstants.TOPIC_RENDERED_VIEW_DIV);
    }

    @Override
    protected void populateTopActionBar() {
        addActionButton(this.getRenderedSplit());
        addActionButton(this.getRenderedDown());
        addActionButton(this.getXml());
        addActionButton(this.getXmlErrors());
        addActionButton(this.getFields());
        addActionButton(this.getTags());
        addActionButton(this.getBugs());
        addActionButton(this.getHistory());
        addActionButton(this.getSave());

        fixReadOnlyButtons();

        addRightAlignedActionButtonPaddingPanel();
    }

    @Override
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final SplitType splitType) {
        this.readOnly = readOnly;
        fixReadOnlyButtons();
        buildSplitViewButtons(splitType);

        try {
            // Any number of processors can be created, they will behave
            // independently. Every stylesheet have to have its own processor.
            final XsltProcessor processor = new XsltProcessor();

            // Setting the stylesheet to transform with

            processor.importStyleSheet(DocbookToHTML.XSL);

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