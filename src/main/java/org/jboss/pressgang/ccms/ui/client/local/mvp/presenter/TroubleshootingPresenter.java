package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TroubleshootingPresenter extends BaseTemplatePresenter {

    public static final String HISTORY_TOKEN = "TroubleshootingView";

    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject
    private Display display;

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go() {
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