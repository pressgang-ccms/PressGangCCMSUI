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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.integerconstant;

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTIntegerConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.integerconstants.IntegerConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * The view used to display the integer constants results and their details.
 */
public class IntegerConstantFilteredResultsAndDetailsView extends
        BaseSearchAndEditView<RESTIntegerConstantV1, RESTIntegerConstantCollectionV1, RESTIntegerConstantCollectionItemV1> implements
        IntegerConstantFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Save());


    @Override
    public PushButton getSave() {
        return save;
    }


    public IntegerConstantFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.IntegerConstants());
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
