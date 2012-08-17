package org.jboss.pressgangccms.client.local.view.base;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;

public interface BaseTemplateViewInterface
{
	PushButton getBug();
	PushButton getSearch();
	Panel getTopLevelPanel();
	FlexTable getTopActionPanel();
	void setSpinnerVisible(final boolean enabled);
	SimplePanel getPanel();
}
