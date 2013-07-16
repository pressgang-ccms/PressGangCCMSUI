package org.jboss.pressgang.ccms.ui.client.local.data;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import edu.ycp.cs.dh.acegwt.client.tagdb.GetRESTServerCallback;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentTopicV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jetbrains.annotations.NotNull;
import edu.ycp.cs.dh.acegwt.client.tagdb.TagDB;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Loads the topics that provide style information for tags.
 */
public class TagDBLoader {
    private final TagDB tagDB = new TagDB(new GetRESTServerCallback() {
        @Override
        public String getBaseRESTURL() {
            return ServerDetails.getSavedServer().getRestEndpoint();
        }
    });

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TagDBLoader.class.getName());

    @Inject
    private FailOverRESTCall failOverRESTCall;

    public TagDBLoader() {
        try {
            LOGGER.log(Level.INFO, "ENTER TagDBLoader()");


            final String query = Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.PROPERTY_TAG_EXISTS + ServiceConstants.TAG_STYLE_PROPERTY_TAG + "=true";
            failOverRESTCall.performRESTCall(
                    FailOverRESTCallDatabase.getTopicsWithPropertiesFromQuery(query),
                    new RESTCallBack<RESTTopicCollectionV1>() {
                        public void success(@NotNull final RESTTopicCollectionV1 value) {
                            final JSONObject jsonObject = new JSONObject();
                            for (final RESTTopicCollectionItemV1 restTopicV1 : value.getItems())  {
                                final RESTAssignedPropertyTagV1 property = ComponentTopicV1.returnProperty(restTopicV1.getItem(), ServiceConstants.TAG_STYLE_PROPERTY_TAG);
                                jsonObject.put(property.getValue(), new JSONNumber(restTopicV1.getItem().getId()));
                            }
                            tagDB.setLoaded(true);
                        }
                    },
                    null,
                    true
            );
        } finally {
            LOGGER.log(Level.INFO, "EXIT TagDBLoader()");
        }
    }

    public TagDB getTagDB() {
        return tagDB;
    }
}
