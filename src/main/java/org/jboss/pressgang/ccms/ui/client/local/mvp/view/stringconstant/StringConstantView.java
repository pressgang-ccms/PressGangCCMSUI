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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.stringconstant;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants.StringConstantPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.stringconstant.RESTStringConstantV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The view used to display the integer constant's details.
 */
public class StringConstantView extends BaseTemplateView implements StringConstantPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final StringConstantPresenter.StringConstantPresenterDriver driver = GWT.create(StringConstantPresenter.StringConstantPresenterDriver.class);

    private boolean readOnly = false;

    public StringConstantView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.StringConstantDetails());
    }

    @Override
    public void display(@NotNull final RESTStringConstantV1 entity, final boolean readonly) {
        this.readOnly = readonly;

        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTStringConstantV1DetailsEditor editor = new RESTStringConstantV1DetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

    @Override
    public StringConstantPresenter.StringConstantPresenterDriver getDriver() {
        return driver;
    }
}
