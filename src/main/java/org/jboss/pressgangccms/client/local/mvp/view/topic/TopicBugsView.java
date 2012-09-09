package org.jboss.pressgangccms.client.local.mvp.view.topic;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.rest.v1.entities.RESTBugzillaBugV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.AnchorCell;
import com.google.gwt.user.client.ui.DisableableCheckboxCell;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

/**
 * A MVP view for displaying a topic's Bugzilla Bugs
 * 
 * @author Matthew Casperson
 */
public class TopicBugsView extends TopicViewBase implements TopicBugsPresenter.Display {
    public static final String HISTORY_TOKEN = "TopicBugsView";

    private final VerticalPanel searchResultsPanel = new VerticalPanel();

    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTBugzillaBugV1> results = new CellTable<RESTBugzillaBugV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private AsyncDataProvider<RESTBugzillaBugV1> provider;

    private final TextColumn<RESTBugzillaBugV1> summaryColumn = new TextColumn<RESTBugzillaBugV1>() {
        @Override
        public String getValue(final RESTBugzillaBugV1 object) {
            return object.getSummary();
        }
    };

    private final Column<RESTBugzillaBugV1, Boolean> checkColumn = new Column<RESTBugzillaBugV1, Boolean>(
            new DisableableCheckboxCell(false, true, false)) {
        @Override
        public Boolean getValue(final RESTBugzillaBugV1 object) {
            // Get the value from the selection model.
            return object.getIsOpen();
        }
    };

    /**
     * The column that renders a link to Bugzilla
     */
    private final Column<RESTBugzillaBugV1, Anchor> linkColumn = new Column<RESTBugzillaBugV1, Anchor>(new AnchorCell()) {
        @Override
        public Anchor getValue(final RESTBugzillaBugV1 bug) {
            final Anchor link = new Anchor(bug.getBugId().toString(), Constants.BUGZILLA_VIEW_BUG_URL + bug.getBugId());
            return link;
        }
    };

    @Override
    public AsyncDataProvider<RESTBugzillaBugV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(final AsyncDataProvider<RESTBugzillaBugV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @Override
    public CellTable<RESTBugzillaBugV1> getResults() {
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

        searchResultsPanel.addStyleName(CSSConstants.SEARCHRESULTSPANEL);

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
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final SplitType splitType) {
        this.readOnly = readOnly;
        fixReadOnlyButtons();
        buildSplitViewButtons(splitType);
    }

    @Override
    protected void populateTopActionBar() {
        addActionButton(this.getRenderedSplit());
        addActionButton(this.getRendered());
        addActionButton(this.getXml());
        addActionButton(this.getXmlErrors());
        addActionButton(this.getFields());
        addActionButton(this.getTags());
        addActionButton(this.getBugsDown());
        addActionButton(this.getHistory());
        addActionButton(this.getSave());

        fixReadOnlyButtons();

        addRightAlignedActionButtonPaddingPanel();
    }
}
