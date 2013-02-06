package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchFieldEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class SearchFieldPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "SearchFieldView";

    public interface Display extends BaseTemplateViewInterface, BaseEditorViewInterface<SearchUIFields, SearchFieldEditor> {
        interface SearchFieldPresenterDriver extends SimpleBeanEditorDriver<SearchUIFields, SearchFieldEditor> {
        }
        PushButton getSearchTopics();
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
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int helpTopicId, final String pageId)
    {
        bind(helpTopicId, pageId, display);
    }

    @Override
    public void parseToken(final String historyToken) {

    }

}
