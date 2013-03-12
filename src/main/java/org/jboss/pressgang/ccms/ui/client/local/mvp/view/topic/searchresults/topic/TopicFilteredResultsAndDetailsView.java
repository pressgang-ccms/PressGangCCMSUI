package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.base.BaseSearchResultsAndTopicView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.vectomatic.file.FileUploadExt;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import java.util.logging.Logger;

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
    /**
     * The save button.
     */
    private final PushButton save;
    /**
     * The revisions button.
     */
    private final PushButton history;
    /**
     * The label used to represent the history button in a down state.
     */
    private final Label historyDown;

    /**
     * The bulk import dialog box.
     */
    private @Inject
    BulkImportImpl bulkImport;

    @Override
    @NotNull
    public BulkImport getBulkImport() {
        return bulkImport;
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
    public PushButton getSave() {
        return save;
    }

    public TopicFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

        /* Build the action bar icons */
        save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
        history = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Revisions());

        historyDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Revisions());

        this.addActionButton(history);
        this.addActionButton(save);
    }

    @Dependent
    public static class BulkImportImpl extends DialogBox implements BulkImport {

        private final FileUploadExt fileUploadExt = new FileUploadExt();
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        @New private TopicTagsPresenter.Display tagsView;
        private final FlexTable layout = new FlexTable();

        @NotNull
        @Override
        public DialogBox getDialog() {
            return this;
        }

        @NotNull
        @Override
        public FileUploadExt getFiles() {
            return fileUploadExt;
        }

        @NotNull
        @Override
        public PushButton getOK() {
            return ok;
        }

        @NotNull
        @Override
        public PushButton getCancel() {
            return cancel;
        }

        @NotNull
        @Override
        public TopicTagsPresenter.Display getTagsView() {
            return tagsView;
        }

        public BulkImportImpl() {
            this.setGlassEnabled(true);
            /* If true, this interferes with the "After the Deadline" plugin */
            this.setModal(false);
            this.setText(PressGangCCMSUI.INSTANCE.BulkImageUpload());

            @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.SelectFiles()));
            this.layout.setWidget(0, 1, fileUploadExt);
            this.layout.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.CommonDescription()));
            this.layout.setWidget(1, 1, tagsView.getPanel());
            this.layout.setWidget(2, 0, buttonPanel);

            this.layout.getFlexCellFormatter().setColSpan(2, 0, 2);

            this.add(this.layout);
        }
    }

}
