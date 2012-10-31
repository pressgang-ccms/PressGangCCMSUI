package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;

/**
 * This interface defines the methods that need top be found on views that use editors to display an entities details.
 * 
 * @author Matthew Casperson
 */
public interface BaseEditorViewInterface<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>> extends BaseTemplateViewInterface {
    @SuppressWarnings("rawtypes")
    SimpleBeanEditorDriver getDriver();
}
