package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec.actions;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions.ProcessWaitingPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

public class ProcessWaitingDialogBox extends DialogBox implements ProcessWaitingPresenter.Display {
    private final FlexTable layout = new FlexTable();
    private final HTML message = new HTML();
    private final Label uuidLabel = new Label(PressGangCCMSUI.INSTANCE.ProcessID());
    private final Label statusLabel = new Label(PressGangCCMSUI.INSTANCE.ProcessStatus());
    private final Label uuid = new Label();
    private final Label status = new Label();
    private final Label refreshLabel = new Label();
    private final HorizontalPanel buttonPanel = new HorizontalPanel();
    private final PushButton proceed = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ProceedWithoutWaiting());
    private final PushButton close = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Close());
    private final PushButton viewLogs = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ProcessViewLogs());
    private final Image loadingImage = new Image(ImageResources.INSTANCE.loading());

    public ProcessWaitingDialogBox() {
        setGlassEnabled(true);

        layout.setWidget(0, 0, message);
        layout.setWidget(1, 0, uuidLabel);
        layout.setWidget(1, 1, uuid);
        layout.setWidget(2, 0, statusLabel);
        layout.setWidget(2, 1, status);
        layout.setWidget(3, 0, refreshLabel);
        layout.setWidget(4, 0, loadingImage);
        layout.setWidget(5, 0, buttonPanel);

        buttonPanel.add(proceed);

        layout.getFlexCellFormatter().setColSpan(0, 0, 2);
        layout.getFlexCellFormatter().setColSpan(3, 0, 2);
        layout.getFlexCellFormatter().setColSpan(4, 0, 2);
        layout.getFlexCellFormatter().setColSpan(5, 0, 2);
        layout.getFlexCellFormatter().setAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
        layout.getFlexCellFormatter().setAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
        layout.getFlexCellFormatter().setAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);

        layout.setStyleName(CSSConstants.ContentSpecActionsView.WAITING_TABLE);
        message.addStyleName(CSSConstants.ContentSpecActionsView.WAITING_MESSAGE);
        buttonPanel.addStyleName(CSSConstants.ContentSpecActionsView.WAITING_BUTTON_PANEL);
        uuidLabel.addStyleName(CSSConstants.ContentSpecActionsView.WAITING_LABEL);
        statusLabel.addStyleName(CSSConstants.ContentSpecActionsView.WAITING_LABEL);

        add(layout);
    }

    public void setMessage(@NotNull final String message) {
        this.message.setHTML(new SafeHtmlBuilder().appendEscapedLines(message).toSafeHtml());
    }

    @Override
    public Label getUUID() {
        return uuid;
    }

    @Override
    public Label getStatus() {
        return status;
    }

    @Override
    public void setRefreshTime(int timeInSecs) {
        refreshLabel.setText("Status update in " + timeInSecs + " sec(s)...");
    }

    @Override
    public PushButton getProceedWithoutWaiting() {
        return proceed;
    }

    @Override
    public PushButton getViewLogs() {
        return viewLogs;
    }

    @Override
    public PushButton getClose() {
        return close;
    }

    @Override
    public DialogBox getDialogBox() {
        return this;
    }

    @Override
    public void displayCompletedButtons() {
        proceed.removeFromParent();
        buttonPanel.add(viewLogs);
        buttonPanel.add(close);
        layout.getRowFormatter().setVisible(3, false);
        layout.getRowFormatter().setVisible(4, false);
    }

    @Override
    public void reset() {
        if (viewLogs.isAttached()) {
            viewLogs.removeFromParent();
        }
        if (close.isAttached()) {
            close.removeFromParent();
        }
        buttonPanel.add(proceed);
        status.setText("");
        uuid.setText("");
        message.setHTML("");
        refreshLabel.setText("");
        layout.getRowFormatter().setVisible(3, true);
        layout.getRowFormatter().setVisible(4, true);
        status.removeStyleName(CSSConstants.ProcessView.FAILED_STATUS);
        status.removeStyleName(CSSConstants.ProcessView.SUCCESSFUL_STATUS);
    }
}