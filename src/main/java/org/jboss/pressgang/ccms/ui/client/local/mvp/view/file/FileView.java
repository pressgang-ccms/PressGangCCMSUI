/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.file;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileView extends BaseTemplateView implements FilePresenter.Display {

    private RESTFileV1Editor editor;
    private final FilePresenterDriver driver = GWT.create(FilePresenterDriver.class);

    private final PushButton addLocale = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.AddLocale());
    private final PushButton removeLocale = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.RemoveLocale());
    private final PushButton downloadFile = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.DownloadFile());
    private final PushButton save = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Save());
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

    @Override
    public PushButton getDownloadFile() {
        return downloadFile;
    }

    public FileView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Files());

        addActionButton(addLocale);
        addActionButton(removeLocale);
        addActionButton(downloadFile);
        addActionButton(save);
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
