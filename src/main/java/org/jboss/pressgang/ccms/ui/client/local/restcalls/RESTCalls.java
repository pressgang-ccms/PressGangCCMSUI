package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.PathSegmentImpl;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;

/**
 * This class provides a standard way to call the REST server and respond to the various success and failure paths.
 *
 * @author Matthew Casperson
 */
public final class RESTCalls {
    /**
     * The required expansion details for the topics.
     */
    private static final String TOPIC_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTTopicV1.BUGZILLABUGS_NAME
            + "\"}}, {\"trunk\":{\"name\": \"" + RESTTopicV1.REVISIONS_NAME + "\"},\"branches\":[{\"trunk\":{\"name\": \""
            + RESTTopicV1.LOG_DETAILS_NAME + "\"}}]}, {\"trunk\":{\"name\": \"" + RESTTopicV1.TAGS_NAME
            + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTTagV1.PROJECTS_NAME + "\"}},{\"trunk\":{\"name\":\""
            + RESTTagV1.CATEGORIES_NAME + "\"}}]}";
    /**
     * A topic with expanded revisions
     */
    private static final String TOPIC_REVISIONS_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTTopicV1.REVISIONS_NAME + "\"}}";
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
    private static final String TAG_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTTagV1.PROJECTS_NAME
            + "\"}}, {\"trunk\":{\"name\": \"" + RESTTagV1.CATEGORIES_NAME + "\"}}";
    /**
     * The required expansion details for the categories.
     */
    private static final String CATEGORY_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTCategoryV1.TAGS_NAME + "\"}}";
    /**
     * The required expansion details for the projects.
     */
    private static final String PROJECT_EXPANSION = "{\"trunk\":{\"name\": \"" + RESTProjectV1.TAGS_NAME + "\"}}";

    /**
     * All REST calls follow a similar pattern:
     * Before it starts
     * An Exception may be thrown
     * The call succeeds or
     * The call fails
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
         * @param e
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
         */
        void failed(final Message message, final Throwable throwable);
    }

    public interface RestMethodCaller {
        public void call() throws Exception;
    }

    private RESTCalls() {

    }

    private static <T> RemoteCallback<T> constructSuccessCallback(final RESTCallback<T> callback) {
        final RemoteCallback<T> successCallback = new RemoteCallback<T>() {
            @Override
            public void callback(final T retValue) {
                callback.success(retValue);
            }
        };
        return successCallback;
    }

    private static <T> ErrorCallback constructErrorCallback(final RESTCallback<T> callback) {
        final ErrorCallback errorCallback = new ErrorCallback() {
            @Override
            public boolean error(final Message message, final Throwable throwable) {
                callback.failed(message, throwable);
                return true;
            }
        };
        return errorCallback;
    }

    private static <T> RESTInterfaceV1 createRestMethod(RESTCallback<T> callback) {
        return RestClient.create(RESTInterfaceV1.class, constructSuccessCallback(callback),
                constructErrorCallback(callback));
    }

    private static <T> void doRestCall(final RESTCallback<T> callback, final RestMethodCaller methodCaller) {
        try {
            callback.begin();
            methodCaller.call();
        } catch (final Exception e) {
            callback.generalException(e);
        }
    }

    public static void saveTag(final RESTCallback<RESTTagV1> callback, final RESTTagV1 tag) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONTag(expand, tag);
            }
        });
    }
    
    public static void createTag(final RESTCallback<RESTTagV1> callback, final RESTTagV1 tag) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONTag(expand, tag);
            }
        });
    }
    
    public static void saveCategory(final RESTCallback<RESTCategoryV1> callback, final RESTCategoryV1 category) {
        final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONCategory(expand, category);
            }
        });
    }
    
    public static void createCategory(final RESTCallback<RESTCategoryV1> callback, final RESTCategoryV1 category) {
        final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONCategory(expand, category);
            }
        });
    }

    static public void saveCategories(final RESTCallback<RESTCategoryCollectionV1> callback, final RESTCategoryCollectionV1 categories) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONCategories("", categories);
            }
        });
    }

    static public void saveTopic(final RESTCallback<RESTTopicV1> callback, final RESTTopicV1 topic) {
        // final String expand = "{\"branches\":[" + TOPIC_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONTopic("", topic);
            }
        });
    }
    
    static public void createTopic(final RESTCallback<RESTTopicV1> callback, final RESTTopicV1 topic) {
        // final String expand = "{\"branches\":[" + TOPIC_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONTopic("", topic);
            }
        });
    }

    static public void getStringConstant(final RESTCalls.RESTCallback<RESTStringConstantV1> callback, final int id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONStringConstant(id, "");
            }
        });
    }

    static public void getTags(final RESTCalls.RESTCallback<RESTTagCollectionV1> callback) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"branches\":[" + TAG_EXPANSION + "],\"trunk\":{\"name\":\"tags\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTags(expand);
            }
        });
    }

    static public void getTag(final RESTCallback<RESTTagV1> callback, final Integer id) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + TAG_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTag(id, expand);
            }
        });
    }

    static public void getUnexpandedTag(final RESTCallback<RESTTagV1> callback, final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTag(id, "");
            }
        });
    }

    static public void getTopicWithRevisions(final RESTCallback<RESTTopicV1> callback, final Integer id) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + TOPIC_REVISIONS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, expand);
            }
        });
    }

    static public void getTopicWithBugs(final RESTCallback<RESTTopicV1> callback, final Integer id) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + TOPIC_BUGS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, expand);
            }
        });
    }

    static public void getTopicWithTags(final RESTCallback<RESTTopicV1> callback, final Integer id) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + TOPIC_TAGS_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, expand);
            }
        });
    }

    static public void getTopic(final RESTCallback<RESTTopicV1> callback, final Integer id) {
        //final String expand = "{\"branches\":[" + TOPIC_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopic(id, "");
            }
        });
    }

    static public void getImage(final RESTCallback<RESTImageV1> callback, final Integer id) {
        /* Expand the language images */
        final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME
                + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTLanguageImageV1.IMAGEDATABASE64_NAME + "\"}}]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONImage(id, expand);
            }
        });
    }

    static public void getImageWithoutLanguageImages(final RESTCallback<RESTImageV1> callback, final Integer id) {
        /* Expand the language images */
        final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME + "\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONImage(id, expand);
            }
        });
    }

    static public void saveImage(final RESTCallback<RESTImageV1> callback, final RESTImageV1 image) {
        /* Expand the language images */
        final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"" + RESTImageV1.LANGUAGEIMAGES_NAME
                + "\"},\"branches\":[{\"trunk\":{\"name\": \"" + RESTLanguageImageV1.IMAGEDATABASE64_NAME + "\"}}]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONImage(expand, image);
            }
        });
    }

    static public void getTopicRevision(final RESTCallback<RESTTopicV1> callback, final Integer id, final Integer revision) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + TOPIC_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopicRevision(id, revision, expand);
            }
        });
    }

    static public void getTopicsFromQuery(final RESTCallback<RESTTopicCollectionV1> callback, final String queryString,
                                          int start, int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                + ", \"name\": \"topics\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTopicsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    static public void getTagsFromQuery(final RESTCallback<RESTTagCollectionV1> callback, final String queryString, int start,
                                        int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                + ", \"name\": \"tags\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONTagsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    static public void getCategory(final RESTCallback<RESTCategoryV1> callback, final Integer id) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[" + CATEGORY_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategory(id, expand);
            }
        });
    }

    static public void getUnexpandedCategory(final RESTCallback<RESTCategoryV1> callback, final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategory(id, "");
            }
        });
    }

    static public void getCategories(final RESTCallback<RESTCategoryCollectionV1> callback) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{ \"name\": \"categories\"}, \"branches\":[" + CATEGORY_EXPANSION
                + "]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategories(expand);
            }
        });
    }

    static public void getUnexpandedCategoriesFromQuery(final RESTCallback<RESTCategoryCollectionV1> callback, final String queryString,
                                                        int start, int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                + ", \"name\": \"categories\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategoriesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    static public void getCategoriesFromQuery(final RESTCallback<RESTCategoryCollectionV1> callback, final String queryString,
                                              int start, int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                + ", \"name\": \"categories\"}, \"branches\":[" + CATEGORY_EXPANSION + "]}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONCategoriesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    static public void getProjectsFromQuery(final RESTCallback<RESTProjectCollectionV1> callback, final String queryString,
                                            int start, int end) {
        /* Expand the categories and projects in the tags */
        final String tagsExpand = "\"branches\":[{\"trunk\":{\"name\": \"" + RESTProjectV1.TAGS_NAME + "\"}}]";
        final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end
                + ", \"name\": \"projects\"}, " + tagsExpand + "}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONProjectsWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }

    static public void getProjects(final RESTCallback<RESTProjectCollectionV1> callback) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"name\": \"projects\"}, " + PROJECT_EXPANSION + "}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONProjects(expand);
            }
        });
    }
    
    static public void getUnexpandedProject(final RESTCallback<RESTProjectV1> callback, final Integer id) {
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONProject(id, "");
            }
        });
    }
    
    public static void saveProject(final RESTCallback<RESTProjectV1> callback, final RESTProjectV1 category) {
        final String expand = "{\"branches\":[" + PROJECT_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).updateJSONProject(expand, category);
            }
        });
    }
    
    public static void createProject(final RESTCallback<RESTProjectV1> callback, final RESTProjectV1 category) {
        final String expand = "{\"branches\":[" + PROJECT_EXPANSION + "]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).createJSONProject(expand, category);
            }
        });
    }

    static public void getImagesFromQuery(final RESTCallback<RESTImageCollectionV1> callback, final String queryString,
                                          int start, int end) {
        /* Expand the categories and projects in the tags */
        final String expand = "{\"branches\":[{\"trunk\":{\"start\":" + start + ", \"end\":" + end
                + ",\"name\": \"images\"}}]}";
        doRestCall(callback, new RestMethodCaller() {
            @Override
            public void call() throws Exception {
                createRestMethod(callback).getJSONImagesWithQuery(new PathSegmentImpl(queryString), expand);
            }
        });
    }
}
