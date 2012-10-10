package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

public interface TopicViewComponentInterface<T extends BaseTemplateViewInterface> extends Component<T> {
    void getTopic(final Integer topicId);
}
