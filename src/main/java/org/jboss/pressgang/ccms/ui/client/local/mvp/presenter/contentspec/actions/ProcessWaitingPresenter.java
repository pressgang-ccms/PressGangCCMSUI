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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTProcessInformationV1;
import org.jboss.pressgang.ccms.rest.v1.elements.enums.RESTProcessStatusV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ActionCompletedCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.process.ProcessFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

public class ProcessWaitingPresenter extends BaseActionPresenter<RESTProcessInformationV1> {
    private static final int DEFAULT_REFRESH_TIME = 10;
    private static final int ONE_SEC_MS = 1000;

    @Inject
    private Display display;
    @Inject
    private ProcessFilteredResultsPresenter.LogsDialogBox logsDialogBox;
    private RESTProcessInformationV1 process;
    private Timer timer;
    private int timeUntilRefresh;
    private final List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();

    public Display getDisplay() {
        return display;
    }

    public void display(final RESTProcessInformationV1 process) {
        display(process, DEFAULT_REFRESH_TIME);
    }

    public void display(final RESTProcessInformationV1 process, final int refreshTime) {
        display.reset();
        this.process = process;
        bindButtons();
        initStatusRefresh(refreshTime);

        display.setMessage(PressGangCCMSUI.INSTANCE.ProcessWaitingMessage());
        display.getUUID().setText(process.getId());
        display.getStatus().setText(process.getStatus().name());
        display.getDialogBox().center();
    }

    @Override
    public void close() {
        if (timer != null) {
            timer.cancel();
        }
        display.reset();
        display.getDialogBox().hide();
        for (final HandlerRegistration handlerRegistration : handlers) {
            handlerRegistration.removeHandler();
        }
        process = null;
        timer = null;
        super.close();
    }

    protected void bindButtons() {
        final ClickHandler closeClickHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                /*
                 * Call the success method for  the completed handlers
                 *
                 * Note: If the "Proceed" button is clicked the process isn't likely to be finished, but the action is complete,
                 * so pass the process as is.
                 */
                for (final ActionCompletedCallback<RESTProcessInformationV1> callback : getCallbacks()) {
                    callback.success(process);
                }

                close();
            }
        };

        handlers.add(display.getProceedWithoutWaiting().addClickHandler(closeClickHandler));
        handlers.add(display.getClose().addClickHandler(closeClickHandler));
        handlers.add(display.getViewLogs().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getProcessLogs(process.getId()),
                        new RESTCallBack<RESTProcessInformationV1>() {
                            @Override
                            public void success(@NotNull final RESTProcessInformationV1 retValue) {
                                display.getDialogBox().hide();
                                logsDialogBox.setText(retValue.getLogs());
                                logsDialogBox.getDialogBox().addCloseHandler(new CloseHandler<PopupPanel>() {
                                    @Override
                                    public void onClose(CloseEvent<PopupPanel> event) {
                                        display.getDialogBox().center();
                                    }
                                });
                                logsDialogBox.getDialogBox().center();
                            }
                        });
            }
        }));
    }

    protected void initStatusRefresh(final int refreshTime) {
        // Create the callback
        final RESTCallBack<RESTProcessInformationV1> callback = new RESTCallBack<RESTProcessInformationV1>() {
            @Override
            public void success(@NotNull final RESTProcessInformationV1 retValue) {
                process = retValue;
                final RESTProcessStatusV1 status = retValue.getStatus();
                display.getStatus().setText(status.name());

                if (status == RESTProcessStatusV1.COMPLETED) {
                    display.getStatus().addStyleName(CSSConstants.ProcessView.SUCCESSFUL_STATUS);
                } else if (status == RESTProcessStatusV1.FAILED) {
                    display.getStatus().addStyleName(CSSConstants.ProcessView.FAILED_STATUS);
                }

                if (retValue.getStatus().ordinal() < RESTProcessStatusV1.COMPLETED.ordinal()) {
                    timeUntilRefresh = refreshTime;
                    timer.scheduleRepeating(ONE_SEC_MS);
                } else {
                    display.displayCompletedButtons();
                }
            }
        };

        timeUntilRefresh = refreshTime;
        display.setRefreshTime(timeUntilRefresh);
        timer = new Timer() {
            @Override
            public void run() {
                timeUntilRefresh -= 1;
                display.setRefreshTime(timeUntilRefresh);

                if (timeUntilRefresh <= 0) {
                    timer.cancel();
                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getProcess(process.getId()), callback);
                }
            }
        };

        timer.scheduleRepeating(ONE_SEC_MS);
    }

    public interface Display {
        void setMessage(String message);

        Label getUUID();

        Label getStatus();

        void setRefreshTime(int timeInSecs);

        PushButton getProceedWithoutWaiting();

        PushButton getViewLogs();

        PushButton getClose();

        DialogBox getDialogBox();

        void reset();

        void displayCompletedButtons();
    }
}
