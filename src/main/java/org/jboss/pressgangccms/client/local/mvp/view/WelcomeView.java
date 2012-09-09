package org.jboss.pressgangccms.client.local.mvp.view;

import org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;

public class WelcomeView extends BaseTemplateView implements WelcomePresenter.Display {
    public static final String HISTORY_TOKEN = "WelcomeView";

    public WelcomeView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Welcome());
    }
}
