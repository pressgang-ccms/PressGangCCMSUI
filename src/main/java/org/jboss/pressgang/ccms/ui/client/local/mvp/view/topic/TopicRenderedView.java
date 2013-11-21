package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;


import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class TopicRenderedView extends BaseTopicRenderedView implements TopicRenderedPresenter.Display {
    public TopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.RenderedView());
    }
}