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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertytag.RESTPropertyTagV1DetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter for the property tag details view.
 */
@Dependent
public class PropertyTagPresenter extends BaseTemplatePresenter {

    // Empty interface declaration, similar to UiBinder
    public interface PropertyTagPresenterDriver extends SimpleBeanEditorDriver<RESTPropertyTagV1, RESTPropertyTagV1DetailsEditor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTPropertyTagV1, RESTPropertyTagV1, RESTPropertyTagV1DetailsEditor> {

    }

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "PropertyTagView";

    @Nullable
    private Integer entityId;

    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            entityId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            entityId = null;
        }
    }

    @Override
    protected void go() {
        bindExtended();
    }

    @Override
    public void close() {

    }

    public void bindExtended() {

    }

    /**
     * Get the category from the database and use it to populate the editor in the view
     */
    public void getEntity(@NotNull final Integer entityId) {
        final RESTCallBack<RESTPropertyTagV1> callback = new RESTCallBack<RESTPropertyTagV1>() {
            @Override
            public void success(@NotNull final RESTPropertyTagV1 retValue) {
                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(boolean readOnly) {
                        display.display(retValue, readOnly);
                    }
                });
            }
        };

        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getPropertyTag(entityId), callback, display);
    }
}
