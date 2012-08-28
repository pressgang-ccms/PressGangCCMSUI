package org.jboss.pressgangccms.client.local.view.base;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public interface BaseTemplateViewInterface
{
	PushButton getBug();
	PushButton getSearch();
	FlexTable getTopActionPanel();
	SimpleLayoutPanel getPanel();
	DockLayoutPanel getTopLevelPanel();
	void setSpinnerVisible(final boolean enabled);
	void showRegularMenu();
}
