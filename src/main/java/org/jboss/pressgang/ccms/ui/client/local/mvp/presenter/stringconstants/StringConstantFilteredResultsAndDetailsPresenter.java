/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.StringConstantFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.stringconstant.RESTStringConstantV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter used to display a list of string constants and their details.
 */
@Dependent
public class StringConstantFilteredResultsAndDetailsPresenter extends
        BaseSearchAndEditPresenter<
                RESTStringConstantV1,
                RESTStringConstantCollectionV1,
                RESTStringConstantCollectionItemV1,
                RESTStringConstantV1DetailsEditor>
        implements BaseTemplatePresenterInterface {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "StringConstantFilteredResultsAndDetailsView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(StringConstantFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    /**
     * The presenter used to display the filtered results.
     */
    @Inject
    private StringConstantFilteredResultsPresenter stringConstantFilteredResultsPresenter;
    /**
     * The presenter used to display the string constant's details.
     */
    @Inject
    private StringConstantPresenter stringConstantPresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    protected void loadAdditionalDisplayedItemData() {
        /*
            Do Nothing.
        */
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        checkState(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                stringConstantPresenter.getDisplay().display(
                        stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), readOnly);
            }
        });
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to save any changes to the project
         */
        @NotNull final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                checkState(stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
                checkState(stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");

                /* Was the entity we just saved a new entity? */
                final boolean wasNewEntity = stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                stringConstantPresenter.getDisplay().getDriver().flush();

                final RESTCallBack<RESTStringConstantV1> callback = new RESTCallBack<RESTStringConstantV1>() {
                    @Override
                    public void success(@NotNull final RESTStringConstantV1 retValue) {
                        checkState(stringConstantFilteredResultsPresenter.getProviderData().isValid(), "The filtered results data provider should be valid.");

                        retValue.cloneInto(stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);
                        retValue.cloneInto(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);

                                /* This project is no longer a new project */
                        stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);
                        stringConstantFilteredResultsPresenter.getDisplay().getProvider().updateRowData(
                                stringConstantFilteredResultsPresenter.getProviderData().getStartRow(),
                                stringConstantFilteredResultsPresenter.getProviderData().getItems());

                        updateDisplayWithNewEntityData(wasNewEntity);

                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.SaveSuccess());
                    }
                };



                if (stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

                        final RESTStringConstantV1 stringConstant = new RESTStringConstantV1();
                        stringConstant.setId(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
                        stringConstant.explicitSetName(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName());
                        stringConstant.explicitSetValue(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getValue());

                        if (wasNewEntity) {
                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createStringConstant(stringConstant), callback, display);
                        } else {
                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateStringConstant(stringConstant), callback, display);
                        }
                    } else {
                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                }
            }
        };

        display.getSave().addClickHandler(saveClickHandler);
    }

    private void doSearch(final boolean newWindow) {
        if (isOKToProceed()) {
            getEventBus().fireEvent(new StringConstantFilteredResultsAndDetailsViewEvent(stringConstantFilteredResultsPresenter.getQuery(),
                    newWindow));
        }
    }

    @Override
    protected void bindFilteredResultsButtons() {
        stringConstantFilteredResultsPresenter.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
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

        stringConstantFilteredResultsPresenter.getDisplay().getValueFilter().addKeyPressHandler(searchKeyPressHandler);
        stringConstantFilteredResultsPresenter.getDisplay().getIdFilter().addKeyPressHandler(searchKeyPressHandler);
        stringConstantFilteredResultsPresenter.getDisplay().getNameFilter().addKeyPressHandler(searchKeyPressHandler);

        stringConstantFilteredResultsPresenter.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                /* The 'selected' entity will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTStringConstantV1 selectedEntity = new RESTStringConstantV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTStringConstantCollectionItemV1 selectedTagWrapper = new RESTStringConstantCollectionItemV1(selectedEntity);

                /* The displayed entity will also be blank. This is the object that our data will be saved into */
                final RESTStringConstantV1 displayedEntity = new RESTStringConstantV1();
                displayedEntity.setId(Constants.NULL_ID);
                final RESTStringConstantCollectionItemV1 displayedTagWrapper = new RESTStringConstantCollectionItemV1(displayedEntity, RESTBaseEntityCollectionItemV1.ADD_STATE);

                stringConstantFilteredResultsPresenter.setSelectedItem(selectedTagWrapper);
                stringConstantFilteredResultsPresenter.getProviderData().setDisplayedItem(displayedTagWrapper);

                updateViewsAfterNewEntityLoaded();
            }
        });
    }

    @Override
    public void bindSearchAndEditExtended(@NotNull final String queryString) {
        try {
            LOGGER.log(Level.INFO, "ENTER StringConstantFilteredResultsAndDetailsPresenter.bindSearchAndEditExtended()");

            /* A call back used to get a fresh copy of the entity that was selected */
            final GetNewEntityCallback<RESTStringConstantV1> getNewEntityCallback = new GetNewEntityCallback<RESTStringConstantV1>() {

                @Override
                public void getNewEntity(@NotNull final RESTStringConstantV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTStringConstantV1> displayCallback) {
                    /*
                        There is nothing additional to load here, so just return the selected entity.
                     */
                    displayCallback.displayNewEntity(selectedEntity);
                }
            };

            stringConstantFilteredResultsPresenter.bindExtendedFilteredResults(queryString);
            stringConstantPresenter.bindExtended();
            super.bindSearchAndEdit(Preferences.STRING_CONSTANTS_VIEW_MAIN_SPLIT_WIDTH, stringConstantPresenter.getDisplay(), stringConstantPresenter.getDisplay(),
                    stringConstantFilteredResultsPresenter.getDisplay(), stringConstantFilteredResultsPresenter, display, display, getNewEntityCallback);
        } finally {
            LOGGER.log(Level.INFO, "EXIT StringConstantFilteredResultsAndDetailsPresenter.bindSearchAndEditExtended()");
        }

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                display.getSave().setEnabled(!readOnly);
                stringConstantFilteredResultsPresenter.getDisplay().getCreate().setEnabled(!readOnly);
            }
        });
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    @Override
    public void go() {
        try {
            LOGGER.log(Level.INFO, "ENTER StringConstantFilteredResultsAndDetailsPresenter.go()");
            bindSearchAndEditExtended(queryString);
        } finally {
            LOGGER.log(Level.INFO, "EXIT StringConstantFilteredResultsAndDetailsPresenter.go()");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying entity */
        if (stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {

            checkState(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
            checkState(stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
            checkState(stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");

            stringConstantPresenter.getDisplay().getDriver().flush();

            final RESTStringConstantV1 selectedItem = stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem();
            final RESTStringConstantV1 displayedItem = stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem();

            return !(stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(selectedItem.getName(), displayedItem.getName())
                    && stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(selectedItem.getValue(), displayedItem.getValue()));
        }
        return false;
    }

    /**
     * The definition of the view that corresponds to this presenter.
     */
    public interface Display extends BaseSearchAndEditViewInterface<
            RESTStringConstantV1,
            RESTStringConstantCollectionV1,
            RESTStringConstantCollectionItemV1> {
        /**
         * @return The Save button
         */
        PushButton getSave();
    }
}
