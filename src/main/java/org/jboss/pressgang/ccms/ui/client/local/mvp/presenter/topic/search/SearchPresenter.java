package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchUIProjects;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class SearchPresenter extends TemplatePresenter implements EditableView {

    public static final String HISTORY_TOKEN = "SearchView";

    public interface Display extends BaseTemplateViewInterface {
        // Empty interface declaration, similar to UiBinder
        interface SearchPresenterDriver extends SimpleBeanEditorDriver<SearchUIProjects, SearchUIProjectsEditor> {
        }

        SearchUIProjects getSearchUIProjects();

        @Override
        PushButton getSearch();

        SearchPresenterDriver getDriver();

        void initialise(final RESTTagCollectionV1 tags);
    }

    @Inject
    private HandlerManager eventBus;

    @Inject
    private Display display;

    @Override
    public void go(final HasWidgets container) {
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);
        display.setViewShown(true);
        clearContainerAndAddTopLevelPanel(container, display);
        getProjects();
        bind();
    }

    protected void bind() {
        super.bind(display, this);

        display.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(display.getSearchUIProjects().getRESTQueryString()));
            }
        });
    }

    private void getProjects() {
        final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, Display>(
                display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTTagCollectionV1 retValue, Display display) {
                        display.initialise(retValue);
                    }
                }) {
            @Override
            public void generalException(Exception e) {
                Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTags());
                super.generalException(e);
            }

            @Override
            public void failed() {
                super.failed();
            }
        };
        RESTCalls.getTags(callback);
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
