package org.jboss.pressgangccms.client.local.presenter;

import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

public class TroubleshootingPresenter extends TemplatePresenter
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