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

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;

/**
 * A {@link com.google.gwt.cell.client.Cell} used to render formatted numbers.
 */
public class IntegerCell extends AbstractCell<Integer> {

    /**
     * The {@link com.google.gwt.i18n.client.NumberFormat} used to render the number.
     */
    private final NumberFormat format;

    /**
     * The {@link com.google.gwt.text.shared.SafeHtmlRenderer} used to render the formatted number as HTML.
     */
    private final SafeHtmlRenderer<String> renderer;

    /**
     * Construct a new {@link IntegerCell} using decimal format and a
     * {@link com.google.gwt.text.shared.SimpleSafeHtmlRenderer}.
     */
    public IntegerCell() {
        this(NumberFormat.getDecimalFormat(), SimpleSafeHtmlRenderer.getInstance());
    }

    /**
     * Construct a new {@link IntegerCell} using the given {@link NumberFormat} and
     * a {@link SimpleSafeHtmlRenderer}.
     *
     * @param format the {@link NumberFormat} used to render the number
     */
    public IntegerCell(NumberFormat format) {
        this(format, SimpleSafeHtmlRenderer.getInstance());
    }

    /**
     * Construct a new {@link IntegerCell} using decimal format and the given
     * {@link SafeHtmlRenderer}.
     *
     * @param renderer the {@link SafeHtmlRenderer} used to render the formatted
     *          number as HTML
     */
    public IntegerCell(SafeHtmlRenderer<String> renderer) {
        this(NumberFormat.getDecimalFormat(), renderer);
    }

    /**
     * Construct a new {@link IntegerCell} using the given {@link NumberFormat} and
     * a {@link SafeHtmlRenderer}.
     *
     * @param format the {@link NumberFormat} used to render the number
     * @param renderer the {@link SafeHtmlRenderer} used to render the formatted
     *          number as HTML
     */
    public IntegerCell(NumberFormat format, SafeHtmlRenderer<String> renderer) {
        if (format == null) {
            throw new IllegalArgumentException("format == null");
        }
        if (renderer == null) {
            throw new IllegalArgumentException("renderer == null");
        }
        this.format = format;
        this.renderer = renderer;
    }

    @Override
    public void render(Context context, Integer value, SafeHtmlBuilder sb) {
        if (value != null) {
            sb.append(renderer.render(format.format(value)));
        }
    }
}
