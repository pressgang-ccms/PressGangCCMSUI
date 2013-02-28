package org.jboss.pressgang.ccms.ui.client.local.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jetbrains.annotations.NotNull;

/**
 * This class contains helper methods for creating and managing UI elements.
 *
 * @author Matthew Casperson
 */
final public class UIUtilities {
    private UIUtilities() {

    }

    @NotNull
    public static PushButton createPushButton(final String text) {
        return createPushButton(text, false, false);
    }

    @NotNull
    public static PushButton createPushButton(final String text, final boolean subMenu) {
        return createPushButton(text, subMenu, false);
    }

    @NotNull
    public static PushButton createPushButton(final String text, final boolean subMenu, final boolean externalLink) {
        @NotNull final PushButton retvalue = new PushButton(text);
        retvalue.addStyleName(CSSConstants.Common.TEXT_BUTTON);

        if (subMenu) {
            retvalue.addStyleName(CSSConstants.Common.SUB_MENU);
        } else if (externalLink) {
            retvalue.addStyleName(CSSConstants.Common.EXTERNAL_BUTTON);
        }

        return retvalue;
    }

    @NotNull
    public static PushButton createTopTabPushButton(final String text) {
        return createTopTabPushButton(text, false, false);
    }

    @NotNull
    public static PushButton createTopTabPushButton(final String text, final boolean subMenu, final boolean externalLink) {
        @NotNull final PushButton retvalue = createPushButton(text, subMenu, externalLink);
        retvalue.addStyleName(CSSConstants.Common.TOP_TAB_BUTTON);
        return retvalue;
    }

    @NotNull
    public static PushButton createLeftSideTabPushButton(final String text) {
        return createLeftSideTabPushButton(text, false, false);
    }

    @NotNull
    public static PushButton createLeftSideTabPushButton(final String text, final boolean subMenu, final boolean externalLink) {
        @NotNull final PushButton retvalue = createPushButton(text, subMenu, externalLink);
        retvalue.addStyleName(CSSConstants.Common.LEFT_TAB_BUTTON);
        return retvalue;
    }


    @NotNull
    public static ToggleButton createToggleButton(final String text, final boolean subMenu) {
        @NotNull final ToggleButton retvalue = new ToggleButton(text);
        retvalue.addStyleName(CSSConstants.Common.TEXT_BUTTON);

        if (subMenu) {
            retvalue.addStyleName(CSSConstants.Common.SUB_MENU);
        }

        return retvalue;
    }

    @NotNull
    public static ToggleButton createToggleButton(final String text) {
        return createToggleButton(text, false);
    }

    @NotNull
    public static Label createDownLabel(final String text) {
        @NotNull final Label retvalue = new Label(text);
        retvalue.addStyleName(CSSConstants.Common.DOWN_LABEL);
        return retvalue;
    }

    @NotNull
    public static Label createTopTabDownLabel(final String text) {
        return createTopTabDownLabel(text, false)    ;
    }

    @NotNull
    public static Label createTopTabDownLabel(final String text, final boolean subMenu) {
        @NotNull final Label retvalue = createDownLabel(text);
        retvalue.addStyleName(CSSConstants.Common.TOP_TAB_BUTTON);
        return retvalue;
    }

    @NotNull
    public static Label createLeftSideTabDownLabel(final String text) {
        return createLeftSideTabDownLabel(text, false);
    }

    @NotNull
    public static Label createLeftSideTabDownLabel(final String text, final boolean subMenu) {
        @NotNull final Label retvalue = createDownLabel(text);
        retvalue.addStyleName(CSSConstants.Common.LEFT_TAB_BUTTON);
        return retvalue;
    }

    /**
     * @return a new SimplePager with the default paging settings
     */
    @NotNull
    public static SimplePager createSimplePager() {
        return new SimplePager(TextLocation.CENTER, true, Constants.FAST_FORWARD_ROWS, true);
    }

    /**
     * @param <T> The type of collection item to be displayed by the celltable
     * @return A new celltable with the default settings
     */
    @NotNull
    public static <T extends RESTBaseCollectionItemV1<?, ?, ?>> CellTable<T> createCellTable() {
        return new CellTable<T>(Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
    }
}
