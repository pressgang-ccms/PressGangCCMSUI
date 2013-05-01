package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search;

import com.google.gwt.user.cellview.client.TextColumn;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;

/**
 * The view to display a list of filters
 */
@Dependent
public class SearchFilterFilteredResultsView extends BaseFilteredResultsView<RESTFilterCollectionItemV1>
        implements SearchFilterFilteredResultsPresenter.Display {

    @NotNull
    private final TextColumn<RESTFilterCollectionItemV1> idColumn = new TextColumn<RESTFilterCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTFilterCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getId().toString();
        }
    };

    @NotNull
    private final TextColumn<RESTFilterCollectionItemV1> nameColumn = new TextColumn<RESTFilterCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTFilterCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getName();
        }
    };

    public SearchFilterFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Filters(), PressGangCCMSUI.INSTANCE.CreateFilter());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.FilterId());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.FilterName());

        /*
            Remove both the search and create buttons. The search button should not be displayed,
            and the create button should be added to the end.
         */
        this.getTopActionPanel().removeAllRows();
    }
}
