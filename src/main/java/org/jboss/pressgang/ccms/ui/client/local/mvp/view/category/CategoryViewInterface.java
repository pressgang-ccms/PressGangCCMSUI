package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.PushButton;

public interface CategoryViewInterface extends BaseTemplateViewInterface {

    
    void initialize(final RESTCategoryV1 category, final boolean readOnly);
}
