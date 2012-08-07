package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.Presenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;

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
