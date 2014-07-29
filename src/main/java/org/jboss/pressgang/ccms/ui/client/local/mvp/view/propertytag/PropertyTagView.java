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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertytag;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertytag.RESTPropertyTagV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The view used to display the property tag details via the RESTPropertyTagV1DetailsEditor class.
 */
public class PropertyTagView extends BaseTemplateView implements PropertyTagPresenter.Display {


    /**
     * The GWT Editor Driver
     */
    private final PropertyTagPresenter.PropertyTagPresenterDriver driver = GWT.create(PropertyTagPresenter.PropertyTagPresenterDriver.class);

    private boolean readOnly = false;

    @Override
    public PropertyTagPresenter.PropertyTagPresenterDriver getDriver() {
        return driver;
    }

    public PropertyTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedProperties());
    }

    @Override
    public void display(final RESTPropertyTagV1 entity, final boolean readOnly) {
        this.readOnly = readOnly;

        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTPropertyTagV1DetailsEditor editor = new RESTPropertyTagV1DetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }


}