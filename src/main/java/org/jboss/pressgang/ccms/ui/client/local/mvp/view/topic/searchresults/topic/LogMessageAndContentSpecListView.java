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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

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
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
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

    /**
     * Used to group the radio buttons
     */
    private static final String CHANGE_TYPE_GROUP = "ChangeType";

    private final TabBar tabBar = new TabBar();
    private final FlexTable rootPanel = new FlexTable();

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
    public TabBar getTabBar() {
        return tabBar;
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

        final SimplePanel tabContent = new SimplePanel();
        tabContent.addStyleName(CSSConstants.LogMessageDialog.TAB_CONTENT_PANEL);
        tabContent.setWidget(messageLayout);

        tabBar.addTab(PressGangCCMSUI.INSTANCE.Message());
        tabBar.addTab(PressGangCCMSUI.INSTANCE.TopicContentSpecs());
        tabBar.setHeight(Constants.TAB_PANEL_HEIGHT + "px");
        tabBar.setWidth("100%");

        rootPanel.setWidget(0, 0, tabBar);
        rootPanel.setWidget(1, 0, tabContent);
        rootPanel.setWidget(2, 0, buttonPanel);
        rootPanel.addStyleName(CSSConstants.LogMessageDialog.ROOT_PANEL);
        add(rootPanel);

        tabBar.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(final SelectionEvent<Integer> event) {
                if (event.getSelectedItem() == 1) {
                    tabContent.setWidget(contentSpecLayout);
                } else {
                    tabContent.setWidget(messageLayout);
                }
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
        tabBar.selectTab(0, true);
    }

    @Override
    public void show() {
        super.show();
        message.setFocus(true);
    }
}