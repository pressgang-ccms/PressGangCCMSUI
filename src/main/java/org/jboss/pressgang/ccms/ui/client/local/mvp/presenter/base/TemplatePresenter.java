package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;


/**
 * The base interface for all presenters.
 */
public interface TemplatePresenter extends Presenter {

    /**
     * Parse the history token (i.e. what is after the # in the URL).
     * @param historyToken The URL history token
     */
    void parseToken(final String historyToken);

}
