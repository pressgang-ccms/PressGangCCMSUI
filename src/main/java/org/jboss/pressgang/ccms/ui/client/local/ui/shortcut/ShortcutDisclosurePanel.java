package org.jboss.pressgang.ccms.ui.client.local.ui.shortcut;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jetbrains.annotations.NotNull;

public class ShortcutDisclosurePanel extends Composite implements IShortcutPanel {
    private final DisclosurePanel panel;
    private final BaseShortcutPanel content = new BaseShortcutPanel();

    public ShortcutDisclosurePanel(final String header) {
        panel = new DisclosurePanel(header);
        init();
    }

    public ShortcutDisclosurePanel() {
        panel = new DisclosurePanel();
    }

    private void init() {
        initWidget(panel);
        panel.setContent(content);
        addStyleName(CSSConstants.Shortcut.SHORTCUT_COLLAPSE_MENU);
        panel.getHeader().addStyleName(CSSConstants.Shortcut.SHORTCUT_COLLAPSE_MENU_HEADER);
        content.addStyleName(CSSConstants.Shortcut.SHORTCUT_COLLAPSE_MENU_CONTENT);

    }

    public Widget getHeader() {
        return panel.getHeader();
    }

    @Override
    public Widget getWidget(final int index) {
        return content.getWidget(index);
    }

    @Override
    public int getWidgetIndex(@NotNull Widget widget) {
        return content.getWidgetIndex(widget);
    }

    @Override
    public void add(@NotNull final Widget widget) {
        content.add(widget);
    }

    @Override
    public void insert(@NotNull final Widget widget, final int beforeIndex) {
        content.insert(widget, beforeIndex);
    }

    @Override
    public void replace(@NotNull final Widget existing, @NotNull final Widget replacement) {
        content.replace(existing, replacement);
    }

    @Override
    public void remove(@NotNull final Widget widget) {
        content.remove(widget);
    }

    public void setOpen(final boolean isOpen) {
        panel.setOpen(isOpen);
    }

    public boolean isOpen() {
        return panel.isOpen();
    }
}
