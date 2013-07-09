package org.jboss.pressgang.ccms.ui.client.local.restcalls;

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


}
