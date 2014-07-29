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

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants.BlobConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * The view used to display the blob constants results and their details.
 */
public class BlobConstantFilteredResultsAndDetailsView extends BaseSearchAndEditView<RESTBlobConstantV1, RESTBlobConstantCollectionV1,
        RESTBlobConstantCollectionItemV1> implements BlobConstantFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Save());


    @Override
    public PushButton getSave() {
        return save;
    }


    public BlobConstantFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.BlobConstants());
        populateTopActionBar();
        super.initialize(true);
    }

    @Override
    protected void initialiseShortcuts() {
        super.initialiseShortcuts();
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getSave());
    }
}
