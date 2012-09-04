package org.jboss.pressgangccms.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public interface BaseTemplateViewInterface
{
	PushButton getBug();
	PushButton getSearch();
	PushButton getImages();
	FlexTable getTopActionPanel();
	SimpleLayoutPanel getPanel();
	DockLayoutPanel getTopLevelPanel();
	void setSpinnerVisible(final boolean enabled);
	void showRegularMenu();
	Label getPageTitle();
	String getPageName();
	String getApplicationName();
}
