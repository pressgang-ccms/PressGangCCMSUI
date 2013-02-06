package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TagProjectsPresenter extends BaseChildrenComponent<
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1,
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> implements
        BaseTemplatePresenterInterface {

    public interface Display extends BaseChildrenViewInterface<
                RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,
                RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1,
                RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {

    }

    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "TagProjectsView";


    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {

    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, new RESTTagV1());
    }

    @Override
    public void bindChildrenExtended(final int helpTopicId, @NotNull final String pageId, @NotNull final RESTTagV1 parent) {
        super.bindChildren(helpTopicId, pageId, parent, display);
        display.setPossibleChildrenProvider(generatePossibleChildrenProvider(parent));
        refreshPossibleChildrenDataAndList(parent);
    }

    /**
     * @return A provider to be used for the project display list
     */
    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTTagV1 parent) {

        final EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTProjectCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTProjectCollectionItemV1> display) {

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

    @Override
    public boolean hasUnsavedChanges() {
        /* It is possible that the list of categories has not loaded yet, in which case no changes could have been made */
        if (getPossibleChildrenProviderData().getItems() != null) {
            for (final RESTProjectCollectionItemV1 project : getPossibleChildrenProviderData().getItems()) {
                if (project.getItem().getTags().returnDeletedAddedAndUpdatedCollectionItems().size() != 0) {
                    return true;
                }
            }
        }

        return false;
    }



    /**
     * Get the collection of projects, to which we will add or remove the currently selected tag. Note that the changes made to
     * this collection will be synced in reverse to the tag when the save button is clicked i.e. where the displayed tag is
     * added to a project, that will actually be persisted through the REST interface as a project added to the displayed tag.
     */
    @Override
    public void refreshPossibleChildrenDataAndList(@NotNull final RESTTagV1 parent) {
        final RESTCalls.RESTCallback<RESTProjectCollectionV1> callback = new RESTCalls.RESTCallback<RESTProjectCollectionV1>() {
            @Override
            public void begin() {
                display.addWaitOperation();
            }

            @Override
            public void generalException(final Exception e) {
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                display.removeWaitOperation();
            }

            @Override
            public void success(final RESTProjectCollectionV1 retValue) {
                try {
                    /* Zero results can be a null list */
                    getPossibleChildrenProviderData().setStartRow(0);
                    getPossibleChildrenProviderData().setItems(retValue.getItems());
                    getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                    /* Refresh the list */
                    display.getPossibleChildrenProvider().displayNewFixedList(getPossibleChildrenProviderData().getItems());

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

        /* Redisplay the loading widget. updateRowCount(0, false) is used to display the cell table loading widget. */
        getPossibleChildrenProviderData().reset();
        display.getPossibleChildrenProvider().resetProvider();

        RESTCalls.getProjects(callback);
    }
}


