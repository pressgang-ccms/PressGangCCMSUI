package org.jboss.pressgang.ccms.ui.client.local.mvp.view.image;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.vectomatic.file.FileUploadExt;

public class ImagesFilteredResultsAndImageView extends
        BaseSearchAndEditView<RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1> implements
        ImagesFilteredResultsAndDetailsPresenter.Display {

    @NotNull
    @Override
    public BulkUploadDisplay getBulkUploadDialog() {
        return bulkUploadDialog;
    }

    public class BulkUploadDisplayDialog extends DialogBox implements BulkUploadDisplay {

        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final TextArea description = new TextArea();
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

        public BulkUploadDisplayDialog() {
            this.setGlassEnabled(true);
            /* If true, this interferes with the "After the Deadline" plugin */
            this.setModal(false);
            this.setText(PressGangCCMSUI.INSTANCE.BulkImageUpload());

            this.description.addStyleName(CSSConstants.BulkImageUploadDialog.DESCRIPTION_FIELD);

            @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.SelectFiles()));
            this.layout.setWidget(0, 1, files);
            this.layout.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.CommonDescription()));
            this.layout.setWidget(1, 1, description);
            this.layout.setWidget(2, 0, buttonPanel);

            this.layout.getFlexCellFormatter().setColSpan(2, 0, 2);

            this.add(this.layout);
        }
    }

    private final BulkUploadDisplay bulkUploadDialog = new BulkUploadDisplayDialog();

    public ImagesFilteredResultsAndImageView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images());
        super.initialize(true);
    }

    @Override
    protected void initialiseShortcuts() {
        super.initialiseShortcuts();
    }
}
