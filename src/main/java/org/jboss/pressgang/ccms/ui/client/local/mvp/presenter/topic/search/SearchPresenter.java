package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class SearchPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public interface Display extends BasePopulatedEditorViewInterface<RESTTagCollectionV1, SearchUIProjects, SearchUIProjectsEditor> {
        // Empty interface declaration, similar to UiBinder
        interface SearchPresenterDriver extends SimpleBeanEditorDriver<SearchUIProjects, SearchUIProjectsEditor> {
        }

        void displayExtended(final RESTTagCollectionV1 tagCollection, final RESTFilterV1 filter, final boolean readOnly);

        SearchUIProjects getSearchUIProjects();

        PushButton getFields();

        PushButton getSearchTopics();

        PushButton getFilters();
    }

    public static final String HISTORY_TOKEN = "SearchView";

    @Inject
    private HandlerManager eventBus;

    final private HandlerManager handlerManager = new HandlerManager(this);

    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);
        display.setViewShown(true);
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int helpTopicId, @NotNull final String pageId)
    {
        bind(helpTopicId, pageId, display);
        displayTags(null);
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    /**
     * Gets the tags from the REST server, and optionally sets their state against a filter
     *
     * @param filter a filter that defines the state of the tags, or null if no initial state is required
     */
    public void displayTags(@Nullable final RESTFilterV1 filter) {
        final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, BaseTemplateViewInterface>(
                display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, @NotNull final BaseTemplateViewInterface waitDisplay) {
                display.displayExtended(retValue, filter, false);
            }
        });
        RESTCalls.getTags(callback);
    }

}
