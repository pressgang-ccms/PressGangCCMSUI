package org.jboss.pressgang.ccms.ui.client.local.ui.editor.blobconstant;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.vectomatic.file.FileUploadExt;

/**
 * An editor used to bind the blob constant's details to ui elements.
 */
public class RESTBlobConstantV1DetailsEditor extends Grid implements ValueAwareEditor<RESTBlobConstantV1> {
    private static final int ROWS = 3;
    private static final int COLS = 2;

    /**
     * Keep a reference to the object this editor gets its values from.
     */
    public RESTBlobConstantV1 self;

    private final SimpleIntegerBox id = new SimpleIntegerBox();
    private final TextBox name = new TextBox();
    private final FileUploadExt upload = new FileUploadExt(false);
    private final PushButton uploadButton = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Upload());

    public SimpleIntegerBox idEditor() {
        return id;
    }

    public TextBox nameEditor() {
        return name;
    }

    public PushButton getUploadButton() {
        return uploadButton;
    }

    public FileUploadExt getUpload() {
        return upload;
    }

    public RESTBlobConstantV1DetailsEditor(final boolean readOnly) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.BlobConstantView.BLOB_CONSTANT_VIEW_PANEL);
        id.addStyleName(CSSConstants.BlobConstantView.BLOB_CONSTANT_VIEW_ID_FIELD);
        name.addStyleName(CSSConstants.BlobConstantView.BLOB_CONSTANT_VIEW_NAME_FIELD);

        id.setReadOnly(true);

        final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.BlobConstantId());
        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);

        final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.BlobConstantName());
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);

        final Label uploadLabel = new Label(PressGangCCMSUI.INSTANCE.Upload());
        this.setWidget(2, 0, uploadLabel);
        final HorizontalPanel uploadPanel = new HorizontalPanel();
        this.setWidget(2, 1, uploadPanel);
        uploadPanel.add(upload);
        uploadPanel.add(uploadButton);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TOPIC_VIEW_LABEL);
        }

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TOPIC_VIEW_DETAIL);
        }
    }

    @Override
    public void flush() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPropertyChange(final String... paths) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setValue(final RESTBlobConstantV1 value) {
        this.self = value;
        this.id.setText(value.getId().toString());
        this.name.setText(value.getName());
    }

    @Override
    public void setDelegate(final EditorDelegate<RESTBlobConstantV1> delegate) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
