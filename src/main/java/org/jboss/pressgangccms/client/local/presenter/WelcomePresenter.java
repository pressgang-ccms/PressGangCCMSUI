package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class WelcomePresenter extends TemplatePresenter
{
	public interface Display extends BaseTemplateViewInterface
	{
		
	}

	@Inject
	private Display display;

	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());
		
		bind(display);
	}
}
