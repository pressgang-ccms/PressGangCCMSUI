package org.jboss.pressgang.ccms.ui.client.local.ui.shortcut;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jetbrains.annotations.NotNull;

public class BaseShortcutPanel extends Composite implements IShortcutPanel {
    private final VerticalPanel panel = new VerticalPanel();

    public BaseShortcutPanel() {
        initWidget(panel);
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        setStyleName(CSSConstants.Shortcut.SHORTCUT_PANEL);
    }

    public Widget getWidget(final int index) {
        return panel.getWidget(index);
    }

    public int getWidgetIndex(@NotNull final Widget widget) {
        return panel.getWidgetIndex(widget);
    }

    public void add(@NotNull final Widget widget) {
        panel.add(widget);
    }

    public void insert(@NotNull final Widget widget, final int beforeIndex) {
        panel.insert(widget, beforeIndex);
    }

    public void replace(@NotNull final Widget existing, @NotNull final Widget replacement) {
        /* Early out if the existing widget isn't actually attached */
        if (existing.getParent() != panel) {
            return;
        }

        int widgetIndex = panel.getWidgetIndex(existing);
        panel.insert(replacement, widgetIndex);
        panel.remove(existing);
    }

    public void remove(@NotNull final Widget widget) {
        /* Early out if the existing widget isn't actually attached */
        if (widget.getParent() != panel) {
            return;
        }

        panel.remove(widget);
    }

    public void setVerticalAlignment(final HasVerticalAlignment.VerticalAlignmentConstant verticalAlignment) {
        panel.setVerticalAlignment(verticalAlignment);
    }

    public void setCellHeight(final Widget widget, final String height) {
        panel.setCellHeight(widget, height);
    }
}
