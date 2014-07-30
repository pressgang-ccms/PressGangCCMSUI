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

import com.google.gwt.dom.client.Document;
import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.text.shared.AbstractRenderer;
import org.jetbrains.annotations.Nullable;

/**
 * A ValueBox that displays Integers as plain strings
 */
public class SimpleIntegerBox extends ValueBox<Integer> {

    static class IntegerRenderer extends AbstractRenderer<Integer> {
        public static final IntegerRenderer INSTANCE = new IntegerRenderer();

        protected IntegerRenderer() {
        }

        @Override
        public String render(@Nullable final Integer object) {
            if (null == object) {
                return "";
            }

            return object.toString();
        }
    }

    public SimpleIntegerBox() {
        super(Document.get().createTextInputElement(), IntegerRenderer.INSTANCE, IntegerParser.instance());
    }
}
