package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

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
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A MVP view for displaying a topic's revision history
 * 
 * @author Matthew Casperson
 */
public class TopicRevisionsView extends TopicViewBase implements TopicRevisionsPresenter.Display {

    private final VerticalPanel searchResultsPanel = new VerticalPanel();

    private final SimplePager pager = UIUtilities.createSimplePager();
    private final CellTable<RESTTopicCollectionItemV1> results = new CellTable<RESTTopicCollectionItemV1>(
            Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider;
    private RESTTopicCollectionItemV1 revisionTopic;
    private RESTTopicV1 mainTopic;

    private final TextColumn<RESTTopicCollectionItemV1> revisionNumber = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            return object.getItem().getRevision().toString();
        }
    };

    private final TextColumn<RESTTopicCollectionItemV1> revisionDate = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            return DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG).format(object.getItem().getLastModified());
        }
    };

    private final TextColumn<RESTTopicCollectionItemV1> revisionMessage = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            if (object.getItem().getLogDetails() != null) {
                return object.getItem().getLogDetails().getMessage();
            }
            return "";
        }
    };

    final DisableableButtonCell viewButtonCell = new DisableableButtonCell();
    private final Column<RESTTopicCollectionItemV1, String> viewButton = new Column<RESTTopicCollectionItemV1, String>(
            viewButtonCell) {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            viewButtonCell.setEnabled(true);
            
            /*
             * The last revision is the same as the topic in the main database. We indicate that by showing the last revision as
             * editable instead of read only.
             */
            if (mainTopic != null && object.getItem().getRevision().equals(mainTopic.getRevision()))
            {
                if (revisionTopic == null || revisionTopic.getItem().getRevision().equals(mainTopic.getRevision())) {
                    viewButtonCell.setEnabled(false);
                    return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                } else {
                    return PressGangCCMSUI.INSTANCE.Edit();
                }
            }

            if (revisionTopic == null || !revisionTopic.getItem().getRevision().equals(object.getItem().getRevision())) {
                return PressGangCCMSUI.INSTANCE.View();
            }

            viewButtonCell.setEnabled(false);
            return PressGangCCMSUI.INSTANCE.CurrentlyViewing();
        }
    };

    final DisableableButtonCell diffButtonCell = new DisableableButtonCell();
    private final Column<RESTTopicCollectionItemV1, String> diffButton = new Column<RESTTopicCollectionItemV1, String>(
            diffButtonCell) {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            diffButtonCell.setEnabled(true);
            
            if (mainTopic != null && object.getItem().getRevision().equals(mainTopic.getRevision())) {
                if (revisionTopic == null || revisionTopic.getItem().getRevision().equals(mainTopic.getRevision())) {
                    diffButtonCell.setEnabled(false);
                    return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                }
            }

            if (revisionTopic == null || !revisionTopic.getItem().getRevision().equals(object.getItem().getRevision())) {
                
                /* Diffs don't work if there is no XML to compare to */
                if (object.getItem().getXml().trim().isEmpty())
                {
                    diffButtonCell.setEnabled(false);
                    return PressGangCCMSUI.INSTANCE.NoXML();
                }
                else
                {
                    return PressGangCCMSUI.INSTANCE.Diff();
                }
            }

            diffButtonCell.setEnabled(false);
            return PressGangCCMSUI.INSTANCE.CurrentlyViewing();
        }
    };

    @Override
    public Column<RESTTopicCollectionItemV1, String> getDiffButton() {
        return diffButton;
    }

    @Override
    public RESTTopicCollectionItemV1 getRevisionTopic() {
        return revisionTopic;
    }

    @Override
    public void setRevisionTopic(final RESTTopicCollectionItemV1 revisionTopic) {
        this.revisionTopic = revisionTopic;
    }

    @Override
    public Column<RESTTopicCollectionItemV1, String> getViewButton() {
        return viewButton;
    }

    @Override
    public void setProvider(final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @Override
    public EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public CellTable<RESTTopicCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    public TopicRevisionsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.Revisions());

        results.addColumn(revisionNumber, PressGangCCMSUI.INSTANCE.RevisionNumber());
        results.addColumn(revisionDate, PressGangCCMSUI.INSTANCE.RevisionDate());
        results.addColumn(revisionMessage, PressGangCCMSUI.INSTANCE.RevisionMessage());
        results.addColumn(viewButton, PressGangCCMSUI.INSTANCE.View() + " / " + PressGangCCMSUI.INSTANCE.Edit());
        results.addColumn(diffButton, PressGangCCMSUI.INSTANCE.Diff());

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
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final boolean newTopic, final SplitType splitType, final String[] locales) {
        this.readOnly = readOnly;
        this.mainTopic = topic;
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
            addActionButton(this.getBugs());
            addActionButton(this.getHistoryDown());
        }
        addActionButton(this.getSave());

        fixReadOnlyButtons();

        addRightAlignedActionButtonPaddingPanel();
    }

}
