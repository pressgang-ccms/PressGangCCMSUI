package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.CheckBoxList;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.LogMessageAndContentSpecListInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * The dialog box that presents the list of locales for the user to select from.
 *
 * @author Matthew Casperson
 */
public class LogMessageAndContentSpecListView extends DialogBox implements LogMessageAndContentSpecListInterface {
    private static final int TAB_PANEL_BASE_HEIGHT = 40;
    private static final String TAB_PANEL_ORIGINAL_HEIGHT = "175px";

    /**
     * Used to group the radio buttons
     */
    private static final String CHANGE_TYPE_GROUP = "ChangeType";

    private final TabLayoutPanel tabPanel = new TabLayoutPanel(Constants.TAB_PANEL_HEIGHT, Constants.TAB_PANEL_HEIGHT_FORMAT);

    private final FlexTable messageLayout = new FlexTable();
    private final FlexTable contentSpecLayout = new FlexTable();
    private final RadioButton minorChange = new RadioButton(CHANGE_TYPE_GROUP, "");
    private final RadioButton majorChange = new RadioButton(CHANGE_TYPE_GROUP, "");
    private final TextArea message = new TextArea();
    private final TextBox username = new TextBox();
    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
    private final CheckBoxList contentSpecList = new CheckBoxList();

    @NotNull
    @Override
    public TextArea getMessage() {
        return message;
    }

    @NotNull
    @Override
    public RadioButton getMajorChange() {
        return majorChange;
    }

    @NotNull
    @Override
    public RadioButton getMinorChange() {
        return minorChange;
    }

    @NotNull
    @Override
    public DialogBox getDialogBox() {
        return this;
    }

    @Override
    public PushButton getCancel() {
        return cancel;
    }

    @Override
    public PushButton getOk() {
        return ok;
    }

    @NotNull
    @Override
    public TextBox getUsername() {
        return username;
    }

    @Override
    public TabLayoutPanel getTabLayoutPanel() {
        return tabPanel;
    }

    @Override
    public CheckBoxList getContentSpecList() {
        return contentSpecList;
    }

    public LogMessageAndContentSpecListView() {
        setGlassEnabled(true);
        setText(PressGangCCMSUI.INSTANCE.SaveLog());

        cancel.getElement().setId(Constants.ElementIDs.CANCEL_SAVE_DIALOG.getId());
        ok.getElement().setId(Constants.ElementIDs.OK_SAVE_DIALOG.getId());

        final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
        buttonPanel.add(cancel);
        buttonPanel.add(ok);

        setupMessageTab();
        setupContentSpecTab();

        tabPanel.add(createWrapperPanel(messageLayout), PressGangCCMSUI.INSTANCE.Message());
        tabPanel.add(createWrapperPanel(contentSpecLayout), PressGangCCMSUI.INSTANCE.TopicContentSpecs());

        final VerticalPanel rootPanel = new VerticalPanel();
        tabPanel.setHeight(TAB_PANEL_ORIGINAL_HEIGHT);
        tabPanel.setWidth("500px");
        rootPanel.add(tabPanel);
        rootPanel.add(buttonPanel);
        add(rootPanel);

        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(final SelectionEvent<Integer> event) {
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                    @Override
                    public void execute() {
                        final SimpleLayoutPanel tabWidget = (SimpleLayoutPanel) tabPanel.getWidget(event.getSelectedItem());
                        tabPanel.setHeight((TAB_PANEL_BASE_HEIGHT + tabWidget.getWidget().getOffsetHeight()) + "px");
                    }
                });
            }
        });

        reset();
    }

    protected SimpleLayoutPanel createWrapperPanel(final Widget widget) {
        final SimpleLayoutPanel contentSpecLayout = new SimpleLayoutPanel();
        contentSpecLayout.setWidget(widget);
        return contentSpecLayout;
    }

    protected void setupContentSpecTab() {
        final Label label = new Label(PressGangCCMSUI.INSTANCE.SelectContentSpecsToUpdate());
        final Label note = new Label(PressGangCCMSUI.INSTANCE.NoteOnlyCSNodesWithRevisionDisplayed());

        contentSpecLayout.setWidget(0, 0, label);
        contentSpecLayout.setWidget(1, 0, contentSpecList);
        contentSpecLayout.setWidget(2, 0, note);

        contentSpecList.getElement().setId(Constants.ElementIDs.CONTENT_SPEC_LIST_SAVE_DIALOG.getId());
    }

    protected void setupMessageTab() {
        message.getElement().setId(Constants.ElementIDs.MESSAGE_SAVE_DIALOG.getId());
        username.getElement().setId(Constants.ElementIDs.USERNAME_SAVE_DIALOG.getId());
        minorChange.getElement().setId(Constants.ElementIDs.MINOR_CHANGE_SAVE_DIALOG.getId());
        majorChange.getElement().setId(Constants.ElementIDs.MAJOR_CHANGE_SAVE_DIALOG.getId());

        int row = 0;
        messageLayout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.Message()));
        messageLayout.setWidget(row, 1, message);

        ++row;
        messageLayout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.Username()));
        messageLayout.setWidget(row, 1, username);

        ++row;
        messageLayout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.MinorChange()));
        messageLayout.setWidget(row, 1, minorChange);

        ++row;
        messageLayout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.MajorChange()));
        messageLayout.setWidget(row, 1, majorChange);

        messageLayout.setWidth("100%");
    }

    @Override
    public void reset() {
        message.setText("");
        minorChange.setValue(true);
        contentSpecList.clear();
        tabPanel.selectTab(0, false);
        tabPanel.setHeight(TAB_PANEL_ORIGINAL_HEIGHT);
    }

    @Override
    public void show() {
        super.show();
        message.setFocus(true);
    }
}