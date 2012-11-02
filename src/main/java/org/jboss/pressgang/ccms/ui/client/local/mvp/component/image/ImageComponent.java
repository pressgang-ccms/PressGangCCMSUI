package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import java.util.Arrays;
import java.util.List;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTLanguageImageV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.events.ErrorHandler;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

public class ImageComponent extends ComponentBase<ImagePresenter.Display> implements ImagePresenter.LogicComponent {
    /**
     * The currently displayed image.
     */
    private ProviderUpdateData<RESTImageCollectionItemV1> imageData;

    /**
     * A reference to the StringConstants that holds the locales.
     */
    private String[] locales;

    @Override
    public ProviderUpdateData<RESTImageCollectionItemV1> getImageData() {
        return imageData;
    }

    @Override
    public void setImageData(ProviderUpdateData<RESTImageCollectionItemV1> imageData) {
        this.imageData = imageData;
    }

    public void setLocales(final String[] locales) {
        this.locales = locales;
    }

    public String[] getLocales() {
        return locales;
    }

    public ImageComponent() {
        final RESTImageCollectionItemV1 item = new RESTImageCollectionItemV1();
        item.setItem(new RESTImageV1());
        imageData = new ProviderUpdateData<RESTImageCollectionItemV1>(item);
    }

    @Override
    public List<String> getUnassignedLocales() {
        final List<String> newLocales = Arrays.asList(locales);

        /* Make it so you can't add a locale if it already exists */
        if (imageData.getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
            for (final RESTLanguageImageCollectionItemV1 langImage : imageData.getDisplayedItem().getItem()
                    .getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
                newLocales.remove(langImage.getItem().getLocale());
            }
        }

        return newLocales;
    }

    private void populateLocales() {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                display, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                        /* Get the list of locales from the StringConstant */
                        locales = retValue.getValue().replaceAll("\\n", "").replaceAll(" ", "").split(",");
                    }
                }) {
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRING_CONSTANT);
    }

    



    

    public void reInitialiseImageView() {
        display.initialize(imageData.getDisplayedItem().getItem(), getUnassignedLocales().toArray(new String[0]));

        
    }

    @Override
    public void bind(final ImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);        
        populateLocales();
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
                        finishLoading();
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

    /**
     * Potentially two REST calls have to finish before we can display the page. This function will be called as each REST call
     * finishes, and when all the information has been gathered, the page will be displayed.
     */
    private void finishLoading() {
        if (locales != null && imageData.getDisplayedItem() != null) {
            reInitialiseImageView();
        }
    }
}
