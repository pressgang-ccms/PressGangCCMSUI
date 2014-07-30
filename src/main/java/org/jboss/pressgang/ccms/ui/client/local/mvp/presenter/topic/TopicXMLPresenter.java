/*
  Copyright 2011-2014 Red Hat, Inc

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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TopicXMLPresenter extends BaseTopicXMLPresenter {

    public static final String HISTORY_TOKEN = "TopicXMLView";

    // Empty interface declaration, similar to UiBinder
    public interface TopicXMLPresenterDriver extends SimpleBeanEditorDriver<RESTBaseTopicV1<?, ?, ?>, RESTTopicV1XMLEditor> {
    }

    /**
     * This interface defines nothing over BaseTopicXMLPresenter.Display,
     * but exists for the benefit of the injection.
     */
    public interface Display extends BaseTopicXMLPresenter.Display<RESTBaseTopicV1<?, ?, ?>, RESTTopicV1XMLEditor> {

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
