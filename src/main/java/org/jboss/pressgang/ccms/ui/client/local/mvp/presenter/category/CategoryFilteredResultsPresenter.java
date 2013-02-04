package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter used to add logic to the category filtered list view.
 */
@Dependent
public final class CategoryFilteredResultsPresenter
        extends BaseFilteredResultsComponent<
        RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1>
        implements BaseTemplatePresenterInterface {

    /**
     * This history token.
     */
    public static final String HISTORY_TOKEN = "CategoryFilteredResultsView";
    /**
     * A logger.
     */
    private static final Logger logger = Logger.getLogger(CategoryFilteredResultsPresenter.class.getName());
    /**
     * The display used to show the list of categories.
     */
    @Inject
    private Display display;
    /**
     * The query string used to return the list of categories.
     */
    private String queryString;

    public Display getDisplay()
    {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public CategoryFilteredResultsPresenter() {

    }

    @Override
    public final void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, this.queryString);
    }

    @Override
    public void parseToken(final String searchToken) {
        this.queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    public void bindExtendedFilteredResults(final int topicId, final String pageId, final String queryString) {
        try {
            logger.log(Level.INFO, "ENTER CategoryFilteredResultsPresenter.bind()");
            super.bindFilteredResults(topicId, pageId, queryString, display);
            display.setProvider(generateListProvider(queryString, display));
        } finally {
            logger.log(Level.INFO, "EXIT CategoryFilteredResultsPresenter.bind()");
        }
    }

    @Override
    protected EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generateListProvider(final String queryString, final BaseTemplateViewInterface waitDisplay) {
        return new EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTCategoryCollectionItemV1> list) {

                final RESTCallback<RESTCategoryCollectionV1> callback = new RESTCallback<RESTCategoryCollectionV1>() {
                    @Override
                    public void begin() {
                        logger.log(Level.INFO, "RESTCallback.begin()");
                        resetProvider();
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        logger.log(Level.SEVERE, "RESTCallback.generalException()\n\tException: " + ex.toString());
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTCategoryCollectionV1 retValue) {
                        try {
                            logger.log(Level.INFO, "RESTCallback.success(). retValue.getSize(): " + retValue.getSize() + " retValue.getItems().size(): " + retValue.getItems().size());
                            getProviderData().setItems(retValue.getItems());
                            getProviderData().setSize(retValue.getSize());
                            relinkSelectedItem();
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(),
                                    getProviderData().getStartRow());
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed(final Message message, final Throwable throwable) {
                        display.removeWaitOperation();
                        logger.log(Level.SEVERE, "RESTCallback.failed()\n\tMessage: " + message.toString() + "\n\t Throwable: " + throwable.toString());
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                RESTCalls.getCategoriesFromQuery(callback, queryString, getProviderData().getStartRow(), end);
            }
        };
    }

    @Override
    public String getQuery() {
        final StringBuilder retValue = new StringBuilder();
        if (!this.display.getIdFilter().getText().isEmpty()) {
            retValue.append(";catIds=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.display.getIdFilter().getText()) : this.display.getIdFilter().getText()));
        }
        if (!this.display.getNameFilter().getText().isEmpty()) {
            retValue.append(";catName=").append((Constants.ENCODE_QUERY_OPTIONS ?URL.encodeQueryString(this.display.getNameFilter().getText()) : this.display.getNameFilter().getText()));
        }
        if (!this.display.getDescriptionFilter().getText().isEmpty()) {
            retValue.append(";catDesc=").append((Constants.ENCODE_QUERY_OPTIONS ?URL.encodeQueryString(this.display.getDescriptionFilter().getText()) : this.display.getDescriptionFilter().getText()));
        }

        return retValue.toString().isEmpty() ? Constants.QUERY_PATH_SEGMENT_PREFIX
                : Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON + retValue.toString();
    }

    @Override
    protected void displayQueryElements(final String queryString) {
        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("catIds")) {
                    this.display.getIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals("catName")) {
                    this.display.getNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals("catDesc")) {
                    this.display.getDescriptionFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }
    }

    /**
     * The interface used to define the category filtered list view.
     */
    public interface Display extends
            BaseFilteredResultsViewInterface<RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1> {

        /**
         * @return The fields used to specify the category ids filter
         */
        TextBox getIdFilter();

        /**
         * @return The fields used to specify the category description filter
         */
        TextBox getDescriptionFilter();

        /**
         * @return The fields used to specify the category name filter
         */
        TextBox getNameFilter();
    }
}
