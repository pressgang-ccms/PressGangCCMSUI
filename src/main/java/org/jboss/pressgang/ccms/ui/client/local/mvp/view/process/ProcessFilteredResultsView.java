/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.process;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProcessInformationCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.enums.RESTProcessStatusV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.process.ProcessFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProcessFilteredResultsView extends BaseTemplateView implements ProcessFilteredResultsPresenter.Display {
    /**
     * The provider used to populate the cell table
     */
    private EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1> provider;
    /**
     * The pager used to move over the results
     */
    private final SimplePager pager = UIUtilities.createSimplePager();
    /**
     * The cell table that displays the results
     */
    private final CellTable<RESTProcessInformationCollectionItemV1> results = UIUtilities
            .<RESTProcessInformationCollectionItemV1>createCellTable();
    /**
     * The panel that holds the filter fields and the search results
     */
    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    private final ScrollPanel resultsPanel = new ScrollPanel();

    @NotNull
    private TextColumn<RESTProcessInformationCollectionItemV1> idColumn = new TextColumn<RESTProcessInformationCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTProcessInformationCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return "";
        }
    };

    @NotNull
    private TextColumn<RESTProcessInformationCollectionItemV1> nameColumn = new TextColumn<RESTProcessInformationCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTProcessInformationCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }

            return "";
        }
    };

    @NotNull
    private TextColumn<RESTProcessInformationCollectionItemV1> statusColumn = new TextColumn<RESTProcessInformationCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTProcessInformationCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getStatus() != null) {
                return object.getItem().getStatus().name();
            }

            return "";
        }
    };

    @NotNull
    private TextColumn<RESTProcessInformationCollectionItemV1> submittedTimeColumn = new
            TextColumn<RESTProcessInformationCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTProcessInformationCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getStartTime() != null) {
                return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(object.getItem().getStartTime());
            }

            return "";
        }
    };

    @NotNull
    private TextColumn<RESTProcessInformationCollectionItemV1> endTimeColumn = new TextColumn<RESTProcessInformationCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTProcessInformationCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getEndTime() != null) {
                return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(object.getItem().getEndTime());
            }

            return "";
        }
    };

    @NotNull
    private TextColumn<RESTProcessInformationCollectionItemV1> startedByColumn = new TextColumn<RESTProcessInformationCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTProcessInformationCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getStartedBy() != null) {
                return object.getItem().getStartedBy();
            }

            return "Unknown";
        }
    };

    private final DisableableButtonCell logsButtonCell = new DisableableButtonCell();
    private final Column<RESTProcessInformationCollectionItemV1, String> logsButtonColumn = new
            Column<RESTProcessInformationCollectionItemV1, String>(
            logsButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTProcessInformationCollectionItemV1 object) {
            logsButtonCell.setEnabled(true);
            if (object != null && object.getItem() != null && object.getItem().getStatus() != null) {
                final RESTProcessStatusV1 status = object.getItem().getStatus();
                if (status == RESTProcessStatusV1.PENDING || status == RESTProcessStatusV1.QUEUED || status == RESTProcessStatusV1
                        .EXECUTING) {
                    logsButtonCell.setEnabled(false);
                }
            }
            return PressGangCCMSUI.INSTANCE.ProcessViewLogs();
        }
    };

    private final Column<RESTProcessInformationCollectionItemV1, String> cancelColumn = new
            Column<RESTProcessInformationCollectionItemV1, String>(
            new ProcessActionsCell()) {
        @Override
        public String getValue(RESTProcessInformationCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getStatus() != null) {
                return object.getItem().getStatus().name();
            }

            return "";
        }
    };

    private final SingleSelectionModel<RESTProcessInformationCollectionItemV1> selectionModel = new
            SingleSelectionModel<RESTProcessInformationCollectionItemV1>();

    public ProcessFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Processes());

        idColumn.setCellStyleNames(CSSConstants.ProcessView.RESULTS_TABLE_ID_COLUMN);
        statusColumn.setCellStyleNames(CSSConstants.ProcessView.RESULTS_TABLE_STATUS_COLUMN);
        submittedTimeColumn.setCellStyleNames(CSSConstants.ProcessView.RESULTS_TABLE_DATE_COLUMN);
        endTimeColumn.setCellStyleNames(CSSConstants.ProcessView.RESULTS_TABLE_DATE_COLUMN);
        startedByColumn.setCellStyleNames(CSSConstants.ProcessView.RESULTS_TABLE_STARTED_BY_COLUMN);
        logsButtonColumn.setCellStyleNames(CSSConstants.ProcessView.RESULTS_TABLE_LOGS_COLUMN);
        cancelColumn.setCellStyleNames(CSSConstants.ProcessView.RESULTS_TABLE_CANCEL_COLUMN);

        results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProcessID());
        results.addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProcessName());
        results.addColumn(statusColumn, PressGangCCMSUI.INSTANCE.ProcessStatus());
        results.addColumn(submittedTimeColumn, PressGangCCMSUI.INSTANCE.ProcessSubmittedTime());
        results.addColumn(endTimeColumn, PressGangCCMSUI.INSTANCE.ProcessEndedTime());
        results.addColumn(startedByColumn, PressGangCCMSUI.INSTANCE.ProcessStartedBy());
        results.addColumn(logsButtonColumn, PressGangCCMSUI.INSTANCE.ProcessLogs());
        results.addColumn(cancelColumn, PressGangCCMSUI.INSTANCE.ProcessActions());

        // Configure the selector to display the selected result
        results.setSelectionModel(selectionModel);
        results.setRowStyles(new RowStyles<RESTProcessInformationCollectionItemV1>() {
            @Override
            public String getStyleNames(RESTProcessInformationCollectionItemV1 row, int rowIndex) {
                String style = "";
                if (row != null && row.getItem() != null && row.getItem().getStatus() != null) {
                    final RESTProcessStatusV1 status = row.getItem().getStatus();
                    if (status == RESTProcessStatusV1.COMPLETED) {
                        style = CSSConstants.ProcessView.SUCCESSFUL_STATUS;
                    } else if (status == RESTProcessStatusV1.FAILED) {
                        style = CSSConstants.ProcessView.FAILED_STATUS;
                    }
                }

                if (selectionModel.isSelected(row)) {
                    style += " " + CSSConstants.ProcessView.RESULTS_TABLE_SELECTED_ROW;
                }

                return style;
            }
        });

        searchResultsPanel.addStyleName(CSSConstants.ProcessView.RESULTS_PANEL);
        results.addStyleName(CSSConstants.ProcessView.RESULTS_TABLE);

        resultsPanel.setWidget(results);
        resultsPanel.setHeight("100%");
        searchResultsPanel.add(resultsPanel);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);
        getPanel().setWidget(searchResultsPanel);
    }

    @Override
    public CellTable<RESTProcessInformationCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    @Override
    public EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(final EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1> provider) {
        if (this.provider != null) {
            this.provider.removeDataDisplay(getResults());
        }

        this.provider = provider;
        if (provider != null) {
            provider.addDataDisplay(getResults());
        }
    }

    @NotNull
    @Override
    public VerticalPanel getSearchResultsPanel() {
        return searchResultsPanel;
    }

    @Override
    public Column<RESTProcessInformationCollectionItemV1, String> getLogsColumn() {
        return logsButtonColumn;
    }

    @Override
    public Column<RESTProcessInformationCollectionItemV1, String> getCancelColumn() {
        return cancelColumn;
    }
}
