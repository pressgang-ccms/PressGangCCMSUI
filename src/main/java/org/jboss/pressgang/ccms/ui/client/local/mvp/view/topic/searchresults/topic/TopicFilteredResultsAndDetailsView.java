package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.base.BaseSearchResultsAndTopicView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * The view that combines the topic search results with the individual topic views
 *
 * @author Matthew Casperson
 */
@Dependent
public class TopicFilteredResultsAndDetailsView extends
        BaseSearchResultsAndTopicView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> implements
        TopicFilteredResultsAndDetailsPresenter.Display {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TopicFilteredResultsAndDetailsView.class.getName());

    private LogMessageAndContentSpecListView messageLogAndContentSpecListDialog = new LogMessageAndContentSpecListView();

    /**
     * The save button.
     */
    private final PushButton save;
    /**
     * The revisions button.
     */
    private final PushButton history;
    private final PushButton duplicates;
    private final PushButton csps;
    /**
     * The label used to represent the history button in a down state.
     */
    private final Label historyDown;
    private final Label duplicatesDown;
    private final Label cspsDown;

    private PushButton review = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Review());
    private Label reviewDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Review());

    /**
     * The bulk import dialog box.
     */
    @Inject
    private BulkImportImpl bulkImport;

    /**
     * The bulk overwrite dialog box.
     */
    @Inject
    private BulkOverwriteImpl bulkOverwrite;

    /**
     * The create topic dialog box.
     */
    @Inject
    private CreateWizardImpl createWizard;

    @Override
    @NotNull
    public PushButton getReview() {
        return review;
    }

    @Override
    @NotNull
    public Label getReviewDown() {
        return reviewDown;
    }

    @Override
    @NotNull
    public TopicFilteredResultsAndDetailsPresenter.BulkImport getBulkImport() {
        return bulkImport;
    }

    @Override
    @NotNull
    public TopicFilteredResultsAndDetailsPresenter.BulkOverwrite getBulkOverwrite() {
        return bulkOverwrite;
    }

    @NotNull
    @Override
    public TopicFilteredResultsAndDetailsPresenter.CreateWizard getCreateWizard() {
        return createWizard;
    }

    @Override
    @NotNull
    public Label getHistoryDown() {
        return historyDown;
    }

    @Override
    @NotNull
    public PushButton getHistory() {
        return history;
    }

    @Override
    @NotNull
    public Label getDuplicatesDown() {
        return duplicatesDown;
    }

    @Override
    @NotNull
    public PushButton getDuplicates() {
        return duplicates;
    }

    @Override
    @NotNull
    public PushButton getSave() {
        return save;
    }

    @NotNull
    @Override
    public PushButton getCsps() {
        return csps;
    }

    @NotNull
    @Override
    public Label getCspsDown() {
        return cspsDown;
    }

    @NotNull
    @Override
    public LogMessageAndContentSpecListView getMessageLogDialog() {
        return messageLogAndContentSpecListDialog;
    }

    public TopicFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

        /* Build the action bar icons */
        save = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Save(), Constants.ElementIDs.SAVE_TOPIC_EDIT_BUTTON_ID.getId());
        history = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Revisions(), Constants.ElementIDs.REVISIONS_TOPIC_EDIT_BUTTON_ID.getId());
        duplicates = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Duplicates(), Constants.ElementIDs.REVISIONS_TOPIC_EDIT_BUTTON_ID.getId());
        csps = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ContentSpecifications(), Constants.ElementIDs.CONTENT_SPECS_TOPIC_EDIT_BUTTON_ID.getId());

        historyDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Revisions(), Constants.ElementIDs.REVISIONS_TOPIC_EDIT_BUTTON_ID.getId());
        duplicatesDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Duplicates(), Constants.ElementIDs.REVISIONS_TOPIC_EDIT_BUTTON_ID.getId());
        cspsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ContentSpecifications(), Constants.ElementIDs.CONTENT_SPECS_TOPIC_EDIT_BUTTON_ID.getId());

        addActionButton(csps);
        addActionButton(history, true);
        //addActionButton(duplicates);
        addActionButton(save);
    }
}
