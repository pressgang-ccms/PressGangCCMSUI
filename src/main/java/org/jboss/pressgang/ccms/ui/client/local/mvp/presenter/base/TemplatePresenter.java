package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;


/**
 * The base interface for all presenters.
 */
public interface TemplatePresenter extends Presenter {
    void parseToken(final String historyToken);

}
