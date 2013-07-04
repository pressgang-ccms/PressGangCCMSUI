package org.jboss.pressgang.ccms.ui.client.local.mvp.view.file;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFileCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFileCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFileV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file.FilesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.vectomatic.file.FileUploadExt;

public class FilesFilteredResultsAndFileView extends
        BaseSearchAndEditView<RESTFileV1, RESTFileCollectionV1, RESTFileCollectionItemV1> implements
        FilesFilteredResultsAndDetailsPresenter.Display {

    @NotNull
    @Override
    public BulkUploadDisplay getBulkUploadDialog() {
        return bulkUploadDialog;
    }

    public class BulkUploadDisplayDialog extends DialogBox implements BulkUploadDisplay {

        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final TextArea description = new TextArea();
        private final TextBox filePath = new TextBox();
        private final FileUploadExt files = new FileUploadExt(true);
        private final FlexTable layout = new FlexTable();

        @NotNull
        @Override
        public DialogBox getDialogBox() {
            return this;
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
        public FileUploadExt getFiles() {
            return files;
        }

        @NotNull
        @Override
        public TextArea getDescription() {
            return description;
        }

        @NotNull
        @Override
        public TextBox getFilePath() {
            return filePath;
        }

        public BulkUploadDisplayDialog() {
            this.setGlassEnabled(true);
            /* If true, this interferes with the "After the Deadline" plugin */
            this.setModal(false);
            this.setText(PressGangCCMSUI.INSTANCE.BulkFileUpload());

            description.addStyleName(CSSConstants.BulkFileUploadDialog.DESCRIPTION_FIELD);
            filePath.addStyleName(CSSConstants.BulkFileUploadDialog.FILE_PATH_FIELD);

            @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.SelectFiles()));
            this.layout.setWidget(0, 1, files);
            this.layout.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.CommonDescription()));
            this.layout.setWidget(1, 1, description);
            this.layout.setWidget(2, 0, new Label(PressGangCCMSUI.INSTANCE.FilePath()));
            this.layout.setWidget(2, 1, filePath);
            this.layout.setWidget(3, 0, buttonPanel);

            this.layout.getFlexCellFormatter().setColSpan(3, 0, 2);

            this.add(this.layout);
        }
    }

    private final BulkUploadDisplay bulkUploadDialog = new BulkUploadDisplayDialog();

    public FilesFilteredResultsAndFileView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
        addSpacerToShortcutPanels();
        super.initialize(true);
    }

}
