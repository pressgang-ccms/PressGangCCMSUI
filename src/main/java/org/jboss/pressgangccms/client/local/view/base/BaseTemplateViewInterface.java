package org.jboss.pressgangccms.client.local.view.base;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;

public interface BaseTemplateViewInterface
{
	PushButton getBug();
	PushButton getSearch();
	Panel getTopLevelPanel();
	HorizontalPanel getTopActionPanel();
	void setSpinnerVisible(final boolean enabled);
}
