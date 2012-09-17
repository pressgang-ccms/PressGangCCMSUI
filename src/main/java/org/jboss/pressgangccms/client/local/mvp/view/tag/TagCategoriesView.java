package org.jboss.pressgangccms.client.local.mvp.view.tag;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

public class TagCategoriesView extends TagViewBase implements TagCategoriesPresenter.Display {
    public static final String HISTORY_TOKEN = "TagCategoriesView";

    /** A reference to the tag that this view will be modifying. */
    private RESTTagV1 tag;

    private SplitLayoutPanel split = new SplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);

    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTCategoryV1> results = new CellTable<RESTCategoryV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private AsyncDataProvider<RESTCategoryV1> provider;

    private final VerticalPanel tagsResultsPanel = new VerticalPanel();
    private final SimplePager tagsPager = new SimplePager();
    private final CellTable<RESTTagV1> tagsResults = new CellTable<RESTTagV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private AsyncDataProvider<RESTTagV1> tagsProvider;

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

    private final TextColumn<RESTTagV1> tagIdColumn = new TextColumn<RESTTagV1>() {
        @Override
        public String getValue(final RESTTagV1 object) {
            return object.getId().toString();

        }
    };

    private final TextColumn<RESTTagV1> tagNameColumn = new TextColumn<RESTTagV1>() {
        @Override
        public String getValue(final RESTTagV1 object) {
            return object.getName();
        }
    };

    private final Column<RESTTagV1, String> tagUpButtonColumn = new Column<RESTTagV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTTagV1 object) {
            return PressGangCCMSUI.INSTANCE.Up();
        }
    };

    private final Column<RESTTagV1, String> tagDownButtonColumn = new Column<RESTTagV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTTagV1 object) {
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
    public Column<RESTTagV1, String> getTagDownButtonColumn() {
        return tagDownButtonColumn;
    }

    @Override
    public Column<RESTTagV1, String> getTagUpButtonColumn() {
        return tagUpButtonColumn;
    }

    @Override
    public AsyncDataProvider<RESTTagV1> getTagsProvider() {
        return tagsProvider;
    }

    @Override
    public void setTagsProvider(final AsyncDataProvider<RESTTagV1> tagsProvider) {
        this.tagsProvider = tagsProvider;
        tagsProvider.addDataDisplay(tagsResults);
    }

    @Override
    public Column<RESTCategoryV1, String> getButtonColumn() {
        return buttonColumn;
    }

    @Override
    public AsyncDataProvider<RESTCategoryV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(final AsyncDataProvider<RESTCategoryV1> provider) {
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
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void hideWaiting() {
        // TODO Auto-generated method stub
        
    }
}
