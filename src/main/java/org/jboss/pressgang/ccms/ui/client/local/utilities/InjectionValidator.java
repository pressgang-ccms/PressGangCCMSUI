package org.jboss.pressgang.ccms.ui.client.local.utilities;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;

public class InjectionValidator {
    private static final RegExp INJECT_RE = RegExp.compile("^\\s*(Inject\\w*)(:?)" + "\\s*([\\w\\d\\s,\\.]*)\\s*$", "i");
    private static final RegExp INJECT_ID_RE = RegExp.compile("^[\\d ,]+$");
    private static final List<String> VALID_INJECTION_TYPES = Arrays.asList("Inject", "InjectList", "InjectListItems",
            "InjectListAlphaSort", "InjectSequence");
    private static final String VALID_INJECTION_TYPES_STRING = "Inject, InjectList, InjectListItems, " +
            "InjectListAlphaSort and InjectSequence";

    /**
     * Checks for instances of PressGang Injections that are invalid. This will check for the following problems:
     * <ul>
     * <li>Incorrect Capitalisation</li>
     * <li>Invalid Injection types (eg. InjectListItem)</li>
     * <li>Missing colons</li>
     * <li>Incorrect ID list (eg referencing Topic 10 as 10.xml)</li>
     * </ul>
     *
     * @param doc The DOM document to be checked for invalid PressGang injections.
     * @return A Map of Invalid Injections to its errors.
     */
    public static Map<Comment, List<String>> checkForInvalidInjections(final Document doc) {
        final Map<Comment, List<String>> retValue = new HashMap<Comment, List<String>>();

        final List<Comment> comments = XMLUtilities.getComments(doc.getDocumentElement());
        for (final Comment comment : comments) {
            final String commentText = comment.getNodeValue();
            final MatchResult match = INJECT_RE.exec(commentText);
            if (match != null) {
                final String type = match.getGroup(1);
                final String colon = match.getGroup(2);
                final String ids = match.getGroup(3);

                final List<String> errors = new ArrayList<String>();

                // Check the type
                if (!VALID_INJECTION_TYPES.contains(type)) {
                    errors.add("\"" + type + "\" is not a valid injection type. The valid types are: " + VALID_INJECTION_TYPES_STRING);
                }

                // Check that a colon has been specified
                if (isNullOrEmpty(colon)) {
                    errors.add("No colon specified in the injection.");
                }

                // Check that the id(s) are valid
                if (isNullOrEmpty(ids) || INJECT_ID_RE.exec(ids) == null) {
                    if (type.equalsIgnoreCase("inject")) {
                        errors.add(
                                "The Topic ID in the injection is invalid. Please ensure that only the Topic ID is used. eg " +
                                        "\"Inject: 1\"");
                    } else {
                        errors.add(
                                "The Topic ID(s) in the injection are invalid. Please ensure that only the Topic ID is used and" + " is " +
                                        "in a comma separated list. eg \"InjectList: 1, 2, 3\"");
                    }
                }

                if (!errors.isEmpty()) {
                    retValue.put(comment, errors);
                }
            }
        }

        return retValue;
    }
}
