package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertycategory;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertycategory.RESTPropertyCategoryV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertytag.RESTPropertyTagV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The view used to display the property tagincategory details via the RESTPropertyTagV1DetailsEditor class.
 */
public class PropertyCategoryView extends BaseTemplateView implements PropertyCategoryPresenter.Display {


    /**
     * The GWT Editor Driver
     */
    private final PropertyCategoryPresenter.PropertyCategoryPresenterDriver driver = GWT.create(PropertyCategoryPresenter.PropertyCategoryPresenterDriver.class);

    private boolean readOnly = false;

    @Override
    public PropertyCategoryPresenter.PropertyCategoryPresenterDriver getDriver() {
        return driver;
    }

    public PropertyCategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedPropertyCategories());
    }

    @Override
    public void display(final RESTPropertyCategoryV1 entity, final boolean readOnly) {
        this.readOnly = readOnly;

        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTPropertyCategoryV1DetailsEditor editor = new RESTPropertyCategoryV1DetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }


}