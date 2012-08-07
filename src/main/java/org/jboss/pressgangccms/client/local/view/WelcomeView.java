package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WelcomeView extends Presenter.TemplateDisplay implements WelcomePresenter.Display
{

	public static final String HISTORY_TOKEN = "WelcomeView";

	public WelcomeView()
	{
		super(pressGangCCMSUI.PressGangCCMS(), pressGangCCMSUI.Welcome());
	}

	@Override
	protected Panel getContentPanel()
	{
		return new VerticalPanel();
	}
}
