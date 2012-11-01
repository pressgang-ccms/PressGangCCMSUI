package org.jboss.pressgang.ccms.ui.client.local.mvp.view.project;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectPresenter.ProjectPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.projectview.RESTProjectV1BasicDetailsEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.PushButton;

public class ProjectView extends BaseTemplateView<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1>
        implements ProjectPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ProjectDetails());
    private final PushButton children = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ProjectTags());
  
    /** The GWT Editor Driver */
    private final ProjectPresenterDriver driver = GWT.create(ProjectPresenterDriver.class);

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

    public ProjectView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ProjectDetails());
        populateTopActionBar();
    }

    @Override
    public void initialize(final RESTProjectV1 entity, final boolean readOnly) {
        this.readOnly = readOnly;

        /* SearchUIProjectsEditor is a grid */
        final RESTProjectV1BasicDetailsEditor editor = new RESTProjectV1BasicDetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

    private void populateTopActionBar() {
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.ProjectDetails()));
        this.addActionButton(this.getChildren());
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
    }
}