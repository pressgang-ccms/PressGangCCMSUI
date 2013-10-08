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
