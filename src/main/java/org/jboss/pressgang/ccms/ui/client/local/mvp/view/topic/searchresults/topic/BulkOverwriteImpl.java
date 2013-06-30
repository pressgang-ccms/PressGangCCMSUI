package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.vectomatic.file.FileUploadExt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The bulk topic import dialog.
 */
@Dependent
public class BulkOverwriteImpl extends DialogBox implements TopicFilteredResultsAndDetailsPresenter.BulkOverwrite {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BulkImportImpl.class.getName());

    private final FileUploadExt fileUploadExt = new FileUploadExt();
    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
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
    public TextArea getCommitMessage() {
        return commitMessage;
    }


    public BulkOverwriteImpl() {

    }

    /**
     * Construct the dialog box. Use the PostConstruct annotation, because
     * we reference an injected object in this code.
     */
    @PostConstruct
    private void postConstruct() {
        try {
            LOGGER.log(Level.INFO, "ENTER BulkOverwriteImpl.postConstruct()");

            this.setGlassEnabled(true);
            this.setModal(false);
            this.setText(PressGangCCMSUI.INSTANCE.BulkTopicOverwrite());

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            final HorizontalPanel logMessagePanel = new HorizontalPanel();
            logMessagePanel.add(new Label(PressGangCCMSUI.INSTANCE.Message()));
            logMessagePanel.add(commitMessage);

            logMessagePanel.addStyleName(CSSConstants.ImportDialogs.IMPORT_DIALOG_MESSAGE_PANEL);
            commitMessage.addStyleName(CSSConstants.ImportDialogs.IMPORT_DIALOG_MESSAGE);

            this.layout.addNorth(fileUploadExt, 3);
            this.layout.addSouth(buttonPanel, 3);
            this.layout.add(logMessagePanel);

            this.layout.setWidth(Constants.BULK_OVERWRITE_DIALOG_WIDTH);
            this.layout.setHeight(Constants.BULK_OVERWRITE_DIALOG_HEIGHT);

            this.add(this.layout);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BulkOverwriteImpl.postConstruct()");
        }
    }


}
