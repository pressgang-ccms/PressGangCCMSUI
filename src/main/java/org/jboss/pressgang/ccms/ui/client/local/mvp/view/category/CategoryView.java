package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.PushButton;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;

import static org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter.CategoryPresenterDriver;

public class CategoryView extends BaseTemplateView implements CategoryPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CategoryDetails());
    private final PushButton children = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CategoryTags());
    
    /** The GWT Editor Driver */
    private final CategoryPresenterDriver driver = GWT.create(CategoryPresenterDriver.class);

    private boolean readOnly = false;
    
    @Override
    public PushButton getChildren() {
        return children;
    }

    @Override
    public PushButton getDetails() {
        return details;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return driver;
    }

    public CategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.CategoryDetails());
        populateTopActionBar();
    }

    @Override
    public void initialize(final RESTCategoryV1 category, final boolean readOnly) {
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
    
    private void populateTopActionBar() {
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.CategoryDetails()));
        this.addActionButton(this.getChildren());
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
    }
}
