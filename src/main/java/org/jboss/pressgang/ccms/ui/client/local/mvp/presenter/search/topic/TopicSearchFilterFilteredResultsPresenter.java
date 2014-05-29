package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic;

import javax.enterprise.context.Dependent;

import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TopicSearchFilterFilteredResultsPresenter extends BaseSearchFilterFilteredResultsPresenter {
    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "SearchFilterFilteredResultsView";

    @NotNull
    @Override
    public String getQuery() {
        return Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILTER_TYPE_FILTER_VAR + "=" + CommonConstants.FILTER_TOPIC;
    }

    @Override
    protected void go() {
        bindExtendedFilteredResults(getQuery());
    }
}
