package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.BlobConstantFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.blobconstant.RESTBlobConstantV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.events.ErrorHandler;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.*;

/**
 * The presenter used to display a list of integer constants and their details.
 */
@Dependent
public class BlobConstantFilteredResultsAndDetailsPresenter extends
        BaseSearchAndEditPresenter<
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

    @Inject private FailOverRESTCall failOverRESTCall;

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;
    /**
     * The presenter used to display the filtered results.
     */
    @Inject
    private BlobConstantFilteredResultsPresenter blobConstantFilteredResultsPresenter;
    /**
     * The presenter used to display the blob constant details.
     */
    @Inject
    private BlobConstantPresenter blobConstantPresenter;

    /**
     * The query string extracted from the history token
     */
    private String queryString;

    /**
     * The global event bus.
     */
    @Inject
    private EventBus eventBus;

    @Override
    protected void loadAdditionalDisplayedItemData() {

    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
        checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");

        blobConstantPresenter.getDisplay().display(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false);
        bindUploadButton();
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to save any changes to the project
         */
        @NotNull final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There has to be a displayed item");

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                blobConstantPresenter.getDisplay().getDriver().flush();

                final RESTCallBack<RESTBlobConstantV1> callback = new RESTCallBack<RESTBlobConstantV1>() {
                    @Override
                    public void success(@NotNull final RESTBlobConstantV1 retValue) {
                        checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");
                        checkState(blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem() != null, "There has to be a selected item");
                        checkState(blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem() != null, "The selected item need to reference a valid entity");
                        checkState(blobConstantFilteredResultsPresenter.getProviderData().isValid(), "The provider data needs to be valid");

                        retValue.cloneInto(blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);
                        retValue.cloneInto(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);

                                /* This project is no longer a new project */
                        blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                        blobConstantFilteredResultsPresenter.getDisplay().getProvider().updateRowData(
                                blobConstantFilteredResultsPresenter.getProviderData().getStartRow(),
                                blobConstantFilteredResultsPresenter.getProviderData().getItems());

                        updateDisplayWithNewEntityData(wasNewEntity);

                        Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                    }
                };

                if (blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

                        checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");

                        final RESTBlobConstantV1 blobConstant = new RESTBlobConstantV1();
                        blobConstant.setId(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
                        blobConstant.explicitSetName(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName());
                        blobConstant.explicitSetValue(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getValue());

                        if (wasNewEntity) {
                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.createBlobConstant(blobConstant), callback, display);
                        } else {
                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.updateBlobConstant(blobConstant), callback, display);
                        }
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                }
            }
        };

        display.getSave().addClickHandler(saveClickHandler);
    }

    private void doSearch(final boolean newWindow) {
        if (isOKToProceed()) {
            eventBus.fireEvent(new BlobConstantFilteredResultsAndDetailsViewEvent(blobConstantFilteredResultsPresenter.getQuery(), newWindow));
        }
    }

    @Override
    protected void bindFilteredResultsButtons() {
        blobConstantFilteredResultsPresenter.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                doSearch(GWTUtilities.isEventToOpenNewWindow(event));
            }
        });

        final KeyPressHandler searchKeyPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(@NotNull final KeyPressEvent event) {
                if (GWTUtilities.enterKeyWasPressed(event)) {
                    doSearch(false);
                }
            }
        };

        blobConstantFilteredResultsPresenter.getDisplay().getIdFilter().addKeyPressHandler(searchKeyPressHandler);
        blobConstantFilteredResultsPresenter.getDisplay().getNameFilter().addKeyPressHandler(searchKeyPressHandler);

        blobConstantFilteredResultsPresenter.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTBlobConstantV1 selectedEntity = new RESTBlobConstantV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTBlobConstantCollectionItemV1 selectedTagWrapper = new RESTBlobConstantCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTBlobConstantV1 displayedEntity = new RESTBlobConstantV1();
                displayedEntity.setId(Constants.NULL_ID);
                final RESTBlobConstantCollectionItemV1 displayedTagWrapper = new RESTBlobConstantCollectionItemV1(displayedEntity, RESTBaseCollectionItemV1.ADD_STATE);

                blobConstantFilteredResultsPresenter.setSelectedItem(selectedTagWrapper);
                blobConstantFilteredResultsPresenter.getProviderData().setDisplayedItem(displayedTagWrapper);

                initializeViews();

                switchView(lastDisplayedView == null ? blobConstantPresenter.getDisplay() : lastDisplayedView);
            }
        });
    }

    @Override
    public void bindSearchAndEditExtended(@NotNull final String queryString) {
        try {
            LOGGER.log(Level.INFO, "ENTER BlobConstantFilteredResultsAndDetailsPresenter.bindSearchAndEditExtended()");

            /* A call back used to get a fresh copy of the entity that was selected */
            @NotNull final GetNewEntityCallback<RESTBlobConstantV1> getNewEntityCallback = new GetNewEntityCallback<RESTBlobConstantV1>() {

                @Override
                public void getNewEntity(@NotNull final RESTBlobConstantV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTBlobConstantV1> displayCallback) {
                    /*
                        There is nothing additional to load here, so just return the selected entity.
                     */
                    displayCallback.displayNewEntity(selectedEntity);
                }
            };

            blobConstantFilteredResultsPresenter.bindExtendedFilteredResults(queryString);
            blobConstantPresenter.bindExtended();
            super.bindSearchAndEdit(Preferences.STRING_CONSTANTS_VIEW_MAIN_SPLIT_WIDTH, blobConstantPresenter.getDisplay(), blobConstantPresenter.getDisplay(),
                    blobConstantFilteredResultsPresenter.getDisplay(), blobConstantFilteredResultsPresenter, display, display, getNewEntityCallback);

        } finally {
            LOGGER.log(Level.INFO, "EXIT BlobConstantFilteredResultsAndDetailsPresenter.bindSearchAndEditExtended()");
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        try {
            LOGGER.log(Level.INFO, "ENTER BlobConstantFilteredResultsAndDetailsPresenter.go()");

            clearContainerAndAddTopLevelPanel(container, display);
            bindSearchAndEditExtended(queryString);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BlobConstantFilteredResultsAndDetailsPresenter.go()");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
            blobConstantPresenter.getDisplay().getDriver().flush();

            checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
            checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");
            checkState(blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem() != null, "There has to be a selected item");
            checkState(blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem() != null, "The selected item need to reference a valid entity");

            final RESTBlobConstantV1 selectedItem = blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem();
            final RESTBlobConstantV1 displayedItem = blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem();

            return !stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(selectedItem.getName(), displayedItem.getName());
        }
        return false;
    }

    private void bindUploadButton() {
        checkState(blobConstantPresenter.getDisplay().getEditor() != null, "An entity should have been displayed and bound to an editor.");

        blobConstantPresenter.getDisplay().getEditor().getUploadButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                /*
                 * There should only be one file, but use a loop to accommodate any changes that might implement multiple
                 * files
                 */
                for (@NotNull final File file : blobConstantPresenter.getDisplay().getEditor().getUpload().getFiles()) {
                    display.addWaitOperation();

                    final FileReader reader = new FileReader();

                    reader.addErrorHandler(new ErrorHandler() {
                        @Override
                        public void onError(@NotNull final org.vectomatic.file.events.ErrorEvent event) {
                            display.removeWaitOperation();
                        }
                    });

                    reader.addLoadEndHandler(new LoadEndHandler() {
                        @Override
                        public void onLoadEnd(@NotNull final LoadEndEvent event) {
                            try {
                                final String result = reader.getStringResult();
                                final byte[] buffer = GWTUtilities.getByteArray(result, 1);

                                /* Flush any changes */
                                blobConstantPresenter.getDisplay().getDriver().flush();

                                checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
                                checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");

                                /*
                                 * Create the blob constant to be modified. This is so we don't send off unnecessary data.
                                 */
                                final boolean wasNewEntity = blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem();
                                final RESTBlobConstantV1 updateEntity = new RESTBlobConstantV1();
                                updateEntity.setId(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
                                updateEntity.explicitSetName(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName());
                                updateEntity.explicitSetValue(buffer);

                                final RESTCallBack<RESTBlobConstantV1> callback = new RESTCallBack<RESTBlobConstantV1>() {
                                    @Override
                                    public void success(@NotNull final RESTBlobConstantV1 retValue) {
                                        checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
                                        checkState(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");

                                        retValue.cloneInto(blobConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem(), false);
                                        retValue.cloneInto(blobConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false);
                                        initializeViews();
                                        updateDisplayWithNewEntityData(wasNewEntity);
                                    }
                                };

                                if (wasNewEntity) {
                                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.createBlobConstant(updateEntity), callback, display);
                                } else {
                                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.updateBlobConstant(updateEntity), callback, display);
                                }

                            } finally {
                                display.removeWaitOperation();
                            }
                        }
                    });

                    reader.readAsBinaryString(file);

                    /* we only upload one file */
                    break;
                }
            }
        });
    }

    /**
     * The display that this presenter is associated with.
     */
    public interface Display extends BaseSearchAndEditViewInterface<
            RESTBlobConstantV1,
            RESTBlobConstantCollectionV1,
            RESTBlobConstantCollectionItemV1> {
        /**
         * @return The save button.
         */
        PushButton getSave();
    }
}
