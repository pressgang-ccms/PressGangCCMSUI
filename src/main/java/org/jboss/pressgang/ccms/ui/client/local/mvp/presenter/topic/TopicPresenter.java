package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class TopicPresenter extends TemplatePresenter {

    public static final String HISTORY_TOKEN = "TopicView";

    // Empty interface declaration, similar to UiBinder
    public interface TopicPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1BasicDetailsEditor> {
    }

    public interface Display extends TopicViewInterface {

    }

    private String topicId;

    @Inject
    private Display display;

    @Override
    public void parseToken(final String searchToken) {
        topicId = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        getTopic();
        bind();
    }

    private void getTopic() {
        final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTTopicV1 retValue, Display display) {
                        display.initialize(retValue, false, SplitType.DISABLED);
                    }
                }) {
        };

        try {
            RESTCalls.getTopic(callback, Integer.parseInt(topicId));
        } catch (final NumberFormatException ex) {
            display.removeWaitOperation();
        }
    }

    private void bind() {

    }
}
