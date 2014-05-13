package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTCSNodeCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTCSNodeCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.enums.RESTCSNodeTypeV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicDuplicatesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.mergelygwt.client.Mergely;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;

/**
 * A MVP view for displaying a topic's revision history.
 *
 * @author Matthew Casperson
 */
public class TopicDuplicatesView extends BaseTemplateView implements TopicDuplicatesPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicDuplicatesView.class.getName());

    /**
     * The height of the button panel in the merge view
     */
    private static final int BUTTON_PANEL_HEIGHT = 44;


    private boolean readOnly = false;

    /**
     * Holds the mergely elements and the ok/cancel buttons
     */
    private final DockLayoutPanel htmlDiffPanel = new DockLayoutPanel(Style.Unit.PX);
    /**
     * The parent for the mergely elements. Needs to be a layout panel so resize events are propogated.
     */
    private final SimpleLayoutPanel htmlDiffParent = new SimpleLayoutPanel();
    /**
     * The done button.
     */
    private final PushButton htmlDone = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Done());
    private final PushButton htmlOpenDiff = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.NewWindow());

    /**
     * A button used when rendering the view button column.
     */
    private final DisableableButtonCell viewButtonCell = new DisableableButtonCell();
    /**
     * A button used when rendering the diff button column.
     */
    private final DisableableButtonCell htmlDiffButtonCell = new DisableableButtonCell();
    /**
     * Holds the mergely elements and the ok/cancel buttons
     */
    private final DockLayoutPanel diffPanel = new DockLayoutPanel(Style.Unit.PX);
    private final SimpleLayoutPanel diffParent = new SimpleLayoutPanel();
    /**
     * The done button.
     */
    private final PushButton done = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Done());
    /**
     * The cancel button.
     */
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
    /**
     * The current instance of the Mergely ui element.
     */
    private Mergely mergely;
    /**
     * true if we are displaying the revisions, and false if we are displaying the mergely ui element.
     */
    private boolean isDisplayingDuplicates = true;
    /**
     * true if the diff and view buttons in the revisions cell table are enabled, and false otherwise.
     */
    private boolean buttonsEnabled = true;

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
    private final TextColumn<RESTTopicCollectionItemV1> topicId = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTTopicCollectionItemV1 object) {
            return object.getItem().getId().toString();
        }
    };
    /**
     * The column displaying the revision date.
     */
    private final TextColumn<RESTTopicCollectionItemV1> lastModified = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTTopicCollectionItemV1 object) {
            return DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG).format(object.getItem().getLastModified());
        }
    };
    /**
     * The column displaying the revision message.
     */
    @NotNull
    private final TextColumn<RESTTopicCollectionItemV1> newerOrOlder = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getLastModified() != null) {
                return object.getItem().getLastModified().after(mainTopic.getLastModified()) ?
                        PressGangCCMSUI.INSTANCE.Newer() : PressGangCCMSUI.INSTANCE.Older();
            }
            return "";
        }
    };
    /**
     * The column displaying the revision message.
     */
    @NotNull
    private final TextColumn<RESTTopicCollectionItemV1> contentSpecs = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getContentSpecs_OTM() != null && object.getItem().getContentSpecs_OTM().getSize() != 0) {
                final StringBuilder specs = new StringBuilder();
                for (final RESTContentSpecCollectionItemV1 contentSpec : object.getItem().getContentSpecs_OTM().getItems()) {
                    if (specs.length() != 0) {
                        specs.append("\n");
                    }

                    for (final RESTCSNodeCollectionItemV1 specNode : contentSpec.getItem().getChildren_OTM().getItems()) {
                        if (specNode.getItem().getNodeType() == RESTCSNodeTypeV1.META_DATA &&
                                specNode.getItem().getTitle().equals("Title")) {
                            specs.append(specNode.getItem().getAdditionalText());
                            break;
                        }
                    }
                }
                return specs.toString();
            }
            return "";
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
            viewButtonCell.setEnabled(buttonsEnabled);

            /*
             * The last revision is the same as the topic in the main database. We indicate that by showing the last revision as
             * editable instead of read only.
             */
            if (mainTopic != null && object != null && object.getItem() != null && object.getItem().getRevision().equals(mainTopic.getRevision())) {
                if (duplicateTopic == null || duplicateTopic.getRevision().equals(mainTopic.getRevision())) {
                    viewButtonCell.setEnabled(false);
                    return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                } else {
                    return PressGangCCMSUI.INSTANCE.Edit();
                }
            }

            if (duplicateTopic == null || (object != null && object.getItem() != null && !duplicateTopic.getRevision().equals(object.getItem().getRevision()))) {
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
            htmlDiffButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            htmlDiffButtonCell.setEnabled(buttonsEnabled);

            if (mainTopic != null) {
                if (object != null && object.getItem() != null && object.getItem().getRevision().equals(mainTopic.getRevision())) {
                    if (duplicateTopic == null || duplicateTopic.getRevision().equals(mainTopic.getRevision())) {
                        htmlDiffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                    }
                }

                if (duplicateTopic == null || (object != null && object.getItem() != null && !duplicateTopic.getRevision().equals(object.getItem().getRevision()))) {

                    final String viewingXML = duplicateTopic == null ? mainTopic.getXml() : duplicateTopic.getXml();
                    final String fixedViewingXML = viewingXML == null ? "" : viewingXML.trim();

                    checkState(object.getItem() != null, "The collection item should reference a valid entity.");

                    if (object.getItem().getXml() == null || object.getItem().getXml().trim().isEmpty()) {
                        /* Diffs don't work if there is no XML to compare to */
                        htmlDiffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.NoXML();
                    } else if (object.getItem().getXml().trim().equals(fixedViewingXML)) {
                        /* The XML is the same */
                        htmlDiffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.SameXML();
                    } else {
                        return PressGangCCMSUI.INSTANCE.Diff();
                    }
                }
            }

            htmlDiffButtonCell.setEnabled(false);
            return PressGangCCMSUI.INSTANCE.CurrentlyViewing();
        }
    };

    /**
     * The column that displays the diff button.
     */
    @NotNull
    private final Column<RESTTopicCollectionItemV1, String> htmlDiffButton = new Column<RESTTopicCollectionItemV1, String>(
            htmlDiffButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            htmlDiffButtonCell.setEnabled(buttonsEnabled);

            if (mainTopic != null) {
                if (object != null && object.getItem() != null && object.getItem().getRevision().equals(mainTopic.getRevision())) {
                    if (duplicateTopic == null || duplicateTopic.getRevision().equals(mainTopic.getRevision())) {
                        htmlDiffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.CurrentlyEditing();
                    }
                }

                if (duplicateTopic == null || (object != null && object.getItem() != null && !duplicateTopic.getRevision().equals(object.getItem().getRevision()))) {

                    final String viewingXML = duplicateTopic == null ? mainTopic.getXml() : duplicateTopic.getXml();
                    final String fixedViewingXML = viewingXML == null ? "" : viewingXML.trim();

                    checkState(object.getItem() != null, "The collection item should reference a valid entity.");

                    if (object.getItem().getXml() == null || object.getItem().getXml().trim().isEmpty()) {
                        /* Diffs don't work if there is no XML to compare to */
                        htmlDiffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.NoXML();
                    } else if (object.getItem().getXml().trim().equals(fixedViewingXML)) {
                        /* The XML is the same */
                        htmlDiffButtonCell.setEnabled(false);
                        return PressGangCCMSUI.INSTANCE.SameXML();
                    } else {
                        return PressGangCCMSUI.INSTANCE.HTMLDiff();
                    }
                }
            }

            htmlDiffButtonCell.setEnabled(false);
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
    private RESTTopicV1 duplicateTopic;
    /**
     * A reference to the displayed topic.
     */
    private RESTTopicV1 mainTopic;

    /**
     * The image to display in the waiting dialog.
     */
    private final Image spinner = new Image(ImageResources.INSTANCE.spinner());

    /**
     * The image to display if the rendered diff could not be created
     */
    private final Image bigError = new Image(ImageResources.INSTANCE.bigError());

    /**
     * Builds the UI.
     */
    public TopicDuplicatesView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Revisions());

        LOGGER.info("ENTER TopicRevisionsView()");

        this.getPanel().addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_BASE_PANEL);

        results.addColumn(viewButton, PressGangCCMSUI.INSTANCE.View() + " / " + PressGangCCMSUI.INSTANCE.Edit());
        results.addColumn(diffButton, PressGangCCMSUI.INSTANCE.Diff());
        results.addColumn(htmlDiffButton, PressGangCCMSUI.INSTANCE.HTMLDiff());
        results.addColumn(topicId, PressGangCCMSUI.INSTANCE.TopicID());
        results.addColumn(lastModified, PressGangCCMSUI.INSTANCE.TopicLastModified());
        results.addColumn(newerOrOlder, PressGangCCMSUI.INSTANCE.NewerOrOlder());
        results.addColumn(contentSpecs, PressGangCCMSUI.INSTANCE.TopicContentSpecs());


        /*results.addColumnStyleName(0, CSSConstants.TopicRevisionView.TOPIC_REVISION_NUMBER_COLUMN);
        results.addColumnStyleName(1, CSSConstants.TopicRevisionView.TOPIC_REVISION_DATE_COLUMN);
        results.addColumnStyleName(2, CSSConstants.TopicRevisionView.TOPIC_REVISION_MINOR_RELEASE_COLUMN);
        results.addColumnStyleName(3, CSSConstants.TopicRevisionView.TOPIC_REVISION_MAJOR_RELEASE_COLUMN);
        results.addColumnStyleName(4, CSSConstants.TopicRevisionView.TOPIC_REVISION_USER_COLUMN);
        results.addColumnStyleName(5, CSSConstants.TopicRevisionView.TOPIC_REVISION_MESSAGE_COLUMN);
        results.addColumnStyleName(6, CSSConstants.TopicRevisionView.TOPIC_REVISION_VIEW_COLUMN);
        results.addColumnStyleName(7, CSSConstants.TopicRevisionView.TOPIC_REVISION_DIFF_COLUMN);*/

        searchResultsPanel.addStyleName(CSSConstants.TopicView.SEARCH_RESULTS_PANEL);

        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        /*
            Setup the mergely container
         */
        diffParent.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_DIFF_PARENT_PANEL);


        final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_DIFF_BUTTON_PANEL);
        buttonPanel.add(done);
        buttonPanel.add(cancel);

        diffPanel.addSouth(buttonPanel, BUTTON_PANEL_HEIGHT);
        diffPanel.add(diffParent);
        diffPanel.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_DIFF_PANEL);

        /*
            Setup the HTML diff container
         */
        final HorizontalPanel htmlButtonPanel = new HorizontalPanel();
        htmlButtonPanel.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_DIFF_BUTTON_PANEL);
        htmlButtonPanel.add(htmlDone);
        htmlButtonPanel.add(htmlOpenDiff);


        htmlDiffPanel.addSouth(htmlButtonPanel, BUTTON_PANEL_HEIGHT);
        htmlDiffPanel.add(htmlDiffParent);
        htmlDiffPanel.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_DIFF_PANEL);

        this.getPanel().setWidget(searchResultsPanel);

        spinner.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_VIEW_SPINNER);
        bigError.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_VIEW_SPINNER);
    }

    @NotNull
    @Override
    public Column<RESTTopicCollectionItemV1, String> getDiffButton() {
        return diffButton;
    }

    @NotNull
    @Override
    public Column<RESTTopicCollectionItemV1, String> getHTMLDiffButton() {
        return htmlDiffButton;
    }

    @Override
    public RESTTopicV1 getDuplicateTopic() {
        return duplicateTopic;
    }

    @Override
    public void setDuplicateTopic(@NotNull final RESTTopicV1 duplicateTopic) {
        this.duplicateTopic = duplicateTopic;
    }

    @NotNull
    @Override
    public Column<RESTTopicCollectionItemV1, String> getViewButton() {
        return viewButton;
    }

    @NotNull
    @Override
    public EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(@NotNull final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider) {
        if (this.provider != null) {
            this.provider.removeDataDisplay(results);
        }
        this.provider = provider;
        if (provider != null) {
            provider.addDataDisplay(results);
        }
    }

    @NotNull
    @Override
    public CellTable<RESTTopicCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    @Override
    public void display(@NotNull final RESTTopicV1 topic, final boolean readOnly) {
        this.mainTopic = topic;
        this.readOnly = readOnly;
    }

    @Override
    public void displayDuplicates() {
        this.getPanel().setWidget(searchResultsPanel);
        isDisplayingDuplicates = true;
    }

    /**
     * Display a mergely diff viewer.
     * <p/>
     * For mergely to display properly, it has to be attached when the parent element is attached to the DOM. If you
     * attach mergely when the parent is detached, it will not resize properly.
     *
     * @param lhs         The text for the left hand side
     * @param rhsReadOnly true if the right hand side is read only, and false otherwise
     * @param rhs         The text for the right hand side
     */
    @Override
    public void displayDiff(@NotNull final String lhs, boolean rhsReadOnly, @NotNull final String rhs) {
        diffParent.setWidget(null);

        mergely = new Mergely(lhs, true, rhs, rhsReadOnly || readOnly, true, Constants.XML_MIME_TYPE, false);
        mergely.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_DIFF);

        diffParent.setWidget(mergely);
        this.getPanel().setWidget(diffPanel);

        isDisplayingDuplicates = false;

        done.setEnabled(!readOnly);
    }

    public void displayHtmlDiff(@NotNull final String htmlDiff) {
        final Frame diffFrame = new Frame();
        diffFrame.addStyleName(CSSConstants.TopicRevisionView.TOPIC_REVISION_HTML_DIFF_FRAME);
        htmlDiffParent.setWidget(diffFrame);
        this.getPanel().setWidget(htmlDiffPanel);

        final IFrameElement iFrameElement = diffFrame.getElement().cast();
        GWTUtilities.writeHTMLToIFrame(iFrameElement.getContentDocument(), htmlDiff);

        isDisplayingDuplicates = false;
    }

    @Override
    public void showWaitingFromRenderedDiff() {
        this.getPanel().setWidget(spinner);
        this.isDisplayingDuplicates = false;
    }

    @Override
    public void showRenderedDiffError() {
        this.getPanel().setWidget(bigError);
        isDisplayingDuplicates = false;
    }

    @NotNull
    @Override
    public PushButton getDone() {
        return done;
    }

    @NotNull
    @Override
    public PushButton getHTMLDone() {
        return htmlDone;
    }

    @Override
    @Nullable
    public Mergely getMergely() {
        return mergely;
    }

    @NotNull
    @Override
    public PushButton getCancel() {
        return cancel;
    }

    @Override
    public boolean isDisplayingDuplicates() {
        return isDisplayingDuplicates;
    }

    @Override
    public boolean isButtonsEnabled() {
        return buttonsEnabled;
    }

    @Override
    public void setButtonsEnabled(final boolean buttonsEnabled) {
        this.buttonsEnabled = buttonsEnabled;
        results.redraw();
    }


    /**
     * The open diff button.
     */
    @Override
    public PushButton getHtmlOpenDiff() {
        return htmlOpenDiff;
    }

    /**
     * The panel that holds the table and pager.
     */
    @Override
    @NotNull
    public VerticalPanel getSearchResultsPanel() {
        return searchResultsPanel;
    }

    /**
     * The parent for the mergely elements. Needs to be a layout panel so resize events are propogated.
     */
    public SimpleLayoutPanel getDiffParent() {
        return diffParent;
    }


}
