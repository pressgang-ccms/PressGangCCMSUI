package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertytag;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;

public class PropertyTagFilteredResultsView extends
        BaseFilteredResultsView<RESTPropertyTagCollectionItemV1> implements
        PropertyTagFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    private final TextColumn<RESTPropertyTagCollectionItemV1> idColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    private final TextColumn<RESTPropertyTagCollectionItemV1> nameColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTPropertyTagCollectionItemV1 object) {
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

    public PropertyTagFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Projects(), PressGangCCMSUI.INSTANCE.CreateProject());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProjectID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProjectName());

        addFilterField(PressGangCCMSUI.INSTANCE.ProjectIDs(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ProjectName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ProjectDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}