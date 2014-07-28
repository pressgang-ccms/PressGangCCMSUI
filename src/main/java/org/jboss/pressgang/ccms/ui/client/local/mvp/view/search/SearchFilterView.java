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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.filter.RESTFilterV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;

/**
 * The view that displays a filter's details.
 */
@Dependent
public class SearchFilterView extends BaseTemplateView implements SearchFilterPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final SearchFilterPresenter.FilterPresenterDriver driver = GWT.create(SearchFilterPresenter.FilterPresenterDriver.class);


    /**
     * Sets the application and page titles.
     */
    public SearchFilterView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Filters());
    }

    @Override
    public void display(final RESTFilterV1 entity, final boolean readonly) {
        /* SearchUIProjectsEditor is a grid */
        final RESTFilterV1BasicDetailsEditor editor = new RESTFilterV1BasicDetailsEditor(readonly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

    @Override
    public SimpleBeanEditorDriver<RESTFilterV1, RESTFilterV1BasicDetailsEditor> getDriver() {
        return driver;
    }
}
