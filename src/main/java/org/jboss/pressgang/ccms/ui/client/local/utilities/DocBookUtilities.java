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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jetbrains.annotations.NotNull;

public class DocBookUtilities {

    /**
     * Replace all custom entities so they show up in the text as the entity. This is used when we need
     * to render the docbook without knowing the custom entities.
     * @param xml The source xml
     * @return the fixed xml
     */
    public static String escapeAllCustomEntities(@NotNull final String xml) {
        final Set<String> allMatches = new HashSet<String>();
        final RegExp entityRe = RegExp.compile("&([\\w-\\.]+?);", "g");

        MatchResult matchResult = null;
        while ((matchResult = entityRe.exec(xml)) != null) {
            final String entityName = matchResult.getGroup(1);
            final String entity = matchResult.getGroup(0);
            if (Constants.DOCBOOK_45_ENTITIES.indexOf(entityName) == -1) {
                allMatches.add(entity);
            }
        }

        String retValue = xml;
        for (final String customEntity : allMatches) {
            retValue = retValue.replace(customEntity, "&amp;" + customEntity.substring(1));
        }

        return retValue;
    }

    /**
     * Check to ensure that a table isn't missing any entries in its rows.
     *
     * @param table The DOM table node to be checked.
     * @return True if the table has the required number of entries, otherwise false.
     */
    public static boolean validateTableRows(final Element table) {
        assert table != null;
        assert table.getNodeName().equals("table") || table.getNodeName().equals("informaltable");

        final NodeList tgroups = table.getElementsByTagName("tgroup");
        for (int i = 0; i < tgroups.getLength(); i++) {
            final Element tgroup = (Element) tgroups.item(i);
            if (!validateTableGroup(tgroup)) return false;
        }

        return true;
    }

    /**
     * Check to ensure that a Docbook tgroup isn't missing an row entries, using number of cols defined for the tgroup.
     *
     * @param tgroup The DOM tgroup element to be checked.
     * @return True if the tgroup has the required number of entries, otherwise false.
     */
    public static boolean validateTableGroup(final Element tgroup) {
        assert tgroup != null;
        assert tgroup.getNodeName().equals("tgroup");

        final Integer numColumns = Integer.parseInt(tgroup.getAttribute("cols"));

        // Check that all the thead, tbody and tfoot elements have the correct number of entries.
        final List<Node> nodes = XMLUtilities.getDirectChildNodes(tgroup, "thead", "tbody", "tfoot");
        for (final Node ele : nodes) {
            // Find all child nodes that are a row
            final NodeList children = ele.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                final Node node = children.item(i);
                if (node.getNodeName().equals("row") || node.getNodeName().equals("tr")) {
                    if (!validateTableRow(node, numColumns)) return false;
                }
            }
        }

        return true;
    }

    /**
     * Check to ensure that a docbook row has the required number of columns for a table.
     *
     * @param row        The DOM row element to be checked.
     * @param numColumns The number of entry elements that should exist in the row.
     * @return True if the row has the required number of entries, otherwise false.
     */
    public static boolean validateTableRow(final Node row, final int numColumns) {
        assert row != null;
        assert row.getNodeName().equals("row") || row.getNodeName().equals("tr");

        if (row.getNodeName().equals("row")) {
            final List<Node> entries = XMLUtilities.getDirectChildNodes(row, "entry");
            final List<Node> entryTbls = XMLUtilities.getDirectChildNodes(row, "entrytbl");

            if ((entries.size() + entryTbls.size()) <= numColumns) {
                for (final Node entryTbl : entryTbls) {
                    if (!validateEntryTbl((Element) entryTbl)) return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            final List<Node> nodes = XMLUtilities.getDirectChildNodes(row, "td", "th");

            return nodes.size() <= numColumns;
        }
    }

    /**
     * Check to ensure that a Docbook entrytbl isn't missing an row entries, using number of cols defined for the entrytbl.
     *
     * @param entryTbl The DOM entrytbl element to be checked.
     * @return True if the entryTbl has the required number of entries, otherwise false.
     */
    public static boolean validateEntryTbl(final Element entryTbl) {
        assert entryTbl != null;
        assert entryTbl.getNodeName().equals("entrytbl");

        final Integer numColumns = Integer.parseInt(entryTbl.getAttribute("cols"));

        // Check that all the thead and tbody elements have the correct number of entries.
        final List<Node> nodes = XMLUtilities.getDirectChildNodes(entryTbl, "thead", "tbody");
        for (final Node ele : nodes) {
            // Find all child nodes that are a row
            final NodeList children = ele.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                final Node node = children.item(i);
                if (node.getNodeName().equals("row") || node.getNodeName().equals("tr")) {
                    if (!validateTableRow(node, numColumns)) return false;
                }
            }
        }

        return true;
    }

    /**
     * It is expected that a Docbook 5 book or article will define the namespaces at the root element. But for
     * validation against a single topic, we need to add these namespaces in in order for the dtd to validate.
     * @param xml The source xml
     * @return the xml with the docbook 5 namespaces added
     */
    public static String addDocBook50Namespaces(@NotNull final String xml) {
        final String rootEleName = XMLUtilities.getRootElementName(xml);
        final String fixedRootEleName = rootEleName == null ? "section" : rootEleName;
        final MatchResult result = RegExp.compile("^([\\s\\S]*?)<\\s*" + fixedRootEleName + "(\\s*.*?)>").exec(xml);
        if (result != null) {
            final StringBuilder retValue = new StringBuilder(result.getGroup(1));
            retValue.append("<" + fixedRootEleName);

            // Clean out the normal attributes
            String fixedAttributes = result.getGroup(2);
            // Remove any current namespace declaration
            fixedAttributes = fixedAttributes.replaceFirst(" xmlns\\s*=\\s*('|\").*?('|\")", "");
            // Remove any current version declaration
            fixedAttributes = fixedAttributes.replaceFirst(" version\\s*=\\s*('|\").*?('|\")", "");
            // Remove any current xlink namespace declaration
            fixedAttributes = fixedAttributes.replaceFirst(" xmlns:xlink\\s*=\\s*('|\").*?('|\")", "");

            // Add the generic attributes
            retValue.append(" xmlns=\"http://docbook.org/ns/docbook\"");
            retValue.append(" version=\"5.0\"");
            if (!rootEleName.equalsIgnoreCase("info")) {
                // Info is not allowed to have xlink declared for the DocBook 5.0 DTD
                retValue.append(" xmlns:xlink=\"http://www.w3.org/1999/xlink\"");
            }

            retValue.append(fixedAttributes + ">");
            return xml.replace(result.getGroup(0), retValue.toString());
        }

        return xml;
    }

    /**
     * Escapes a String so that it can be used in a Docbook Element, ensuring that any entities or elements are maintained.
     *
     * @param content The string to be escaped.
     * @return The escaped string that can be used in XML.
     */
    public static String escapeForXML(final String content) {
        if (content == null) return "";

        /*
         * Note: The following characters should be escaped: & < > " '
         *
         * However, all but ampersand pose issues when other elements are included in the title.
         *
         * eg <title>Product A > Product B<phrase condition="beta">-Beta</phrase></title>
         *
         * should become
         *
         * <title>Product A &gt; Product B<phrase condition="beta">-Beta</phrase></title>
         */

        String fixedContent = content.replaceAll("&(?!\\S+?;)", "&amp;");

        // Loop over and find all the XML Elements as they should remain untouched.
        final LinkedList<String> elements = new LinkedList<String>();
        if (fixedContent.indexOf('<') != -1) {
            int index = -1;
            while ((index = fixedContent.indexOf('<', index + 1)) != -1) {
                int endIndex = fixedContent.indexOf('>', index);
                int nextIndex = fixedContent.indexOf('<', index + 1);

                /*
                  * If the next opening tag is less than the next ending tag, than the current opening tag isn't a match for the next
                  * ending tag, so continue to the next one
                  */
                if (endIndex == -1 || (nextIndex != -1 && nextIndex < endIndex)) {
                    continue;
                } else if (index + 1 == endIndex) {
                    // This is a <> sequence, so it should be ignored as well.
                    continue;
                } else {
                    elements.add(fixedContent.substring(index, endIndex + 1));
                }

            }
        }

        // Find all the elements and replace them with a marker
        String escapedTitle = fixedContent;
        for (int count = 0; count < elements.size(); count++) {
            escapedTitle = escapedTitle.replace(elements.get(count), "###" + count + "###");
        }

        // Perform the replacements on what's left
        escapedTitle = escapedTitle.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");

        // Replace the markers
        for (int count = 0; count < elements.size(); count++) {
            escapedTitle = escapedTitle.replace("###" + count + "###", elements.get(count));
        }

        return escapedTitle;
    }
}
