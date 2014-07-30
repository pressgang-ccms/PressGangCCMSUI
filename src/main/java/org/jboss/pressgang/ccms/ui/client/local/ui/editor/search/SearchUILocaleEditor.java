/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TriStatePushButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.locale.SearchUILocale;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.locale.SearchUILocales;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * The editor that binds a list of locales and their states to buttons and labels.
 */
public final class SearchUILocaleEditor extends FlexTable implements LeafValueEditor<SearchUILocales> {
    private static final int COLUMNS = 2;

    private final Map<TriStatePushButton, SearchUILocale> buttonsMap = new HashMap<TriStatePushButton, SearchUILocale>();

    @Override
    public void setValue(@Nullable final SearchUILocales value) {
        removeAllRows();
        buttonsMap.clear();

        if (value != null) {

            int index = 0;
            for (@NotNull final SearchUILocale locale : value.getLocales()) {

                final int fixedIndex = index / COLUMNS;
                final int column = index % COLUMNS;

                @NotNull final Label label = new Label(locale.getName());
                label.addStyleName(CSSConstants.LocaleList.LOCALE_LABEL);
                @NotNull final TriStatePushButton button = new TriStatePushButton();

                buttonsMap.put(button, locale);

                setWidget(fixedIndex, column * 2, label);
                setWidget(fixedIndex, (column * 2) + 1, button);

                ++index;
            }
        }
    }

    @NotNull
    @Override
    public SearchUILocales getValue() {
        @NotNull final SearchUILocales retValue = new SearchUILocales();

        for (@NotNull final TriStatePushButton button : buttonsMap.keySet()) {
            final SearchUILocale locale = buttonsMap.get(button);
            locale.setLocale(button.getState());
        }

        return retValue;
    }
}
