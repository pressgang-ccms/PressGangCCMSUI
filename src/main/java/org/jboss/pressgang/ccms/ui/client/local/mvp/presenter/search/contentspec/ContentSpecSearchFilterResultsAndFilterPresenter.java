package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class ContentSpecSearchFilterResultsAndFilterPresenter extends BaseSearchFilterResultsAndFilterPresenter {
    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "ContentSpecSearchFilterResultsAndFilterView";
    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ContentSpecSearchFilterResultsAndFilterPresenter.class.getName());

    @Inject
    private ContentSpecSearchFilterFilteredResultsPresenter searchFilterFilteredResultsPresenter;

    @Override
    public BaseSearchFilterFilteredResultsPresenter getSearchFilterFilteredResultsPresenter() {
        return searchFilterFilteredResultsPresenter;
    }

    public void setSearchFilterFilteredResultsPresenter(
            @NotNull ContentSpecSearchFilterFilteredResultsPresenter searchFilterFilteredResultsPresenter) {
        this.searchFilterFilteredResultsPresenter = searchFilterFilteredResultsPresenter;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {

        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecSearchFilterResultsAndFilterPresenter.go()");

            clearContainerAndAddTopLevelPanel(container, getDisplay());
            bindSearchAndEditExtended(Constants.QUERY_PATH_SEGMENT_PREFIX +
                    CommonFilterConstants.FILTER_TYPE_FILTER_VAR + "=" + CommonConstants.FILTER_CONTENT_SPEC);

        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecSearchFilterResultsAndFilterPresenter.go()");
        }
    }
}
