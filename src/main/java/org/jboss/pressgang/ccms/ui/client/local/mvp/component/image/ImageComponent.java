package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

import com.google.gwt.user.client.Window;

public class ImageComponent extends ComponentBase<ImagePresenter.Display> implements ImagePresenter.LogicComponent {
   
    public ImageComponent() {

    }


    @Override
    public void bind(final ImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);        
        
    }

    @Override
    public void getEntity(final Integer imageId) {
        final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTImageV1, Display>() {
                    @Override
                    public void doSuccessAction(final RESTImageV1 retValue, final Display display) {
                        display.initialize(retValue, new String[] {});
                    }
                }) {
        };

        if (imageId != null) {
            RESTCalls.getImage(callback, imageId);
        }
    }
}
