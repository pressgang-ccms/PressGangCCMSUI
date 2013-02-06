package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter.CategoryPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;

public class CategoryView extends BaseTemplateView implements CategoryPresenter.Display {



    /** The GWT Editor Driver */
    private final CategoryPresenterDriver driver = GWT.create(CategoryPresenterDriver.class);

    private boolean readOnly = false;

    @Override
    public CategoryPresenterDriver getDriver() {
        return driver;
    }

    public CategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.CategoryDetails());
    }

    @Override
    public void display(final RESTCategoryV1 category, final boolean readOnly) {
        this.readOnly = readOnly;

        /* SearchUIProjectsEditor is a grid */
        final RESTCategoryV1BasicDetailsEditor editor = new RESTCategoryV1BasicDetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(category);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }
}
