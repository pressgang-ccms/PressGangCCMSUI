package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.BlobConstantFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.blobconstant.RESTBlobConstantV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.*;

/**
 * The presenter used to display a list of integer constants and their details.
 */
@Dependent
public class BlobConstantFilteredResultsAndDetailsPresenter extends
        BaseSearchAndEditComponent<
                RESTBlobConstantV1,
                RESTBlobConstantCollectionV1,
                RESTBlobConstantCollectionItemV1,
                RESTBlobConstantV1DetailsEditor>
implements BaseTemplatePresenterInterface {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "BlobConstantFilteredResultsAndDetailsView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BlobConstantFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    @Inject private BlobConstantFilteredResultsPresenter blobConstantFilteredResultsPresenter;
    @Inject private BlobConstantPresenter blobConstantPresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Inject
    private HandlerManager eventBus;


    @Override
    protected void loadAdditionalDisplayedItemData() {
        /*
            Do Nothing.
         */
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        blobConstantPresenter.getDisplay().display(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false);
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to save any changes to the project
         */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                blobConstantPresenter.getDisplay().getDriver().flush();

                final RESTCalls.RESTCallback<RESTBlobConstantV1> callback = new BaseRestCallback<RESTBlobConstantV1, Display>(display,
                        new BaseRestCallback.SuccessAction<RESTBlobConstantV1, Display>() {
                            @Override
                            public void doSuccessAction(final RESTBlobConstantV1 retValue, final Display display) {
                                retValue.cloneInto(blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);
                                retValue.cloneInto(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);

                                /* This project is no longer a new project */
                                blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                                blobConstantFilteredResultsPresenter.getDisplay().getProvider().updateRowData(
                                        blobConstantFilteredResultsPresenter.getProviderData().getStartRow(),
                                        blobConstantFilteredResultsPresenter.getProviderData().getItems());

                                updateDisplayAfterSave(wasNewEntity);

                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        });

                if (blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

                        final RESTBlobConstantV1 project = new RESTBlobConstantV1();
                        project.setId(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
                        project.explicitSetName(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName());
                        project.explicitSetValue(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getValue());

                        if (wasNewEntity) {
                            RESTCalls.createBlobConstant(callback, project);
                        } else {
                            RESTCalls.updateBlobConstant(callback, project);
                        }
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                }
            }
        };

        display.getSave().addClickHandler(saveClickHandler);
    }

    @Override
    protected void bindFilteredResultsButtons() {
        blobConstantFilteredResultsPresenter.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new BlobConstantFilteredResultsAndDetailsViewEvent(blobConstantFilteredResultsPresenter.getQuery(),
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        blobConstantFilteredResultsPresenter.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTBlobConstantV1 selectedEntity = new RESTBlobConstantV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTBlobConstantCollectionItemV1 selectedTagWrapper = new RESTBlobConstantCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTBlobConstantV1 displayedEntity = new RESTBlobConstantV1();
                displayedEntity.setId(Constants.NULL_ID);
                final RESTBlobConstantCollectionItemV1 displayedTagWrapper = new RESTBlobConstantCollectionItemV1(displayedEntity, RESTBaseCollectionItemV1.ADD_STATE);

                blobConstantFilteredResultsPresenter.getProviderData().setSelectedItem(selectedTagWrapper);
                blobConstantFilteredResultsPresenter.getProviderData().setDisplayedItem(displayedTagWrapper);

                initializeViews();

                switchView(lastDisplayedView == null ? blobConstantPresenter.getDisplay() : lastDisplayedView);
            }
        });
    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, final String pageId, final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTBlobConstantV1> getNewEntityCallback = new GetNewEntityCallback<RESTBlobConstantV1>() {

            @Override
            public void getNewEntity(final RESTBlobConstantV1 selectedEntity, final DisplayNewEntityCallback<RESTBlobConstantV1> displayCallback) {
                /*
                    There is nothing additional to load here, so just return the selected entity.
                 */
                displayCallback.displayNewEntity(selectedEntity);
            }
        };


        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        blobConstantFilteredResultsPresenter.bindExtendedFilteredResults(ServiceConstants.BLOB_CONSTANT_HELP_TOPIC, pageId, queryString);
        blobConstantPresenter.bindExtended(ServiceConstants.STRING_CONSTANT_DETAILS_HELP_TOPIC, pageId);
        super.bindSearchAndEdit(topicId, pageId, Preferences.STRING_CONSTANTS_VIEW_MAIN_SPLIT_WIDTH, blobConstantPresenter.getDisplay(), blobConstantPresenter.getDisplay(),
                blobConstantFilteredResultsPresenter.getDisplay(), blobConstantFilteredResultsPresenter, display, display, getNewEntityCallback);
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindSearchAndEditExtended(ServiceConstants.BLOB_CONSTANT_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
            blobConstantPresenter.getDisplay().getDriver().flush();

            final RESTBlobConstantV1 selectedItem = blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem();
            final RESTBlobConstantV1 displayedItem = blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem();

            return !stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(selectedItem.getName(), displayedItem.getName());
        }
        return false;
    }

    public interface Display extends BaseSearchAndEditViewInterface<
                RESTBlobConstantV1,
                RESTBlobConstantCollectionV1,
                RESTBlobConstantCollectionItemV1> {
        PushButton getSave();
    }
}
