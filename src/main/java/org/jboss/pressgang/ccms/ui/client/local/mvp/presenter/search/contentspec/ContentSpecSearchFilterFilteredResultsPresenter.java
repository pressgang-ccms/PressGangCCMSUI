package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;

@Dependent
public class ContentSpecSearchFilterFilteredResultsPresenter extends BaseSearchFilterFilteredResultsPresenter {
    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "ContentSpecSearchFilterFilteredResultsView";

    @NotNull
    @Override
    public String getQuery() {
        return Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILTER_TYPE_FILTER_VAR + "=" + CommonConstants.FILTER_CONTENT_SPEC;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        bindExtendedFilteredResults(getQuery());
    }
}
