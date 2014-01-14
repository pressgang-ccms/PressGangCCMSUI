package org.jboss.pressgang.ccms.ui.client.local.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.AnchorButton;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class contains helper methods for creating and managing UI elements.
 *
 * @author Matthew Casperson
 */
final public class UIUtilities {
    private UIUtilities() {

    }

    @NotNull
    public static PushButton createPushButton(@NotNull final String text) {
        return createPushButton(text, false, false);
    }

    @NotNull
    public static PushButton createPushButton(@NotNull final String text, @NotNull final String id) {
        return createPushButton(text, false, false, id);
    }

    @NotNull
    public static PushButton createPushButton(@NotNull final String text, @NotNull final boolean subMenu) {
        return createPushButton(text, subMenu, false);
    }

    @NotNull
    public static PushButton createPushButton(@NotNull final String text, @NotNull final boolean subMenu, @NotNull final String id) {
        return createPushButton(text, subMenu, false, id);
    }

    @NotNull
    public static PushButton createPushButton(@NotNull final String text, @NotNull final boolean subMenu, @NotNull final boolean externalLink) {
        return createPushButton(text, subMenu, externalLink, null);
    }

    @NotNull
    public static PushButton createPushButton(final String text, final boolean subMenu, final boolean externalLink, @Nullable final String id) {
        @NotNull final PushButton retValue = new PushButton(text);
        retValue.addStyleName(CSSConstants.Common.TEXT_BUTTON);

        if (subMenu) {
            retValue.addStyleName(CSSConstants.Common.SUB_MENU);
        } else if (externalLink) {
            retValue.addStyleName(CSSConstants.Common.EXTERNAL_BUTTON);
        }

        if (id != null) {
            retValue.getElement().setId(id);
        }

        return retValue;
    }

    @NotNull
    public static PushButton createTopTabPushButton(final String text) {
        return createTopTabPushButton(text, false, false);
    }

    @NotNull
    public static PushButton createTopTabPushButton(final String text, @NotNull final String id) {
        return createTopTabPushButton(text, false, false, id);
    }

    @NotNull
    public static PushButton createTopTabPushButton(final String text, final boolean subMenu, final boolean externalLink) {
        return createTopTabPushButton(text, false, externalLink, null);
    }

    @NotNull
    public static PushButton createTopTabPushButton(final String text, final boolean subMenu, final boolean externalLink,
            @Nullable final String id) {
        @NotNull final PushButton retValue = createPushButton(text, subMenu, externalLink, id);

        retValue.addStyleName(CSSConstants.Common.TOP_TAB_BUTTON);
        if (id != null) {
            retValue.getElement().setId(id);
        }
        return retValue;
    }

    @NotNull
    public static PushButton createLeftSideTabPushButton(final String text) {
        return createLeftSideTabPushButton(text, false, false);
    }

    @NotNull
    public static PushButton createLeftSideTabPushButton(final String text, final boolean subMenu, final boolean externalLink) {
        @NotNull final PushButton retValue = createPushButton(text, subMenu, externalLink);
        retValue.addStyleName(CSSConstants.Common.LEFT_TAB_BUTTON);
        return retValue;
    }

    @NotNull
    public static ToggleButton createLeftSideTabToggleButton(@NotNull final String text, @NotNull final String id) {
        return createLeftSideTabToggleButton(text, false, id);
    }

    @NotNull
    public static ToggleButton createLeftSideTabToggleButton(@NotNull final String text) {
        return createLeftSideTabToggleButton(text, false, null);
    }

    @NotNull
    public static ToggleButton createLeftSideTabToggleButton(final String text, final boolean subMenu, @Nullable final String id) {
        final ToggleButton retValue = createToggleButton(text, subMenu);
        if (id != null) {
            retValue.getElement().setId(id);
        }
        retValue.addStyleName(CSSConstants.Common.LEFT_TAB_BUTTON);
        return retValue;
    }

    @NotNull
    public static ToggleButton createToggleButton(final String text, final boolean subMenu) {
        @NotNull final ToggleButton retValue = new ToggleButton(text);
        retValue.addStyleName(CSSConstants.Common.TEXT_BUTTON);

        if (subMenu) {
            retValue.addStyleName(CSSConstants.Common.SUB_MENU);
        }

        return retValue;
    }

    @NotNull
    public static ToggleButton createToggleButton(final String text) {
        return createToggleButton(text, false);
    }

    @NotNull
    public static Label createDownLabel(@NotNull final String text) {
        return createDownLabel(text, null);
    }

    @NotNull
    public static Label createDownLabel(@NotNull final String text, @Nullable final String id) {
        @NotNull final Label retValue = new Label(text);
        retValue.addStyleName(CSSConstants.Common.DOWN_LABEL);
        if (id != null) {
            retValue.getElement().setId(id);
        }
        return retValue;
    }

    @NotNull
    public static Label createTopTabDownLabel(final String text) {
        return createTopTabDownLabel(text, false, null);
    }

    @NotNull
    public static Label createTopTabDownLabel(final String text, @NotNull final String id) {
        return createTopTabDownLabel(text, false, id);
    }

    @NotNull
    public static Label createTopTabDownLabel(final String text, final boolean subMenu) {
        @NotNull final Label retValue = createDownLabel(text, null);
        retValue.addStyleName(CSSConstants.Common.TOP_TAB_BUTTON);
        return retValue;
    }

    @NotNull
    public static Label createTopTabDownLabel(final String text, final boolean subMenu, @NotNull final String id) {
        @NotNull final Label retValue = createDownLabel(text, id);
        retValue.addStyleName(CSSConstants.Common.TOP_TAB_BUTTON);
        return retValue;
    }

    @NotNull
    public static Label createLeftSideTabDownLabel(final String text) {
        return createLeftSideTabDownLabel(text, false);
    }

    @NotNull
    public static Label createLeftSideTabDownLabel(final String text, final boolean subMenu) {
        @NotNull final Label retValue = createDownLabel(text, null);
        retValue.addStyleName(CSSConstants.Common.LEFT_TAB_BUTTON);

        if (subMenu) {
            retValue.addStyleName(CSSConstants.Common.SUB_MENU);
        }

        return retValue;
    }

    @NotNull
    public static Label createLeftSideTabLabel(final String text) {
        return createLeftSideTabLabel(text, false);
    }

    @NotNull
    public static Label createLeftSideTabLabel(final String text, final boolean subMenu) {
        @NotNull final Label retValue = new Label(text);
        retValue.addStyleName(CSSConstants.Common.LEFT_TAB_BUTTON);

        if (subMenu) {
            retValue.addStyleName(CSSConstants.Common.SUB_MENU);
        }

        return retValue;
    }

    /**
     * @return a new SimplePager with the default paging settings
     */
    @NotNull
    public static SimplePager createSimplePager() {
        return new SimplePager(TextLocation.CENTER, true, Constants.FAST_FORWARD_ROWS, true);
    }

    /**
     * @param <T> The type of collection item to be displayed by the cell table
     * @return A new cell table with the default settings
     */
    @NotNull
    public static <T extends RESTBaseEntityCollectionItemV1<?, ?, ?>> CellTable<T> createCellTable() {
        return new CellTable<T>(Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
    }

    @NotNull
    public static AnchorButton createMenuButton(final String text) {
        return createMenuButton(text, false, false);
    }

    @NotNull
    public static AnchorButton createMenuButton(final String text, @NotNull final String id) {
        return createMenuButton(text, false, false, id);
    }

    @NotNull
    public static AnchorButton createMenuButton(final String text, final boolean subMenu, final boolean externalLink) {
        return createMenuButton(text, subMenu, externalLink, null);
    }

    @NotNull
    public static AnchorButton createMenuButton(final String text, final boolean subMenu, final boolean externalLink, @Nullable final String id) {
        @NotNull final AnchorButton retValue = new AnchorButton(text);
        retValue.addStyleName(CSSConstants.Common.TEXT_BUTTON);

        if (subMenu) {
            retValue.addStyleName(CSSConstants.Common.SUB_MENU);
        } else if (externalLink) {
            retValue.addStyleName(CSSConstants.Common.EXTERNAL_BUTTON);
        }

        retValue.addStyleName(CSSConstants.Common.TOP_TAB_BUTTON);
        if (id != null) {
            retValue.getElement().setId(id);
        }
        return retValue;
    }

    @NotNull
    public static Label createMenuDownLabel(final String text) {
        return createMenuDownLabel(text, false, null);
    }

    @NotNull
    public static Label createMenuDownLabel(final String text, @NotNull final String id) {
        return createMenuDownLabel(text, false, id);
    }

    @NotNull
    public static Label createMenuDownLabel(final String text, final boolean subMenu) {
        @NotNull final Label retValue = createDownLabel(text, null);
        retValue.addStyleName(CSSConstants.Common.TOP_TAB_BUTTON);
        return retValue;
    }

    @NotNull
    public static Label createMenuDownLabel(final String text, final boolean subMenu, @NotNull final String id) {
        @NotNull final Label retValue = createDownLabel(text, id);
        retValue.addStyleName(CSSConstants.Common.TOP_TAB_BUTTON);
        return retValue;
    }

    @NotNull
    public static AnchorButton createAnchorButton(@NotNull final String text) {
        return createAnchorButton(text, false, false);
    }

    @NotNull
    public static AnchorButton createAnchorButton(@NotNull final String text, @NotNull final String id) {
        return createAnchorButton(text, false, false, id);
    }

    @NotNull
    public static AnchorButton createAnchorButton(@NotNull final String text, @NotNull final boolean subMenu) {
        return createAnchorButton(text, subMenu, false);
    }

    @NotNull
    public static AnchorButton createAnchorButton(@NotNull final String text, @NotNull final boolean subMenu, @NotNull final String id) {
        return createAnchorButton(text, subMenu, false, id);
    }

    @NotNull
    public static AnchorButton createAnchorButton(@NotNull final String text, @NotNull final boolean subMenu, @NotNull final boolean externalLink) {
        return createAnchorButton(text, subMenu, externalLink, null);
    }

    @NotNull
    public static AnchorButton createAnchorButton(final String text, final boolean subMenu, final boolean externalLink, @Nullable final String id) {
        @NotNull final AnchorButton retValue = new AnchorButton(text);
        retValue.addStyleName(CSSConstants.Common.TEXT_BUTTON);

        if (subMenu) {
            retValue.addStyleName(CSSConstants.Common.SUB_MENU);
        } else if (externalLink) {
            retValue.addStyleName(CSSConstants.Common.EXTERNAL_BUTTON);
        }

        if (id != null) {
            retValue.getElement().setId(id);
        }

        return retValue;
    }
}
