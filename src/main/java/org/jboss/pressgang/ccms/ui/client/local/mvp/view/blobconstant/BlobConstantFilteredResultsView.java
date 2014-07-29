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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.blobconstant;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants.BlobConstantFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The view used to show the list of blob constants.
 */
public class BlobConstantFilteredResultsView extends
        BaseFilteredResultsView<RESTBlobConstantCollectionItemV1> implements
        BlobConstantFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox valueFilter = new TextBox();

    @NotNull
    private TextColumn<RESTBlobConstantCollectionItemV1> idColumn = new TextColumn<RESTBlobConstantCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTBlobConstantCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private TextColumn<RESTBlobConstantCollectionItemV1> nameColumn = new TextColumn<RESTBlobConstantCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTBlobConstantCollectionItemV1 object) {
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
    public TextBox getValueFilter() {
        return valueFilter;
    }

    public BlobConstantFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.BlobConstants(), PressGangCCMSUI.INSTANCE.CreateBlobConstant());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.BlobConstantId());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.BlobConstantName());

        addFilterField(PressGangCCMSUI.INSTANCE.BlobConstantId(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.BlobConstantName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.BlobConstantValue(), valueFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}
