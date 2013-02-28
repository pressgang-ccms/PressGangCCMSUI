package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class ProjectTagPresenter extends BaseChildrenPresenter<
        RESTProjectV1,                                                          // The main REST types
        RESTTagCollectionItemV1,                                                // The possible children types
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>                // The existing children types
        implements BaseTemplatePresenterInterface {

    public interface Display extends
        BaseChildrenViewInterface<RESTProjectV1, RESTTagCollectionItemV1, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {
    }

    public static final String HISTORY_TOKEN = "ProjectTagView";

    @Nullable
    private Integer entityId;

    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            entityId = Integer.parseInt(GWTUtilities.removeHistoryToken(HISTORY_TOKEN, historyToken));
        } catch (@NotNull final NumberFormatException ex) {
            entityId = null;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void bindChildrenExtended(final int topicId, @NotNull final String pageId) {
        super.bindChildren(topicId, pageId, display);
    }

    @Override
    public void displayChildrenExtended(@NotNull final RESTProjectV1 parent, final boolean readOnly) {
        super.displayChildren(parent, readOnly);
        display.display(parent, readOnly);
    }

    @Override
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTProjectV1 parent) {

        @NotNull final RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, ProjectTagPresenter.Display>(display,
                new BaseRestCallback.SuccessAction<RESTTagCollectionV1, ProjectTagPresenter.Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, final ProjectTagPresenter.Display display) {

                        /* Zero results can be a null list */
                        getPossibleChildrenProviderData().setStartRow(0);
                        getPossibleChildrenProviderData().setItems(retValue.getItems());
                        getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                        /* Refresh the list */
                        redisplayPossibleChildList(parent);
                    }
                });

        getPossibleChildrenProviderData().reset();

        RESTCalls.getTags(callback);
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTTagCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTProjectV1 parent) {

        @NotNull final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTagCollectionItemV1> display) {

                getPossibleChildrenProviderData().setStartRow(display.getVisibleRange().getStart());

                if (getPossibleChildrenProviderData().getItems() != null) {
                    displayNewFixedList(getPossibleChildrenProviderData().getItems());
                } else {
                    resetProvider();
                }

            }
        };
        return provider;
    }
}
