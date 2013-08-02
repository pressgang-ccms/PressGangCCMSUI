package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.integerconstants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTIntegerConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.IntegerConstantFilteredResultsAndDetailsViewEvent;
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
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.integerconstant.RESTIntegerConstantV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.*;

/**
 * The presenter used to display a list of integer constants and their details.
 */
@Dependent
public class IntegerConstantFilteredResultsAndDetailsPresenter extends
        BaseSearchAndEditPresenter<
                RESTIntegerConstantV1,
                RESTIntegerConstantCollectionV1,
                RESTIntegerConstantCollectionItemV1,
                RESTIntegerConstantV1DetailsEditor>
        implements BaseTemplatePresenterInterface {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "IntegerConstantFilteredResultsAndDetailsView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(IntegerConstantFilteredResultsAndDetailsPresenter.class.getName());

    @Inject private FailOverRESTCall failOverRESTCall;

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    @Inject
    private IntegerConstantFilteredResultsPresenter integerConstantFilteredResultsPresenter;
    @Inject
    private IntegerConstantPresenter integerConstantPresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Inject
    private EventBus eventBus;


    @Override
    protected void loadAdditionalDisplayedItemData() {
        /*
            Do Nothing.
         */
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        checkState(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        integerConstantPresenter.getDisplay().display(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false);
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to save any changes to the project
         */
        @NotNull final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                checkState(integerConstantFilteredResultsPresenter.getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
                checkState(integerConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                integerConstantPresenter.getDisplay().getDriver().flush();

                final RESTCallBack<RESTIntegerConstantV1> callback = new RESTCallBack<RESTIntegerConstantV1>() {
                    @Override
                    public void success(@NotNull final RESTIntegerConstantV1 retValue) {
                        retValue.cloneInto(integerConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);
                        retValue.cloneInto(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);

                                /* This project is no longer a new project */
                        integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                        integerConstantFilteredResultsPresenter.getDisplay().getProvider().updateRowData(
                                integerConstantFilteredResultsPresenter.getProviderData().getStartRow(),
                                integerConstantFilteredResultsPresenter.getProviderData().getItems());

                        updateDisplayWithNewEntityData(wasNewEntity);

                        Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                    }
                };

                if (integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

                        final RESTIntegerConstantV1 integerConstant = new RESTIntegerConstantV1();
                        integerConstant.setId(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
                        integerConstant.explicitSetName(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName());
                        integerConstant.explicitSetValue(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getValue());

                        if (wasNewEntity) {
                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.createIntegerConstant(integerConstant), callback, display);
                        } else {
                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.updateIntegerConstant(integerConstant), callback, display);
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
            eventBus.fireEvent(new IntegerConstantFilteredResultsAndDetailsViewEvent(integerConstantFilteredResultsPresenter.getQuery(), newWindow));
        }
    }


    @Override
    protected void bindFilteredResultsButtons() {
        integerConstantFilteredResultsPresenter.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
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

        integerConstantFilteredResultsPresenter.getDisplay().getIdFilter().addKeyPressHandler(searchKeyPressHandler);
        integerConstantFilteredResultsPresenter.getDisplay().getNameFilter().addKeyPressHandler(searchKeyPressHandler);

        integerConstantFilteredResultsPresenter.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTIntegerConstantV1 selectedEntity = new RESTIntegerConstantV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTIntegerConstantCollectionItemV1 selectedTagWrapper = new RESTIntegerConstantCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTIntegerConstantV1 displayedEntity = new RESTIntegerConstantV1();
                displayedEntity.setId(Constants.NULL_ID);
                final RESTIntegerConstantCollectionItemV1 displayedTagWrapper = new RESTIntegerConstantCollectionItemV1(displayedEntity, RESTBaseCollectionItemV1.ADD_STATE);

                integerConstantFilteredResultsPresenter.setSelectedItem(selectedTagWrapper);
                integerConstantFilteredResultsPresenter.getProviderData().setDisplayedItem(displayedTagWrapper);

                updateViewsAfterNewEntityLoaded();
            }
        });
    }

    @Override
    public void bindSearchAndEditExtended(@NotNull final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTIntegerConstantV1> getNewEntityCallback = new GetNewEntityCallback<RESTIntegerConstantV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTIntegerConstantV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTIntegerConstantV1> displayCallback) {
                /*
                    There is nothing additional to load here, so just return the selected entity.
                 */
                displayCallback.displayNewEntity(selectedEntity);
            }
        };

        integerConstantFilteredResultsPresenter.bindExtendedFilteredResults( queryString);
        integerConstantPresenter.bindExtended();
        super.bindSearchAndEdit(Preferences.STRING_CONSTANTS_VIEW_MAIN_SPLIT_WIDTH, integerConstantPresenter.getDisplay(), integerConstantPresenter.getDisplay(),
                integerConstantFilteredResultsPresenter.getDisplay(), integerConstantFilteredResultsPresenter, display, display, getNewEntityCallback);
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
        clearContainerAndAddTopLevelPanel(container, display);
        bindSearchAndEditExtended(queryString);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
            integerConstantPresenter.getDisplay().getDriver().flush();

            checkState(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
            checkState(integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
            checkState(integerConstantFilteredResultsPresenter.getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
            checkState(integerConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");

            final RESTIntegerConstantV1 selectedItem = integerConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem();
            final RESTIntegerConstantV1 displayedItem = integerConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem();

            return !(stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(selectedItem.getName(), displayedItem.getName())
                    && integerEquals(selectedItem.getValue(), displayedItem.getValue()));
        }
        return false;
    }

    public interface Display extends BaseSearchAndEditViewInterface<
            RESTIntegerConstantV1,
            RESTIntegerConstantCollectionV1,
            RESTIntegerConstantCollectionItemV1> {
        PushButton getSave();
    }
}
