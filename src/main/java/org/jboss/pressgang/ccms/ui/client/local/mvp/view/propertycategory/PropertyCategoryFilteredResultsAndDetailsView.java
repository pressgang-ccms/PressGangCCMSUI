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

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

public class PropertyCategoryFilteredResultsAndDetailsView extends BaseSearchAndEditView<RESTPropertyCategoryV1, RESTPropertyCategoryCollectionV1, RESTPropertyCategoryCollectionItemV1>
        implements PropertyCategoryFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryDetails());
    private final PushButton children = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryExtendedProperties());

    private final Label detailsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryDetails());
    private final Label childrenDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryExtendedProperties());

    @Override
    public PushButton getChildren() {
        return children;
    }

    @Override
    public PushButton getDetails() {
        return details;
    }

    @Override
    public Label getChildrenDown() {
        return childrenDown;
    }

    @Override
    public Label getDetailsDown() {
        return detailsDown;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    public PropertyCategoryFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedPropertyCategories());
        populateTopActionBar();
        super.initialize(true);
    }

    @Override
    protected void initialiseShortcuts() {
        super.initialiseShortcuts();
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getDetails());
        this.addActionButton(this.getChildren(), true);
        this.addActionButton(this.getSave());
    }
}