package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTImageV1Editor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class ImagePresenter implements EditableView, TemplatePresenter {
    public static final String HISTORY_TOKEN = "ImageView";

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    /**
     * The id of the image to display, extracted from the history token.
     */
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

    public interface LogicComponent extends Component<Display> {
        ProviderUpdateData<RESTImageCollectionItemV1> getImageData();
        void setImageData(ProviderUpdateData<RESTImageCollectionItemV1> imageData);
        void setLocales(final String[] locales);
        String[] getLocales() ;
        public void reInitialiseImageView();
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        /*
         * normally the displayedImage would be from a collection, but as this presenter only works with one specified entity,
         * we just create the wrapper RESTImageCollectionItemV1 object manually.
         */
        component.getImageData().setDisplayedItem(new RESTImageCollectionItemV1());
        component.getImageData().getDisplayedItem().setState(RESTImageCollectionItemV1.UNCHANGED_STATE);
        component.getImageData().getDisplayedItem().setItem(new RESTImageV1());
        bind();

        getImage();
    }

    /**
     * Potentially two REST calls have to finish before we can display the page. This function will be called as each REST call
     * finishes, and when all the information has been gathered, the page will be displayed.
     */
    private void finishLoading() {
        if (component.getLocales() != null && component.getImageData().getDisplayedItem() != null) {
            component.reInitialiseImageView();
        }
    }

    private void bind() {
        
    }

    @Override
    public void parseToken(final String historyToken) {
        try {
            imageId = Integer.parseInt(removeHistoryToken(historyToken, HISTORY_TOKEN));
        } catch (final Exception ex) {
            // bad history token. silently fail
            imageId = null;
        }
    }

    private void getImage() {
        /*
         * Create a call back that resets the exception handler on any normal error or exception
         */
        final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTImageV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTImageV1 retValue, Display display) {
                        retValue.cloneInto(component.getImageData().getDisplayedItem().getItem(), true);
                        finishLoading();
                    }
                }) {

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
    public boolean checkForUnsavedChanges() {
        return true;
    }

}