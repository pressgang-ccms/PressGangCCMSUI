package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.DisableableCheckboxCell;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A MVP view for displaying a topic's revision history.
 *
 * @author Matthew Casperson
 */
public class TopicRevisionsView extends BaseTemplateView implements TopicRevisionsPresenter.Display {

    /**
     * A button used when rendering the view button column.
     */
    private final DisableableButtonCell viewButtonCell = new DisableableButtonCell();
    /**
     * A button used when rendering the diff button column.
     */
    private final DisableableButtonCell diffButtonCell = new DisableableButtonCell();
    /**
     * The panel that holds the table and pager.
     */
    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    /**
     * The pager used to page over the table results.
     */
    private final SimplePager pager = UIUtilities.createSimplePager();
    /**
     * The table used to display the revisions.
     */
    private final CellTable<RESTTopicCollectionItemV1> results = new CellTable<RESTTopicCollectionItemV1>(
            Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
    /**
     * The column displaying the revision number.
     */
    private final TextColumn<RESTTopicCollectionItemV1> revisionNumber = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTTopicCollectionItemV1 object) {
            return object.getItem().getRevision().toString();
        }
    };
    /**
     * The column displaying the revision date.
     */
    private final TextColumn<RESTTopicCollectionItemV1> revisionDate = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTTopicCollectionItemV1 object) {
            return DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG).format(object.getItem().getLastModified());
        }
    };
    /**
     * The column displaying the revision message.
     */
    @NotNull
    private final TextColumn<RESTTopicCollectionItemV1> revisionMessage = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getLogDetails() != null && object.getItem().getLogDetails().getMessage() != null) {
                return object.getItem().getLogDetails().getMessage();
            }
            return "";
        }
    };
    /**
     * The column displaying the user that committed the revision.
     */
    @NotNull
    private final TextColumn<RESTTopicCollectionItemV1> revisionUser = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getLogDetails() != null
                    && object.getItem().getLogDetails().getUser() != null
                    && object.getItem().getLogDetails().getUser().getName() != null) {
                return object.getItem().getLogDetails().getUser().getName();
            }
            return "";
        }
    };
    /**
     * The column displaying the minor revision checkbox.
     */
    @NotNull
    private final Column<RESTTopicCollectionItemV1, Boolean> minorRevisionColumn = new Column<RESTTopicCollectionItemV1, Boolean>(
            new DisableableCheckboxCell(false)) {
        @NotNull
        @Override
        public Boolean getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getLogDetails() != null && object.getItem().getLogDetails().getFlag() != null) {
                final Integer flag = object.getItem().getLogDetails().getFlag();
                return (flag & ServiceConstants.MINOR_CHANGE) == ServiceConstants.MINOR_CHANGE;
            }
            return false;
        }
    };
    /**
     * The column displaying the major revision checkbox.
     */
    @NotNull
    private final Column<RESTTopicCollectionItemV1, Boolean> majorRevisionColumn = new Column<RESTTopicCollectionItemV1, Boolean>(
            new DisableableCheckboxCell(false)) {
        @NotNull
        @Override
        public Boolean getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getLogDetails() != null && object.getItem().getLogDetails().getFlag() != null) {
                return (object.getItem().getLogDetails().getFlag() & ServiceConstants.MAJOR_CHANGE) == ServiceConstants.MAJOR_CHANGE;
            }
            return false;
        }
    };
    /**
     * The column that displays the button used to view the revision.
     */
    @NotNull
    private final Column<RESTTopicCollectionItemV1, String> viewButton = new Column<RESTTopicCollectionItemV1, String>(
            viewButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            viewButtonCell.setEnabled(true);

            /*
             * The last revision is the same as the topic in the main database. We indicate that by showing the last revision as
             * editable instead of read only.
             */
            if (mainTopic != null && object != null && object.getItem() != null && object.getItem().getRevision().equals(mainTopic.getRevision())) {
                if (revisionTopic == null || revisionTopic.getItem().getRevision().equals(mainTopic.getRevision())) {
                    viewButtonCell.setEnabled(false);
                    return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                } else {
                    return PressGangCCMSUI.INSTANCE.Edit();
                }
            }

            if (revisionTopic == null || (object != null && object.getItem() != null && !revisionTopic.getItem().getRevision().equals(object.getItem().getRevision()))) {
                return PressGangCCMSUI.INSTANCE.View();
            }

            viewButtonCell.setEnabled(false);
            return PressGangCCMSUI.INSTANCE.CurrentlyViewing();
        }
    };
    /**
     * The column that displays the diff button.
     */
    @NotNull
    private final Column<RESTTopicCollectionItemV1, String> diffButton = new Column<RESTTopicCollectionItemV1, String>(
            diffButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            diffButtonCell.setEnabled(true);

            if (mainTopic != null) {
                if (object != null && object.getItem() != null && object.getItem().getRevision().equals(mainTopic.getRevision())) {
                    if (revisionTopic == null || revisionTopic.getItem().getRevision().equals(mainTopic.getRevision())) {
                        diffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                    }
                }

                if (revisionTopic == null || (object != null && object.getItem() != null && !revisionTopic.getItem().getRevision().equals(object.getItem().getRevision()))) {

                    final String viewingXML = revisionTopic == null ? mainTopic.getXml() : revisionTopic.getItem().getXml();
                    @NotNull final String fixedViewingXML = viewingXML == null ? "" : viewingXML.trim();

                    if (object.getItem().getXml() == null || object.getItem().getXml().trim().isEmpty()) {
                        /* Diffs don't work if there is no XML to compare to */
                        diffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.NoXML();
                    } else if (object.getItem().getXml().trim().equals(fixedViewingXML)) {
                        /* The XML is the same */
                        diffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.SameXML();
                    } else {
                        return PressGangCCMSUI.INSTANCE.Diff();
                    }
                }
            }

            diffButtonCell.setEnabled(false);
            return PressGangCCMSUI.INSTANCE.CurrentlyViewing();
        }
    };
    /**
     * The data provider used to display the revisions.
     */
    private EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider;
    /**
     * A reference to the displayed revision topic.
     */
    private RESTTopicCollectionItemV1 revisionTopic;
    /**
     * A reference to the displayed topic.
     */
    private RESTTopicV1 mainTopic;

    /**
     * Builds the UI.
     */
    public TopicRevisionsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - " + PressGangCCMSUI.INSTANCE.Revisions());

        results.addColumn(revisionNumber, PressGangCCMSUI.INSTANCE.RevisionNumber());
        results.addColumn(revisionDate, PressGangCCMSUI.INSTANCE.RevisionDate());
        results.addColumn(minorRevisionColumn, PressGangCCMSUI.INSTANCE.MinorChange());
        results.addColumn(majorRevisionColumn, PressGangCCMSUI.INSTANCE.MajorChange());
        results.addColumn(revisionUser, PressGangCCMSUI.INSTANCE.Username());
        results.addColumn(revisionMessage, PressGangCCMSUI.INSTANCE.RevisionMessage());
        results.addColumn(viewButton, PressGangCCMSUI.INSTANCE.View() + " / " + PressGangCCMSUI.INSTANCE.Edit());
        results.addColumn(diffButton, PressGangCCMSUI.INSTANCE.Diff());

        searchResultsPanel.addStyleName(CSSConstants.TopicView.SEARCH_RESULTS_PANEL);

        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }

    @NotNull
    @Override
    public final Column<RESTTopicCollectionItemV1, String> getDiffButton() {
        return diffButton;
    }

    @Override
    public final RESTTopicCollectionItemV1 getRevisionTopic() {
        return revisionTopic;
    }

    @Override
    public final void setRevisionTopic(final RESTTopicCollectionItemV1 revisionTopic) {
        this.revisionTopic = revisionTopic;
    }

    @NotNull
    @Override
    public final Column<RESTTopicCollectionItemV1, String> getViewButton() {
        return viewButton;
    }

    @Override
    public final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public final void setProvider(@NotNull final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @NotNull
    @Override
    public final CellTable<RESTTopicCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public final SimplePager getPager() {
        return pager;
    }

    @Override
    public final void display(final RESTTopicV1 topic, final boolean readOnly) {
        this.mainTopic = topic;
    }


}
