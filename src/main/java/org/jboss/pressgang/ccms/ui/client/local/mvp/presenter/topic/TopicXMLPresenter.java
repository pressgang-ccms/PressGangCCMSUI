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
