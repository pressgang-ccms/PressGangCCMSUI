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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

public class WelcomeView extends BaseTemplateView implements WelcomePresenter.Display {

    private final FlexTable panel = new FlexTable();
    private final Frame content = new Frame();
    private final AnchorButton edit = UIUtilities.createAnchorButton(PressGangCCMSUI.INSTANCE.ContributeToThisContent());

    public WelcomeView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Welcome());

        panel.setWidget(0, 0, edit);
        panel.setWidget(1, 0, content);

        //this.getPanel().setWidget(panel);

        // just show the content. If you want an edit button, comment this out and uncomment the line above
        this.getPanel().setWidget(content);

        panel.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
        panel.getFlexCellFormatter().setHeight(1, 0, "100%");

        panel.addStyleName(CSSConstants.WelcomeView.WELCOME_VIEW_PANEL);
        content.addStyleName(CSSConstants.WelcomeView.WELCOME_VIEW_IFRAME);

        // Remove the action bar since it's not needed
        getTopActionGrandParentPanel().removeFromParent();
    }

    @Override
    protected void initialiseShortcuts() {
        super.initialiseShortcuts();
    }

    @Override
    public void displayTopicRendered(@NotNull final Integer topicXMLHoldID) {
        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                content.setUrl(serverDetails.getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" +
                        topicXMLHoldID + "&" + Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded());
            }
        });
    }

    @Override
    @NotNull
    public AnchorButton getEdit() {
        return edit;
    }
}
