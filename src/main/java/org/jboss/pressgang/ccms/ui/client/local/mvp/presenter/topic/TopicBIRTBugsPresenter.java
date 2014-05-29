package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TopicBIRTBugsPresenter extends BaseTemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicBIRTBugsView";

    public interface Display extends BaseCustomViewInterface<RESTBaseTopicV1<?, ?, ?>> {

    }

    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    protected void go() {
        bindExtended();
    }

    @Override
    public void close() {

    }

    public void bindExtended() {

    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }
}
