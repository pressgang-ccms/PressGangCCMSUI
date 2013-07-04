package org.jboss.pressgang.ccms.ui.client.local.mvp.view.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFileV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file.FilePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file.FilePresenter.FilePresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.file.RESTFileV1Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FileView extends BaseTemplateView implements FilePresenter.Display {

    private RESTFileV1Editor editor;
    private final FilePresenterDriver driver = GWT.create(FilePresenterDriver.class);

    private final PushButton addLocale = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.AddLocale());
    private final PushButton removeLocale = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RemoveLocale());
    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final AddLocaleDialog addLocaleDialog = new AddLocaleDialog();

    /**
     * The dialog box that presents the list of locales for the user to select from.
     *
     * @author Matthew Casperson
     */
    public final static class AddLocaleDialog extends DialogBox implements AddLocaleInterface {
        private final FlexTable layout = new FlexTable();
        private final ListBox locales = new ListBox();
        private final Label localesLabel = new Label(PressGangCCMSUI.INSTANCE.Locales());
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

        @NotNull
        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @Override
        public PushButton getCancel() {
            return cancel;
        }

        @Override
        public PushButton getOk() {
            return ok;
        }

        @NotNull
        @Override
        public ListBox getLocales() {
            return locales;
        }

        @NotNull
        public Label getLocalesLabel() {
            return localesLabel;
        }

        public AddLocaleDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.AddLocale());

            layout.setWidget(0, 0, localesLabel);
            layout.setWidget(0, 1, locales);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            layout.setWidget(1, 0, buttonPanel);

            layout.getFlexCellFormatter().setColSpan(1, 0, 2);

            this.add(layout);
        }

    }

    @Override
    public FilePresenterDriver getDriver() {
        return driver;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    @Override
    public RESTFileV1Editor getEditor() {
        return editor;
    }

    public void setEditor(final RESTFileV1Editor editor) {
        this.editor = editor;
    }

    @NotNull
    @Override
    public AddLocaleDialog getAddLocaleDialog() {
        return addLocaleDialog;
    }

    @Override
    public PushButton getRemoveLocale() {
        return removeLocale;
    }

    @Override
    public PushButton getAddLocale() {
        return addLocale;
    }

    public FileView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Files());

        this.addActionButton(addLocale);
        this.addActionButton(removeLocale);
        this.addActionButton(save);
    }

    @Override
    public void display(final RESTFileV1 file, final boolean readOnly) {

    }

    @Override
    public void displayExtended(@Nullable final RESTFileV1 file, final boolean readOnly, @Nullable final String[] locales) {
        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        if (locales == null) {
            throw new IllegalArgumentException("locales cannot be null");
        }

        editor = new RESTFileV1Editor();
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(file);
        /* Add the projects */
        this.getPanel().setWidget(editor);

        /* Sort the array */
        final List<String> localesList = new ArrayList<String>(Arrays.asList(locales));
        Collections.sort(localesList);

        /* populate the locales list box */
        this.addLocaleDialog.locales.clear();
        for (final String locale : localesList) {
            this.addLocaleDialog.locales.addItem(locale);
        }

        /* Make sure the dialog box is closed */
        addLocaleDialog.hide();
    }
}
