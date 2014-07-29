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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter used to add logic to the category view.
 */
@Dependent
public class CategoryPresenter extends
        BaseTemplatePresenter
        implements BaseTemplatePresenterInterface {

    // Empty interface declaration, similar to UiBinder
    public interface CategoryPresenterDriver extends SimpleBeanEditorDriver<RESTCategoryV1, RESTCategoryV1BasicDetailsEditor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTCategoryV1, RESTCategoryV1, RESTCategoryV1BasicDetailsEditor> {

    }

    /**
     * The history token
     */
    public static final String HISTORY_TOKEN = "CategoryView";

    /**
     * The id of the category that is being viewed
     */
    @Nullable
    private Integer categoryId;

    /**
     * The category view
     */
    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    protected void go() {
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindExtended() {

    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            categoryId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            categoryId = null;
        }
    }
}
