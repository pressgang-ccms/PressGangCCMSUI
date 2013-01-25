package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchFieldEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;

@Dependent
public class SearchFieldPresenter extends ComponentBase<SearchFieldPresenter.Display> implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "SearchFieldView";

    public interface Display extends BaseEditorViewInterface<SearchUIFields, SearchFieldEditor> {
        interface SearchFieldPresenterDriver extends SimpleBeanEditorDriver<SearchUIFields, SearchFieldEditor> {
        }
        @Override
        PushButton getSearch();
        PushButton getTagsSearch();
        SearchUIFields getSearchUIFields();
    }

    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void go(final HasWidgets container) {
        GWTUtilities.clearContainerAndAddTopLevelPanel(container, display);
        process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display);
    }

    public void process(final int helpTopicId, final String pageId, final BaseTemplateViewInterface waitDisplay)
    {
        bind(helpTopicId, pageId, display, waitDisplay);
    }

    @Override
    public void parseToken(final String historyToken) {

    }

}
