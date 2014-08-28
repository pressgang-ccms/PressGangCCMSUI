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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.vectomatic.file.FileUploadExt;

/**
 * The bulk topic import dialog.
 */
@Dependent
public class BulkImportImpl extends DialogBox implements TopicFilteredResultsAndDetailsPresenter.BulkImport {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BulkImportImpl.class.getName());

    private final FileUploadExt fileUploadExt = new FileUploadExt();
    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
    @Inject
    private TopicTagsPresenter.Display tagsView;
    private final TextArea commitMessage = new TextArea();
    private final DockLayoutPanel layout = new DockLayoutPanel(Style.Unit.EM);

    @NotNull
    @Override
    public DialogBox getDialog() {
        return this;
    }

    @NotNull
    @Override
    public FileUploadExt getFiles() {
        return fileUploadExt;
    }

    @NotNull
    @Override
    public PushButton getOK() {
        return ok;
    }

    @NotNull
    @Override
    public PushButton getCancel() {
        return cancel;
    }

    @NotNull
    @Override
    public TopicTagsPresenter.Display getTagsView() {
        return tagsView;
    }

    @NotNull
    @Override
    public TextArea getCommitMessage() {
        return commitMessage;
    }


    public BulkImportImpl() {

    }

    public void setLoading() {
        this.ok.setEnabled(false);
        this.tagsView.getAdd().setEnabled(false);
        this.ok.setText(PressGangCCMSUI.INSTANCE.Loading());
        this.tagsView.getAdd().setText(PressGangCCMSUI.INSTANCE.Loading());
    }

    public void setLoaded() {
        this.ok.setEnabled(true);
        this.tagsView.getAdd().setEnabled(true);
        this.ok.setText(PressGangCCMSUI.INSTANCE.OK());
        this.tagsView.getAdd().setText(PressGangCCMSUI.INSTANCE.Add());
    }

    /**
     * Construct the dialog box. Use the PostConstruct annotation, because
     * we reference an injected object in this code.
     */
    @PostConstruct
    private void postConstruct() {
        try {
            LOGGER.log(Level.INFO, "ENTER BulkImportImpl.postConstruct()");

            this.setGlassEnabled(true);
            this.setModal(false);
            this.setText(PressGangCCMSUI.INSTANCE.BulkTopicUpload());

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.ImportDialogs.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);
            final SimplePanel buttonWrapperPanel = new SimplePanel();
            buttonWrapperPanel.setWidget(buttonPanel);

            final HorizontalPanel logMessagePanel = new HorizontalPanel();
            logMessagePanel.add(new Label(PressGangCCMSUI.INSTANCE.Message()));
            logMessagePanel.add(commitMessage);

            logMessagePanel.addStyleName(CSSConstants.ImportDialogs.IMPORT_DIALOG_MESSAGE_PANEL);
            commitMessage.addStyleName(CSSConstants.ImportDialogs.IMPORT_DIALOG_MESSAGE);

            this.layout.addNorth(fileUploadExt, 3);
            this.layout.addSouth(buttonWrapperPanel, 3);
            this.layout.addSouth(logMessagePanel, 5);
            this.layout.add(tagsView.getPanel());

            this.layout.setWidth(Constants.BULK_IMPORT_DIALOG_WIDTH);
            this.layout.setHeight(Constants.BULK_IMPORT_DIALOG_HEIGHT);

            this.add(this.layout);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BulkImportImpl.postConstruct()");
        }
    }
}
