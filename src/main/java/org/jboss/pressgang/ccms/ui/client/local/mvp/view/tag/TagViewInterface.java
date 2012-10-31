package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.PushButton;

public interface TagViewInterface extends BaseTemplateViewInterface {
    void initialize(final RESTTagV1 entity, final boolean readonly);

    PushButton getTagProjects();

    PushButton getTagDetails();

    PushButton getSave();

    PushButton getTagCategories();
}
