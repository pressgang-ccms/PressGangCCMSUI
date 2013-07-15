package org.jboss.pressgang.ccms.ui.client.local.ui.shortcut;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jetbrains.annotations.NotNull;

public class ShortcutPanel extends Composite implements IShortcutPanel {
    private SimplePanel rootPanel = new SimplePanel();
    private BaseShortcutPanel baseShortcutPanel = new BaseShortcutPanel();
    private Map<SubShortcutPanel, PushButton> subMenus = new HashMap<SubShortcutPanel, PushButton>();

    public ShortcutPanel() {
        initWidget(rootPanel);
        rootPanel.setWidget(baseShortcutPanel);
        setStyleName(CSSConstants.Shortcut.SHORTCUT_PANEL_PARENT);
    }

    public Widget getWidget(final int index) {
        return baseShortcutPanel.getWidget(index);
    }

    public int getWidgetIndex(@NotNull final Widget widget) {
        return baseShortcutPanel.getWidgetIndex(widget);
    }

    public void add(@NotNull final Widget widget) {
        baseShortcutPanel.add(widget);
    }

    public void insert(@NotNull final Widget widget, final int beforeIndex) {
        baseShortcutPanel.insert(widget, beforeIndex);
    }

    public void replace(@NotNull final Widget existing, @NotNull final Widget replacement) {
        /* Early out if the existing widget isn't actually attached */
        if (existing.getParent() != baseShortcutPanel) {
            return;
        }

        int widgetIndex = baseShortcutPanel.getWidgetIndex(existing);
        baseShortcutPanel.insert(replacement, widgetIndex);
        baseShortcutPanel.remove(existing);
    }

    public void remove(@NotNull final Widget widget) {
        /* Early out if the existing widget isn't actually attached */
        if (widget.getParent() != baseShortcutPanel) {
            return;
        }

        baseShortcutPanel.remove(widget);
    }

    public void setCellHeight(final Widget widget, final String height) {
        baseShortcutPanel.setCellHeight(widget, height);
    }

    public void addSubMenu(@NotNull final SubShortcutPanel subShortcutPanel, @NotNull final PushButton subMenuButton) {
        subMenus.put(subShortcutPanel, subMenuButton);
        add(subMenuButton);

        // Add the click handlers
        subMenuButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showSubMenu(subShortcutPanel);
            }
        });

        final ClickHandler closeClickHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showBaseMenu();
            }
        };
        subShortcutPanel.getCloseButton().addClickHandler(closeClickHandler);
        subShortcutPanel.getOpenButton().addClickHandler(closeClickHandler);
    }

    public void removeSubMenu(@NotNull final SubShortcutPanel subShortcutPanel) {
        if (subMenus.containsKey(subShortcutPanel)) {
            baseShortcutPanel.remove(subMenus.get(subShortcutPanel));
            subMenus.remove(subShortcutPanel);
        }
    }

    protected void showSubMenu(final SubShortcutPanel subShortcutPanel) {
        rootPanel.setWidget(subShortcutPanel);
    }

    protected void showBaseMenu() {
        rootPanel.setWidget(baseShortcutPanel);
    }
}
