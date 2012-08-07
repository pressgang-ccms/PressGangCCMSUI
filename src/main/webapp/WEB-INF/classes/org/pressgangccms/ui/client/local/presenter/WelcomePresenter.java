package org.pressgangccms.ui.client.local.presenter;

import javax.inject.Inject;

import org.pressgangccms.ui.client.local.Presenter;

import com.google.gwt.user.client.ui.HasWidgets;

public class WelcomePresenter implements Presenter
{
	public interface Display extends TemplateInterface
	{

	}

	@Inject
	private Display display;

	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());
	}
}
