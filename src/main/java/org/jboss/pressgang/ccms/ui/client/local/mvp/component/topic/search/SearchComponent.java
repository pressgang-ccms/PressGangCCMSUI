package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

import com.google.gwt.event.shared.HandlerManager;

@Dependent
public class SearchComponent extends ComponentBase<SearchPresenter.Display> implements SearchPresenter.LogicComponent {

    @Inject
    private HandlerManager eventBus;

    @Override
    public void bind(final SearchPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        super.bind(display, waitDisplay);

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
