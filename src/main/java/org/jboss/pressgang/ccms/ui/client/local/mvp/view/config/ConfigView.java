package org.jboss.pressgang.ccms.ui.client.local.mvp.view.config;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.config.ConfigPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class ConfigView extends BaseTemplateView implements ConfigPresenter.Display {
    /**
     * The constructor is used to piece the template together.
     */
    public ConfigView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ConfigOverride());
    }
}
