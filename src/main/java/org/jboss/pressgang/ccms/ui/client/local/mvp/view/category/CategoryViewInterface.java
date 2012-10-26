package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.PushButton;

public interface CategoryViewInterface extends BaseTemplateViewInterface {
    PushButton getChildren();

    PushButton getDetails();

    PushButton getSave();
}
