package org.jboss.pressgangccms.client.local.mvp.view.tag;

import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.user.client.ui.PushButton;

public interface TagViewInterface extends BaseTemplateViewInterface {
    void initialize(final RESTTagV1 tag, final boolean readOnly);

    PushButton getTagProjects();

    PushButton getTagDetails();

    PushButton getSave();

    PushButton getTagCategories();
}
