package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter.LogicComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search.SearchView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;

@Dependent
public class SearchComponent implements LogicComponent {

    @Inject
    private HandlerManager eventBus;

    @Override
    public void bind(final SearchPresenter.Display display) {
        display.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(display.getSearchUIProjects().getSearchQuery(true)));
            }
        });

        getProjects(display);
    }

    private void getProjects(final SearchPresenter.Display display) {
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

}
