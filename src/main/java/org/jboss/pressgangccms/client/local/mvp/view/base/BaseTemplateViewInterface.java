package org.jboss.pressgangccms.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public interface BaseTemplateViewInterface {
    FlexTable getTopActionPanel();

    SimpleLayoutPanel getPanel();

    DockLayoutPanel getTopLevelPanel();

    WaitingDialog getWaiting();

    void showRegularMenu();

    Label getPageTitle();

    String getPageName();

    String getApplicationName();

    FlexTable getAdvancedShortcutPanel();

    FlexTable getShortcutPanel();

    SimplePanel getShortCutPanelParent();

    PushButton getBug();

    PushButton getSearch();

    PushButton getImages();

    PushButton getAdvanced();

    PushButton getPropertyTagCategories();

    PushButton getPropertyTags();

    PushButton getRoles();

    PushButton getUsers();

    PushButton getIntegerConstants();

    PushButton getBlobConstants();

    PushButton getStringConstants();

    PushButton getProjects();

    PushButton getCategories();

    PushButton getTags();

    PushButton getAdvancedOpen();

    PushButton getClose();
}
