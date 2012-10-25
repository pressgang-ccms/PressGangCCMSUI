package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter;
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

public class TagProjectsView extends TagViewBase implements TagProjectsPresenter.Display {
    

    /** A reference to the tag that this view will be modifying */
    private RESTTagV1 tag;

    private final VerticalPanel searchResultsPanel = new VerticalPanel();

    private final SimplePager pager = UIUtilities.createSimplePager();
    private final CellTable<RESTProjectCollectionItemV1> results = new CellTable<RESTProjectCollectionItemV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> provider;

    private final TextColumn<RESTProjectCollectionItemV1> idColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> nameColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            return object.getItem().getName();
        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> descriptionColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            return object.getItem().getDescription();
        }
    };

    private final Column<RESTProjectCollectionItemV1, String> buttonColumn = new Column<RESTProjectCollectionItemV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            if (tag != null) {
                if (ComponentProjectV1.containsTag(object.getItem(), tag.getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @Override
    public Column<RESTProjectCollectionItemV1, String> getButtonColumn() {
        return buttonColumn;
    }

    @Override
    public EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(final EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @Override
    public CellTable<RESTProjectCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    public TagProjectsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagProjects());

        results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProjectID());
        results.addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProjectName());
        results.addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ProjectDescription());
        results.addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        pager.setDisplay(results);

        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        this.getPanel().setWidget(searchResultsPanel);
    }

    @Override
    public void initialize(final RESTTagV1 tag, final boolean readOnly) {
        this.tag = tag;
    }

    @Override
    protected void populateTopActionBar() {
        this.addActionButton(this.getTagDetails());
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TagProjects()));
        this.addActionButton(this.getTagCategories());
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
    }
}
