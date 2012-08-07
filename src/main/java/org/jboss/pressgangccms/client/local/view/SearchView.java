package org.jboss.pressgangccms.client.local.view;


import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.presenter.SearchPresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchView extends Presenter.TemplateDisplay implements SearchPresenter.Display
{
	public static final String HISTORY_TOKEN = "SearchView";

	public SearchView()
	{
		super(Constants.pressGangCCMSUI.PressGangCCMS(), Constants.pressGangCCMSUI.Search());
	}

	@Override
	protected Panel getContentPanel()
	{
		return new VerticalPanel();
	}
}
