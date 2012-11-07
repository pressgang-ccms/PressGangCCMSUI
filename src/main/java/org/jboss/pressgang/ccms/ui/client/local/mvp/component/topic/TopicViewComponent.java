package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.propertyview.BasePropertyViewComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

public class TopicViewComponent<S extends TopicViewInterface> extends ComponentBase<S> implements
        BasePropertyViewComponentInterface<S> {

    @Override
    public void getEntity(final Integer topicId) {
        final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, S>(display,
                new BaseRestCallback.SuccessAction<RESTTopicV1, S>() {
                    @Override
                    public void doSuccessAction(RESTTopicV1 retValue, S display) {
                        display.initialize(retValue, false, false, SplitType.DISABLED, null);
                    }
                }) {
        };

        if (topicId != null)
            RESTCalls.getTopic(callback, topicId);
    }
}
