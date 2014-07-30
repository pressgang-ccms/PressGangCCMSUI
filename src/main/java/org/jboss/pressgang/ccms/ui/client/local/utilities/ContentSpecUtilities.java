/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.utilities;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.http.client.URL;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSNodeV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.enums.RESTCSNodeTypeV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;

public class ContentSpecUtilities {

    public static String buildEntities(final RESTContentSpecV1 contentSpec) {
        return buildEntities(contentSpec, true);
    }

    public static String buildEntities(final RESTContentSpecV1 contentSpec, final boolean includeCustomEntities) {
        checkArgument(contentSpec.getChildren_OTM() != null, "The RESTContentSpecV1 children collection should not be null");
        String title, product, copyrightHolder, copyrightYear, bzServer, bzUrl, bzProduct, bzComponent, bzVersion, entities, format;
        title = product = copyrightHolder = copyrightYear = bzServer = bzUrl = bzProduct = bzComponent = bzVersion = entities = null;
        format = CommonConstants.DOCBOOK_45_TITLE;

        // Set the values based on the content spec metadata
        for (final RESTCSNodeV1 childNode : contentSpec.getChildren_OTM().returnItems()) {
            // make sure the child node is a Meta Data node
            if (childNode.getNodeType() != RESTCSNodeTypeV1.META_DATA) continue;

            if (CommonConstants.CS_TITLE_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                title = childNode.getAdditionalText();
            } else if (CommonConstants.CS_PRODUCT_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                product = childNode.getAdditionalText();
            } else if (CommonConstants.CS_COPYRIGHT_HOLDER_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                copyrightHolder = childNode.getAdditionalText();
            } else if (CommonConstants.CS_COPYRIGHT_YEAR_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                copyrightYear = childNode.getAdditionalText();
            } else if (CommonConstants.CS_BUGZILLA_SERVER_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                bzServer = childNode.getAdditionalText();
            } else if (CommonConstants.CS_BUGZILLA_URL_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                bzUrl = childNode.getAdditionalText();
            } else if (CommonConstants.CS_BUGZILLA_PRODUCT_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                bzProduct = childNode.getAdditionalText();
            } else if (CommonConstants.CS_BUGZILLA_COMPONENT_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                bzComponent = childNode.getAdditionalText();
            } else if (CommonConstants.CS_BUGZILLA_VERSION_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                bzVersion = childNode.getAdditionalText();
            } else if (CommonConstants.CS_FORMAT_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                format = childNode.getAdditionalText();
            } else if (CommonConstants.CS_ENTITIES_TITLE.equalsIgnoreCase(childNode.getTitle())) {
                entities = childNode.getAdditionalText();
            }
        }

        // Find what entities have already been defined
        final StringBuilder retValue = new StringBuilder(100);
        final String definedEntities = entities == null ? "" : entities;

        // Add the default entities
        // BOOKID
        if (!definedEntities.contains(" BOOKID ") && title != null) {
            final String escapedTitle = title.replaceAll(" ", "_").replaceAll("^[^A-Za-z0-9]*", "").replaceAll("[^A-Za-z0-9_.-]", "");
            retValue.append("<!ENTITY BOOKID \"").append(escapedTitle).append("\">\n");
        }

        // PRODUCT
        if (!definedEntities.contains(" PRODUCT ") && product != null) {
            final String escapedProduct = escapeForXMLEntity(product);
            retValue.append("<!ENTITY PRODUCT \"").append(escapedProduct).append("\">\n");
        }

        // TITLE
        if (!definedEntities.contains(" TITLE ") && title != null) {
            final String escapedTitle = escapeTitleForXMLEntity(title);
            retValue.append("<!ENTITY TITLE \"").append(escapedTitle).append("\">\n");
        }

        // YEAR
        if (!definedEntities.contains(" YEAR ")) {
            final String year = copyrightYear == null ? Integer.toString(JsDate.create().getFullYear()) : copyrightYear;
            retValue.append("<!ENTITY YEAR \"").append(year).append("\">\n");
        }

        // HOLDER
        if (!definedEntities.contains(" HOLDER ") && copyrightHolder != null) {
            final String escapedHolder = escapeTitleForXMLEntity(copyrightHolder);
            retValue.append("<!ENTITY HOLDER \"").append(escapedHolder).append("\">\n");
        }

        // BZPRODUCT
        if (!definedEntities.contains(" BZPRODUCT ") && (product != null || bzProduct != null)) {
            final String escapedBZProduct = escapeForXMLEntity(bzProduct == null ? product : bzProduct);
            retValue.append("<!ENTITY BZPRODUCT \"").append(escapedBZProduct).append("\">\n");
        }

        // BZCOMPONENT
        if (!definedEntities.contains(" BZCOMPONENT ")) {
            final String escapedBZComponent = escapeForXMLEntity(bzComponent == null ? Constants.DEFAULT_BZCOMPONENT : bzComponent);
            retValue.append("<!ENTITY BZCOMPONENT \"").append(escapedBZComponent).append("\">\n");
        }

        // BZURL
        if (!definedEntities.contains(" BZURL ")) {
            final String host = isNullOrEmpty(bzServer) ? Constants.DEFAULT_BUGZILLA_URL : bzServer;
            final StringBuilder fixedBZURL = new StringBuilder();
            if (bzUrl == null) {
                if (CommonConstants.DOCBOOK_50_TITLE.equalsIgnoreCase(format)) {
                    fixedBZURL.append("<link xlink:href='");
                } else {
                    fixedBZURL.append("<ulink url='");
                }
                fixedBZURL.append(host);
                fixedBZURL.append("enter_bug.cgi");
                // Add in the product specific link details
                if (bzProduct != null) {
                    final String encodedProduct = URL.encode(bzProduct);
                    fixedBZURL.append("?product=").append(escapeForXMLEntity(encodedProduct));
                    if (bzComponent != null) {
                        final String encodedComponent = URL.encode(bzComponent);
                        fixedBZURL.append("&amp;component=").append(escapeForXMLEntity(encodedComponent));
                    }
                    if (bzVersion != null) {
                        final String encodedVersion = URL.encode(bzVersion);
                        fixedBZURL.append("&amp;version=").append(escapeForXMLEntity(encodedVersion));
                    }
                }
                fixedBZURL.append("'>").append(host);
                if (CommonConstants.DOCBOOK_50_TITLE.equalsIgnoreCase(format)) {
                    fixedBZURL.append("</link>");
                } else {
                    fixedBZURL.append("</ulink>");
                }
            } else {
                fixedBZURL.append(escapeForXMLEntity(bzUrl));
            }

            retValue.append("<!ENTITY BZURL \"").append(fixedBZURL).append("\">\n");
        }

        // Add the custom entities if any exist
        if (includeCustomEntities && entities != null) {
            retValue.append(entities.trim());
        }

        return retValue.toString();
    }

    protected static String escapeForXMLEntity(final String input) {
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;")
                .replace("%", "&percnt;");
    }

    protected static String escapeTitleForXMLEntity(final String input) {
        return DocBookUtilities.escapeForXML(input).replace("%", "&percnt;");
    }
}
