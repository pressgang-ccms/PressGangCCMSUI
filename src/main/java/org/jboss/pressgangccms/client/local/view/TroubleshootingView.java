package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.presenter.TroubleshootingPresenter;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;

public class TroubleshootingView extends BaseTemplateView implements TroubleshootingPresenter.Display
{

	public TroubleshootingView(String applicationName, String pageName)
	{
		super(Constants.pressGangCCMSUI.PressGangCCMS(), Constants.pressGangCCMSUI.Troubleshooting());
	}
}
