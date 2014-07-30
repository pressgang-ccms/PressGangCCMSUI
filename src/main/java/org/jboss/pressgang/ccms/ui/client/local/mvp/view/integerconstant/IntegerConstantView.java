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

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.integerconstants.IntegerConstantPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.integerconstant.RESTIntegerConstantV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The view used to display the integer constant's details.
 */
public class IntegerConstantView extends BaseTemplateView implements IntegerConstantPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final IntegerConstantPresenter.IntegerConstantPresenterDriver driver = GWT.create(IntegerConstantPresenter.IntegerConstantPresenterDriver.class);

    private boolean readOnly = false;

    public IntegerConstantView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.IntegerConstantDetails());
    }

    @Override
    public void display(@NotNull final RESTIntegerConstantV1 entity, final boolean readonly) {
        this.readOnly = readonly;

        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTIntegerConstantV1DetailsEditor editor = new RESTIntegerConstantV1DetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

    @Override
    public IntegerConstantPresenter.IntegerConstantPresenterDriver getDriver() {
        return driver;
    }
}
