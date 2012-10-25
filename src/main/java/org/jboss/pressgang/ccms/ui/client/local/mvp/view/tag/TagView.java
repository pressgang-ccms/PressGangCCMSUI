package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter.TagPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.tagview.RESTTagV1BasicDetailsEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.PushButton;

public class TagView extends BaseTemplateView implements TagPresenter.Display {
    
    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton tagDetails = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagDetails());
    private final PushButton tagProjects = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagProjects());
    private final PushButton tagCategories = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagCategories());

    @Override
    public PushButton getTagCategories() {
        return tagCategories;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    @Override
    public PushButton getTagProjects() {
        return tagProjects;
    }

    @Override
    public PushButton getTagDetails() {
        return tagDetails;
    }

    /** The GWT Editor Driver */
    private final TagPresenterDriver driver = GWT.create(TagPresenterDriver.class);

    private boolean readOnly = false;

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return driver;
    }

    public TagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagDetails());
        populateTopActionBar();
    }


    private void populateTopActionBar() {
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TagDetails()));
        this.addActionButton(this.getTagProjects());
        this.addActionButton(this.getTagCategories());
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
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
