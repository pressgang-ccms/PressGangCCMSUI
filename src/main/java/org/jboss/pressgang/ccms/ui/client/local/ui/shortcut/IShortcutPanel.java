package org.jboss.pressgang.ccms.ui.client.local.ui.shortcut;

import com.google.gwt.user.client.ui.Widget;

public interface IShortcutPanel {
    Widget getWidget(int index);

    int getWidgetIndex(Widget widget);

    void add(Widget widget);

    void insert(Widget widget, int beforeIndex);

    void replace(Widget existing, Widget replacement);

    void remove(Widget widget);
}