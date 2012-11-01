package org.jboss.pressgang.ccms.ui.client.local.mvp.view.project;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.PushButton;

public interface ProjectViewInterface extends BaseTemplateViewInterface {
    PushButton getChildren();

    PushButton getDetails();

    PushButton getSave();

    void initialize(final RESTProjectV1 entity, final boolean readOnly);
}
