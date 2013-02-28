package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertytag;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertytag.RESTPropertyTagV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The view used to display the property tag details via the RESTPropertyTagV1DetailsEditor class.
 */
public class PropertyTagView extends BaseTemplateView implements PropertyTagPresenter.Display {


    /**
     * The GWT Editor Driver
     */
    private final PropertyTagPresenter.PropertyTagPresenterDriver driver = GWT.create(PropertyTagPresenter.PropertyTagPresenterDriver.class);

    private boolean readOnly = false;

    @Override
    public PropertyTagPresenter.PropertyTagPresenterDriver getDriver() {
        return driver;
    }

    public PropertyTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedProperties());
    }

    @Override
    public void display(final RESTPropertyTagV1 entity, final boolean readOnly) {
        this.readOnly = readOnly;

        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTPropertyTagV1DetailsEditor editor = new RESTPropertyTagV1DetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }


}