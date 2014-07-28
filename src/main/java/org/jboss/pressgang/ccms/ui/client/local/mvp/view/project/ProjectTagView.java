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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.project;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProjectTagView
        extends BaseChildrenView<
        RESTProjectV1,  // The main entity types
        RESTTagCollectionItemV1,             // The possible child types
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>             // The existing child types
        implements ProjectTagPresenter.Display {

    private boolean readOnly = false;

    private final TextColumn<RESTTagCollectionItemV1> tagsIdColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    private final TextColumn<RESTTagCollectionItemV1> tagsNameColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };

    private final DisableableButtonCell button = new DisableableButtonCell();
    private final Column<RESTTagCollectionItemV1, String> tagsButtonColumn = new Column<RESTTagCollectionItemV1, String>(button) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTTagCollectionItemV1 object) {
            button.setEnabled(!readOnly);
            if (getOriginalEntity() != null && object != null && object.getItem().getId() != null) {
                if (ComponentProjectV1.containsTag(getOriginalEntity(), object.getItem().getId())) {
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
    public Column<RESTTagCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return tagsButtonColumn;
    }

    public ProjectTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());

        getPossibleChildrenResults().addColumn(tagsIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        getPossibleChildrenResults().addColumn(tagsNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        getPossibleChildrenResults().addColumn(tagsButtonColumn, PressGangCCMSUI.INSTANCE.AddRemove());
        tagsButtonColumn.setSortable(true);
        tagsNameColumn.setSortable(true);
        tagsIdColumn.setSortable(true);

        /*
            Allow the table to be sorted.
        */
        @NotNull final ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(getPossibleChildrenResults());
        getPossibleChildrenResults().addColumnSortHandler(columnSortHandler);
    }

    public void display(@NotNull final RESTProjectV1 entity, final boolean readOnly) {
        super.displayChildren(entity, readOnly);
        this.readOnly = readOnly;
    }

    @NotNull
    @Override
    public TextColumn<RESTTagCollectionItemV1> getTagsIdColumn() {
        return tagsIdColumn;
    }

    @NotNull
    @Override
    public TextColumn<RESTTagCollectionItemV1> getTagsNameColumn() {
        return tagsNameColumn;
    }
}