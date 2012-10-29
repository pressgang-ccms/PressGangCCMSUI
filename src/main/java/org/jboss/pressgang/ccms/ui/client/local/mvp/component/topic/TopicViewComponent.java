package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.propertyview.BasePropertyViewComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

public class TopicViewComponent<T extends TopicViewInterface> extends ComponentBase<T> implements BasePropertyViewComponentInterface<T>{
    
    @Override
    public void getEntity(final Integer topicId) {
        final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, T>(display,
                new BaseRestCallback.SuccessAction<RESTTopicV1, T>() {
                    @Override
                    public void doSuccessAction(RESTTopicV1 retValue, T display) {
                        display.initialize(retValue, false, false, SplitType.DISABLED);
                    }
                }) {
        };

        if (topicId != null)
            RESTCalls.getTopic(callback, topicId);
    }
}
