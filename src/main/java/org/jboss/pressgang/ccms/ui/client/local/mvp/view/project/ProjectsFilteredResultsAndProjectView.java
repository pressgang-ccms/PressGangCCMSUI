package org.jboss.pressgang.ccms.ui.client.local.mvp.view.project;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class ProjectsFilteredResultsAndProjectView extends
        BaseSearchAndEditView<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> implements
        ProjectsFilteredResultsAndProjectPresenter.Display {
    
    public ProjectsFilteredResultsAndProjectView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Projects());
    }
}