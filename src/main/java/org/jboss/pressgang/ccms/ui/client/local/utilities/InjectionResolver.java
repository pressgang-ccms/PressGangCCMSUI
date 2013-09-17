package org.jboss.pressgang.ccms.ui.client.local.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

public class InjectionResolver {
    /**
     * Used to identify that an <orderedlist> should be generated for the injection point
     */
    protected static final int ORDEREDLIST_INJECTION_POINT = 1;
    /**
     * Used to identify that an <itemizedlist> should be generated for the injection point
     */
    protected static final int ITEMIZEDLIST_INJECTION_POINT = 2;
    /**
     * Used to identify that an <xref> should be generated for the injection point
     */
    protected static final int XREF_INJECTION_POINT = 3;
    /**
     * Used to identify that an <xref> should be generated for the injection point
     */
    protected static final int LIST_INJECTION_POINT = 4;
    /**
     * This text identifies an option task in a list
     */
    protected static final String OPTIONAL_MARKER = "OPT:";
    /**
     * The text to be prefixed to a list item if a topic is optional
     */
    protected static final String OPTIONAL_LIST_PREFIX = "Optional: ";
    /**
     * A regular expression that identifies a topic id
     */
    protected static final String OPTIONAL_TOPIC_ID_RE = "(" + OPTIONAL_MARKER + "\\s*)?\\d+";

    /**
     * A regular expression that matches an InjectSequence custom injection point
     */
    public static final String CUSTOM_INJECTION_SEQUENCE_RE =
            "^\\s*InjectSequence:\\s*((\\s*" + OPTIONAL_TOPIC_ID_RE + "\\s*,)*(\\s*" + OPTIONAL_TOPIC_ID_RE + ",?))\\s*$";

    /**
     * A regular expression that matches an InjectList custom injection point
     */
    public static final String CUSTOM_INJECTION_LIST_RE =
            "^\\s*InjectList:\\s*((\\s*" + OPTIONAL_TOPIC_ID_RE + "\\s*,)*(\\s*" + OPTIONAL_TOPIC_ID_RE + ",?))\\s*$";

    public static final String CUSTOM_INJECTION_LISTITEMS_RE =
            "^\\s*InjectListItems:\\s*((\\s*" + OPTIONAL_TOPIC_ID_RE + "\\s*,)*(\\s*" + OPTIONAL_TOPIC_ID_RE + ",?))\\s*$";

    public static final String CUSTOM_ALPHA_SORT_INJECTION_LIST_RE =
            "^\\s*InjectListAlphaSort:\\s*((\\s*" + OPTIONAL_TOPIC_ID_RE + "\\s*,)*(\\s*" + OPTIONAL_TOPIC_ID_RE + ",?))\\s*$";

    /**
     * A regular expression that matches an Inject custom injection point
     */
    public static final String CUSTOM_INJECTION_SINGLE_RE = "^\\s*Inject:\\s*(" + OPTIONAL_TOPIC_ID_RE + ")\\s*$";


    public static void resolveInjections(final Document doc) {
        final List<Comment> injectionComments = new ArrayList<Comment>();
        for (final Comment comment : XMLUtilities.getComments(doc)) {
            if (comment.getNodeValue().matches("^\\s*Inject.*")) {
                injectionComments.add(comment);
            }
        }

        final Map<Integer, Map<Comment, List<InjectionTopicData>>> injections = new HashMap<Integer, Map<Comment, List<InjectionTopicData>>>();
        processInjections(injectionComments, injections, ORDEREDLIST_INJECTION_POINT, CUSTOM_INJECTION_SEQUENCE_RE);
        processInjections(injectionComments, injections, XREF_INJECTION_POINT, CUSTOM_INJECTION_SINGLE_RE);
        processInjections(injectionComments, injections, ITEMIZEDLIST_INJECTION_POINT, CUSTOM_INJECTION_LIST_RE);
        processInjections(injectionComments, injections, ITEMIZEDLIST_INJECTION_POINT, CUSTOM_ALPHA_SORT_INJECTION_LIST_RE);
        processInjections(injectionComments, injections, LIST_INJECTION_POINT, CUSTOM_INJECTION_LISTITEMS_RE);

        /* now make the custom injection point substitutions */
        for (final Map.Entry<Integer, Map<Comment, List<InjectionTopicData>>> entry : injections.entrySet()) {
            final Integer listType = entry.getKey();
            final Map<Comment, List<InjectionTopicData>> items = entry.getValue();

            for (final Map.Entry<Comment, List<InjectionTopicData>> typeEntry : items.entrySet()) {
                final Comment customInjectionCommentNode = typeEntry.getKey();
                List<Element> list = null;

                /*
                 * this may not be true if we are not building all related topics
                 */
                if (typeEntry.getValue() != null && typeEntry.getValue().size() > 0) {
                    if (listType == ORDEREDLIST_INJECTION_POINT) {
                        list = Arrays.asList(createDummyOrderedList(doc, typeEntry.getValue()));
                    } else if (listType == XREF_INJECTION_POINT) {
                        list = createDummyXRef(doc, typeEntry.getValue().get(0));
                    } else if (listType == ITEMIZEDLIST_INJECTION_POINT) {
                        list = Arrays.asList(createDummyItemizedList(doc, typeEntry.getValue()));
                    } else if (listType == LIST_INJECTION_POINT) {
                        list = createDummyListItems(doc, typeEntry.getValue());
                    }
                }

                if (list != null) {
                    for (final Element element : list) {
                        customInjectionCommentNode.getParentNode().insertBefore(element, customInjectionCommentNode);
                    }

                    customInjectionCommentNode.getParentNode().removeChild(customInjectionCommentNode);
                }
            }
        }
    }

    public static void processInjections(final List<Comment> injectionNodes, final Map<Integer, Map<Comment, List<InjectionTopicData>>> injections,
            final Integer injectionPointType, final String regularExpression) {

        if (!injections.containsKey(injectionPointType)) {
            injections.put(injectionPointType, new HashMap<Comment, List<InjectionTopicData>>());
        }

        /* compile the regular expression */
        final RegExp injectionSequencePattern = RegExp.compile(regularExpression);

        /* loop over all of the comments in the document */
        for (final Comment comment : injectionNodes) {
            final String commentContent = comment.getNodeValue();

            /* find any matches */
            final MatchResult injectionMatchResult = injectionSequencePattern.exec(commentContent);

            /* loop over the regular expression matches */
            if (injectionMatchResult != null) {
                /*
                 * get the list of topics from the named group in the regular expression match
                 */
                final String reMatch = injectionMatchResult.getGroup(1);

                /* make sure we actually found a matching named group */
                if (reMatch != null) {
                    /* get the sequence of ids */
                    final List<InjectionTopicData> injectionData = processTopicIdList(reMatch);

                    injections.get(injectionPointType).put(comment, injectionData);
                }
            }
        }
    }

    private static List<Element> createDummyXRef(final Document doc, final InjectionTopicData topicData) {
        final List<Element> retValue = new ArrayList<Element>();

        if (topicData.optional) {
            final Element emphasis = doc.createElement("emphasis");
            emphasis.appendChild(doc.createTextNode(OPTIONAL_LIST_PREFIX));
            retValue.add(emphasis);
        }

        final Element xRef = doc.createElement("ulink");
        xRef.setAttribute("url", "#");
        xRef.appendChild(doc.createTextNode("Topic " + topicData.topicId));
        retValue.add(xRef);

        return retValue;
    }
    
    private static Element createDummyItemizedList(final Document doc, final List<InjectionTopicData> injectionTopicDatas) {
        final Element para = doc.createElement("para");

        final Element itemizedList = doc.createElement("itemizedlist");
        para.appendChild(itemizedList);

        final List<Element> listItems = createDummyListItems(doc, injectionTopicDatas);
        for (final Element listItem : listItems) {
            itemizedList.appendChild(listItem);
        }

        return para;
    }

    private static Element createDummyOrderedList(final Document doc, final List<InjectionTopicData> injectionTopicDatas) {
        final Element para = doc.createElement("para");

        final Element orderedList = doc.createElement("orderedlist");
        para.appendChild(orderedList);

        final List<Element> listItems = createDummyListItems(doc, injectionTopicDatas);
        for (final Element listItem : listItems) {
            orderedList.appendChild(listItem);
        }

        return para;
    }

    private static List<Element> createDummyListItems(final Document doc, final List<InjectionTopicData> injectionTopicDatas) {
        final List<Element> retValue = new ArrayList<Element>();

        for (final InjectionTopicData topicData : injectionTopicDatas) {
            final Element listitem = doc.createElement("listitem");
            retValue.add(listitem);

            final Element listItemPara = doc.createElement("para");
            listitem.appendChild(listItemPara);

            final List<Element> elements = createDummyXRef(doc, topicData);
            for (final Element ele : elements) {
                listItemPara.appendChild(ele);
            }
        }

        return retValue;
    }

    /**
     * Takes a comma separated list of ints, and returns an array of Integers. This is used when processing custom injection
     * points.
     */
    private static List<InjectionTopicData> processTopicIdList(final String list) {
        /* find the individual topic ids */
        final String[] topicIDs = list.split(",");

        List<InjectionTopicData> retValue = new ArrayList<InjectionTopicData>(topicIDs.length);

        /* clean the topic ids */
        for (final String topicID : topicIDs) {
            final String topicId = topicID.replaceAll(OPTIONAL_MARKER, "").trim();
            final boolean optional = topicID.contains(OPTIONAL_MARKER);

            try {
                final InjectionTopicData topicData = new InjectionTopicData(Integer.parseInt(topicId), optional);
                retValue.add(topicData);
            } catch (final NumberFormatException ex) {
                /*
                 * these lists are discovered by a regular expression so we shouldn't have any trouble here with Integer.parse
                 */
                retValue.add(new InjectionTopicData(-1, false));
            }
        }

        return retValue;
    }
}

class InjectionTopicData {
    /**
     * The topic ID
     */
    public Integer topicId;
    /**
     * whether this topic was marked as optional
     */
    public boolean optional;

    public InjectionTopicData(final Integer topicId, final boolean optional) {
        this.topicId = topicId;
        this.optional = optional;
    }
}