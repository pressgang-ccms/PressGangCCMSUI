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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertycategory;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyTagInPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyTagInPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentPropertyCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyTagInPropertyCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * The view used to display a property tag's categories.
 */
public class PropertyCategoryTagView extends BaseChildrenView<
        RESTPropertyCategoryV1,                                                                                                                         // The main REST types
        RESTPropertyTagCollectionItemV1,                                                                                                                // The possible children types
        RESTPropertyTagInPropertyCategoryV1, RESTPropertyTagInPropertyCategoryCollectionV1, RESTPropertyTagInPropertyCategoryCollectionItemV1>          // The existing children types
        implements PropertyCategoryTagPresenter.Display {

    private boolean readOnly = false;

    private final TextColumn<RESTPropertyTagCollectionItemV1> tagsIdColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTPropertyTagCollectionItemV1 object) {
            checkArgument(object == null || (object.getItem() != null && object.getItem().getName() != null), "object should be null or it should have a valid item and the item should have a valid id");

            if (object != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    private final TextColumn<RESTPropertyTagCollectionItemV1> tagsNameColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTPropertyTagCollectionItemV1 object) {
            checkArgument(object == null || (object.getItem() != null && object.getItem().getName() != null), "object should be null or it should have a valid item and the item should have a valid name");

            if (object != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };

    /**
     * The column used to render the property category's id.
     */
    @NotNull
    @Override
    public TextColumn<RESTPropertyTagCollectionItemV1> getTagsIdColumn() {
        return tagsIdColumn;
    }

    /**
     * The column used to render the property category's name.
     */
    @NotNull
    @Override
    public TextColumn<RESTPropertyTagCollectionItemV1> getTagsNameColumn() {
        return tagsNameColumn;
    }

    private final DisableableButtonCell button = new DisableableButtonCell();
    /**
     * The column used to render the property tag category's add/remove button.
     */
    private final Column<RESTPropertyTagCollectionItemV1, String> tagsButtonColumn = new Column<RESTPropertyTagCollectionItemV1, String>(button) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTPropertyTagCollectionItemV1 object) {
            checkState(getOriginalEntity() != null, "getOriginalEntity() should not be null");
            checkArgument(object == null || (object.getItem() != null && object.getItem().getId() != null), "object should be null or it should have a valid item and the item should have a valid id");
            button.setEnabled(!readOnly);
            if (object != null) {
                if (ComponentPropertyCategoryV1.isInCategory(getOriginalEntity(), object.getItem().getId())) {
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
    public Column<RESTPropertyTagCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return tagsButtonColumn;
    }

    public PropertyCategoryTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedPropertyCategories());

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

    public void display(@NotNull final RESTPropertyCategoryV1 entity, final boolean readOnly) {
        super.displayChildren(entity, readOnly);
        this.readOnly = readOnly;
    }


}