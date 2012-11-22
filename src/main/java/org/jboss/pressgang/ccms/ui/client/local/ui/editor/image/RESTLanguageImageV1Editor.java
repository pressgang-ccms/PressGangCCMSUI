package org.jboss.pressgang.ccms.ui.client.local.ui.editor.image;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.vectomatic.file.FileUploadExt;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Provides a UI for viewing and editing a RESTLanguageImageV1 object.
 * 
 * We can't bind to a byte array directly (http://code.google.com/p/google-web-toolkit/issues/detail?id=6600), so we need to use
 * the ValueAwareEditor to do some manual binding.
 * 
 * @author Matthew Casperson
 * 
 */
public class RESTLanguageImageV1Editor extends FlexTable implements ValueAwareEditor<RESTLanguageImageCollectionItemV1> {
    private static final String JPG_BASE64_PREFIX = "data:image/jpg;base64,";

    /** Keep a reference to the object this editor gets its values from */
    public RESTLanguageImageCollectionItemV1 self;

    /**
     * To set the name of a tab based on the object that initializes this editor we need to keep a track of the tab layout and
     * the index that this editor is assigned to. See http://stackoverflow.com/questions/10463782/adding-list
     * -sub-editors-to-tab-panel for an explanation. This is the reference to the tab panel
     */
    private final TabLayoutPanel parentPanel;

    /**
     * This is a reference to the tab index
     */
    private final int parentIndex;

    private final TextBox filename = new TextBox();
    private final TextBox dimensions = new TextBox();
    private final Image imageDataBase64 = new Image();
    private final FileUploadExt upload = new FileUploadExt(false);
    private final PushButton uploadButton = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Upload());

    private final Label filenameLabel = new Label(PressGangCCMSUI.INSTANCE.ImageFilename());
    private final Label dimensionsLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDimensions());
    private final Label imageLabel = new Label(PressGangCCMSUI.INSTANCE.ImageSample());
    private final Label newFileLabel = new Label(PressGangCCMSUI.INSTANCE.UploadFile());

    @Ignore
    public TextBox getFilename() {
        return filename;
    }

    @Ignore
    public Image getImageDataBase64() {
        return imageDataBase64;
    }

    @Ignore
    public FileUploadExt getUpload() {
        return upload;
    }

    @Ignore
    public PushButton getUploadButton() {
        return uploadButton;
    }

    public RESTLanguageImageV1Editor(final TabLayoutPanel parentPanel, final int parentIndex) {
        this.addStyleName(CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_TAB);

        filename.setReadOnly(true);
        dimensions.setReadOnly(true);

        this.parentPanel = parentPanel;
        this.parentIndex = parentIndex;

        final HorizontalPanel uploadPanel = new HorizontalPanel();
        uploadPanel.add(upload);
        uploadPanel.add(uploadButton);

        this.setWidget(0, 0, newFileLabel);
        this.setWidget(0, 1, uploadPanel);
        this.setWidget(1, 0, filenameLabel);
        this.setWidget(1, 1, filename);
        this.setWidget(2, 0, dimensionsLabel);
        this.setWidget(2, 1, dimensions);
        this.setWidget(3, 0, imageLabel);
        this.setWidget(3, 1, imageDataBase64);

        filenameLabel.addStyleName(CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_LABEL);
        filename.addStyleName(CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_TEXT);
        imageLabel.addStyleName(CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_LABEL);
        imageDataBase64.addStyleName(CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_IMAGE);

        this.getCellFormatter().addStyleName(0, 0, CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_UPLOAD_LABEL_CELL);
        this.getCellFormatter().addStyleName(0, 1, CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_UPLOAD_BUTTONS_CELL);
        this.getCellFormatter().addStyleName(1, 0, CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_LABEL_CELL);
        this.getCellFormatter().addStyleName(1, 1, CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_TEXT_CELL);
        this.getCellFormatter().addStyleName(2, 0, CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_DIMENSION_LABEL_CELL);
        this.getCellFormatter().addStyleName(2, 1, CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_DIMENSION_TEXT_CELL);
        this.getCellFormatter().addStyleName(3, 0, CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_LABEL_CELL);
        this.getCellFormatter().addStyleName(3, 1, CSSConstants.IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_IMAGE_CELL);
    }

    @Override
    public void setDelegate(final EditorDelegate<RESTLanguageImageCollectionItemV1> delegate) {
        // TODO Auto-generated method stub
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPropertyChange(final String... paths) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setValue(final RESTLanguageImageCollectionItemV1 value) {
        this.self = value;

        if (value.getItem().getFilename() != null) {
            this.filename.setText(value.getItem().getFilename());
        }

        if (value.getItem().getImageDataBase64() != null) {
            final String base64 = GWTUtilities.getStringUTF8(value.getItem().getImageDataBase64());
            this.imageDataBase64.addLoadHandler(new LoadHandler(){

                @Override
                public void onLoad(final LoadEvent event) {
                    dimensions.setText(imageDataBase64.getWidth() + "x" + imageDataBase64.getHeight());
                }});
            this.imageDataBase64.setUrl(JPG_BASE64_PREFIX + base64);

        }

        if (value.getItem().getLocale() != null) {
            parentPanel.setTabText(parentIndex, value.getItem().getLocale());
        }
    }
}
