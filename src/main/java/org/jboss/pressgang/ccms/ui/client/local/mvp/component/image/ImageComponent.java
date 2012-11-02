package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
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
    /**
     * The currently displayed image.
     */
    private ProviderUpdateData<RESTImageCollectionItemV1> imageData;



    @Override
    public ProviderUpdateData<RESTImageCollectionItemV1> getImageData() {
        return imageData;
    }

    @Override
    public void setImageData(ProviderUpdateData<RESTImageCollectionItemV1> imageData) {
        this.imageData = imageData;
    }

    public ImageComponent() {
        final RESTImageCollectionItemV1 item = new RESTImageCollectionItemV1();
        item.setItem(new RESTImageV1());
        imageData = new ProviderUpdateData<RESTImageCollectionItemV1>(item);
    }


    @Override
    public void bind(final ImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);        
        
    }

    @Override
    public void getImage(final Integer imageId) {
        /*
         * Create a call back that resets the exception handler on any normal error or exception
         */
        final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTImageV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTImageV1 retValue, Display display) {
                        retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
  
                    }
                }) {

            @Override
            public void failed(final Message message, final Throwable throwable) {
                display.removeWaitOperation();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };

        if (imageId != null) {
            RESTCalls.getImage(callback, imageId);
        }
    }


}
