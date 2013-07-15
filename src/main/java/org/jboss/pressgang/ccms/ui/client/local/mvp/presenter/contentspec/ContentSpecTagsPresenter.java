package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ValueListBox;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
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

public class ContentSpecTagsPresenter extends BaseTemplatePresenter {

    public interface Display extends BasePopulatedEditorViewInterface<RESTTextContentSpecV1, SearchUIProjects, TopicTagViewProjectsEditor> {
        void initializeNewTags(@NotNull final RESTTagCollectionV1 retValue);

        void updateNewTagCategoriesDisplay();

        void updateNewTagTagDisplay();

        ValueListBox<SearchUITag> getMyTags();

        ValueListBox<SearchUICategory> getCategoriesList();

        ValueListBox<SearchUIProject> getProjectsList();

        TopicTagViewProjectsEditor getEditor();

        PushButton getAdd();
    }

    public static final String HISTORY_TOKEN = "ContentSpecTagsView";

    private static final Logger LOGGER = Logger.getLogger(ContentSpecTagsPresenter.class.getName());

    @Nullable
    private Integer contentSpecID;

    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            contentSpecID = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            contentSpecID = null;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        getTags();
    }

    @Override
    public void close() {
    }

    public void bindExtended(final int helpTopicId, @NotNull final String pageId) {
        super.bind(helpTopicId, pageId, display);
    }

    /**
     * Gets the tags, so they can be displayed and added to topics
     */
    public void getTags() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecTagsPresenter.getTags()");

            final RESTCallBack<RESTTagCollectionV1> callback = new RESTCallBack<RESTTagCollectionV1>() {
                @Override
                public void success(@NotNull final RESTTagCollectionV1 retValue) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER ContentSpecTagsPresenter.getTags() callback.doSuccessAction()");

                        checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                        display.initializeNewTags(retValue);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT ContentSpecTagsPresenter.getTags() callback.doSuccessAction()");
                    }
                }
            };

            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTags(), callback, display);
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecTagsPresenter.getTags()");
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
