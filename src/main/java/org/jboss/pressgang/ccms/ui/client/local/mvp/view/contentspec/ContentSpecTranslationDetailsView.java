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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslationServerV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSTranslationDetailV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecTranslationDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTCSTranslationDetailV1Editor;
import org.jetbrains.annotations.NotNull;

public class ContentSpecTranslationDetailsView extends BaseTemplateView implements ContentSpecTranslationDetailsPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final ContentSpecTranslationDetailsPresenter.ContentSpecTranslationDetailsPresenterDriver driver = GWT.create(ContentSpecTranslationDetailsPresenter.ContentSpecTranslationDetailsPresenterDriver.class);

    @Override
    public ContentSpecTranslationDetailsPresenter.ContentSpecTranslationDetailsPresenterDriver getDriver() {
        return driver;
    }

    public ContentSpecTranslationDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TranslationProperties());

    }

    @Override
    public void display(final RESTCSTranslationDetailV1 translationDetail, final boolean readOnly) {
        throw new UnsupportedOperationException("ContentSpecTranslationDetailsView.display() is not supported. Use " +
                "ContentSpecTranslationDetailsView.displayContentSpecTranslationDetails() instead.");
    }

    @Override
    public void displayContentSpecTranslationDetails(@NotNull final RESTTextContentSpecV1 contentSpec, final boolean readOnly,
            final List<RESTLocaleV1> locales, final List<RESTTranslationServerV1> translationServers) {
        // Make sure we have a translation details value
        if (contentSpec.getTranslationDetails() == null) {
            contentSpec.setTranslationDetails(new RESTCSTranslationDetailV1());
        }

        // Clone the locales and remove the content spec locale
        final List<RESTLocaleV1> fixedLocales = new ArrayList<RESTLocaleV1>(locales);
        fixedLocales.remove(contentSpec.getLocale());

        final RESTCSTranslationDetailV1Editor editor = new RESTCSTranslationDetailV1Editor(readOnly, fixedLocales, translationServers);
        // Initialize the driver with the top-level editor
        driver.initialize(editor);
        // Copy the data in the object into the UI
        driver.edit(contentSpec.getTranslationDetails());
        getPanel().setWidget(editor);
    }

}
