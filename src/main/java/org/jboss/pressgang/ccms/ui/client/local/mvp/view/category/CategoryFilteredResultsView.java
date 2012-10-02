package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

public class CategoryFilteredResultsView extends BaseTemplateView implements CategoryFilteredResultsPresenter.Display {

    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    private final FlexTable filterTable = new FlexTable();
    private final Label idFilterLabel = new Label(PressGangCCMSUI.INSTANCE.CategoryID());
    private final TextBox idFilter = new TextBox();
    private final Label nameFilterLabel = new Label(PressGangCCMSUI.INSTANCE.CategoryName());
    private final TextBox nameFilter = new TextBox();
    private final Label descriptionFilterLabel = new Label(PressGangCCMSUI.INSTANCE.CategoryDescription());
    private final TextBox descriptionFilter = new TextBox();
    private final PushButton search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());

    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTCategoryCollectionItemV1> results = new CellTable<RESTCategoryCollectionItemV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider;

    private final TextColumn<RESTCategoryCollectionItemV1> idColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTCategoryCollectionItemV1 object) {
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTCategoryCollectionItemV1> nameColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTCategoryCollectionItemV1 object) {
            return object.getItem().getName();
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

    @Override
    public SimplePager getPager() {
        return pager;
    }

    @Override
    public CellTable<RESTCategoryCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    public CategoryFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());

        this.addActionButton(search);
        this.addRightAlignedActionButtonPaddingPanel();

        results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.CategoryID());
        results.addColumn(nameColumn, PressGangCCMSUI.INSTANCE.CategoryName());

        searchResultsPanel.addStyleName(CSSConstants.CategoryFilteredResultsView.CATEGORY_FILTERED_RESULTS_PANEL);
        filterTable.addStyleName(CSSConstants.CategoryFilteredResultsView.CATEGORY_FILTERED_OPTIONS_PANEL);

        filterTable.setWidget(0, 0, idFilterLabel);
        filterTable.setWidget(0, 1, idFilter);
        filterTable.setWidget(1, 0, nameFilterLabel);
        filterTable.setWidget(1, 1, nameFilter);
        filterTable.setWidget(2, 0, descriptionFilterLabel);
        filterTable.setWidget(2, 1, descriptionFilter);

        searchResultsPanel.add(filterTable);
        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }

    @Override
    public PushButton getSearch() {
        return search;
    }
}

