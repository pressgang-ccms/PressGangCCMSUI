package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.translatedtopic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseTopicSearchTagsFieldsAndFiltersPresenter;

/**
 * The presenter used to display the search screen, including the child tags, fields, locales and filters
 * presenters.
 */
@Dependent
public class TranslatedTopicSearchTagsFieldsAndFiltersPresenter extends BaseTopicSearchTagsFieldsAndFiltersPresenter {

    /**
     * The presenter used to display the list of filters
     */
    @Inject
    private TranslatedTopicSearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;

    @Inject
    private TranslatedTopicSearchFieldPresenter fieldsPresenter;

    @Inject
    private Display display;

    @Override
    protected BaseSearchFieldPresenter getFieldsPresenter() {
        return fieldsPresenter;
    }

    @Override
    protected BaseSearchFilterResultsAndFilterPresenter getSearchFilterResultsAndFilterPresenter() {
        return searchFilterResultsAndFilterPresenter;
    }

    @Override
    protected Display getDisplay() {
        return display;
    }

    public interface Display extends BaseTopicSearchTagsFieldsAndFiltersPresenter.Display {}
}
