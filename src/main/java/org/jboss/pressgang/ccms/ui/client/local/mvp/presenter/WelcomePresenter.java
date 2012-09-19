package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class WelcomePresenter extends TemplatePresenter implements EditableView {
    
    public static final String HISTORY_TOKEN = "WelcomeView";
    
    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject
    private Display display;

    @Override
    public void go(final HasWidgets container) {
        display.setViewShown(true);
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);
        
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
