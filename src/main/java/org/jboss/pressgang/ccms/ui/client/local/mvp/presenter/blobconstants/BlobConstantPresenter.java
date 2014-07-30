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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.blobconstant.RESTBlobConstantV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter used to display the blob constants details.
 */
@Dependent
public class BlobConstantPresenter extends BaseTemplatePresenter {

    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "BlobConstantView";

    @Inject
    private Display display;

    /**
     * The view this presenter is associated with.
     */
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public void bindExtended() {

    }

    @Override
    protected void go() {
        bindExtended();
    }

    @Override
    public void close() {

    }

    // Empty interface declaration, similar to UiBinder
    public interface BlobConstantPresenterDriver extends SimpleBeanEditorDriver<RESTBlobConstantV1, RESTBlobConstantV1DetailsEditor> {
    }

    /**
     * The display that this presenter is associated with.
     */
    public interface Display extends BasePopulatedEditorViewInterface<RESTBlobConstantV1, RESTBlobConstantV1, RESTBlobConstantV1DetailsEditor> {
        /**
         * @return The editor that has bound the REST entity to the UI elements displayed by the view.
         */
        RESTBlobConstantV1DetailsEditor getEditor();
    }
}
