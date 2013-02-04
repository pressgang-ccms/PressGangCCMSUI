package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;

import com.google.gwt.user.client.ui.HasWidgets;

public interface PresenterInterface {
    
    /**
     * Called when the presenter is to be displayed.
     * 
     * @param container The container that will host the presenter
     */
    void go(final HasWidgets container);


}
