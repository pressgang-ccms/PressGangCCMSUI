package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ValueListBox;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

public class TopicTagsPresenter extends BaseTemplatePresenter {

    public interface Display extends BasePopulatedEditorViewInterface<RESTBaseTopicV1<?, ?, ?>, SearchUIProjects, TopicTagViewProjectsEditor> {
        void initializeNewTags(final SearchUIProjects tags);

        void updateNewTagCategoriesDisplay();

        void updateNewTagTagDisplay();

        ValueListBox<SearchUITag> getMyTags();

        ValueListBox<SearchUICategory> getCategoriesList();

        ValueListBox<SearchUIProject> getProjectsList();

        TopicTagViewProjectsEditor getEditor();

        PushButton getAdd();
    }

    public static final String HISTORY_TOKEN = "TopicTagsView";

    private static final Logger LOGGER = Logger.getLogger(TopicTagsPresenter.class.getName());

    @Nullable
    private Integer topicId;

    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    /**
     * A copy of all the tags in the system, broken down into project->category->tag. Used when adding new tags to a topic.
     */
    private final SearchUIProjects searchUIProjects = new SearchUIProjects();

    @NotNull
    public SearchUIProjects getSearchUIProjects() {
        return searchUIProjects;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            topicId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            topicId = null;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.TOPIC_TAGS_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int helpTopicId, @NotNull final String pageId) {
        super.bind(helpTopicId, pageId, display);
        getTags();
    }

    /**
     * Gets the tags, so they can be displayed and added to topics
     */
    private void getTags() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicTagsPresenter.getTags()");

            @NotNull final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, TopicTagsPresenter.Display>(
                    display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, TopicTagsPresenter.Display>() {
                @Override
                public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, @NotNull final TopicTagsPresenter.Display display) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicTagsPresenter.getTags() callback.doSuccessAction()");

                        checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                        searchUIProjects.initialize(retValue, null);
                        display.initializeNewTags(searchUIProjects);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT TopicTagsPresenter.getTags() callback.doSuccessAction()");
                    }
                }
            });
            RESTCalls.getTags(callback);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicTagsPresenter.getTags()");
        }
    }

    /**
     * Add behaviour to the tag view screen elements
     */
    public void bindNewTagListBoxes(@NotNull final ClickHandler clickHandler) {
        display.getProjectsList().addValueChangeHandler(new ValueChangeHandler<SearchUIProject>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<SearchUIProject> event) {
                display.updateNewTagCategoriesDisplay();
            }
        });

        display.getCategoriesList().addValueChangeHandler(new ValueChangeHandler<SearchUICategory>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<SearchUICategory> event) {
                display.updateNewTagTagDisplay();
            }
        });

        display.getAdd().addClickHandler(clickHandler);
    }
}
