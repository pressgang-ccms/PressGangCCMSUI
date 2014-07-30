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

package org.jboss.pressgang.ccms.ui.client.local.data;

import com.google.gwt.http.client.*;
import org.jetbrains.annotations.NotNull;

/**
 * Holds a copy of the flattened DocBook DTD. This is used for validation, and also to inject the
 * entities (like nbsp) into the XML when rendered. This has to be inline for the XML rendering to
 * work in Firefox, as Firefox will not load external entity files.
 */
public final class DocBookDTD {
    private static String ent = "";

    public static String getDtdDoctype() {
        return getDtdDoctype("");
    }

    public static String getDtdDoctype(final String additionalEntities) {
        if (ent.isEmpty()) {
            return "<!DOCTYPE section [" + additionalEntities + "]>";
        } else {
            return "<!DOCTYPE section [" + ent + "\n" + additionalEntities + "]>";
        }
    }

    /**
     * Load the DTD file.
     */
    public static void loadDtd() {
        try {
            new RequestBuilder(RequestBuilder.GET, "javascript/xmllint/docbook.ent").sendRequest("", new RequestCallback() {
                @Override
                public void onResponseReceived(@NotNull final Request req, @NotNull final Response resp) {
                    ent = resp.getText();
                }

                @Override
                public void onError(@NotNull final Request res, @NotNull final Throwable throwable) {
                    ent = "";
                }
            });
        } catch (@NotNull final RequestException e) {
            ent = "";
        }
    }
}
