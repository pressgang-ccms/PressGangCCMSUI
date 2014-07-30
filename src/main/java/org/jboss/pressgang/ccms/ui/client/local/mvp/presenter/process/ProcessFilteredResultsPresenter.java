/*
  Copyright 2011-2014 Red Hat, Inc

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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.process;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClosablePopup;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProcessInformationCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProcessInformationCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTProcessInformationV1;
import org.jboss.pressgang.ccms.rest.v1.elements.enums.RESTProcessStatusV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.process.ProcessActionsCell;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter used to add logic to the category filtered list view.
 */
@Dependent
public class ProcessFilteredResultsPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    /**
     * This history token.
     */
    public static final String HISTORY_TOKEN = "ProcessFilteredResultsView";
    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ProcessFilteredResultsPresenter.class.getName());

    /**
     * The display used to show the list of categories.
     */
    @Inject
    private Display display;
    @Inject
    private LogsDialogBox logsDialogBox;
    /**
     * The query string used to return the list of categories.
     */
    private String queryString;

    /**
     * Holds the data required to populate and refresh the tags list
     */
    private ProviderUpdateData<RESTProcessInformationCollectionItemV1> providerData = new
            ProviderUpdateData<RESTProcessInformationCollectionItemV1>();


    @NotNull
    public ProviderUpdateData<RESTProcessInformationCollectionItemV1> getProviderData() {
        return providerData;
    }

    @NotNull
    public Display getDisplay() {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public ProcessFilteredResultsPresenter() {

    }

    @Override
    protected void go() {
        bindExtendedFilteredResults(queryString);
    }

    @Override
    public void close() {

    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    public void bindExtended() {
        bindResultsListRowClicks();
    }

    public void bindExtendedFilteredResults(@NotNull final String queryString) {
        try {
            LOGGER.log(Level.INFO, "ENTER ProcessFilteredResultsPresenter.bind()");
            bindExtended();
            display.setProvider(generateListProvider(queryString, display));
        } finally {
            LOGGER.log(Level.INFO, "EXIT ProcessFilteredResultsPresenter.bind()");
        }
    }

    @NotNull
    protected EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1> generateListProvider(@NotNull final String queryString,
            @NotNull final BaseTemplateViewInterface waitDisplay) {

        final ProviderUpdateData<RESTProcessInformationCollectionItemV1> providerData = getProviderData();
        providerData.reset();

        return new EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTProcessInformationCollectionItemV1> list) {

                providerData.setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = providerData.getStartRow() + length;

                final RESTCallBack<RESTProcessInformationCollectionV1> callback = new RESTCallBack<RESTProcessInformationCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTProcessInformationCollectionV1 retValue) {
                        checkState(retValue.getItems() != null, "There returned collection should have a valid items collection.");
                        checkState(retValue.getSize() != null, "There returned collection should have a valid size collection.");

                        providerData.setItems(retValue.getItems());
                        providerData.setSize(retValue.getSize());
                        relinkSelectedItem();
                        displayAsynchronousList(providerData.getItems(), providerData.getSize(), providerData.getStartRow());
                    }
                };

                getFailOverRESTCall().performRESTCall(
                        FailOverRESTCallDatabase.getProcessesFromQuery(queryString, providerData.getStartRow(), end), callback,
                        waitDisplay);
            }
        };
    }

    public void reset() {
        providerData = new ProviderUpdateData<RESTProcessInformationCollectionItemV1>();
        if (getDisplay().getProvider() != null) {
            getDisplay().getProvider().resetProvider(true);
            getDisplay().setProvider(null);
        }
    }

    public void refreshList() {
        if (getDisplay().getProvider() != null && getProviderData().isValid()) {
            getDisplay().getProvider().displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(),
                    getProviderData().getStartRow());
        }
    }

    /**
     * When a new entity is created, the filtered results are reloaded. This process breaks the link between the selected item
     * and the collection being displayed by the filtered results. This methods will go through and set the selected item to the
     * item in the filtered results list (if it exists).
     */
    protected void relinkSelectedItem() {
        checkState(providerData != null, "The providerData variable should have been set.");

        if (providerData.getSelectedItem() != null && providerData.getItems() != null) {
            for (@NotNull final RESTProcessInformationCollectionItemV1 filteredResultEntity : providerData.getItems()) {

                checkState(providerData.getSelectedItem() != null, "There has to be a selected item");
                checkState(providerData.getSelectedItem().getItem() != null, "The selected item needs to have reference a valid entity");
                checkState(providerData.getSelectedItem().getItem().getId() != null,
                        "The entity collection item needs to have a valid entity with a valid id");

                if (filteredResultEntity.getItem().getId().equals(providerData.getSelectedItem().getItem().getId())) {
                    setSelectedItem(filteredResultEntity);

                    break;
                }
            }
        }
    }

    /**
     * Sets the selected item in the Filtered Results.
     *
     * @param selectedItem
     */
    public void setSelectedItem(@Nullable final RESTProcessInformationCollectionItemV1 selectedItem) {
        getProviderData().setSelectedItem(selectedItem);
        if (selectedItem == null) {
            final SingleSelectionModel<RESTProcessInformationCollectionItemV1> selectionModel =
                    (SingleSelectionModel<RESTProcessInformationCollectionItemV1>) getDisplay().getResults().getSelectionModel();
            selectionModel.clear();
        } else {
            final SingleSelectionModel<RESTProcessInformationCollectionItemV1> selectionModel =
                    (SingleSelectionModel<RESTProcessInformationCollectionItemV1>) getDisplay().getResults().getSelectionModel();
            selectionModel.clear();
            selectionModel.setSelected(selectedItem, true);
        }
    }

    /**
     * Binds logic to the search results list row click event
     */
    protected void bindResultsListRowClicks() {
        getDisplay().getResults().addCellPreviewHandler(new CellPreviewEvent.Handler<RESTProcessInformationCollectionItemV1>() {
            @Override
            public void onCellPreview(@NotNull final CellPreviewEvent<RESTProcessInformationCollectionItemV1> event) {

                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    if (!isOKToProceed()) {
                        return;
                    }

                    final RESTProcessInformationCollectionItemV1 selectedItem = event.getValue();
                    setSelectedItem(selectedItem);
                }
            }
        });

        getDisplay().getLogsColumn().setFieldUpdater(new FieldUpdater<RESTProcessInformationCollectionItemV1, String>() {
            @Override
            public void update(int index, RESTProcessInformationCollectionItemV1 object, String value) {
                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getProcessLogs(object.getItem().getId()),
                        new RESTCallBack<RESTProcessInformationV1>() {
                            @Override
                            public void success(@NotNull final RESTProcessInformationV1 retValue) {
                                logsDialogBox.setText(retValue.getLogs());
                                logsDialogBox.getDialogBox().center();
                            }
                        }, display);

            }
        });

        getDisplay().getCancelColumn().setFieldUpdater(new FieldUpdater<RESTProcessInformationCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTProcessInformationCollectionItemV1 object, final String value) {
                final RESTProcessStatusV1 status = object.getItem().getStatus();
                // Make sure the status hasn't completed
                if (status != RESTProcessStatusV1.PENDING && status != RESTProcessStatusV1.QUEUED && status != RESTProcessStatusV1
                        .EXECUTING) {
                    return;
                } else {
                    if (value.equals(ProcessActionsCell.STOP_NAME) && status != RESTProcessStatusV1.EXECUTING) {
                        if (Window.confirm(PressGangCCMSUI.INSTANCE.ConfirmStopProcess())) {
                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.stopProcess(object.getItem().getId()),
                                    new RESTCallBack<RESTProcessInformationV1>() {
                                        @Override
                                        public void success(@NotNull final RESTProcessInformationV1 retValue) {
                                            object.setItem(retValue);
                                            getDisplay().getResults().redrawRow(index);

                                            AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.StoppedProcessMessage());
                                        }
                                    }, display);
                        }
                    } else if (value.equals(ProcessActionsCell.REFRESH_NAME)) {
                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getProcess(object.getItem().getId()),
                                new RESTCallBack<RESTProcessInformationV1>() {
                                    @Override
                                    public void success(@NotNull final RESTProcessInformationV1 retValue) {
                                        object.setItem(retValue);
                                        getDisplay().getResults().redrawRow(index);
                                    }
                                }, display);
                    }
                }
            }
        });
    }

    public interface LogsDialogBox {
        PushButton getClose();

        String getText();

        void setText(String text);

        ClosablePopup getDialogBox();
    }

    public interface Display extends BaseTemplateViewInterface {
        /**
         * @return The cell table that displays the results
         */
        CellTable<RESTProcessInformationCollectionItemV1> getResults();

        /**
         * @return The pager used to move over the results
         */
        SimplePager getPager();

        /**
         * @return The provider used to populate the cell table
         */
        EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1> getProvider();

        /**
         * @param provider The provider used to populate the cell table
         */
        void setProvider(final EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1> provider);

        @NotNull
        VerticalPanel getSearchResultsPanel();

        Column<RESTProcessInformationCollectionItemV1, String> getLogsColumn();

        Column<RESTProcessInformationCollectionItemV1, String> getCancelColumn();
    }
}