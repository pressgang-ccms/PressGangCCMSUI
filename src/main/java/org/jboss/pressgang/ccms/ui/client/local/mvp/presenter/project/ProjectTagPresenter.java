package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.project.ProjectViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class ProjectTagPresenter
        extends BaseChildrenComponent<
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1,    // The main REST types 
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,                // The possible children types
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>                // The existing children types
        implements BaseTemplatePresenterInterface {

    public interface Display
            extends
            BaseChildrenViewInterface<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>,
            ProjectViewInterface {
    }

    public static final String HISTORY_TOKEN = "ProjectTagView";

    private Integer entityId;

    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void parseToken(final String historyToken) {
        try {
            entityId = Integer.parseInt(GWTUtilities.removeHistoryToken(HISTORY_TOKEN, historyToken));
        } catch (final NumberFormatException ex) {
            entityId = null;
        }
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int topicId, final String pageId)
    {
        display.initialize(null, false);
        super.bind(topicId, pageId, display);
    }

    @Override
    public void refreshPossibleChildrenDataAndList() {

        final RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, ProjectTagPresenter.Display>(display,
                new BaseRestCallback.SuccessAction<RESTTagCollectionV1, ProjectTagPresenter.Display>() {
                    @Override
                    public void doSuccessAction(final RESTTagCollectionV1 retValue, final ProjectTagPresenter.Display display) {

                            /* Zero results can be a null list */
                        providerData.setStartRow(0);
                        providerData.setItems(retValue.getItems());
                        providerData.setSize(retValue.getItems().size());

                            /* Refresh the list */
                        display.getPossibleChildrenProvider().displayNewFixedList(providerData.getItems());
                    }
                }) {
        };

        /* Redisplay the loading widget. updateRowCount(0, false) is used to display the cell table loading widget. */
        providerData.reset();
        display.getPossibleChildrenProvider().resetProvider();

        RESTCalls.getTags(callback);
    }

    @Override
    public EnhancedAsyncDataProvider<RESTTagCollectionItemV1> generatePossibleChildrenProvider() {

        final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagCollectionItemV1> display) {

                providerData.setStartRow(display.getVisibleRange().getStart());

                if (providerData.getItems() != null)
                    displayNewFixedList(providerData.getItems());
                else
                    resetProvider();

            }
        };
        return provider;
    }

}
