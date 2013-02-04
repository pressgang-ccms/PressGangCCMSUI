package org.jboss.pressgang.ccms.ui.client.local.mvp.view.project;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

public interface ProjectViewInterface extends BaseTemplateViewInterface {
    void initialize(final RESTProjectV1 entity, final boolean readOnly);
}
