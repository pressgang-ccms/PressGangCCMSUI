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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base.BaseSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base.BaseSearchUIFields;
import org.jetbrains.annotations.NotNull;

public abstract class BaseSearchFieldPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public interface Display<T extends BaseSearchUIFields, U extends BaseSearchFieldUIEditor<T>> extends BaseTemplateViewInterface,
            BasePopulatedEditorViewInterface<RESTFilterV1, T, U> {
        PushButton getSearchButton();

        PushButton getTagsButton();

        T getFields();

        PushButton getLocales();

        PushButton getFiltersButton();
    }

    public abstract Display getDisplay();

    @Override
    public void bindExtended() {

    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }
}
