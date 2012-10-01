package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLErrorsEditor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicXMLErrorsPresenter extends TemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicXMLErrorsView";

    // Empty interface declaration, similar to UiBinder
    public interface TopicXMLErrorsPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1XMLErrorsEditor> {
    }

    public interface Display extends TopicViewInterface {

    }

    private String topicId;

    @Inject
    private Display display;

    @Override
    public void parseToken(final String searchToken) {
        topicId = searchToken.replace(HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

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
