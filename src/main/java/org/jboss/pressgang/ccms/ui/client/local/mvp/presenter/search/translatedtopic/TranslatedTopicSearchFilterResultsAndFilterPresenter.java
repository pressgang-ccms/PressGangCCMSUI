package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.translatedtopic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic.TopicSearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TranslatedTopicSearchFilterResultsAndFilterPresenter extends BaseSearchFilterResultsAndFilterPresenter {
    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "TranslatedSearchFilterResultsAndFilterView";
    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TranslatedTopicSearchFilterResultsAndFilterPresenter.class.getName());

    @Inject
    private TopicSearchFilterFilteredResultsPresenter searchFilterFilteredResultsPresenter;

    @Override
    public BaseSearchFilterFilteredResultsPresenter getSearchFilterFilteredResultsPresenter() {
        return searchFilterFilteredResultsPresenter;
    }

    public void setSearchFilterFilteredResultsPresenter(
            @NotNull TopicSearchFilterFilteredResultsPresenter searchFilterFilteredResultsPresenter) {
        this.searchFilterFilteredResultsPresenter = searchFilterFilteredResultsPresenter;
    }

    @Override
    protected void go() {
        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicSearchFilterResultsAndFilterPresenter.go()");

            bindSearchAndEditExtended(Constants.QUERY_PATH_SEGMENT_PREFIX +
                    CommonFilterConstants.FILTER_TYPE_FILTER_VAR + "=" + CommonConstants.FILTER_TOPIC);

        } finally {
            LOGGER.log(Level.INFO, "EXIT TranslatedTopicSearchFilterResultsAndFilterPresenter.go()");
        }
    }
}
