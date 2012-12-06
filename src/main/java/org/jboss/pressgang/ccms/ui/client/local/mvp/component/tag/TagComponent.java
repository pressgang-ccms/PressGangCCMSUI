package org.jboss.pressgang.ccms.ui.client.local.mvp.component.tag;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

public class TagComponent extends ComponentBase<TagPresenter.Display> implements TagPresenter.LogicComponent {
    @Override
    public void getEntity(final Integer tagId) {
        final RESTCalls.RESTCallback<RESTTagV1> callback = new BaseRestCallback<RESTTagV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTTagV1, Display>() {
                    @Override
                    public void doSuccessAction(final RESTTagV1 retValue, final Display display) {
                        display.initialize(retValue, false);
                    }
                });
        RESTCalls.getUnexpandedTag(callback, tagId);
    }
}
