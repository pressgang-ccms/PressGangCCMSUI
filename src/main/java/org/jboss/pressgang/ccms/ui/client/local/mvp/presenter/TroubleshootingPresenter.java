package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

public class TroubleshootingPresenter extends TemplatePresenter implements EditableView {
    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject
    private Display display;

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        bind(display, this);
    }

    @Override
    public void parseToken(final String historyToken) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}