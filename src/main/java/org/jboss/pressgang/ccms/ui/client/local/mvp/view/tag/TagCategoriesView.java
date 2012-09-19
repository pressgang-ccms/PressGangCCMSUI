package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TagCategoriesView extends TagViewBase implements TagCategoriesPresenter.Display {
    

    /** A reference to the tag that this view will be modifying. */
    private RESTTagV1 tag;

    private SplitLayoutPanel split = new SplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);

    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTCategoryV1> results = new CellTable<RESTCategoryV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTCategoryV1> provider;

    private final VerticalPanel tagsResultsPanel = new VerticalPanel();
    private final SimplePager tagsPager = new SimplePager();
    private final CellTable<RESTTagCategoryV1> tagsResults = new CellTable<RESTTagCategoryV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTTagCategoryV1> tagsProvider;

    private final TextColumn<RESTCategoryV1> idColumn = new TextColumn<RESTCategoryV1>() {
        @Override
        public String getValue(final RESTCategoryV1 object) {
            return object.getId().toString();

        }
    };

    private final TextColumn<RESTCategoryV1> nameColumn = new TextColumn<RESTCategoryV1>() {
        @Override
        public String getValue(final RESTCategoryV1 object) {
            return object.getName();
        }
    };

    private final Column<RESTCategoryV1, String> buttonColumn = new Column<RESTCategoryV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTCategoryV1 object) {
            if (tag != null) {
                if (ComponentCategoryV1.containsTag(object, tag.getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    private final TextColumn<RESTTagCategoryV1> tagIdColumn = new TextColumn<RESTTagCategoryV1>() {
        @Override
        public String getValue(final RESTTagCategoryV1 object) {
            return object.getId().toString();

        }
    };

    private final TextColumn<RESTTagCategoryV1> tagNameColumn = new TextColumn<RESTTagCategoryV1>() {
        @Override
        public String getValue(final RESTTagCategoryV1 object) {
            return object.getName();
        }
    };

    private final Column<RESTTagCategoryV1, String> tagUpButtonColumn = new Column<RESTTagCategoryV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTTagCategoryV1 object) {
            return PressGangCCMSUI.INSTANCE.Up();
        }
    };

    private final Column<RESTTagCategoryV1, String> tagDownButtonColumn = new Column<RESTTagCategoryV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTTagCategoryV1 object) {
            return PressGangCCMSUI.INSTANCE.Down();
        }
    };

    @Override
    public SplitLayoutPanel getSplit() {
        return split;
    }

    public void setSplit(final SplitLayoutPanel split) {
        this.split = split;
    }

    @Override
    public VerticalPanel getTagsResultsPanel() {
        return tagsResultsPanel;
    }

    @Override
    public Column<RESTTagCategoryV1, String> getTagDownButtonColumn() {
        return tagDownButtonColumn;
    }

    @Override
    public Column<RESTTagCategoryV1, String> getTagUpButtonColumn() {
        return tagUpButtonColumn;
    }

    @Override
    public EnhancedAsyncDataProvider<RESTTagCategoryV1> getTagsProvider() {
        return tagsProvider;
    }

    @Override
    public void setTagsProvider(final EnhancedAsyncDataProvider<RESTTagCategoryV1> tagsProvider) {
        this.tagsProvider = tagsProvider;
        tagsProvider.addDataDisplay(tagsResults);
    }

    @Override
    public Column<RESTCategoryV1, String> getButtonColumn() {
        return buttonColumn;
    }

    @Override
    public EnhancedAsyncDataProvider<RESTCategoryV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(final EnhancedAsyncDataProvider<RESTCategoryV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @Override
    public CellTable<RESTCategoryV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    public TagCategoriesView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagCategories());

        split.addStyleName(CSSConstants.TagCategoryView.TAGCATEGORIESSPLITPANEL);

        results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.CategoryID());
        results.addColumn(nameColumn, PressGangCCMSUI.INSTANCE.CategoryName());
        results.addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        searchResultsPanel.addStyleName(CSSConstants.TagCategoryView.TAGCATEGORIESLISTPANEL);

        pager.setDisplay(results);

        split.addWest(searchResultsPanel, Constants.SPLIT_PANEL_SIZE);

        tagsResults.addColumn(tagIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        tagsResults.addColumn(tagNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        tagsResults.addColumn(tagUpButtonColumn, PressGangCCMSUI.INSTANCE.Up());
        tagsResults.addColumn(tagDownButtonColumn, PressGangCCMSUI.INSTANCE.Down());

        tagsResultsPanel.add(tagsResults);
        tagsResultsPanel.add(tagsPager);

        tagsResultsPanel.addStyleName(CSSConstants.TagCategoryView.TAGCATEGORYTAGSLISTPANEL);

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
        this.addActionButton(this.getTagCategories());
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
    }

    @Override
    protected void showWaiting() {
 
    }

    @Override
    protected void hideWaiting() {

    }
}
