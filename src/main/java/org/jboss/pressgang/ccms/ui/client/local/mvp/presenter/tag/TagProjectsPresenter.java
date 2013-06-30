package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.sort.project.RESTProjectCollectionItemDescriptionSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.project.RESTProjectCollectionItemIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.project.RESTProjectCollectionItemNameSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.project.RESTProjectCollectionItemParentSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Collections;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TagProjectsPresenter extends BaseChildrenPresenter<
        RESTTagV1,
        RESTProjectCollectionItemV1,
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> implements
        BaseTemplatePresenterInterface {

    public interface Display extends BaseChildrenViewInterface<
            RESTTagV1,
            RESTProjectCollectionItemV1,
            RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {
        TextColumn<RESTProjectCollectionItemV1> getIdColumn();

        TextColumn<RESTProjectCollectionItemV1> getNameColumn();

        TextColumn<RESTProjectCollectionItemV1> getDescriptionColumn();
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
        bindChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void close() {

    }

    @Override
    public void bindChildrenExtended(final int helpTopicId, @NotNull final String pageId) {
        super.bindChildren(helpTopicId, pageId, display);

    }

    @Override
    public void displayChildrenExtended(final @NotNull RESTTagV1 parent, final boolean readOnly) {
        super.displayChildren(parent, readOnly);
        display.setPossibleChildrenProvider(generatePossibleChildrenProvider(parent));
        refreshPossibleChildrenDataFromRESTAndRedisplayList(parent);
    }

    /**
     * @return A provider to be used for the project display list
     */
    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTTagV1 parent) {

        @NotNull final EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTProjectCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTProjectCollectionItemV1> data) {

                getPossibleChildrenProviderData().setStartRow(data.getVisibleRange().getStart());

                if (getPossibleChildrenProviderData().getItems() != null) {
                    /*
                        Implement sorting
                    */
                    final ColumnSortList sortList = display.getPossibleChildrenResults().getColumnSortList();
                    if (sortList.size() != 0) {
                        final Column<?, ?> column = sortList.get(0).getColumn();
                        final boolean ascending = sortList.get(0).isAscending();

                        /*
                            Sort the collection
                        */
                        if (column == display.getPossibleChildrenButtonColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTProjectCollectionItemParentSort(parent, ascending));
                        } else if (column == display.getIdColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTProjectCollectionItemIDSort(ascending));
                        } else if (column == display.getNameColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTProjectCollectionItemNameSort(ascending));
                        } else if (column == display.getDescriptionColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTProjectCollectionItemDescriptionSort(ascending));
                        }
                    }

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
            for (@NotNull final RESTProjectCollectionItemV1 project : getPossibleChildrenProviderData().getItems()) {
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
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTTagV1 parent) {

        final BaseRestCallback<RESTProjectCollectionV1, Display> callback = new BaseRestCallback<RESTProjectCollectionV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTProjectCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTProjectCollectionV1 retValue, @NotNull final Display display) {
                        checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                        getPossibleChildrenProviderData().setStartRow(0);
                        getPossibleChildrenProviderData().setItems(retValue.getItems());
                        getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                    /* Refresh the list */
                        redisplayPossibleChildList(parent);
                    }
                });

        getPossibleChildrenProviderData().reset();

        RESTCalls.getProjects(callback);
    }
}


