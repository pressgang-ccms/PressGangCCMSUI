package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

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
public class BulkImportImpl extends DialogBox implements TopicFilteredResultsAndDetailsPresenter.BulkImport {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BulkImportImpl.class.getName());

    private final FileUploadExt fileUploadExt = new FileUploadExt();
    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
    @Inject private TopicTagsPresenter.Display tagsView;
    private final FlexTable layout = new FlexTable();

    @NotNull
    @Override
    public final DialogBox getDialog() {
        return this;
    }

    @NotNull
    @Override
    public final FileUploadExt getFiles() {
        return fileUploadExt;
    }

    @NotNull
    @Override
    public final PushButton getOK() {
        return ok;
    }

    @NotNull
    @Override
    public final PushButton getCancel() {
        return cancel;
    }

    @NotNull
    @Override
    public final TopicTagsPresenter.Display getTagsView() {
        return tagsView;
    }


    public BulkImportImpl() {

    }

    /**
     * Construct the dialog box. Use the PostConstruct annotation, because
     * we reference an injected object in this code.
     */
    @PostConstruct
    private void postConstruct() {
        try {
            LOGGER.log(Level.INFO, "ENTER BulkImportImpl.postConstruct()");

            this.setWidth(Constants.HELP_DIALOG_WIDTH);
            this.setHeight(Constants.HELP_DIALOG_HEIGHT);

            this.setGlassEnabled(true);
            this.setModal(false);
            this.setText(PressGangCCMSUI.INSTANCE.BulkImageUpload());

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.SelectFiles()));
            this.layout.setWidget(0, 1, fileUploadExt);
            this.layout.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.CommonDescription()));
            this.layout.setWidget(1, 1, tagsView.getPanel());
            this.layout.setWidget(2, 0, buttonPanel);

            this.layout.getFlexCellFormatter().setColSpan(2, 0, 2);

            this.add(this.layout);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BulkImportImpl.postConstruct()");
        }
    }
}
