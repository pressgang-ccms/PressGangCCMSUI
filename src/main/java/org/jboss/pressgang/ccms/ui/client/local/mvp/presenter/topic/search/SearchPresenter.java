package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

import com.google.gwt.event.shared.HandlerManager;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class SearchPresenter extends ComponentBase<SearchPresenter.Display> implements TemplatePresenter {

    public interface Display extends BaseEditorViewInterface<SearchUIProjects, SearchUIProjectsEditor> {
        // Empty interface declaration, similar to UiBinder
        interface SearchPresenterDriver extends SimpleBeanEditorDriver<SearchUIProjects, SearchUIProjectsEditor> {
        }

        SearchUIProjects getSearchUIProjects();

        PushButton getFields();

        void initialise(final RESTTagCollectionV1 tags);
    }

    public static final String HISTORY_TOKEN = "SearchView";

    @Inject
    private HandlerManager eventBus;

    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void go(final HasWidgets container) {
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);
        display.setViewShown(true);
        clearContainerAndAddTopLevelPanel(container, display);
        process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display);
    }

    public void process(final int helpTopicId, final String pageId, final BaseTemplateViewInterface waitDisplay)
    {
        bind(helpTopicId, pageId, display, waitDisplay);
    }

    @Override
    public void parseToken(final String historyToken) {

    }

    @Override
    public void bind(final int topicId, final String pageId, final SearchPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        super.bind(topicId, pageId, display, waitDisplay);

        getProjects(display);
    }

    private void getProjects(final SearchPresenter.Display display) {
        final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(final RESTTagCollectionV1 retValue, final BaseTemplateViewInterface waitDisplay) {
                display.initialise(retValue);
            }
        }) {
        };
        RESTCalls.getTags(callback);
    }

}
