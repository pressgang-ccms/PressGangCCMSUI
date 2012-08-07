package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.Presenter;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
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
