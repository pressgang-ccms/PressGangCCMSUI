package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchFieldEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.ui.searchfield.SearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;


@Dependent
public class SearchFieldPresenter extends TemplatePresenter implements EditableView {
    
    public static final String HISTORY_TOKEN = "SearchFieldView";
    
    @Inject
    private Display display;
    
    public interface Display extends BaseTemplateViewInterface {
        interface SearchFieldPresenterDriver extends SimpleBeanEditorDriver<SearchUIFields, SearchFieldEditor> {
        }
    }

    @Override
    public void go(final HasWidgets container) {
        GWTUtilities.clearContainerAndAddTopLevelPanel(container, display);
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
