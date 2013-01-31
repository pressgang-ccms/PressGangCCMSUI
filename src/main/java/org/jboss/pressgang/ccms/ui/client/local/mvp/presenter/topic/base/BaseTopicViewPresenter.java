package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.BaseTopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

/**
 * Every presenter that works with a topic needs to retrieve the topic. This base class simply
 * provides a method to retrieve a topic and initialize the view with that topic.
 */
abstract public class BaseTopicViewPresenter extends BaseTemplatePresenter implements
        BaseTopicViewPresenterInterface {

    @Override
    public final void getEntity(final Integer topicId, final BaseTopicViewInterface display) {
        final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, BaseTopicViewInterface>(display,
                new BaseRestCallback.SuccessAction<RESTTopicV1, BaseTopicViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTTopicV1 retValue, final BaseTopicViewInterface display) {
                        display.initialize(retValue, false, false, SplitType.DISABLED, null, false);
                    }
                });

        if (topicId != null) {
            RESTCalls.getTopic(callback, topicId);
        }
    }
}
