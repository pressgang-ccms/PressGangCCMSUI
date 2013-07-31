package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTImageV1Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class ImagePresenter extends BaseTemplatePresenter {

    public interface Display extends BasePopulatedEditorViewInterface<RESTImageV1, RESTImageV1, RESTImageV1Editor> {

        interface AddLocaleInterface {
            PushButton getCancel();

            PushButton getOk();

            ListBox getLocales();

            DialogBox getDialogBox();
        }

        void displayExtended(final RESTImageV1 image, final boolean readOnly, final String[] locales);

        PushButton getRemoveLocale();

        PushButton getAddLocale();

        PushButton getViewImage();

        PushButton getFindTopics();

        PushButton getSave();

        AddLocaleInterface getAddLocaleDialog();

        RESTImageV1Editor getEditor();

    }

    // Empty interface declaration, similar to UiBinder
    public interface ImagePresenterDriver extends SimpleBeanEditorDriver<RESTImageV1, RESTImageV1Editor> {
    }

    public static final String HISTORY_TOKEN = "ImageView";

    @Inject
    private Display display;

    /**
     * The id of the image to display, extracted from the history token.
     */
    @Nullable
    private Integer imageId;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public ImagePresenter() {

    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            imageId = Integer.parseInt(removeHistoryToken(historyToken, HISTORY_TOKEN));
        } catch (@NotNull final Exception ex) {
            // bad history token. silently fail
            imageId = null;
        }
    }

    public void bindExtended() {
        super.bind(display);
    }
}
