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

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;

// See: http://stackoverflow.com/questions/8743699/gwtadding-clickhandler-to-imageresource-cell
public class ClickableImageCell extends AbstractCell<Image> {
    interface Template extends SafeHtmlTemplates {
        @Template("<img src=\"{0}\" title=\"{1}\"/>")
        SafeHtml img(SafeUri url, String title);
    }

    private static Template template;

    /**
     * Construct a new ImageCell.
     */
    public ClickableImageCell() {
        if (template == null) {
            template = GWT.create(Template.class);
        }
    }

    public Set<String> getConsumedEvents() {
        HashSet<String> events = new HashSet<String>();
        events.add("click");
        return events;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, Image value, NativeEvent event,
            ValueUpdater<Image> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if ("click".equals(event.getType())) {
            EventTarget eventTarget = event.getEventTarget();
            if (!Element.is(eventTarget)) {
                return;
            }
            if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
                // Ignore clicks that occur outside of the main element.
                if (valueUpdater != null) {
                    valueUpdater.update(value);
                }
            }
        }

    }

    @Override
    public void render(Context context, Image value, SafeHtmlBuilder sb) {
        if (value != null) {
            sb.append(template.img(UriUtils.fromTrustedString(value.getUrl()), value.getTitle()));
        }
    }
}