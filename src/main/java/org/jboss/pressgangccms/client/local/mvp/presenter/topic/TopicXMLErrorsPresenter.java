package org.jboss.pressgangccms.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicXMLErrorsView;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.RESTTopicV1XMLErrorsEditor;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicXMLErrorsPresenter extends TemplatePresenter {
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
        topicId = searchToken.replace(TopicXMLErrorsView.HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        getTopic();

        bind();
    }

    private void getTopic() {
        final RESTCalls.RESTCallback<RESTTopicV1> callback = new RESTCalls.RESTCallback<RESTTopicV1>() {
            @Override
            public void begin() {
                display.getWaiting().addWaitOperation();
            }

            @Override
            public void generalException(final Exception ex) {
                display.getWaiting().removeWaitOperation();
            }

            @Override
            public void success(final RESTTopicV1 retValue) {
                try {
                    display.initialize(retValue, false, SplitType.DISABLED);
                } finally {
                    display.getWaiting().removeWaitOperation();
                }
            }

            @Override
            public void failed() {
                display.getWaiting().removeWaitOperation();
            }
        };

        try {
            RESTCalls.getTopic(callback, Integer.parseInt(topicId));
        } catch (final NumberFormatException ex) {
            display.getWaiting().removeWaitOperation();
        }
    }

    private void bind() {

    }
}
