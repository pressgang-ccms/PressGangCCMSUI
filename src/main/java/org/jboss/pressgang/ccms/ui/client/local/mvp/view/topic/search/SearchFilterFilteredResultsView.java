package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.user.cellview.client.TextColumn;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import javax.enterprise.context.Dependent;

/**
 *  The view to display a list of filters
 */
@Dependent
public class SearchFilterFilteredResultsView extends BaseFilteredResultsView<
        RESTFilterV1,
        RESTFilterCollectionV1,
        RESTFilterCollectionItemV1>
        implements SearchFilterFilteredResultsPresenter.Display {

    private final TextColumn<RESTFilterCollectionItemV1> idColumn = new TextColumn<RESTFilterCollectionItemV1>() {
        @Override
        public String getValue(final RESTFilterCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getId().toString();
        }
    };

    private final TextColumn<RESTFilterCollectionItemV1> nameColumn = new TextColumn<RESTFilterCollectionItemV1>() {
        @Override
        public String getValue(final RESTFilterCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getName();
        }
    };

    public SearchFilterFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Filters());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.TopicID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.TopicTitle());

        /* the filter results don't have a search button */
        this.getEntitySearch().removeFromParent();
    }
}
