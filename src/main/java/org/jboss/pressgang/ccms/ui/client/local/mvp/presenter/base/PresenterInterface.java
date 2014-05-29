package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jetbrains.annotations.NotNull;

public interface PresenterInterface {
    /**
     * Called when the presenter is to be displayed.
     *
     * @param container The container that will host the presenter
     */
    void initAndGo(@NotNull final HasWidgets container);

    /**
     * Called when the another presenter is to be displayed.
     */
    void close();

    BaseTemplateViewInterface getDisplay();
}
