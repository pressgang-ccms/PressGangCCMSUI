package org.jboss.pressgang.ccms.ui.client.local.mvp.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.DocBuilderPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.resources.xsl.DocbookToHTML;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DocBuilderView extends BaseTemplateView implements DocBuilderPresenter.Display {

    @NotNull
    private final Frame iFrame;

    public DocBuilderView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Welcome());
        iFrame = new Frame();
        iFrame.setWidth("100%");
        iFrame.setHeight("100%");
        iFrame.getElement().getStyle().setBorderWidth(0, Style.Unit.PX);
        this.getPanel().add(iFrame);
    }

    public void display(@Nullable final Integer id) {
        String url = Constants.DOCBUILDER_SERVER;
        if (id != null)  {
            url += "/" + id;
        }

        iFrame.setUrl(url);
    }
}
