package org.jboss.pressgang.ccms.ui.client.local.mvp.view;

import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.resources.xsl.DocbookToHTML;

import com.google.gwt.user.client.ui.HTML;

public class WelcomeView extends BaseTemplateView implements WelcomePresenter.Display {

    private final HTML content = new HTML("div");
    
    public WelcomeView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Welcome());
        
        this.getPanel().setWidget(content);
    }
    
    @Override
    public void initialize(final RESTTopicV1 topic)
    {
        try {
            final XsltProcessor processor = new XsltProcessor();
            processor.importStyleSheet(DocbookToHTML.XSL);
            processor.setParameter("externalImages", true + "");

            processor.importSource(topic.getXml());
            String resultString = processor.transform();
            content.setHTML(resultString);

            this.getPanel().setWidget(content);
        } catch (final XsltProcessingException ex) {
            content.setHTML(PressGangCCMSUI.INSTANCE.TopicCouldNotBeRendered());
        }
    }
}
