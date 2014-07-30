/*
  Copyright 2011-2014 Red Hat, Inc

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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CategoryTagView
        extends BaseOrderedChildrenView<
        RESTCategoryV1,
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,
        RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>
        implements CategoryTagPresenter.Display {

    private boolean readOnly = false;

    @NotNull
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

    @NotNull
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
                if (ComponentCategoryV1.containsTag(getOriginalEntity(), object.getItem().getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }
            button.setEnabled(false);
            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagIdColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagInCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";

        }
    };

    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagNameColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagInCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };

    private final DisableableButtonCell up = new DisableableButtonCell();
    private final Column<RESTTagInCategoryCollectionItemV1, String> tagUpButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(up) {
        @NotNull
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            up.setEnabled(!readOnly);
            return PressGangCCMSUI.INSTANCE.Up();
        }
    };

    private final DisableableButtonCell down = new DisableableButtonCell();
    private final Column<RESTTagInCategoryCollectionItemV1, String> tagDownButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(down) {
        @NotNull
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            down.setEnabled(!readOnly);
            return PressGangCCMSUI.INSTANCE.Down();
        }
    };

    @NotNull
    @Override
    public Column<RESTTagCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return tagsButtonColumn;
    }

    @Override
    public void display(@NotNull final RESTCategoryV1 originalEntity, final boolean readOnly) {
        super.displayChildren(originalEntity, readOnly);
        this.readOnly = readOnly;
    }

    public CategoryTagView() {
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

        getExistingChildrenResults().addColumn(tagIdColumn, PressGangCCMSUI.INSTANCE.ChildTagID());
        getExistingChildrenResults().addColumn(tagNameColumn, PressGangCCMSUI.INSTANCE.ChildTagName());
        getExistingChildrenResults().addColumn(tagUpButtonColumn, PressGangCCMSUI.INSTANCE.Up());
        getExistingChildrenResults().addColumn(tagDownButtonColumn, PressGangCCMSUI.INSTANCE.Down());

        addExistingChildrenPanel();
    }

    @NotNull
    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getExistingChildUpButtonColumn() {
        return tagUpButtonColumn;
    }

    @NotNull
    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getExistingChildDownButtonColumn() {
        return tagDownButtonColumn;
    }

    @NotNull
    public TextColumn<RESTTagCollectionItemV1> getTagsIdColumn() {
        return tagsIdColumn;
    }

    @NotNull
    public TextColumn<RESTTagCollectionItemV1> getTagsNameColumn() {
        return tagsNameColumn;
    }
}
