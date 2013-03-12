package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertycategory;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertyCategoryFilteredResultsView extends
        BaseFilteredResultsView<RESTPropertyCategoryCollectionItemV1> implements
        PropertyCategoryFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    @NotNull
    private final TextColumn<RESTPropertyCategoryCollectionItemV1> idColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private final TextColumn<RESTPropertyCategoryCollectionItemV1> nameColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }

            return null + "";
        }
    };

    @NotNull
    @Override
    public TextBox getNameFilter() {
        return nameFilter;
    }

    @NotNull
    @Override
    public TextBox getIdFilter() {
        return idFilter;
    }

    @NotNull
    @Override
    public TextBox getDescriptionFilter() {
        return descriptionFilter;
    }

    public PropertyCategoryFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedPropertyCategories(), PressGangCCMSUI.INSTANCE.CreateExtendedPropertyCategory());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryName());

        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryID(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}