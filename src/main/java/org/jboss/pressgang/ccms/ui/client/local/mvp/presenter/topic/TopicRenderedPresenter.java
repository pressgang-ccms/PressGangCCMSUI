/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.http.client.URL;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TopicRenderedPresenter extends BaseTopicRenderedPresenter<RESTTopicV1> {
    public static final String HISTORY_TOKEN = "TopicRenderedView";

    public interface Display extends BaseTopicRenderedPresenter.Display {

    }

    /**
     * The rendered topic view display
     */
    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        final String fixedToken = removeHistoryToken(historyToken, HISTORY_TOKEN);

        /*
         * The history token is expected to be the topic id, optionally with a semicolon
         * and then an encoded condition.
         */
        try {
            final String[] tokens = fixedToken.split(";");

            if (tokens.length > 0) {

                // Set the revision/condition override if it is present.
                Integer revision = null;
                if (tokens.length > 1) {
                    if (tokens.length >= 2 && tokens[1].matches("^\\d+$")) {
                        revision = Integer.parseInt(tokens[1]);
                        if (tokens.length >= 3) {
                            conditionOverride = URL.decode(tokens[2]);
                        }
                    } else {
                        conditionOverride = URL.decode(tokens[1]);
                    }
                }

                final Integer topicId = Integer.parseInt(tokens[0]);

                final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
                        displayTopicRendered(retValue, true, true);
                    }
                };

                if (revision == null) {
                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTopic(topicId), callback, display);
                } else {
                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTopicRevision(topicId, revision), callback, display);
                }
            }

        } catch (@NotNull final NumberFormatException ex) {

        }
    }

    @Override
    public void displayTopicRendered(final RESTTopicV1 topic, final boolean readOnly, final boolean showImages) {
        try {
            String xml = cleanXMLAndAddAdditionalContent(topic.getXmlFormat(), topic.getXml(), showImages);

            xml = processXML(topic.getXmlFormat(), xml);

            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.holdXML(xml),
                    new RESTCallBack<IntegerWrapper>() {
                        @Override
                        public void success(@NotNull final IntegerWrapper value) {
                            getDisplay().displayTopicRendered(value.value, readOnly, showImages);
                        }
                    }, getDisplay(), true);
        } catch (DOMParseException e) {
            handleXMLError(e);
        }
    }
}