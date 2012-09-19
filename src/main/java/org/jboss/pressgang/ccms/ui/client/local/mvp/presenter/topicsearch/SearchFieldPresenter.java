package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

public class SearchFieldPresenter extends TemplatePresenter implements EditableView {
    
    public interface Display extends BaseTemplateViewInterface
    {
        
    }

    @Override
    public void go(final HasWidgets container) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }

    @Override
    public void parseToken(final String historyToken) {
        // TODO Auto-generated method stub
        
    }

}
