package org.jboss.pressgangccms.client.local.mvp.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class WelcomePresenter extends TemplatePresenter
{
	public interface Display extends BaseTemplateViewInterface
	{
		
	}

	@Inject
	private Display display;

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());
		
		bind(display);
	}

	@Override
	public void parseToken(String historyToken)
	{
		// TODO Auto-generated method stub
		
	}
}
