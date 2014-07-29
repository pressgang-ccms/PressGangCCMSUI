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

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProjectedFilteredResultsView extends
        BaseFilteredResultsView<RESTProjectCollectionItemV1> implements
        ProjectFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    @NotNull
    private final TextColumn<RESTProjectCollectionItemV1> idColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTProjectCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private final TextColumn<RESTProjectCollectionItemV1> nameColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTProjectCollectionItemV1 object) {
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

    public ProjectedFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Projects(), PressGangCCMSUI.INSTANCE.CreateProject());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProjectID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProjectName());

        addFilterField(PressGangCCMSUI.INSTANCE.ProjectIDs(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ProjectName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ProjectDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}