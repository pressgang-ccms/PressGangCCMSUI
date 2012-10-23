package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.AnchorCell;
import com.google.gwt.user.client.ui.DisableableCheckboxCell;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A MVP view for displaying a topic's Bugzilla Bugs
 * 
 * @author Matthew Casperson
 */
public class TopicBugsView extends TopicViewBase implements TopicBugsPresenter.Display {

    private final VerticalPanel searchResultsPanel = new VerticalPanel();

    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTBugzillaBugCollectionItemV1> results = new CellTable<RESTBugzillaBugCollectionItemV1>(
            Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTBugzillaBugCollectionItemV1> provider;

    private final TextColumn<RESTBugzillaBugCollectionItemV1> summaryColumn = new TextColumn<RESTBugzillaBugCollectionItemV1>() {
        @Override
        public String getValue(final RESTBugzillaBugCollectionItemV1 object) {
            return object.getItem().getSummary();
        }
    };

    private final Column<RESTBugzillaBugCollectionItemV1, Boolean> checkColumn = new Column<RESTBugzillaBugCollectionItemV1, Boolean>(
            new DisableableCheckboxCell(false, true, false)) {
        @Override
        public Boolean getValue(final RESTBugzillaBugCollectionItemV1 object) {
            // Get the value from the selection model.
            return object.getItem().getIsOpen();
        }
    };

    /**
     * The column that renders a link to Bugzilla
     */
    private final Column<RESTBugzillaBugCollectionItemV1, Anchor> linkColumn = new Column<RESTBugzillaBugCollectionItemV1, Anchor>(
            new AnchorCell()) {
        @Override
        public Anchor getValue(final RESTBugzillaBugCollectionItemV1 bug) {
            final Anchor link = new Anchor(bug.getItem().getBugId().toString(), Constants.BUGZILLA_VIEW_BUG_URL
                    + bug.getItem().getBugId());
            return link;
        }
    };

    @Override
    public EnhancedAsyncDataProvider<RESTBugzillaBugCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(final EnhancedAsyncDataProvider<RESTBugzillaBugCollectionItemV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @Override
    public CellTable<RESTBugzillaBugCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    public TopicBugsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.Bugs());

        results.addColumn(linkColumn, PressGangCCMSUI.INSTANCE.BugzillaID());
        results.addColumn(summaryColumn, PressGangCCMSUI.INSTANCE.BugzillaSummary());
        results.addColumn(checkColumn, PressGangCCMSUI.INSTANCE.IsOpen());

        searchResultsPanel.addStyleName(CSSConstants.SEARCH_RESULTS_PANEL);

        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return null;
    }

    @Override
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final boolean newTopic, final SplitType splitType) {
        this.readOnly = readOnly;
        populateTopActionBar(newTopic);
        buildSplitViewButtons(splitType);
    }

    @Override
    protected void populateTopActionBar(final boolean newTopic) {
        super.populateTopActionBar(newTopic);
        
        addActionButton(this.getRenderedSplit());
        addActionButton(this.getRendered());
        addActionButton(this.getXml());
        addActionButton(this.getXmlErrors());
        addActionButton(this.getFields());
        addActionButton(this.getTopicTags());        
        if (!newTopic) {
            addActionButton(this.getHistory());
            addActionButton(this.getBugsDown());
        }
        addActionButton(this.getSave());

        fixReadOnlyButtons();

        addRightAlignedActionButtonPaddingPanel();
    }
}
