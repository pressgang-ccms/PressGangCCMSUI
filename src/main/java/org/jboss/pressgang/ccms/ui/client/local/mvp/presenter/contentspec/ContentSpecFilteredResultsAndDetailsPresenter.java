package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTContentSpecV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * The presenter that combines all the content spec presenters.
 */
public class ContentSpecFilteredResultsAndDetailsPresenter
        extends BaseSearchAndEditPresenter<
            RESTContentSpecV1,
            RESTContentSpecCollectionV1,
            RESTContentSpecCollectionItemV1,
            RESTContentSpecV1BasicDetailsEditor> {

    public final static String HISTORY_TOKEN = "ContentSpecFilteredResultsAndContentSpecView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ContentSpecFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject private Display display;

    @Inject private ContentSpecPresenter contentSpecPresenter;
    @Inject private ContentSpecDetailsPresenter contentSpecDetailsPresenter;
    @Inject private ContentSpecFilteredResultsPresenter filteredResultsPresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    private String contentSpecText = "";

    @Override
    protected void loadAdditionalDisplayedItemData() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        if (viewIsInFilter(filter, contentSpecDetailsPresenter.getDisplay())) {
            contentSpecDetailsPresenter.getDisplay().displayContentSpecDetails(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false, new ArrayList<String>());
        }

        if (viewIsInFilter(filter, contentSpecPresenter.getDisplay())) {
            contentSpecPresenter.getDisplay().display(contentSpecText, false);
        }
    }

    @Override
    protected void bindActionButtons() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void bindFilteredResultsButtons() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTContentSpecV1> getNewEntityCallback = new GetNewEntityCallback<RESTContentSpecV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTContentSpecV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTContentSpecV1> displayCallback) {
                @NotNull final RESTCalls.RESTCallback<RESTContentSpecV1> callback = new BaseRestCallback<RESTContentSpecV1, BaseTemplateViewInterface>(
                        display, new BaseRestCallback.SuccessAction<RESTContentSpecV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTContentSpecV1 retValue, @NotNull final BaseTemplateViewInterface display) {
                        displayCallback.displayNewEntity(retValue);
                    }
                });
                RESTCalls.getContentSpec(callback, selectedEntity.getId());
            }
        };


        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + pageId);

        contentSpecPresenter.bindExtended(ServiceConstants.CONTENT_SPEC_TEXT_EDIT_HELP_TOPIC, pageId);

        super.bindSearchAndEdit(topicId, pageId, Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, contentSpecDetailsPresenter.getDisplay(), contentSpecDetailsPresenter.getDisplay(),
                filteredResultsPresenter.getDisplay(), filteredResultsPresenter, display, display, getNewEntityCallback);
    }

    @Override
    public void parseToken(@NotNull String historyToken) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindSearchAndEditExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    /**
     * The view that holds the other views
     *
     * @author Matthew Casperson
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTContentSpecV1, RESTContentSpecCollectionV1, RESTContentSpecCollectionItemV1> {
        PushButton getText();

        PushButton getDetails();

        PushButton getSave();

        Label getTextDown();

        Label getDetailsDown();
    }
}
