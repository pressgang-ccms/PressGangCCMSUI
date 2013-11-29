package org.jboss.pressgang.ccms.ui.client.local.utilities;

import java.util.ArrayList;
import java.util.List;

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
        final List<String> allMatches = new ArrayList<String>();
        final RegExp entityRe = RegExp.compile("&[\\w-\\.]+?;", "g");

        MatchResult matchResult = null;
        while ((matchResult = entityRe.exec(xml)) != null) {
            final String entity = matchResult.getGroup(0);
            if (Constants.DOCBOOK_45_ENTITIES.indexOf(entity) == -1) {
                allMatches.add(entity);
            }
        }

        String retValue = xml;
        for (final String customEntity : allMatches) {
            retValue = retValue.replaceAll(customEntity, "&amp;" + customEntity.substring(1));
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
}
