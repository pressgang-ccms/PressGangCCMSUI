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

package com.google.gwt.user.client.ui;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.user.cellview.client.Header;

public class TextInputHeader extends Header<String> {
    private ValueUpdater<String> updater;
    private String value;

    public TextInputHeader() {
        this(new TextInputCell());
    }

    public TextInputHeader(final TextInputCell inputCell) {
        super(inputCell);

        super.setUpdater(new ValueUpdater<String>() {
            @Override
            public void update(final String value) {
                if (updater != null) {
                    updater.update(value);
                }
                TextInputHeader.this.value = value;
            }
        });
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public void setUpdater(final ValueUpdater<String> updater) {
        this.updater = updater;
    }
}
