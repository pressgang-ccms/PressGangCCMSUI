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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertycategory;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertycategory.RESTPropertyCategoryV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The view used to display the property tag details via the RESTPropertyTagV1DetailsEditor class.
 */
public class PropertyCategoryView extends BaseTemplateView implements PropertyCategoryPresenter.Display {


    /**
     * The GWT Editor Driver
     */
    private final PropertyCategoryPresenter.PropertyCategoryPresenterDriver driver = GWT.create(PropertyCategoryPresenter.PropertyCategoryPresenterDriver.class);

    private boolean readOnly = false;

    @Override
    public PropertyCategoryPresenter.PropertyCategoryPresenterDriver getDriver() {
        return driver;
    }

    public PropertyCategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedPropertyCategories());
    }

    @Override
    public void display(final RESTPropertyCategoryV1 entity, final boolean readOnly) {
        this.readOnly = readOnly;

        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTPropertyCategoryV1DetailsEditor editor = new RESTPropertyCategoryV1DetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }


}