package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;

public class CategoryFilteredResultsView extends
        BaseFilteredResultsView<RESTCategoryCollectionItemV1> implements
        CategoryFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    private TextColumn<RESTCategoryCollectionItemV1> idColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    private TextColumn<RESTCategoryCollectionItemV1> nameColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTCategoryCollectionItemV1 object) {
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

    public CategoryFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories(), PressGangCCMSUI.INSTANCE.CreateCategory());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.CategoryID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.CategoryName());

        addFilterField(PressGangCCMSUI.INSTANCE.CategoryIDs(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.CategoryName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.CategoryDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}
