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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

public class TagProjectsView extends
        BaseChildrenView<RESTTagV1,
                RESTProjectCollectionItemV1,
                RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> implements
        TagProjectsPresenter.Display {

    boolean readOnly = false;

    private final TextColumn<RESTProjectCollectionItemV1> idColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTProjectCollectionItemV1 object) {
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> nameColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTProjectCollectionItemV1 object) {
            return object.getItem().getName();
        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> descriptionColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTProjectCollectionItemV1 object) {
            return object.getItem().getDescription();
        }
    };

    @NotNull
    private final DisableableButtonCell button = new DisableableButtonCell();
    private final Column<RESTProjectCollectionItemV1, String> buttonColumn = new Column<RESTProjectCollectionItemV1, String>(button) {
        @NotNull
        @Override
        public String getValue(@NotNull final RESTProjectCollectionItemV1 object) {
            button.setEnabled(!readOnly);

            if (getOriginalEntity() != null) {
                if (ComponentProjectV1.containsTag(object.getItem(), getOriginalEntity().getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            button.setEnabled(false);
            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @NotNull
    @Override
    public Column<RESTProjectCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return buttonColumn;
    }

    @Override
    public void display(@NotNull final RESTTagV1 originalEntity, final boolean readOnly) {
        super.displayChildren(originalEntity, readOnly);
        this.readOnly = readOnly;
    }

    public TagProjectsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagProjects());

        getPossibleChildrenResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProjectID());
        getPossibleChildrenResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProjectName());
        getPossibleChildrenResults().addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ProjectDescription());
        getPossibleChildrenResults().addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        idColumn.setSortable(true);
        nameColumn.setSortable(true);
        descriptionColumn.setSortable(true);
        buttonColumn.setSortable(true);

        /*
            Allow the table to be sorted.
         */
        final ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(getPossibleChildrenResults());
        getPossibleChildrenResults().addColumnSortHandler(columnSortHandler);

    }

    @NotNull
    @Override
    public TextColumn<RESTProjectCollectionItemV1> getIdColumn() {
        return idColumn;
    }

    @NotNull
    @Override
    public TextColumn<RESTProjectCollectionItemV1> getNameColumn() {
        return nameColumn;
    }

    @NotNull
    @Override
    public TextColumn<RESTProjectCollectionItemV1> getDescriptionColumn() {
        return descriptionColumn;
    }
}
