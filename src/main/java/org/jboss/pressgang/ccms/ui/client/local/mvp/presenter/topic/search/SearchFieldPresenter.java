package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchFieldEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class SearchFieldPresenter implements EditableView, TemplatePresenter {

    public static final String HISTORY_TOKEN = "SearchFieldView";

    public interface Display extends BaseTemplateViewInterface {
        interface SearchFieldPresenterDriver extends SimpleBeanEditorDriver<SearchUIFields, SearchFieldEditor> {
        }
    }

    public interface LogicComponent extends Component<Display> {

    }

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Override
    public void go(final HasWidgets container) {
        GWTUtilities.clearContainerAndAddTopLevelPanel(container, display);
        component.bind(display, display);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }

    @Override
    public void parseToken(final String historyToken) {

    }

}
