package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;

public class WelcomeView extends BaseTemplateView implements WelcomePresenter.Display
{
	public static final String HISTORY_TOKEN = "WelcomeView";

	public WelcomeView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Welcome());
	}
}
