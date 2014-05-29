package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic;

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
public class TopicSearchTagsFieldsAndFiltersPresenter extends BaseTopicSearchTagsFieldsAndFiltersPresenter {

    /**
     * The presenter used to display the list of filters
     */
    @Inject
    private TopicSearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;

    @Inject
    private TopicSearchFieldPresenter fieldsPresenter;

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
    public Display getDisplay() {
        return display;
    }

    public interface Display extends BaseTopicSearchTagsFieldsAndFiltersPresenter.Display {}
}
