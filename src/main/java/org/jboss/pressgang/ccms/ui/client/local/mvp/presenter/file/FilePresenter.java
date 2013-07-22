package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFileV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.file.RESTFileV1Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class FilePresenter extends BaseTemplatePresenter {

    public interface Display extends BasePopulatedEditorViewInterface<RESTFileV1, RESTFileV1, RESTFileV1Editor> {

        interface AddLocaleInterface {
            PushButton getCancel();

            PushButton getOk();

            ListBox getLocales();

            DialogBox getDialogBox();
        }

        void displayExtended(final RESTFileV1 file, final boolean readOnly, final String[] locales);

        PushButton getRemoveLocale();

        PushButton getAddLocale();

        PushButton getDownloadFile();

        PushButton getSave();

        AddLocaleInterface getAddLocaleDialog();

        RESTFileV1Editor getEditor();

    }

    // Empty interface declaration, similar to UiBinder
    public interface FilePresenterDriver extends SimpleBeanEditorDriver<RESTFileV1, RESTFileV1Editor> {
    }

    public static final String HISTORY_TOKEN = "FileView";

    @Inject
    private Display display;

    /**
     * The id of the image to display, extracted from the history token.
     */
    @Nullable
    private Integer fileId;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public FilePresenter() {

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
            fileId = Integer.parseInt(removeHistoryToken(historyToken, HISTORY_TOKEN));
        } catch (@NotNull final Exception ex) {
            // bad history token. silently fail
            fileId = null;
        }
    }

    @Override
    public void bindExtended() {
        super.bind(display);
    }
}
