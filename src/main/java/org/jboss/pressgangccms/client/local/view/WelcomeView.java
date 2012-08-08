package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;

public class WelcomeView extends BaseTemplateView implements WelcomePresenter.Display
{
	public static final String HISTORY_TOKEN = "WelcomeView";

	public WelcomeView()
	{
		super(Constants.pressGangCCMSUI.PressGangCCMS(), Constants.pressGangCCMSUI.Welcome());
	}
}
