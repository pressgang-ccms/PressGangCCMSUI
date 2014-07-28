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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.file;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFileCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file.FileFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FileFilteredResultsView extends BaseFilteredResultsView<RESTFileCollectionItemV1>
        implements FileFilteredResultsPresenter.Display {

    private final TextBox fileIdFilter = new TextBox();
    private final TextBox fileDescriptionFilter = new TextBox();
    private final TextBox fileNameFilter = new TextBox();
    private final PushButton bulkUpload = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.BulkFileUpload());

    @NotNull
    public PushButton getBulkUpload() {
        return bulkUpload;
    }


    @NotNull
    private TextColumn<RESTFileCollectionItemV1> idColumn = new TextColumn<RESTFileCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTFileCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }

            return object.getItem().getId().toString();

        }
    };

    @NotNull
    private TextColumn<RESTFileCollectionItemV1> fileName = new TextColumn<RESTFileCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTFileCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }

            return object.getItem().getFileName();
        }
    };

    @NotNull
    private TextColumn<RESTFileCollectionItemV1> descriptionColumn = new TextColumn<RESTFileCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTFileCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }

            return object.getItem().getDescription();
        }
    };

    @NotNull
    @Override
    public TextBox getFileNameFilter() {
        return fileNameFilter;
    }

    @NotNull
    @Override
    public TextBox getFileIdFilter() {
        return fileIdFilter;
    }

    @NotNull
    @Override
    public TextBox getFileDescriptionFilter() {
        return fileDescriptionFilter;
    }

    public FileFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Files(), PressGangCCMSUI.INSTANCE.CreateFile());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.FileID());
        getResults().addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.FileDescription());
        getResults().addColumn(fileName, PressGangCCMSUI.INSTANCE.FileName());

        addFilterField(PressGangCCMSUI.INSTANCE.FileID(), fileIdFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.FileDescription(), fileDescriptionFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.FileName(), fileNameFilter);

        new NumbersAndCommaValidator(fileIdFilter);

        this.addActionButton(bulkUpload);
    }
}
