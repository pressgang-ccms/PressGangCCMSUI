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

import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTranslatedTopicV1AdditionalXMLEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TranslatedTopicAdditionalXMLPresenter extends BaseTopicXMLPresenter {

    public static final String HISTORY_TOKEN = "TranslatedTopicAdditionalXMLView";

    // Empty interface declaration, similar to UiBinder
    public interface TranslatedTopicAdditionalXMLPresenterDriver extends SimpleBeanEditorDriver<RESTTranslatedTopicV1, RESTTranslatedTopicV1AdditionalXMLEditor> {
    }

    /**
     * This interface defines nothing over BaseTopicXMLPresenter.Display,
     * but exists for the benefit of the injection.
     */
    public interface Display extends BaseTopicXMLPresenter.Display<RESTTranslatedTopicV1, RESTTranslatedTopicV1AdditionalXMLEditor> {

    }

    @Nullable
    private Integer topicId;

    @Inject
    private Display display;

    @Override
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            topicId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            topicId = null;
        }

    }
}
