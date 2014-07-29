/*
  Copyright 2011-2014 Red Hat

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

package org.jboss.pressgang.ccms.ui.client.local.data;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.json.client.JSONNumber;
import edu.ycp.cs.dh.acegwt.client.tagdb.XMLElementDB;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EntityUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * Loads the topics that provide style information for tags.
 */
public class XMLElementDBLoader {
    private final XMLElementDB xmlElementDB = new XMLElementDB();

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(XMLElementDBLoader.class.getName());

    @Inject
    private FailOverRESTCall failOverRESTCall;

    public XMLElementDBLoader() {
        LOGGER.log(Level.INFO, "ENTER XMLElementDBLoader()");
    }

    @PostConstruct
    public void postConstruct() {
        try {
            LOGGER.log(Level.INFO, "ENTER XMLElementDBLoader.postConstruct()");

            ServerDetails.getSavedServer(new ServerDetailsCallback() {
                @Override
                public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                    xmlElementDB.setRestEndpoint(serverDetails.getRestEndpoint());

                    FailOverRESTCallDatabase.getServerSettings(new ServerSettingsCallback() {
                        @Override
                        public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                            loadPropertyTagDB(serverSettings);
                        }
                    }, null, failOverRESTCall);
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT XMLElementDBLoader()");
        }
    }

    protected void loadPropertyTagDB(final RESTServerSettingsV1 serverSettings) {
        final String query = Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.PROPERTY_TAG_EXISTS + serverSettings.getEntities().getTagStylePropertyTagId() + "=true";
        failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.getTopicsWithPropertiesFromQuery(query),
                new RESTCallBack<RESTTopicCollectionV1>() {
                    public void success(@NotNull final RESTTopicCollectionV1 value) {
                        for (final RESTTopicCollectionItemV1 restTopicV1 : value.getItems())  {
                            final List<RESTAssignedPropertyTagV1> properties = EntityUtilities.returnProperties(
                                    restTopicV1.getItem(), serverSettings.getEntities().getTagStylePropertyTagId());
                            for (final RESTAssignedPropertyTagV1 property : properties) {
                                if (property.getValue() != null) {
                                    xmlElementDB.getDatabase().put(property.getValue(), new JSONNumber(restTopicV1.getItem().getId()));
                                }
                            }
                        }

                        // Let the ACE editor know that the tag database has been loaded.
                        xmlElementDB.setLoaded(true);
                    }
                },
                null,
                true
        );
    }

    public XMLElementDB getXMLElementDB() {
        return xmlElementDB;
    }
}
