package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.constants.RESTv1Constants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;
import org.jetbrains.annotations.NotNull;

/**
 * A collection of REST calls.
 */
public final class FailOverRESTCallDatabase {

    /**
     * The required expansion details for the tags.
     */
    private static final String TAG_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTTagV1.PROJECTS_NAME + "\"}}," +
            "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}," +
            "{\"trunk\":{\"name\": \"" + RESTTagV1.CATEGORIES_NAME + "\"}}";

    /**
     * The required expansion details for the categories.
     */
    private static final String CATEGORY_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTCategoryV1.TAGS_NAME + "\"}}";

    /**
     * The required expansion details for a topic. The revisions are required so we can check to see if
     * the last revision was the one we edited. If not, there was a conflicting save.
     * <p/>
     * We only need the last 2 revisions to check for save conflicts.
     */
    private static final String TOPIC_EXPANSION =
            "{\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.SOURCE_URLS_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.REVISIONS_NAME + "\", \"start\": 0, \"end\": 2}}" +
                    "]}";

    /**
     * A topic with expanded revisions
     */
    private static final String TOPIC_REVISIONS_EXPANSION =
            "{\"trunk\":{\"name\": \"" + RESTTopicV1.REVISIONS_NAME + "\"},\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.LOG_DETAILS_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.SOURCE_URLS_NAME + "\"}}" +
                    "]}";

    /**
     * A topic with expanded property tags
     */
    private static final String TOPIC_PROPERTIES_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}";

    /**
     * Create a RESTCall object to call the REST holdXML method
     * @param xml The XML to be held by the REST server
     * @return A RESTCall that can call the REST holdXML method
     */
    public static final RESTCall holdXML(@NotNull final String xml) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.holdXML(xml);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONTag method
     * @param tag The tag to be saved
     * @return A RESTCall that can call the REST updateJSONTag method
     */
    public static final RESTCall saveTag(@NotNull final RESTTagV1 tag) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
                restService.updateJSONTag(expand, tag);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONTag method
     * @param tag The tag to be created
     * @return A RESTCall that can call the REST createJSONTag method
     */
    public static final RESTCall createTag(@NotNull final RESTTagV1 tag) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
                restService.createJSONTag(expand, tag);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST something method
     * @param category The category to be updated
     * @return A RESTCall that can call the REST something method
     */
    public static final RESTCall saveCategory(@NotNull final RESTCategoryV1 category) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
                restService.updateJSONCategory(expand, category);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONCategory method
     * @param category The category to be created
     * @return A RESTCall that can call the REST createJSONCategory method
     */
    public static final RESTCall createCategory(@NotNull final RESTCategoryV1 category) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
                restService.createJSONCategory(expand, category);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONCategories method
     * @param categories The categories to be saved
     * @return A RESTCall that can call the REST updateJSONCategories method
     */
    public static final RESTCall updateCategories(@NotNull final RESTCategoryCollectionV1 categories) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.updateJSONCategories("", categories);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONTopic method
     * @param topic The topic to be updated
     * @return A RESTCall that can call the REST updateJSONTopic method
     */
    public static final RESTCall saveTopic(@NotNull final RESTTopicV1 topic) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.updateJSONTopic(TOPIC_EXPANSION, topic);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONTopic method
     * @param topic The topic to be updated
     * @param message The revision log message
     * @param flag The flags associated with this revision
     * @param userId The user that made the changes
     * @return A RESTCall that can call the REST updateJSONTopic method
     */
    public static final RESTCall saveTopic(@NotNull final RESTTopicV1 topic, @NotNull final String message,
                                           @NotNull final Integer flag, @NotNull final String userId) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.updateJSONTopic(TOPIC_EXPANSION, topic, message, flag, userId);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createTopic method
     * @param topic The topic to be created
     * @return A RESTCall that can call the REST createTopic method
     */
    public static final RESTCall createTopic(@NotNull final RESTTopicV1 topic) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.createJSONTopic(TOPIC_EXPANSION, topic);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONTopic method
     * @param topic The topic to be created
     * @param message The revision log message
     * @param flag The flags associated with this revision
     * @param userId The user that made the changes
     * @return A RESTCall that can call the REST createJSONTopic method
     */
    public static final RESTCall createTopic(@NotNull final RESTTopicV1 topic, @NotNull final String message,
                                             @NotNull final Integer flag, @NotNull final String userId) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.createJSONTopic(TOPIC_EXPANSION, topic, message, flag, userId);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONStringConstant method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONStringConstant method
     */
    public static final RESTCall getStringConstant(final int id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONStringConstant(id, "");
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONPropertyTags method
     * @return A RESTCall that can call the REST getJSONPropertyTags method
     */
    public static final RESTCall getPropertyTags() {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\":\"" + RESTv1Constants.PROPERTYTAGS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONPropertyTags(expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTags method
     * @return A RESTCall that can call the REST getJSONTags method
     */
    public static final RESTCall getTags() {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"branches\":[" + TAG_EXPANSION + "],\"trunk\":{\"name\":\"tags\"}}]}";
                restService.getJSONTags(expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTag method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONTag method
     */
    public static final RESTCall getTag(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
                restService.getJSONTag(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTag method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONTag method
     */
    public static final RESTCall getUnexpandedTag(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONTag(id, "");
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopic method
     * @param id The entity ID
     * @param start The start of the revisions
     * @param end The end of the revision
     * @return A RESTCall that can call the REST getJSONTopic method
     */
    public static final RESTCall getTopicWithRevisions(@NotNull final Integer id, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String revisionExpand =
                        "{\"branches\":[" +
                            "{\"trunk\":{\"name\": \"" + RESTTopicV1.REVISIONS_NAME + "\", \"start\":" + start + ", \"end\":" + end + "}," +
                            "\"branches\":[" +
                                "{\"trunk\":{\"name\": \"" + RESTTopicV1.LOG_DETAILS_NAME + "\"}}," +
                                "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}," +
                                "{\"trunk\":{\"name\": \"" + RESTTopicV1.SOURCE_URLS_NAME + "\"}}" +
                            "]}" +
                        "]}";
                restService.getJSONTopic(id, revisionExpand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopic method
     * @para id The entity ID
     * @return A RESTCall that can call the REST getJSONTopic method
     */
    public static final RESTCall getTopicWithRevisions(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + TOPIC_REVISIONS_EXPANSION + "]}";
                restService.getJSONTopic(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopicRevision method
     * @param id The entity ID
     * @param revision The entity revision
     * @return A RESTCall that can call the REST getJSONTopicRevision method
     */
    public static final RESTCall getTopicRevisionWithProperties(@NotNull final Integer id, @NotNull final Integer revision) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + TOPIC_PROPERTIES_EXPANSION + "]}";
                restService.getJSONTopicRevision(id, revision, expand);
            }
        };
    }

}
