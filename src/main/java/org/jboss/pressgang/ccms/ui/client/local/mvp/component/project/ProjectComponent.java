package org.jboss.pressgang.ccms.ui.client.local.mvp.component.project;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

public class ProjectComponent extends ComponentBase<ProjectPresenter.Display> implements ProjectPresenter.LogicComponent {

    /**
     * Get the category from the database and use it to populate the editor in the view
     */
    @Override
    public void getEntity(final Integer entityId) {
        final RESTCalls.RESTCallback<RESTProjectV1> callback = new BaseRestCallback<RESTProjectV1, ProjectPresenter.Display>(display,
                new BaseRestCallback.SuccessAction<RESTProjectV1, ProjectPresenter.Display>() {
                    @Override
                    public void doSuccessAction(final RESTProjectV1 retValue, ProjectPresenter.Display display) {
                        display.initialize(retValue, false);
                    }
                }) {
        };
        RESTCalls.getUnexpandedProject(callback, entityId);
    }
}
