package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertycategory;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;

public class PropertyCategoryFilteredResultsView extends
        BaseFilteredResultsView<RESTPropertyCategoryCollectionItemV1> implements
        PropertyCategoryFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    private final TextColumn<RESTPropertyCategoryCollectionItemV1> idColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTPropertyCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    private final TextColumn<RESTPropertyCategoryCollectionItemV1> nameColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTPropertyCategoryCollectionItemV1 object) {
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

    public PropertyCategoryFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedPropertyCategories(), PressGangCCMSUI.INSTANCE.CreateExtendedPropertyCategory());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryName());

        addFilterField(PressGangCCMSUI.INSTANCE.ProjectIDs(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ProjectName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ProjectDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}