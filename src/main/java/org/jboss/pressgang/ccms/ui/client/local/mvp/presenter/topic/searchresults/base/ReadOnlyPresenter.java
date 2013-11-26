package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base;

import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jetbrains.annotations.NotNull;

/**
 * Provides a function that can be used to determine if the presenter is readonly
 */
public interface ReadOnlyPresenter {
    void isReadOnlyMode(@NotNull final ReadOnlyCallback readOnlyCallback);
}
