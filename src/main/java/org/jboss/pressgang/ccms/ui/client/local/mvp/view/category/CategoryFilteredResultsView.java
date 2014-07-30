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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CategoryFilteredResultsView extends
        BaseFilteredResultsView<RESTCategoryCollectionItemV1> implements
        CategoryFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    @NotNull
    private TextColumn<RESTCategoryCollectionItemV1> idColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private TextColumn<RESTCategoryCollectionItemV1> nameColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTCategoryCollectionItemV1 object) {
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

    public CategoryFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories(), PressGangCCMSUI.INSTANCE.CreateCategory());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.CategoryID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.CategoryName());

        addFilterField(PressGangCCMSUI.INSTANCE.CategoryIDs(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.CategoryName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.CategoryDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}
