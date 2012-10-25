package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TagFilteredResultsView extends BaseTemplateView implements TagFilteredResultsPresenter.Display {

    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    private final FlexTable filterTable = new FlexTable();
    private final Label idFilterLabel = new Label(PressGangCCMSUI.INSTANCE.TagID());
    private final TextBox idFilter = new TextBox();
    private final Label nameFilterLabel = new Label(PressGangCCMSUI.INSTANCE.TagName());
    private final TextBox nameFilter = new TextBox();
    private final Label descriptionFilterLabel = new Label(PressGangCCMSUI.INSTANCE.TagDescription());
    private final TextBox descriptionFilter = new TextBox();
    private final PushButton search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton create = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Create());

    private final SimplePager pager = new SimplePager(TextLocation.CENTER, true, Constants.FAST_FORWARD_ROWS, true);
    private final CellTable<RESTTagCollectionItemV1> results = new CellTable<RESTTagCollectionItemV1>(
            Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider;

    private final TextColumn<RESTTagCollectionItemV1> idColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null) {
                /* don't display the null ID for new tags */
                if (object.getItem().getId() != null && object.getItem().getId().equals(Constants.NULL_ID)) {
                    return "";
                } else {
                    return object.getItem().getId() + "";
                }
            }
            return null + "";
        }
    };

    private final TextColumn<RESTTagCollectionItemV1> nameColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null)
                return object.getItem().getName();
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

    @Override
    public SimplePager getPager() {
        return pager;
    }

    @Override
    public CellTable<RESTTagCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public EnhancedAsyncDataProvider<RESTTagCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    public TagFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images());

        this.addActionButton(search);
        this.addActionButton(create);
        this.addRightAlignedActionButtonPaddingPanel();

        results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.TagID());
        results.addColumn(nameColumn, PressGangCCMSUI.INSTANCE.TagName());

        searchResultsPanel.addStyleName(CSSConstants.TagFilteredResultsView.TAG_FILTERED_RESULTS_PANEL);
        filterTable.addStyleName(CSSConstants.TagFilteredResultsView.TAG_FILTERED_OPTIONS_PANEL);

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

    public PushButton getCreate() {
        return create;
    }
}
