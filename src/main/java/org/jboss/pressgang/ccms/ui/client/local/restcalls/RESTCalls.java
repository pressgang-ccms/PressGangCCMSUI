package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.PathSegmentImpl;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.collections.*;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.constants.RESTv1Constants;
import org.jboss.pressgang.ccms.rest.v1.entities.*;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class provides a standard way to call the REST server and respond to the various success and failure paths.
 *
 * @author Matthew Casperson
 */
public final class RESTCalls {
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
     * A topic with expanded bugs
     */
    private static final String TOPIC_BUGS_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTTopicV1.BUGZILLABUGS_NAME + "\"}}";
    /**
     * A topic with expanded tags
     */
    private static final String TOPIC_TAGS_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTTopicV1.TAGS_NAME
            + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTTagV1.PROJECTS_NAME + "\"}},{\"trunk\":{\"name\":\""
            + RESTTagV1.CATEGORIES_NAME + "\"}}]}";
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
     * The required expansion details for the property tags.
     */
    private static final String PROPERTY_TAG_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTPropertyTagV1.PROPERTY_CATEGORIES_NAME + "\"}}";
    /**
     * The required expansion details for the property tags.
     */
    private static final String PROPERTY_CATEGORY_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTPropertyCategoryV1.PROPERTY_TAGS_NAME + "\"}}";
    /**
     * The required expansion details for the projects.
     */
    private static final String PROJECT_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTProjectV1.TAGS_NAME + "\"}}";
    /**
     * The required expansion details for the content specs.
     */
    private static final String CONTENT_SPEC_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTContentSpecV1.CHILDREN_NAME + "\"}}, " +
            "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}";
    /**
     * The required expansion details for a topic. This is used when loading a topic for the first time
     */
    private static final String TOPIC_EXPANSION_WO_REVISIONS =
            "{\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.SOURCE_URLS_NAME + "\"}}" +
                    "]}";
    /**
     * The required expansion details for a topic. The revisions are required so we can check to see if
     * the last revision was the one we edited. If not, there was a conflicting save.
     *
     * We only need the last 2 revisions to check for save conflicts.
     */
    private static final String TOPIC_EXPANSION =
            "{\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.SOURCE_URLS_NAME + "\"}}," +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.REVISIONS_NAME + "\", \"start\": 0, \"end\": 2}}" +
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
     * All REST calls follow a similar pattern: Before it starts An Exception may be thrown The call succeeds or The call fails
     * <p/>
     * This interface defines callbacks for these events.
     *
     * @param <T> The type that is returned by the REST call
     * @author Matthew Casperson
     */
    public interface RESTCallback<T> {

        /**
         * Called before the REST methods is called
         */
        void begin();

        /**
         * Called if an exception is thrown when attempting to call the REST service
         *
         * @param e The exception that was thrown
         */
        void generalException(final Exception e);

        /**
         * Called if the REST call was successful
         *
         * @param retValue The value returned from the REST service
         */
        void success(final T retValue);

        /**
         * Called if the REST call was unsuccessful
         *
         * @param message   The message that accompanied the failure
         * @param throwable The exception that accompanied the failure
         */
        void failed(final Message message, final Throwable throwable);
    }

    public interface RestMethodCaller {
        void call() throws Exception;
    }

    private RESTCalls() {

    }

    @NotNull
    private static <T> RemoteCallback<T> constructSuccessCallback(@NotNull final RESTCallback<T> callback) {
        @NotNull final RemoteCallback<T> successCallback = new RemoteCallback<T>() {
            @Override
            public void callback(final T retValue) {
                callback.success(retValue);
            }
        };
        return successCallback;
    }

    @NotNull
    private static <T> ErrorCallback constructErrorCallback(@NotNull final RESTCallback<T> callback) {
        @NotNull final ErrorCallback errorCallback = new ErrorCallback() {
            @Override
            public boolean error(final Message message, final Throwable throwable) {
                callback.failed(message, throwable);
                return true;
            }
        };
        return errorCallback;
    }

    private static <T> RESTInterfaceV1 createRestMethod(@NotNull final RESTCallback<T> callback) {
        return RestClient.create(RESTInterfaceV1.class, constructSuccessCallback(callback), constructErrorCallback(callback));
    }

    private static <T> void doRestCall(@NotNull final RESTCallback<T> callback, @NotNull final RestMethodCaller methodCaller) {
        try {
            callback.begin();
            methodCaller.call();
        } catch (@NotNull final Exception e) {
            callback.generalException(e);
        }
    }

    public static void saveTag(@NotNull final RESTCallback<RESTTagV1> callback, @NotNull final RESTTagV1 tag) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONTag(expand, tag);
            }
        });
    }

    public static void createTag(@NotNull final RESTCallback<RESTTagV1> callback, @NotNull final RESTTagV1 tag) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONTag(expand, tag);
            }
        });
    }

    public static void saveCategory(@NotNull final RESTCallback<RESTCategoryV1> callback, @NotNull final RESTCategoryV1 category) {
        @NotNull final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONCategory(expand, category);
            }
        });
    }

    public static void createCategory(@NotNull final RESTCallback<RESTCategoryV1> callback, @NotNull final RESTCategoryV1 category) {
        @NotNull final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONCategory(expand, category);
            }
        });
    }

    public static void updateCategories(@NotNull final RESTCallback<RESTCategoryCollectionV1> callback,
                                        final RESTCategoryCollectionV1 categories) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONCategories("", categories);
            }
        });
    }

    public static void saveTopic(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final RESTTopicV1 topic) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONTopic(TOPIC_EXPANSION, topic);
            }
        });
    }

    public static void saveTopic(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final RESTTopicV1 topic, @NotNull final String message,
                                 @NotNull final Integer flag, @NotNull final String userId) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONTopic(TOPIC_EXPANSION, topic, message, flag, userId);
            }
        });
    }

    public static void createTopic(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final RESTTopicV1 topic) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONTopic(TOPIC_EXPANSION, topic);
            }
        });
    }

    public static void createTopic(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final RESTTopicV1 topic, @NotNull final String message,
                                   @NotNull final Integer flag, @NotNull final String userId) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONTopic(TOPIC_EXPANSION, topic, message, flag, userId);
            }
        });
    }

    public static void getStringConstant(@NotNull final RESTCalls.RESTCallback<RESTStringConstantV1> callback, final int id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONStringConstant(id, "");
            }
        });
    }

    public static void getPropertyTags(@NotNull final RESTCalls.RESTCallback<RESTPropertyTagCollectionV1> callback) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"name\":\"" + RESTv1Constants.PROPERTYTAGS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONPropertyTags(expand);
            }
        });
    }

    public static void getTags(@NotNull final RESTCalls.RESTCallback<RESTTagCollectionV1> callback) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[{\"branches\":[" + TAG_EXPANSION + "],\"trunk\":{\"name\":\"tags\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTags(expand);
            }
        });
    }

    public static void getTag(@NotNull final RESTCallback<RESTTagV1> callback, final Integer id) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTag(id, expand);
            }
        });
    }

    public static void getUnexpandedTag(@NotNull final RESTCallback<RESTTagV1> callback, @NotNull final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTag(id, "");
            }
        });
    }

    public static void getTopicWithRevisions(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final Integer id, final int start, final int end) {
        final String revisionExpand =
                "{\"branches\":[" +
                    "{\"trunk\":{\"name\": \"" + RESTTopicV1.REVISIONS_NAME + "\", \"start\":" + start + ", \"end\":" + end + "}," +
                    "\"branches\":[" +
                        "{\"trunk\":{\"name\": \"" + RESTTopicV1.LOG_DETAILS_NAME + "\"}}," +
                        "{\"trunk\":{\"name\": \"" + RESTTopicV1.PROPERTIES_NAME + "\"}}," +
                        "{\"trunk\":{\"name\": \"" + RESTTopicV1.SOURCE_URLS_NAME + "\"}}" +
                    "]}" +
                "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, revisionExpand);
            }
        });
    }

    public static void getTopicWithRevisions(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final Integer id) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TOPIC_REVISIONS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, expand);
            }
        });
    }

    public static void getTopicWithBugs(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final Integer id) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TOPIC_BUGS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, expand);
            }
        });
    }

    public static void getTopicRevisionWithBugs(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final Integer id, @NotNull final Integer revision) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TOPIC_BUGS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopicRevision(id, revision, expand);
            }
        });
    }

    public static void getTopicWithTags(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final Integer id) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TOPIC_TAGS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, expand);
            }
        });
    }

    public static void getTranslatedTopicWithTags(@NotNull final RESTCallback<RESTTranslatedTopicV1> callback, @NotNull final Integer id) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TOPIC_TAGS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTranslatedTopic(id, expand);
            }
        });
    }

    public static void getTopicRevisionWithTags(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final Integer id, @NotNull final Integer revision) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[" + TOPIC_TAGS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopicRevision(id, revision, expand);
            }
        });
    }

    public static void getTopic(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, TOPIC_EXPANSION_WO_REVISIONS);
            }
        });
    }

    public static void getImage(@NotNull final RESTCallback<RESTImageV1> callback, @NotNull final Integer id) {
        /* Expand the language images */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME
                + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTLanguageImageV1.IMAGEDATABASE64_NAME + "\"}}]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONImage(id, expand);
            }
        });
    }

    public static void getImageWithoutLanguageImages(@NotNull final RESTCallback<RESTImageV1> callback, @NotNull final Integer id) {
        /* Expand the language images */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONImage(id, expand);
            }
        });
    }

    public static void updateImage(@NotNull final RESTCallback<RESTImageV1> callback, @NotNull final RESTImageV1 image) {
        /* Expand the language images */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME
                + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTLanguageImageV1.IMAGEDATABASE64_NAME + "\"}}]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONImage(expand, image);
            }
        });
    }

    public static void createImage(@NotNull final RESTCallback<RESTImageV1> callback, @NotNull final RESTImageV1 image) {
        /* Expand the language images */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME
                + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTLanguageImageV1.IMAGEDATABASE64_NAME + "\"}}]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONImage(expand, image);
            }
        });
    }

    public static void createFilter(@NotNull final RESTCallback<RESTFilterV1> callback, @NotNull final RESTFilterV1 filter) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONFilter(FILTER_EXPANSION, filter);
            }
        });
    }

    public static void updateFilter(@NotNull final RESTCallback<RESTFilterV1> callback, @NotNull final RESTFilterV1 filter) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONFilter(FILTER_EXPANSION, filter);
            }
        });
    }

    public static void getFiltersFromQuery(@NotNull final RESTCallback<RESTFilterCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.FILTERS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONFiltersWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getFilter(@NotNull final RESTCallback<RESTFilterV1> callback, @NotNull final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONFilter(id, FILTER_EXPANSION);
            }
        });
    }

    public static void getTopicRevision(@NotNull final RESTCallback<RESTTopicV1> callback, @NotNull final Integer id, @NotNull final Integer revision) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopicRevision(id, revision, TOPIC_EXPANSION_WO_REVISIONS);
            }
        });
    }

    public static void getTopicsFromQuery(@NotNull final RESTCallback<RESTTopicCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.TOPICS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopicsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getTopicsFromQuery(@NotNull final RESTCallback<RESTTopicCollectionV1> callback, @NotNull final String queryString) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTv1Constants.TOPICS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopicsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getTopicsFromQueryWithExpandedTags(@NotNull final RESTCallback<RESTTopicCollectionV1> callback, @NotNull final String queryString) {
        /* Expand the categories and projects in the tags */
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopicsWithQuery(new PathSegmentImpl(queryString), TOPIC_TAG_EXPANSION);
            }
        });
    }

    public static void getTranslatedTopicsFromQuery(@NotNull final RESTCallback<RESTTranslatedTopicCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.TRANSLATEDTOPICS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTranslatedTopicsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getTranslatedTopic(@NotNull final RESTCallback<RESTTranslatedTopicV1> callback, @NotNull final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTranslatedTopic(id, TOPIC_EXPANSION);
            }
        });
    }

    public static void getTagsFromQuery(@NotNull final RESTCallback<RESTTagCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.TAGS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTagsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getTagsFromQuery(@NotNull final RESTCallback<RESTTagCollectionV1> callback, @NotNull final String queryString) {
        /* Expand the categories and projects in the tags */
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTv1Constants.TAGS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTagsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getStringConstantsFromQuery(@NotNull final RESTCallback<RESTStringConstantCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.STRINGCONSTANTS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONStringConstantsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void createStringConstant(@NotNull final RESTCallback<RESTStringConstantV1> callback, @NotNull final RESTStringConstantV1 entity) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONStringConstant("", entity);
            }
        });
    }

    public static void updateStringConstant(@NotNull final RESTCallback<RESTStringConstantV1> callback, @NotNull final RESTStringConstantV1 entity) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONStringConstant("", entity);
            }
        });
    }

    public static void getIntegerConstantsFromQuery(@NotNull final RESTCallback<RESTIntegerConstantCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.INTEGERCONSTANTS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONIntegerConstantsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void createIntegerConstant(@NotNull final RESTCallback<RESTIntegerConstantV1> callback, @NotNull final RESTIntegerConstantV1 entity) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONIntegerConstant("", entity);
            }
        });
    }

    public static void updateIntegerConstant(@NotNull final RESTCallback<RESTIntegerConstantV1> callback, @NotNull final RESTIntegerConstantV1 entity) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONIntegerConstant("", entity);
            }
        });
    }

    public static void getBlobConstantsFromQuery(@NotNull final RESTCallback<RESTBlobConstantCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        @NotNull final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.BLOBCONSTANTS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONBlobConstantsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void createBlobConstant(@NotNull final RESTCallback<RESTBlobConstantV1> callback, @NotNull final RESTBlobConstantV1 entity) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONBlobConstant("", entity);
            }
        });
    }

    public static void updateBlobConstant(@NotNull final RESTCallback<RESTBlobConstantV1> callback, @NotNull final RESTBlobConstantV1 entity) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONBlobConstant("", entity);
            }
        });
    }

    public static void getContentSpecText(@NotNull final RESTCallback<String> callback, @NotNull final Integer id) {
        /* Expand the categories and projects in the tags */
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getTEXTContentSpec(id);
            }
        });
    }

    public static void updateContentSpecText(@NotNull final RESTCallback<String> callback, @NotNull final Integer contentSpecID, @NotNull final String contentSpecText, @NotNull final String message,
                                             @NotNull final Integer flag, @NotNull final String userId) {
        /* Expand the categories and projects in the tags */
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateTEXTContentSpec(contentSpecID, contentSpecText, message, flag, userId);
            }
        });
    }

    public static void getContentSpec(@NotNull final RESTCallback<RESTContentSpecV1> callback, @NotNull final Integer id) {
        /* Expand the categories and projects in the tags */
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONContentSpec(id, "");
            }
        });
    }

    public static void getContentSpecsFromQuery(@NotNull final RESTCallback<RESTContentSpecCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"name\": \"" + RESTv1Constants.CONTENT_SPEC_EXPANSION_NAME + "\"}, " +
                "\"branches\":[" + CONTENT_SPEC_EXPANSION + "]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONContentSpecsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getCategory(@NotNull final RESTCallback<RESTCategoryV1> callback, @NotNull final Integer id) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategory(id, expand);
            }
        });
    }

    public static void getUnexpandedCategory(@NotNull final RESTCallback<RESTCategoryV1> callback, @NotNull final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategory(id, "");
            }
        });
    }

    public static void getCategories(@NotNull final RESTCallback<RESTCategoryCollectionV1> callback) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{ \"name\": \"" + RESTv1Constants.CATEGORIES_EXPANSION_NAME + "\"}, \"branches\":[" + CATEGORY_EXPANSION
                + "]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategories(expand);
            }
        });
    }

    public static void getUnexpandedCategoriesFromQuery(@NotNull final RESTCallback<RESTCategoryCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                + ", \"name\": \"" + RESTv1Constants.CATEGORIES_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategoriesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getCategoriesFromQuery(@NotNull final RESTCallback<RESTCategoryCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                + ", \"name\": \"" + RESTv1Constants.CATEGORIES_EXPANSION_NAME + "\"}, \"branches\":[" + CATEGORY_EXPANSION + "]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategoriesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getProjectsFromQuery(@NotNull final RESTCallback<RESTProjectCollectionV1> callback, @NotNull final String queryString,
                                            final int start, final int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                + ", \"name\": \"" + RESTv1Constants.PROJECTS_EXPANSION_NAME + "\"}, \"branches\":[" + PROJECT_EXPANSION + "]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONProjectsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getProjects(@NotNull final RESTCallback<RESTProjectCollectionV1> callback) {
        final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTv1Constants.PROJECTS_EXPANSION_NAME + "\"}, \"branches\":[" + PROJECT_EXPANSION + "]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONProjects(expand);
            }
        });
    }

    public static void getProject(@NotNull final RESTCallback<RESTProjectV1> callback, @NotNull final Integer id) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + PROJECT_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONProject(id, expand);
            }
        });
    }

    public static void getUnexpandedProject(@NotNull final RESTCallback<RESTProjectV1> callback, @NotNull final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONProject(id, "");
            }
        });
    }

    public static void saveProject(@NotNull final RESTCallback<RESTProjectV1> callback, @NotNull final RESTProjectV1 category) {
        final String expand = "{\"branches\":[" + PROJECT_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONProject(expand, category);
            }
        });
    }

    public static void createProject(@NotNull final RESTCallback<RESTProjectV1> callback, @NotNull final RESTProjectV1 category) {
        final String expand = "{\"branches\":[" + PROJECT_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONProject(expand, category);
            }
        });
    }

    public static void getImagesFromQuery(@NotNull final RESTCallback<RESTImageCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\":" + start + ", \"end\":" + end + ",\"name\": \"" + RESTv1Constants.IMAGES_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONImagesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    /**
     * Get a collection of property tags from the rest server.
     * @param callback The callback to process the results from the REST server
     */
    public static void getPropertyTagCategories(@NotNull final RESTCallback<RESTPropertyCategoryCollectionV1> callback) {
        final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTv1Constants.PROPERTY_CATEGORIES_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONPropertyCategories(expand);
            }
        });
    }

    /**
     * Get a collection of property tags from the rest server.
     * @param callback The callback to process the results from the REST server
     * @param queryString The query passed to the REST endpoint
     * @param start The start of the range of returned entities
     * @param end The end of the range of returned entities
     */
    public static void getPropertyTagsFromQuery(@NotNull final RESTCallback<RESTPropertyTagCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        final String expand = "{\"branches\":[{\"trunk\":{\"start\":" + start + ", \"end\":" + end + ",\"name\": \"" + RESTv1Constants.PROPERTYTAGS_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONPropertyTagsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getPropertyTag(@NotNull final RESTCallback<RESTPropertyTagV1> callback, @NotNull final Integer id) {
        final String expand = "{\"branches\":[" + PROPERTY_TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONPropertyTag(id, expand);
            }
        });
    }

    public static void savePropertyTag(@NotNull final RESTCallback<RESTPropertyTagV1> callback, @NotNull final RESTPropertyTagV1 propertyTag) {
        final String expand = "{\"branches\":[" + PROPERTY_TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONPropertyTag(expand, propertyTag);
            }
        });
    }

    public static void createPropertyTag(@NotNull final RESTCallback<RESTPropertyTagV1> callback, @NotNull final RESTPropertyTagV1 propertyTag) {
        final String expand = "{\"branches\":[" + PROPERTY_TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONPropertyTag(expand, propertyTag);
            }
        });
    }

    /**
     * Get a collection of property categories from the rest server.
     * @param callback The callback to process the results from the REST server
     * @param queryString The query passed to the REST endpoint
     * @param start The start of the range of returned entities
     * @param end The end of the range of returned entities
     */
    public static void getPropertyCategoriesFromQuery(@NotNull final RESTCallback<RESTPropertyCategoryCollectionV1> callback, @NotNull final String queryString, final int start, final int end) {
        final String expand = "{\"branches\":[{\"trunk\":{\"start\":" + start + ", \"end\":" + end + ",\"name\": \"" + RESTv1Constants.PROPERTY_CATEGORIES_EXPANSION_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONPropertyCategoriesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    public static void getPropertyCategory(@NotNull final RESTCallback<RESTPropertyCategoryV1> callback, @NotNull final Integer id) {
        final String expand = "{\"branches\":[" + PROPERTY_CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONPropertyCategory(id, expand);
            }
        });
    }

    public static void savePropertyCategory(@NotNull final RESTCallback<RESTPropertyCategoryV1> callback, @NotNull final RESTPropertyCategoryV1 propertyCategory) {
        @NotNull final String expand = "{\"branches\":[" + PROPERTY_CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONPropertyCategory(expand, propertyCategory);
            }
        });
    }

    public static void createPropertyCategory(@NotNull final RESTCallback<RESTPropertyCategoryV1> callback, @NotNull final RESTPropertyCategoryV1 propertyCategory) {
        @NotNull final String expand = "{\"branches\":[" + PROPERTY_CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONPropertyCategory(expand, propertyCategory);
            }
        });
    }

    /**
     * Retrieve a list of locales from the server.
     *
     * @param loadedCallback The callback to call when the locales are loaded
     */
    public static void populateLocales(@NotNull final StringListLoaded loadedCallback, @NotNull final BaseTemplateViewInterface display) {

            @NotNull final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                    display, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                @Override
                public void doSuccessAction(@NotNull final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                            /* Get the list of locales from the StringConstant */
                    @NotNull final List<String> locales = new LinkedList<String>(Arrays.asList(retValue.getValue()
                            .replaceAll(Constants.CARRIAGE_RETURN_AND_LINE_BREAK_ESCAPED, "").replaceAll(Constants.LINE_BREAK_ESCAPED, "")
                            .replaceAll(" ", "").split(Constants.COMMA)));

                            /* Clean the list */
                    while (locales.contains("")) {
                        locales.remove("");
                    }

                    Collections.sort(locales);

                    loadedCallback.stringListLoaded(locales);
                }
            });

            RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRING_CONSTANT);
    }
}
