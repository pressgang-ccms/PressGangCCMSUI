package org.jboss.pressgang.ccms.ui.client.local.mvp.component.propertyview;

import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

public interface BasePropertyViewComponentInterface<T extends BaseTemplateViewInterface> extends Component<T> {
    /**
     * Get and display the entity
     * 
     * @param entityId The entity primary key
     */
    void getEntity(final Integer entityId);
}
