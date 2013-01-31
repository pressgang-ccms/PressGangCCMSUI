package org.jboss.pressgang.ccms.ui.client.local.mvp.view.project;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

public class ProjectsFilteredResultsAndProjectView extends
        BaseSearchAndEditView<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> implements
        ProjectsFilteredResultsAndProjectPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ProjectDetails());
    private final PushButton children = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ProjectTags());

    private final Label detailsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.ProjectDetails());
    private final Label childrenDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.ProjectTags());

    @Override
    public PushButton getChildren() {
        return children;
    }

    @Override
    public PushButton getDetails() {
        return details;
    }

    @Override
    public Label getChildrenDown() {
        return childrenDown;
    }

    @Override
    public Label getDetailsDown() {
        return detailsDown;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    public ProjectsFilteredResultsAndProjectView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Projects());
        populateTopActionBar();
    }

    private void populateTopActionBar() {
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.ProjectDetails()));
        this.addActionButton(this.getChildren());
        this.addActionButton(this.getSave());
    }
}