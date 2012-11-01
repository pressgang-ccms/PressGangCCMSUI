package org.jboss.pressgang.ccms.ui.client.local.mvp.component.project;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectFilteredResultsPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectFilteredResultsPresenter.LogicCompnent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;

public class ProjectFilteredResultsComponent
        extends
        BaseFilteredResultsComponent<ProjectFilteredResultsPresenter.Display, RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1>
        implements LogicCompnent {

    @Override
    public void bind(final String queryString, final ProjectFilteredResultsPresenter.Display display,
            final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);

        display.setProvider(generateListProvider(queryString, display, waitDisplay));
    }

    /**
     * @return A provider to be used for the category display list
     */
    @Override
    protected EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> generateListProvider(final String queryString,
            final Display display, final BaseTemplateViewInterface waitDisplay) {
        final EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTProjectCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTProjectCollectionItemV1> list) {

                final RESTCallback<RESTProjectCollectionV1> callback = new RESTCallback<RESTProjectCollectionV1>() {
                    @Override
                    public void begin() {
                        resetProvider();
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTProjectCollectionV1 retValue) {
                        try {
                            getProviderData().setItems(retValue.getItems());
                            getProviderData().setSize(retValue.getSize());
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(),
                                    getProviderData().getStartRow());
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed(final Message message, final Throwable throwable) {
                        display.removeWaitOperation();
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                RESTCalls.getProjectsFromQuery(callback, queryString, getProviderData().getStartRow(), end);
            }
        };
        return provider;
    }

    @Override
    public String getQuery() {
        final StringBuilder retValue = new StringBuilder();
        return retValue.toString().isEmpty() ? Constants.QUERY_PATH_SEGMENT_PREFIX
                : Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON + retValue.toString();
    }

    @Override
    protected void displayQueryElements(final String queryString) {
        // TODO Auto-generated method stub

    }
}