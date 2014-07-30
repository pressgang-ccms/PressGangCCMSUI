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

import com.google.gwt.user.client.ui.TextArea;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecErrorPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.Nullable;

/**
 * The view that displays the text content of a content spec
 */
public class ContentSpecErrorView extends BaseTemplateView implements ContentSpecErrorPresenter.Display {

    private final TextArea errors = new TextArea();

    public ContentSpecErrorView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ContentSpecErrors());
        errors.setReadOnly(true);
        errors.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_ERRORS);
        this.getPanel().setWidget(errors);
    }

    public void display(@Nullable final RESTTextContentSpecV1 contentSpec) {
        errors.setText("");

        if (contentSpec != null && contentSpec.getErrors() != null) {
            errors.setText(contentSpec.getErrors());
        }
    }
}
