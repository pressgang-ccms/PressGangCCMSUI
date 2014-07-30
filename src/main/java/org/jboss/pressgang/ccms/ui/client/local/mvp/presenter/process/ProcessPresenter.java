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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.process;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.elements.enums.RESTProcessTypeV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter that adds logic to the category search and edit view.
 *
 * @author Matthew Casperson
 */
@Dependent
public class ProcessPresenter
        extends BaseTemplatePresenter
        implements BaseTemplatePresenterInterface {
    private final DateTimeFormat dateformat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "ProcessView";
    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ProcessPresenter.class.getName());

    @Inject
    private Display display;
    @Inject
    private ProcessFilteredResultsPresenter resultsPresenter;

    @Override
    public void parseToken(@NotNull String historyToken) {

    }

    @Override
    public void bindExtended() {
        resultsPresenter.bindExtended();

        // Display the search results
        switchTab(0);

        // Bind the tab change behaviour
        display.getTabLayoutPanel().addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                switchTab(event.getSelectedItem());
            }
        });

        // Bind the buttons
        bindButtons();
    }

    @Override
    protected void go() {
        bindExtended();
    }

    @Override
    public void close() {
        resultsPresenter.close();
    }

    @Override
    public Display getDisplay() {
        return display;
    }

    protected String getSelectedTabQueryString(@NotNull final Integer selectedTab) {
        String query = "";
        switch (selectedTab) {
            case 1:
                query = CommonFilterConstants.PROCESS_TYPE_FILTER_VAR + "=" + RESTProcessTypeV1.TRANSLATION_SYNC.ordinal() + ";";
                break;
            case 2:
                query = CommonFilterConstants.PROCESS_TYPE_FILTER_VAR + "=" + RESTProcessTypeV1.TRANSLATION_PUSH.ordinal() + ";";
                break;
        }

        if (display.getSubmittedAfter().getValue() != null) {
            query += CommonFilterConstants.STARTDATE_FILTER_VAR + "=" + GWTUtilities.encodeQueryParameter(dateformat.format(display
                    .getSubmittedAfter().getValue())) + ";";
        }

        if (display.getSubmittedBefore().getValue() != null) {
            query += CommonFilterConstants.ENDDATE_FILTER_VAR + "=" + GWTUtilities.encodeQueryParameter(dateformat.format(display
                    .getSubmittedBefore().getValue())) + ";";
        }

        return query.length() == 0 ? null : (Constants.QUERY_PATH_SEGMENT_PREFIX + query);
    }

    /**
     * Called when displaying changes to a entity or when changing views
     */
    protected void switchTab(@NotNull final Integer selectedTab) {
        try {
            LOGGER.log(Level.INFO, "ENTER ProcessPresenter.switchTab()");

            // Remove the results presenter from the old tab if it was attached
            if (resultsPresenter.getDisplay().getSearchResultsPanel().isAttached()) {
                resultsPresenter.getDisplay().getSearchResultsPanel().removeFromParent();
                resultsPresenter.getDisplay().setViewShown(false);
            }

            // Find the selected panel to add the search results to
            final SimpleLayoutPanel tabPanel = (SimpleLayoutPanel) display.getTabLayoutPanel().getWidget(selectedTab);

            // Initialise the results presenter with the correct query
            resetProvider(selectedTab);
            tabPanel.setWidget(resultsPresenter.getDisplay().getSearchResultsPanel());
            resultsPresenter.getDisplay().setViewShown(true);

        } finally {
            LOGGER.log(Level.INFO, "EXIT ProcessPresenter.switchTab()");
        }
    }

    protected void bindButtons() {
        display.getRefreshButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int selectedTab = display.getTabLayoutPanel().getSelectedIndex();
                resetProvider(selectedTab);
            }
        });

        display.getResetButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                display.getSubmittedAfter().setValue(null);
                display.getSubmittedBefore().setValue(null);

                int selectedTab = display.getTabLayoutPanel().getSelectedIndex();
                resetProvider(selectedTab);
            }
        });
    }

    protected void resetProvider(int selectedTab) {
        if (resultsPresenter.getDisplay().getProvider() != null) {
            resultsPresenter.getDisplay().getProvider().resetProvider(true);
            resultsPresenter.getDisplay().getResults().setPageStart(0);
        }
        resultsPresenter.getDisplay().setProvider(
                resultsPresenter.generateListProvider(getSelectedTabQueryString(selectedTab), display));
    }

    public interface Display extends BaseTemplateViewInterface {
        TabLayoutPanel getTabLayoutPanel();

        PushButton getRefreshButton();

        PushButton getResetButton();

        DateBox getSubmittedAfter();

        DateBox getSubmittedBefore();

        SimpleLayoutPanel getAllProcessesPanel();

        SimpleLayoutPanel getZanataSyncProcessesPanel();

        SimpleLayoutPanel getZanataPushProcessesPanel();
    }
}
