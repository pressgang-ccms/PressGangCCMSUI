package org.pressgangccms.ui.client.local.view;

import org.pressgangccms.ui.client.local.Presenter;
import org.pressgangccms.ui.client.local.presenter.WelcomePresenter;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WelcomeView extends Presenter.TemplateDisplay implements WelcomePresenter.Display
{

	public static final String HISTORY_TOKEN = "WelcomeView";

	public WelcomeView()
	{
		super("PressGang CCMS", "Welcome");
	}

	@Override
	protected Panel getContentPanel()
	{
		return new VerticalPanel();
	}
}
