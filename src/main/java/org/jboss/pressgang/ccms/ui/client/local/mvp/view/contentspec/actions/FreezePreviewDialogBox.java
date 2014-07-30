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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec.actions;

import javax.enterprise.context.Dependent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClosablePopup;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions.FreezePresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

@Dependent
public class FreezePreviewDialogBox extends ClosablePopup implements FreezePresenter.PreviewDialogBox {
    private final PushButton close = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Close());
    private final FlexTable layout = new FlexTable();
    private final TextArea text = new TextArea();
    private HandlerRegistration handlerRegistration;

    @Override
    public PushButton getClose() {
        return close;
    }

    @NotNull
    @Override
    public String getText() {
        return text.getText();
    }

    @NotNull
    @Override
    public void setText(final String text) {
        String cleanedText = text;
        if (text != null) {
            // Tabs won't be rendered in some browsers or will be inconsistent so change it to 4 spaces
            cleanedText = cleanedText.replace("\t", "    ");
        }
        this.text.setText(cleanedText);
    }

    @Override
    public ClosablePopup getDialogBox() {
        return this;
    }

    public FreezePreviewDialogBox() {
        super(PressGangCCMSUI.INSTANCE.Preview(), true);
        setGlassEnabled(true);

        addStyleName(CSSConstants.FreezePreviewDialog.PREVIEW_DIALOG_BOX);
        text.addStyleName(CSSConstants.FreezePreviewDialog.PREVIEW_FIELD);
        text.setReadOnly(true);

        @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
        buttonPanel.add(close);

        layout.setWidget(0, 0, text);
        layout.setWidget(1, 0, buttonPanel);

        add(layout);

        // Add the close handler
        close.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });
    }

    @Override
    public void show() {
        // Add a handler to deal with window resizes
        if (handlerRegistration == null) {
            handlerRegistration = Window.addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(final ResizeEvent event) {
                    center();
                }
            });
        }
        super.show();
    }

    @Override
    public void hide() {
        if (handlerRegistration != null) {
            handlerRegistration.removeHandler();
            handlerRegistration = null;
        }
        super.hide();
    }
}
