package org.jboss.pressgang.ccms.ui.client.local.mvp.view.blobconstant;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants.BlobConstantPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.blobconstant.RESTBlobConstantV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The view used to display the blob constant's details.
 */
public class BlobConstantView extends BaseTemplateView implements BlobConstantPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final BlobConstantPresenter.BlobConstantPresenterDriver driver = GWT.create(BlobConstantPresenter.BlobConstantPresenterDriver.class);

    private boolean readOnly = false;

    public BlobConstantView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.BlobConstantDetails());
    }

    @Override
    public void display(@NotNull final RESTBlobConstantV1 entity, final boolean readonly) {
        this.readOnly = readonly;

        /* SearchUIProjectsEditor is a grid */
        final RESTBlobConstantV1DetailsEditor editor = new RESTBlobConstantV1DetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

    @Override
    public BlobConstantPresenter.BlobConstantPresenterDriver getDriver() {
        return driver;
    }
}
