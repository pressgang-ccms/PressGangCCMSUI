package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TagCategoriesView extends TagViewBase implements TagCategoriesPresenter.Display {
    
    /** A reference to the tag that this view will be modifying. */
    private RESTTagV1 tag;

    private HandlerSplitLayoutPanel split = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);

    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTCategoryCollectionItemV1> results = new CellTable<RESTCategoryCollectionItemV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider;

    private final VerticalPanel tagsResultsPanel = new VerticalPanel();
    private final SimplePager tagsPager = new SimplePager();
    private final CellTable<RESTTagInCategoryCollectionItemV1> tagsResults = new CellTable<RESTTagInCategoryCollectionItemV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> tagsProvider;

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

    private final Column<RESTCategoryCollectionItemV1, String> buttonColumn = new Column<RESTCategoryCollectionItemV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTCategoryCollectionItemV1 object) {
            if (tag != null) {
                if (ComponentCategoryV1.containsTag(object.getItem(), tag.getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagIdColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagNameColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return object.getItem().getName();
        }
    };

    private final Column<RESTTagInCategoryCollectionItemV1, String> tagUpButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.Up();
        }
    };

    private final Column<RESTTagInCategoryCollectionItemV1, String> tagDownButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.Down();
        }
    };

    @Override
    public VerticalPanel getSearchResultsPanel() {
        return searchResultsPanel;
    }

    @Override
    public HandlerSplitLayoutPanel getSplit() {
        return split;
    }

    public void setSplit(final HandlerSplitLayoutPanel split) {
        this.split = split;
    }

    @Override
    public VerticalPanel getTagsResultsPanel() {
        return tagsResultsPanel;
    }

    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getTagDownButtonColumn() {
        return tagDownButtonColumn;
    }

    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getTagUpButtonColumn() {
        return tagUpButtonColumn;
    }

    @Override
    public EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> getTagsProvider() {
        return tagsProvider;
    }

    @Override
    public void setTagsProvider(final EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> tagsProvider) {
        this.tagsProvider = tagsProvider;
        tagsProvider.addDataDisplay(tagsResults);
    }

    @Override
    public Column<RESTCategoryCollectionItemV1, String> getButtonColumn() {
        return buttonColumn;
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

    @Override
    public CellTable<RESTCategoryCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    public TagCategoriesView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagCategories());

        split.addStyleName(CSSConstants.TagCategoryView.TAG_CATEGORIES_SPLIT_PANEL);

        results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.CategoryID());
        results.addColumn(nameColumn, PressGangCCMSUI.INSTANCE.CategoryName());
        results.addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        searchResultsPanel.addStyleName(CSSConstants.TagCategoryView.TAG_CATEGORIES_LIST_PANEL);

        pager.setDisplay(results);

        split.addWest(searchResultsPanel, Constants.SPLIT_PANEL_SIZE);

        tagsResults.addColumn(tagIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        tagsResults.addColumn(tagNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        tagsResults.addColumn(tagUpButtonColumn, PressGangCCMSUI.INSTANCE.Up());
        tagsResults.addColumn(tagDownButtonColumn, PressGangCCMSUI.INSTANCE.Down());

        tagsResultsPanel.add(tagsResults);
        tagsResultsPanel.add(tagsPager);

        tagsResultsPanel.addStyleName(CSSConstants.TagCategoryView.TAG_CATEGORY_TAGS_LIST_PANEL);

        tagsPager.setDisplay(tagsResults);

        /* Add this later once a category has been selected */
        // split.add(tagsResultsPanel);

        this.getPanel().setWidget(split);
    }

    @Override
    public void initialize(final RESTTagV1 tag, final boolean readOnly) {
        this.tag = tag;
    }

    @Override
    protected void populateTopActionBar() {
        this.addActionButton(this.getTagDetails());
        this.addActionButton(this.getTagProjects());
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TagCategories()));
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
    }
}
