package org.jboss.pressgangccms.client.local.mvp.view.topic;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableCheckboxCell;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

/**
 * A MVP view for displaying a topic's revision history
 * 
 * @author Matthew Casperson
 */
public class TopicRevisionsView extends TopicViewBase implements TopicRevisionsPresenter.Display {
    public static final String HISTORY_TOKEN = "TopicHistoryView";

    private final VerticalPanel searchResultsPanel = new VerticalPanel();

    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTTopicV1> results = new CellTable<RESTTopicV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private AsyncDataProvider<RESTTopicV1> provider;
    private RESTTopicV1 revisionTopic;
    private RESTTopicV1 mainTopic;

    private final Column<RESTTopicV1, Boolean> viewing = new Column<RESTTopicV1, Boolean>(new DisableableCheckboxCell(false,
            true, false)) {
        @Override
        public Boolean getValue(final RESTTopicV1 object) {
            if (revisionTopic == null) {
                return false;
            }

            return object.getRevision().equals(revisionTopic.getRevision());
        }
    };

    private final TextColumn<RESTTopicV1> revisionNumber = new TextColumn<RESTTopicV1>() {
        @Override
        public String getValue(final RESTTopicV1 object) {
            return object.getRevision().toString();
        }
    };

    private final TextColumn<RESTTopicV1> revisionDate = new TextColumn<RESTTopicV1>() {
        @Override
        public String getValue(final RESTTopicV1 object) {
            return DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG).format(object.getLastModified());
        }
    };

    private final Column<RESTTopicV1, String> viewButton = new Column<RESTTopicV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTTopicV1 object) {
            /*
             * The last revision is the same as the topic in the main database. We indicate that by showing the last revision as
             * editable instead of read only.
             */
            if (mainTopic != null && object.getRevision().equals(mainTopic.getRevision()))

            {
                if (revisionTopic == null || revisionTopic.getRevision().equals(mainTopic.getRevision())) {
                    return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                } else {
                    return PressGangCCMSUI.INSTANCE.Edit();
                }
            }

            if (revisionTopic == null || !revisionTopic.getRevision().equals(object.getRevision())) {
                return PressGangCCMSUI.INSTANCE.View();
            }

            return PressGangCCMSUI.INSTANCE.CurrentlyViewing();
        }
    };

    private final Column<RESTTopicV1, String> diffButton = new Column<RESTTopicV1, String>(new ButtonCell()) {
        @Override
        public String getValue(final RESTTopicV1 object) {
            if (mainTopic != null && object.getRevision().equals(mainTopic.getRevision())) {
                if (revisionTopic == null || revisionTopic.getRevision().equals(mainTopic.getRevision())) {
                    return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                }
            }

            if (revisionTopic == null || !revisionTopic.getRevision().equals(object.getRevision())) {
                return PressGangCCMSUI.INSTANCE.Diff();
            }

            return PressGangCCMSUI.INSTANCE.CurrentlyViewing();
        }
    };

    @Override
    public Column<RESTTopicV1, String> getDiffButton() {
        return diffButton;
    }

    @Override
    public RESTTopicV1 getRevisionTopic() {
        return revisionTopic;
    }

    @Override
    public void setRevisionTopic(final RESTTopicV1 revisionTopic) {
        this.revisionTopic = revisionTopic;
    }

    @Override
    public Column<RESTTopicV1, String> getViewButton() {
        return viewButton;
    }

    @Override
    public void setProvider(final AsyncDataProvider<RESTTopicV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @Override
    public AsyncDataProvider<RESTTopicV1> getProvider() {
        return provider;
    }

    @Override
    public CellTable<RESTTopicV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    public TopicRevisionsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.Revisions());

        results.addColumn(viewing, PressGangCCMSUI.INSTANCE.CurrentlyViewing());
        results.addColumn(revisionNumber, PressGangCCMSUI.INSTANCE.RevisionNumber());
        results.addColumn(revisionDate, PressGangCCMSUI.INSTANCE.RevisionDate());
        results.addColumn(viewButton, PressGangCCMSUI.INSTANCE.View() + " / " + PressGangCCMSUI.INSTANCE.Edit());
        results.addColumn(diffButton, PressGangCCMSUI.INSTANCE.Diff());

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
        this.mainTopic = topic;
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
        addActionButton(this.getBugs());
        addActionButton(this.getHistoryDown());
        addActionButton(this.getSave());

        fixReadOnlyButtons();

        addRightAlignedActionButtonPaddingPanel();
    }

}
