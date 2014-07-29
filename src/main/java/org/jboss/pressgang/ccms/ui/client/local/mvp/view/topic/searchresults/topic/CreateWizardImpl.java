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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

@Dependent
public class CreateWizardImpl extends DialogBox implements TopicFilteredResultsAndDetailsPresenter.CreateWizard {
    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
    private final FlexTable layout = new FlexTable();
    private final Label formatLabel = new Label(PressGangCCMSUI.INSTANCE.TopicFormat());
    private final ListBox format = new ListBox();
    private final Label typesLabel = new Label(PressGangCCMSUI.INSTANCE.TopicType());
    private final ListBox types = new ListBox();
    private final Label localeLabel = new Label(PressGangCCMSUI.INSTANCE.TopicLocale());
    private final ListBox locales = new ListBox();
    private final Label topicTitleLabel = new Label(PressGangCCMSUI.INSTANCE.TopicTitle());
    private final TextBox topicTitle = new TextBox();

    @NotNull
    @Override
    public DialogBox getDialog() {
        return this;
    }

    @Override
    public ListBox getTypes() {
        return types;
    }

    @Override
    public ListBox getFormats() {
        return format;
    }

    @Override
    public ListBox getLocales() {
        return locales;
    }

    @Override
    public TextBox getTopicTitle() {
        return topicTitle;
    }

    @Override
    public PushButton getOk() {
        return ok;
    }

    @Override
    public PushButton getCancel() {
        return cancel;
    }

    /**
     * Construct the dialog box. Use the PostConstruct annotation, because
     * we reference an injected object in this code.
     */
    @PostConstruct
    private void postConstruct() {
        setGlassEnabled(true);
        setModal(false);
        setText(PressGangCCMSUI.INSTANCE.CreateTopic());

        final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
        buttonPanel.add(cancel);
        buttonPanel.add(ok);

        layout.setWidget(0, 0, topicTitleLabel);
        layout.setWidget(0, 1, topicTitle);
        layout.setWidget(1, 0, formatLabel);
        layout.setWidget(1, 1, format);
        layout.setWidget(2, 0, typesLabel);
        layout.setWidget(2, 1, types);
        layout.setWidget(3, 0, localeLabel);
        layout.setWidget(3, 1, locales);
        layout.setWidget(4, 0, buttonPanel);

        layout.getFlexCellFormatter().setColSpan(4, 0, 2);

        // Add the formats
        format.clear();
        for (final RESTXMLFormat docType : RESTXMLFormat.values()) {
            format.addItem(docType.getCommonName(), docType.name());
        }
        format.setSelectedIndex(0);

        add(layout);
    }
}
