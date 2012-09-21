package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTImageV1Editor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;

@Dependent
public class ImagePresenter extends ImagePresenterBase {
    public static final String HISTORY_TOKEN = "ImageView";

    @Inject
    private Display display;

    /** The id of the image to display, extracted from the history token. */
    private Integer imageId;

    public interface Display extends BaseTemplateViewInterface {
        // Empty interface declaration, similar to UiBinder
        public interface ImagePresenterDriver extends SimpleBeanEditorDriver<RESTImageV1, RESTImageV1Editor> {
        }

        public interface AddLocaleInterface {
            PushButton getCancel();

            PushButton getOk();

            ListBox getLocales();

            DialogBox getDialogBox();
        }

        void initialize(final RESTImageV1 image, final String[] locales);

        PushButton getRemoveLocale();

        PushButton getAddLocale();

        PushButton getSave();

        AddLocaleInterface getAddLocaleDialog();

        RESTImageV1Editor getEditor();
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        /*
         * normally the displayedImage would be from a collection, but as this presenter only works with one specified entity,
         * we just create the wrapper RESTImageCollectionItemV1 object manually.
         */
        displayedImage = new RESTImageCollectionItemV1();
        displayedImage.setState(RESTImageCollectionItemV1.UNCHANGED_STATE);
        displayedImage.setItem(new RESTImageV1());

        bind();

        getLocales(display);

        getImage();
    }

    /**
     * Potentially two REST calls have to finish before we can display the page. This function will be called as each REST call
     * finishes, and when all the information has been gathered, the page will be displayed.
     */
    private void finishLoading() {
        if (locales != null && displayedImage != null) {
            reInitialiseImageView();
        }
    }

    private void bind() {
        bindImageViewButtons(display, display);
    }

    @Override
    public void parseToken(final String historyToken) {
        try {
            imageId = Integer.parseInt(historyToken.replace(HISTORY_TOKEN + ";", ""));
        } catch (final Exception ex) {
            // bad history token. silently fail
            imageId = null;
        }
    }

    private void getImage() {
        /*
         * Create a call back that resets the exception handler on any normal error or exception
         */
        final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
            @Override
            public void begin() {
                display.addWaitOperation();
            }

            @Override
            public void generalException(final Exception ex) {
                display.removeWaitOperation();
            }

            @Override
            public void success(final RESTImageV1 retValue) {
                try {
                    retValue.cloneInto(displayedImage.getItem(), true);
                    finishLoading();
                } finally {
                    display.removeWaitOperation();
                }
            }

            @Override
            public void failed() {
                display.removeWaitOperation();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };

        if (imageId != null) {
            RESTCalls.getImage(callback, imageId);
        }
    }

    @Override
    protected void reInitialiseImageView() {
        display.initialize(displayedImage.getItem(), getUnassignedLocales().toArray(new String[0]));

        bindImageUploadButtons(display);
    }

}