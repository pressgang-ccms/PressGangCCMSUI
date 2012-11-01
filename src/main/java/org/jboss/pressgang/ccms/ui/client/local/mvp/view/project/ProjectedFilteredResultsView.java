package org.jboss.pressgang.ccms.ui.client.local.mvp.view.project;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;

public class ProjectedFilteredResultsView extends
        BaseFilteredResultsView<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> implements
        ProjectFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();
    
    private final TextColumn<RESTProjectCollectionItemV1> idColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> nameColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }

            return null + "";
        }
    };

    @Override
    public TextBox getNameFilter() {
        return nameFilter;
    }

    @Override
    public TextBox getIdFilter() {
        return idFilter;
    }

    @Override
    public TextBox getDescriptionFilter() {
        return descriptionFilter;
    }

    public ProjectedFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Projects());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProjectID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProjectName());

        addFilterField(PressGangCCMSUI.INSTANCE.ProjectID(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ProjectName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ProjectDescription(), descriptionFilter);
    }
}