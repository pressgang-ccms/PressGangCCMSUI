/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFileV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.file.RESTFileV1Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    protected void go() {
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

    }
}
