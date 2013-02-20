package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import javax.enterprise.context.Dependent;

/**
 * The view to display a list of filters
 */
@Dependent
public class SearchFilterFilteredResultsView extends BaseFilteredResultsView<RESTFilterCollectionItemV1>
        implements SearchFilterFilteredResultsPresenter.Display {

    private final PushButton searchTopics = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton fields = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Fields());
    private final Label filters = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Filters());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());

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

    @Override
    public PushButton getSearchTopics() {
        return searchTopics;
    }

    @Override
    public PushButton getTagsSearch() {
        return tagsSearch;
    }

    @Override
    public PushButton getFields() {
        return fields;
    }

    @Override
    public PushButton getLocales() {
        return locales;
    }

    public SearchFilterFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Filters(), PressGangCCMSUI.INSTANCE.CreateFilter());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.FilterId());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.FilterName());

        /*
            Remove both the search and create buttons. The search button should not be displayed,
            and the create button should be added to the end.
         */
        this.getTopActionPanel().removeAllRows();

        addActionButton(searchTopics);
        addActionButton(tagsSearch);
        addActionButton(fields);
        //addActionButton(locales);
        addActionButton(filters);
        addActionButton(getCreate());
    }
}
