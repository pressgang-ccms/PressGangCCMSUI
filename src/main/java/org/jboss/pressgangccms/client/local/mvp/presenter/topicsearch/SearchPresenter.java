package org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;

@Dependent
public class SearchPresenter extends TemplatePresenter {
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
        container.clear();
        container.add(display.getTopLevelPanel());

        getProjects();

        bind();
    }

    protected void bind() {
        super.bind(display);

        display.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(display.getSearchUIProjects().getRESTQueryString()));
            }
        });
    }

    private void getProjects() {
        final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTTagCollectionV1>() {

            @Override
            public void begin() {
                display.getWaiting().addWaitOperation();
            }

            @Override
            public void generalException(final Exception ex) {
                Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTags());
                display.getWaiting().removeWaitOperation();
            }

            @Override
            public void success(final RESTTagCollectionV1 retValue) {
                try {
                    display.initialise(retValue);
                } finally {
                    display.getWaiting().removeWaitOperation();
                }
            }

            @Override
            public void failed() {
                Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTags());
                display.getWaiting().removeWaitOperation();
            }
        };

        RESTCalls.getTags(callback);
    }

    @Override
    public void parseToken(final String historyToken) {
        // TODO Auto-generated method stub
    }
}
