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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslationServerV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSTranslationDetailV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTCSTranslationDetailV1Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter that displays the content spec details.
 */
@Dependent
public class ContentSpecTranslationDetailsPresenter extends BaseTemplatePresenter {
    public static final String HISTORY_TOKEN = "ContentSpecTranslationDetailsView";

    // Empty interface declaration, similar to UiBinder
    public interface ContentSpecTranslationDetailsPresenterDriver extends SimpleBeanEditorDriver<RESTCSTranslationDetailV1, RESTCSTranslationDetailV1Editor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTCSTranslationDetailV1, RESTCSTranslationDetailV1, RESTCSTranslationDetailV1Editor> {
        void displayContentSpecTranslationDetails(final RESTTextContentSpecV1 contentSpec, final boolean readOnly, List<RESTLocaleV1> locales,
                List<RESTTranslationServerV1> translationServers);
    }

    @Nullable
    private Integer contentSpecId;

    @Inject
    private Display display;

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            contentSpecId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            contentSpecId = null;
        }
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
}
