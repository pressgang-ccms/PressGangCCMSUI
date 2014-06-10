package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics;

import com.google.gwt.user.client.ui.CheckBoxList;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;

public interface LogMessageAndContentSpecListInterface extends LogMessageInterface {
    TabLayoutPanel getTabLayoutPanel();

    CheckBoxList getContentSpecList();
}
