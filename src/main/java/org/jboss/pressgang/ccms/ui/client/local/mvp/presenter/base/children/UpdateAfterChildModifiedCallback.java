package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

/**
 * Called by BaseChildrenPresenter after a possible child has been added or removed.
 *
 * @author Matthew Casperson
 */
public interface UpdateAfterChildModifiedCallback {
    void updateAfterChildModified();
}
