package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTContentSpecV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter that combines all the content spec presenters.
 */
@Dependent
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

    /**
     * The text version of the content spec, or null if it hasn't been loaded yet.
     */
    private String contentSpecText = null;

    @Override
    protected void loadAdditionalDisplayedItemData() {

        /*
            Load the text version of the content spec.
         */
        final BaseRestCallback<String, Display> callback = new BaseRestCallback<String, Display>(
                display,
                new BaseRestCallback.SuccessAction<String, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final String retValue, @NotNull final Display display) {
                        contentSpecText = retValue;
                        initializeViews(new ArrayList<BaseTemplateViewInterface>(){{add(contentSpecPresenter.getDisplay());}});
                    }
                }
        );

        RESTCalls.getContentSpecText(callback, filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
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
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                display.getMessageLogDialog().getUsername().setText(Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));

                display.getMessageLogDialog().getDialogBox().center();
                display.getMessageLogDialog().getDialogBox().show();
            }
        };

        final ClickHandler messageLogDialogOK = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                try {
                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");

                    final String user = display.getMessageLogDialog().getUsername().getText().trim();
                    Preferences.INSTANCE.saveSetting(Preferences.LOG_MESSAGE_USERNAME, user);

                    final StringBuilder message = new StringBuilder();
                    if (!user.isEmpty()) {
                        message.append(user).append(": ");
                    }
                    message.append(display.getMessageLogDialog().getMessage().getText());
                    final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants.MINOR_CHANGE : ServiceConstants.MAJOR_CHANGE);

                    /*
                        Save the text version of the content spec.
                    */
                    final BaseRestCallback<String, Display> callback = new BaseRestCallback<String, Display>(
                            display,
                            new BaseRestCallback.SuccessAction<String, Display>() {
                                @Override
                                public void doSuccessAction(@NotNull final String retValue, @NotNull final Display display) {
                                    contentSpecText = retValue;
                                    initializeViews(new ArrayList<BaseTemplateViewInterface>() {{add(contentSpecPresenter.getDisplay());}});
                                }
                            }
                    );

                    RESTCalls.updateContentSpecText(callback, contentSpecText, message.toString(), flag, ServiceConstants.NULL_USER_ID.toString());
                } finally {
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().hide();

                    LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");
                }
            }
        };

        final ClickHandler messageLogDialogCancel = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                display.getMessageLogDialog().reset();
                display.getMessageLogDialog().getDialogBox().hide();
            }
        };

        display.getSave().addClickHandler(saveClickHandler);
        display.getMessageLogDialog().getOk().addClickHandler(messageLogDialogOK);
        display.getMessageLogDialog().getCancel().addClickHandler(messageLogDialogCancel);
    }

    @Override
    protected void bindFilteredResultsButtons() {
        display.getText().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(contentSpecPresenter.getDisplay());
            }
        }) ;

        display.getDetails().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(contentSpecDetailsPresenter.getDisplay());
            }
        }) ;
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

        contentSpecDetailsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        contentSpecPresenter.bindExtended(ServiceConstants.CONTENT_SPEC_TEXT_EDIT_HELP_TOPIC, pageId);
        filteredResultsPresenter.bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, pageId, queryString);

        super.bindSearchAndEdit(topicId, pageId, Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, contentSpecPresenter.getDisplay(), contentSpecDetailsPresenter.getDisplay(),
                filteredResultsPresenter.getDisplay(), filteredResultsPresenter, display, display, getNewEntityCallback);
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

        this.queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);

        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            /* Make sure that the query string has at least the prefix */
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindSearchAndEditExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {

        enableAndDisableActionButtons(displayedView);
        setHelpTopicForView(displayedView);

        /* Show any wait dialogs from the new view, and update the view with the currently displayed entity */
        if (displayedView != null) {
            displayedView.setViewShown(true);
        }
    }

    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        this.display.replaceTopActionButton(this.display.getTextDown(), this.display.getText());
        this.display.replaceTopActionButton(this.display.getDetailsDown(), this.display.getDetails());

        if (displayedView == this.contentSpecDetailsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getDetails(), this.display.getDetailsDown());
        } else if (displayedView == this.contentSpecPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getText(), this.display.getTextDown());
        }
    }

    private void setHelpTopicForView(@NotNull final BaseTemplateViewInterface view) {
        if (view == contentSpecDetailsPresenter.getDisplay()) {
            setHelpTopicId(contentSpecDetailsPresenter.getHelpTopicId());
        } else if (view == contentSpecPresenter.getDisplay()) {
            setHelpTopicId(contentSpecPresenter.getHelpTopicId());
        }
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

        LogMessageInterface getMessageLogDialog();
    }
}
