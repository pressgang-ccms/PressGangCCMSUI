package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.errai.enterprise.client.jaxrs.api.PathSegmentImpl;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.constants.RESTv1Constants;
import org.jboss.pressgang.ccms.rest.v1.entities.*;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
     * A topic with expanded tags
     */
    private static final String TOPIC_AND_CONTENT_SPEC_TAGS_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTTopicV1.TAGS_NAME
            + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTTagV1.PROJECTS_NAME + "\"}},{\"trunk\":{\"name\":\""
            + RESTTagV1.CATEGORIES_NAME + "\"}}]}";

    /**
     * The required expansion details for a topic. This is used when loading a topic for the first time
     */
    private static final String TOPIC_EXPANSION_WO_REVISIONS =
            "{\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.SOURCE_URLS_NAME + "\"}}" +
                    "]}";

    /**
     * The required expansion details for a filter.
     */
    private static final String FILTER_EXPANSION =
            "{\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTFilterV1.FILTER_CATEGORIES_NAME + "\"}, \"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTFilterCategoryV1.CATEGORY_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTFilterCategoryV1.PROJECT_NAME + "\"}}" +
                    "]}," +
                    "{\"trunk\":{\"name\": \"" + RESTFilterV1.FILTER_FIELDS_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTFilterV1.FILTER_LOCALES_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTFilterV1.FILTER_TAGS_NAME + "\"}, \"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTFilterTagV1.TAG_NAME + "\"}}" +
                    "]}" +
                    "]}";

    private static final String TOPIC_TAG_EXPANSION =
            "{\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTv1Constants.TOPICS_EXPANSION_NAME + "\"}, \"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.TAGS_NAME + "\"}, \"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTagV1.CATEGORIES_NAME + "\"}}" +
                    "]}" +
                    "]}" +
                    "]}";

    /**
     * The required expansion details for a translated topic. The revisions are required so we can check to see if
     * the last revision was the one we edited. If not, there was a conflicting save.
     * <p/>
     * The properties are loaded here, unlike in TOPIC_EXPANSION. This is really just for convenience, as the topic
     * view is much more optimized for fast loading of the topics basic details.
     * <p/>
     * We only need the last 2 revisions to check for save conflicts.
     */
    private static final String TRANSLATED_TOPIC_EXPANSION =
            "{\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.SOURCE_URLS_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.REVISIONS_NAME + "\", \"start\": 0, \"end\": 2}}" +
                    "]}";

    /**
     * The required expansion details for the projects.
     */
    private static final String PROJECT_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTProjectV1.TAGS_NAME + "\"}}";

    /**
     * The required expansion details for the images.
     */
    private static final String IMAGE_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME + "\"}}";

    /**
     * The required expansion details for the property tags.
     */
    private static final String PROPERTY_TAG_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTPropertyTagV1.PROPERTY_CATEGORIES_NAME + "\"}}";

    /**
     * The required expansion details for the property tags.
     */
    private static final String PROPERTY_CATEGORY_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTPropertyCategoryV1.PROPERTY_TAGS_NAME + "\"}}";

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

    /**
     * Create a RESTCall object to call the REST getJSONTopic method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONTopic method
     */
    public static final RESTCall getTopicWithTags(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + TOPIC_AND_CONTENT_SPEC_TAGS_EXPANSION + "]}";
                restService.getJSONTopic(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTranslatedTopic method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONTranslatedTopic method
     */
    public static final RESTCall getTranslatedTopicWithTags(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + TOPIC_AND_CONTENT_SPEC_TAGS_EXPANSION + "]}";
                restService.getJSONTranslatedTopic(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopicRevision method
     * @param id The entity ID
     * @param revision The entity revision
     * @return A RESTCall that can call the REST getJSONTopicRevision method
     */
    public static final RESTCall getTopicRevisionWithTags(@NotNull final Integer id, @NotNull final Integer revision) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + TOPIC_AND_CONTENT_SPEC_TAGS_EXPANSION + "]}";
                restService.getJSONTopicRevision(id, revision, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopic method
     * @param
     * @return A RESTCall that can call the REST getJSONTopic method
     */
    public static final RESTCall getTopic(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONTopic(id, TOPIC_EXPANSION_WO_REVISIONS);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONImage method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONImage method
     */
    public static final RESTCall getImage(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME
                        + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTLanguageImageV1.IMAGEDATABASE64_NAME + "\"}}]}]}";
                restService.getJSONImage(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONImage method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONImage method
     */
    public static final RESTCall getImageWithoutPreview(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME + "\"}}]}";
                restService.getJSONImage(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONImage method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONImage method
     */
    public static final RESTCall getImageWithoutLanguageImages(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME + "\"}}]}";
                restService.getJSONImage(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONImage method
     * @param image The image to be updated
     * @return A RESTCall that can call the REST updateJSONImage method
     */
    public static final RESTCall updateImage(@NotNull final RESTImageV1 image) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME
                        + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTLanguageImageV1.IMAGEDATABASE64_NAME + "\"}}]}]}";
                restService.updateJSONImage(expand, image);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONImage method
     * @param  image The image to be created
     * @return A RESTCall that can call the REST createJSONImage method
     */
    public static final RESTCall createImage(@NotNull final RESTImageV1 image) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME
                        + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTLanguageImageV1.IMAGEDATABASE64_NAME + "\"}}]}]}";
                restService.createJSONImage(expand, image);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONFilter method
     * @param filter The filter to be created
     * @return A RESTCall that can call the REST createJSONFilter method
     */
    public static final RESTCall createFilter(@NotNull final RESTFilterV1 filter) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.createJSONFilter(FILTER_EXPANSION, filter);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONFilter method
     * @param filter The filter to be updated
     * @return A RESTCall that can call the REST updateJSONFilter method
     */
    public static final RESTCall updateFilter(@NotNull final RESTFilterV1 filter) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.updateJSONFilter(FILTER_EXPANSION, filter);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONFiltersWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONFiltersWithQuery method
     */
    public static final RESTCall getFiltersFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.FILTERS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONFiltersWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONFilter method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONFilter method
     */
    public static final RESTCall getFilter(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONFilter(id, FILTER_EXPANSION);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopicRevision method
     * @param id The entity ID
     * @param revision The entity revision
     * @return A RESTCall that can call the REST getJSONTopicRevision method
     */
    public static final RESTCall getTopicRevision(@NotNull final Integer id, @NotNull final Integer revision) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONTopicRevision(id, revision, TOPIC_EXPANSION_WO_REVISIONS);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopicsWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONTopicsWithQuery method
     */
    public static final RESTCall getTopicsFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.TOPICS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONTopicsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopicsWithQuery method
     * @param queryString The query to use to get the filters
     * @return A RESTCall that can call the REST getJSONTopicsWithQuery method
     */
    public static final RESTCall getTopicsFromQuery(@NotNull final String queryString) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTv1Constants.TOPICS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONTopicsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTopicsWithQuery method
     * @param queryString The query to use to get the filters
     * @return A RESTCall that can call the REST getJSONTopicsWithQuery method
     */
    public static final RESTCall getTopicsFromQueryWithExpandedTags(@NotNull final String queryString) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONTopicsWithQuery(new PathSegmentImpl(queryString), TOPIC_TAG_EXPANSION);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTranslatedTopicsWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONTranslatedTopicsWithQuery method
     */
    public static final RESTCall getTranslatedTopicsFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.TRANSLATEDTOPICS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONTranslatedTopicsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTranslatedTopic method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONTranslatedTopic method
     */
    public static final RESTCall getTranslatedTopic(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONTranslatedTopic(id, TRANSLATED_TOPIC_EXPANSION);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST something method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST something method
     */
    public static final RESTCall getTagsFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.TAGS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONTagsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONTagsWithQuery method
     * @param queryString The query to use to get the filters
     * @return A RESTCall that can call the REST getJSONTagsWithQuery method
     */
    public static final RESTCall getTagsFromQuery(@NotNull final String queryString) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTv1Constants.TAGS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONTagsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONStringConstantsWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONStringConstantsWithQuery method
     */
    public static final RESTCall getStringConstantsFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.STRINGCONSTANTS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONStringConstantsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createStringConstant method
     * @param entity The string constant to create
     * @return A RESTCall that can call the REST createStringConstant method
     */
    public static final RESTCall createStringConstant(@NotNull final RESTStringConstantV1 entity)  {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.createJSONStringConstant("", entity);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONStringConstant method
     * @param entity The String Constant to update
     * @return A RESTCall that can call the REST updateJSONStringConstant method
     */
    public static final RESTCall updateStringConstant(@NotNull final RESTStringConstantV1 entity) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.updateJSONStringConstant("", entity);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONIntegerConstantsWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONIntegerConstantsWithQuery method
     */
    public static final RESTCall getIntegerConstantsFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.INTEGERCONSTANTS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONIntegerConstantsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONIntegerConstant method
     * @param  entity The Integer Constant to create
     * @return A RESTCall that can call the REST createJSONIntegerConstant method
     */
    public static final RESTCall createIntegerConstant(@NotNull final RESTIntegerConstantV1 entity) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.createJSONIntegerConstant("", entity);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONIntegerConstant method
     * @param  entity The Integer Constant to save
     * @return A RESTCall that can call the REST updateJSONIntegerConstant method
     */
    public static final RESTCall updateIntegerConstant(@NotNull final RESTIntegerConstantV1 entity) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.updateJSONIntegerConstant("", entity);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONBlobConstantsWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONBlobConstantsWithQuery method
     */
    public static final RESTCall getBlobConstantsFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.BLOBCONSTANTS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONBlobConstantsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONBlobConstant method
     * @param entity The Blob Constant to create
     * @return A RESTCall that can call the REST createJSONBlobConstant method
     */
    public static final RESTCall createBlobConstant(@NotNull final RESTBlobConstantV1 entity) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.createJSONBlobConstant("", entity);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONBlobConstant method
     * @param entity The Blob Constant to update
     * @return A RESTCall that can call the REST updateJSONBlobConstant method
     */
    public static final RESTCall updateBlobConstant(@NotNull final RESTBlobConstantV1 entity) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.updateJSONBlobConstant("", entity);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONCategory method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONCategory method
     */
    public static final RESTCall getCategory(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
                restService.getJSONCategory(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONCategory method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONCategory method
     */
    public static final RESTCall getUnexpandedCategory(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONCategory(id, "");
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getCategories method
     * @return A RESTCall that can call the REST getCategories method
     */
    public static final RESTCall getCategories() {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{ \"name\": \"" + RESTv1Constants.CATEGORIES_EXPANSION_NAME + "\"}, \"branches\":[" + CATEGORY_EXPANSION
                        + "]}]}";
                restService.getJSONCategories(expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONCategoriesWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONCategoriesWithQuery method
     */
    public static final RESTCall getUnexpandedCategoriesFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                        + ", \"name\": \"" + RESTv1Constants.CATEGORIES_EXPANSION_NAME + "\"}}]}";
                restService.getJSONCategoriesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONCategoriesWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONCategoriesWithQuery method
     */
    public static final RESTCall getCategoriesFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                        + ", \"name\": \"" + RESTv1Constants.CATEGORIES_EXPANSION_NAME + "\"}, \"branches\":[" + CATEGORY_EXPANSION + "]}]}";
                restService.getJSONCategoriesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONProjectsWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONProjectsWithQuery method
     */
    public static final RESTCall getProjectsFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                        + ", \"name\": \"" + RESTv1Constants.PROJECTS_EXPANSION_NAME + "\"}, \"branches\":[" + PROJECT_EXPANSION + "]}]}";
                restService.getJSONProjectsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST something method
     * @return A RESTCall that can call the REST something method
     */
    public static final RESTCall getProjects() {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTv1Constants.PROJECTS_EXPANSION_NAME + "\"}, \"branches\":[" + PROJECT_EXPANSION + "]}]}";
                restService.getJSONProjects(expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONProject method
     * @param  id The entity ID
     * @return A RESTCall that can call the REST getJSONProject method
     */
    public static final RESTCall getProject(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROJECT_EXPANSION + "]}";
                restService.getJSONProject(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getUnexpandedProject method
     * @param  id The entity ID
     * @return A RESTCall that can call the REST getUnexpandedProject method
     */
    public static final RESTCall getUnexpandedProject(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                restService.getJSONProject(id, "");
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONProject method
     * @param  project The project to be saved
     * @return A RESTCall that can call the REST updateJSONProject method
     */
    public static final RESTCall saveProject(@NotNull final RESTProjectV1 project) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROJECT_EXPANSION + "]}";
                restService.updateJSONProject(expand, project);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONProject method
     * @param project The project to be created
     * @return A RESTCall that can call the REST createJSONProject method
     */
    public static final RESTCall createProject(@NotNull final RESTProjectV1 project) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROJECT_EXPANSION + "]}";
                restService.createJSONProject(expand, project);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST something method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST something method
     */
    public static final RESTCall getImagesFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand =
                    "{" +
                        "\"branches\":[" +
                            "{" +
                                "\"trunk\":{\"start\":" + start + ", \"end\":" + end + ",\"name\": \"" + RESTv1Constants.IMAGES_EXPANSION_NAME + "\"}," +
                                "\"branches\":["
                                    + IMAGE_EXPANSION +
                                "]" +
                            "}" +
                        "]" +
                    "}";
                restService.getJSONImagesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONPropertyCategories method
     * @return A RESTCall that can call the REST getJSONPropertyCategories method
     */
    public static final RESTCall getPropertyTagCategories() {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTv1Constants.PROPERTY_CATEGORIES_EXPANSION_NAME + "\"}}]}";
                restService.getJSONPropertyCategories(expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONPropertyTagsWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONPropertyTagsWithQuery method
     */
    public static final RESTCall getPropertyTagsFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\":" + start + ", \"end\":" + end + ",\"name\": \"" + RESTv1Constants.PROPERTYTAGS_EXPANSION_NAME + "\"}}]}";
                restService.getJSONPropertyTagsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONPropertyTag method
     * @param id The entity ID
     * @return A RESTCall that can call the REST getJSONPropertyTag method
     */
    public static final RESTCall getPropertyTag(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROPERTY_TAG_EXPANSION + "]}";
                restService.getJSONPropertyTag(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONPropertyTag method
     * @param propertyTag The property tag to save
     * @return A RESTCall that can call the REST createJSONPropertyTag method
     */
    public static final RESTCall savePropertyTag(@NotNull final RESTPropertyTagV1 propertyTag) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROPERTY_TAG_EXPANSION + "]}";
                restService.updateJSONPropertyTag(expand, propertyTag);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONPropertyTag method
     * @param propertyTag The property tag to create
     * @return A RESTCall that can call the REST createJSONPropertyTag method
     */
    public static final RESTCall createPropertyTag(@NotNull final RESTPropertyTagV1 propertyTag) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROPERTY_TAG_EXPANSION + "]}";
                restService.createJSONPropertyTag(expand, propertyTag);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONPropertyCategoriesWithQuery method
     * @param queryString The query to use to get the filters
     * @param start The start of the results
     * @param end The end of the results
     * @return A RESTCall that can call the REST getJSONPropertyCategoriesWithQuery method
     */
    public static final RESTCall getPropertyCategoriesFromQuery(@NotNull final String queryString, final int start, final int end) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[{\"trunk\":{\"start\":" + start + ", \"end\":" + end + ",\"name\": \"" + RESTv1Constants.PROPERTY_CATEGORIES_EXPANSION_NAME + "\"}}]}";
                restService.getJSONPropertyCategoriesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST getJSONPropertyCategory method
     * @param id The entity id
     * @return A RESTCall that can call the REST getJSONPropertyCategory method
     */
    public static final RESTCall getPropertyCategory(@NotNull final Integer id) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROPERTY_CATEGORY_EXPANSION + "]}";
                restService.getJSONPropertyCategory(id, expand);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST updateJSONPropertyCategory method
     * @param propertyCategory The property tag category to save
     * @return A RESTCall that can call the REST updateJSONPropertyCategory method
     */
    public static final RESTCall savePropertyCategory(@NotNull final RESTPropertyCategoryV1 propertyCategory) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROPERTY_CATEGORY_EXPANSION + "]}";
                restService.updateJSONPropertyCategory(expand, propertyCategory);
            }
        };
    }

    /**
     * Create a RESTCall object to call the REST createJSONPropertyCategory method
     * @param propertyCategory The property tag category to create
     * @return A RESTCall that can call the REST createJSONPropertyCategory method
     */
    public static final RESTCall createPropertyCategory(@NotNull final RESTPropertyCategoryV1 propertyCategory) {
        return new RESTCall() {
            @Override
            public void call(@NotNull final RESTInterfaceV1 restService) {
                final String expand = "{\"branches\":[" + PROPERTY_CATEGORY_EXPANSION + "]}";
                restService.createJSONPropertyCategory(expand, propertyCategory);
            }
        };
    }

    /**
     * Retrieve a list of locales from the server.
     *
     * @param loadedCallback The callback to call when the locales are loaded
     */
    public static final void populateLocales(@NotNull final StringListLoaded loadedCallback, @NotNull final BaseTemplateViewInterface display) {
        FailOverRESTCall.performRESTCall(
            getStringConstant(ServiceConstants.LOCALE_STRING_CONSTANT),
            new RESTCallBack<RESTStringConstantV1>() {
                @Override
                public void success(@NotNull final RESTStringConstantV1 value) {
                    final List<String> locales = new LinkedList<String>(Arrays.asList(value.getValue()
                            .replaceAll(Constants.CARRIAGE_RETURN_AND_LINE_BREAK_ESCAPED, "").replaceAll(Constants.LINE_BREAK_ESCAPED, "")
                            .replaceAll(" ", "").split(Constants.COMMA)));

                    /* Clean the list */
                    while (locales.contains("")) {
                        locales.remove("");
                    }

                    Collections.sort(locales);

                    loadedCallback.stringListLoaded(locales);
                }
            },
            display
        );

    }

    /**
     * Load the default locale
     * @param loadedCallback The callback to call when the default locale is loaded
     */
    public static void loadDefaultLocale(@NotNull final StringLoaded loadedCallback, @NotNull final BaseTemplateViewInterface display) {
        FailOverRESTCall.performRESTCall(
            getStringConstant(ServiceConstants.DEFAULT_LOCALE_ID),
            new RESTCallBack<RESTStringConstantV1>() {
                @Override
                public void success(@NotNull final RESTStringConstantV1 value) {
                    loadedCallback.stringLoaded(value.getValue());
                }
            },
            display
        );
    }
}
