package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter.TagPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.tagview.RESTTagV1BasicDetailsEditor;

public class TagView extends BaseTemplateView implements TagPresenter.Display {

    /** The GWT Editor Driver */
    private final TagPresenterDriver driver = GWT.create(TagPresenterDriver.class);

    private boolean readOnly = false;

    @Override
    public TagPresenterDriver getDriver() {
        return driver;
    }

    public TagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagDetails());
    }


    @Override
    public void initialize(final RESTTagV1 tag, final boolean readOnly) {
        this.readOnly = readOnly;

        /* SearchUIProjectsEditor is a grid */
        final RESTTagV1BasicDetailsEditor editor = new RESTTagV1BasicDetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(tag);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }
}
