package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics;

import com.google.gwt.user.client.ui.CheckBoxList;
import com.google.gwt.user.client.ui.TabBar;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;

public interface LogMessageAndContentSpecListInterface extends LogMessageInterface {
    TabBar getTabBar();

    CheckBoxList getContentSpecList();
}
