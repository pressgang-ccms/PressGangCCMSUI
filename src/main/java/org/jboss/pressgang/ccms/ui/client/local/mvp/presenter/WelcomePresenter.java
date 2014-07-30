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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AnchorButton;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.BaseTopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

@Dependent
public class WelcomePresenter extends BaseRenderedPresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "WelcomeView";

    public interface Display extends BaseTemplateViewInterface {
        void displayTopicRendered(final Integer topicXMLHoldID);

        AnchorButton getEdit();
    }

    @Inject
    private Display display;

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go() {
        display.setViewShown(true);
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindExtended() {
        display.getEdit().setHref(
                "#" + BaseTopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX +
                        CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId());
        display.getEdit().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent clickEvent) {
                getEventBus().fireEvent(new TopicSearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX +
                        CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId(),
                        false));
            }
        });

        getFailOverRESTCall().performRESTCall(
                FailOverRESTCallDatabase.getTopic(ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId()),
                new RESTCallBack<RESTTopicV1>() {
                    public void success(@NotNull final RESTTopicV1 value) {
                        final String xml = cleanXMLAndAddAdditionalContent(value.getXml(), true, true, true);
                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.holdXML(xml), new RESTCallBack<IntegerWrapper>() {
                            public void success(@NotNull final IntegerWrapper value) {
                                display.displayTopicRendered(value.value);
                            }
                        }, display, true);
                    }
                }, display);
    }


    @Override
    public void parseToken(@NotNull final String historyToken) {

    }
}
