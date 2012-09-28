package org.jboss.pressgang.ccms.ui.client.local.mvp.view;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.TroubleshootingPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class TroubleshootingView extends BaseTemplateView implements TroubleshootingPresenter.Display {

    public TroubleshootingView(final String applicationName, final String pageName) {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Troubleshooting());
    }
}
