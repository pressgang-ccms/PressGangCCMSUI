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

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertyCategoryFilteredResultsView extends
        BaseFilteredResultsView<RESTPropertyCategoryCollectionItemV1> implements
        PropertyCategoryFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    @NotNull
    private final TextColumn<RESTPropertyCategoryCollectionItemV1> idColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private final TextColumn<RESTPropertyCategoryCollectionItemV1> nameColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }

            return null + "";
        }
    };

    @NotNull
    @Override
    public TextBox getNameFilter() {
        return nameFilter;
    }

    @NotNull
    @Override
    public TextBox getIdFilter() {
        return idFilter;
    }

    @NotNull
    @Override
    public TextBox getDescriptionFilter() {
        return descriptionFilter;
    }

    public PropertyCategoryFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedPropertyCategories(), PressGangCCMSUI.INSTANCE.CreateExtendedPropertyCategory());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryName());

        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryID(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}