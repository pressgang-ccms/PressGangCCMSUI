package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.BaseOrderedChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * The presenter used to add logic to the category tag view.
 */
@Dependent
public class CategoryTagPresenter
        extends BaseOrderedChildrenPresenter<
                RESTCategoryV1,
                RESTCategoryV1,
                RESTTagCollectionItemV1,
                RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>
        implements BaseTemplatePresenterInterface {

    /**
     * This history token.
     */
    public static final String HISTORY_TOKEN = "CategoryTagView";
    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CategoryTagPresenter.class.getName());
    /**
     * The id of the category to display.
     */
    @Nullable
    private Integer entityId;
    /**
     * The category tag view.
     */
    @Inject
    private Display display;

    /**
     * The category tag view.
     */
    @NotNull
    public Display getDisplay() {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public CategoryTagPresenter() {

    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, this.getDisplay());
        bindDetailedChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindDetailedChildrenExtended(final int helpTopicId, @NotNull final String pageId) {
        super.bindDetailedChildren(helpTopicId, pageId, Preferences.CATEGORY_TAG_VIEW_MAIN_SPLIT_WIDTH, display);
    }

    @Override
    public void displayDetailedChildrenExtended(@NotNull final RESTCategoryV1 parent, final boolean readOnly) {
        super.displayDetailedChildren(parent, readOnly);
        display.display(parent, readOnly);
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
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTCategoryV1 parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER CategoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList()");

            @NotNull final BaseRestCallback<RESTTagCollectionV1, Display> callback = new BaseRestCallback<RESTTagCollectionV1, Display>(display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, Display>() {
                @Override
                public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, @NotNull final Display display) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER CategoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList() BaseRestCallback.doSuccessAction()");

                        checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");



                        getPossibleChildrenProviderData().setStartRow(0);
                        getPossibleChildrenProviderData().setItems(retValue.getItems());
                        getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                        /* Refresh the list */
                        redisplayPossibleChildList(parent);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT CategoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList() BaseRestCallback.doSuccessAction()");
                    }
                }
            });

            getPossibleChildrenProviderData().reset();

            RESTCalls.getTags(callback);
        } finally {
            LOGGER.log(Level.INFO, "EXIT CategoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList()");
        }
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTTagCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTCategoryV1 parent) {

        return new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTagCollectionItemV1> data) {

                getPossibleChildrenProviderData().setStartRow(data.getVisibleRange().getStart());

                if (getPossibleChildrenProviderData().getItems() != null) {
                    /*
                        Implement sorting
                     */
                    final ColumnSortList sortList = display.getExistingChildrenResults().getColumnSortList();
                    if (sortList.size() != 0) {
                        LOGGER.log(Level.INFO, "Sort column found");

                        /*
                            Do a sort based on the tags inclusion in the parent
                         */
                        if (sortList.get(0).getColumn() == display.getPossibleChildrenButtonColumn()) {
                            LOGGER.log(Level.INFO, "Sorting tags");

                            Collections.sort(getPossibleChildrenProviderData().getItems(), new Comparator<RESTTagCollectionItemV1>() {
                                @Override
                                public int compare(@Nullable final RESTTagCollectionItemV1 arg0, @Nullable final RESTTagCollectionItemV1 arg1) {
                                        /*
                                            First deal with null objects
                                         */
                                    if (arg0 == null && arg1 == null) {
                                        return 0;
                                    }

                                    if (arg0 == arg1) {
                                        return 0;
                                    }

                                    if (arg0 == null) {
                                        return -1;
                                    }

                                    if (arg1 == null) {
                                        return 1;
                                    }

                                        /*
                                            Now deal with their inclusion in the parent
                                         */
                                    if (parent != null && parent.getTags() != null && parent.getTags().getItems() != null) {
                                        LOGGER.log(Level.INFO, "Sorting on tag inclusion in category");

                                        final boolean arg0IsChild = ComponentCategoryV1.containsTag(parent, arg0.getItem().getId());
                                        final boolean arg1IsChild = ComponentCategoryV1.containsTag(parent, arg1.getItem().getId());

                                        if (arg0IsChild && arg1IsChild) {

                                                /*
                                                    Fall back to comparing by name
                                                */
                                            if (arg0.getItem().getName() == null && arg1.getItem().getName() == null) {
                                                return 0;
                                            }

                                            if (arg0.getItem().getName() == null) {
                                                return -1;
                                            }

                                            if (arg1.getItem().getName() == null) {
                                                return 1;
                                            }

                                            return arg0.getItem().getName().compareTo(arg1.getItem().getName());
                                        }

                                        if (!arg0IsChild) {
                                            return -1;
                                        }

                                        return 1;
                                    }

                                        /*
                                            Fall back to comparing by name
                                         */
                                    if (arg0.getItem().getName() == null && arg1.getItem().getName() == null) {
                                        return 0;
                                    }

                                    if (arg0.getItem().getName() == null) {
                                        return -1;
                                    }

                                    if (arg1.getItem().getName() == null) {
                                        return 1;
                                    }

                                    return arg0.getItem().getName().compareTo(arg1.getItem().getName());
                                }
                            });
                        }
                    }

                    displayNewFixedList(getPossibleChildrenProviderData().getItems());
                } else {
                    resetProvider();
                }

            }
        };
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> generateExistingProvider(@Nullable final RESTCategoryV1 entity) {
        return new EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTagInCategoryCollectionItemV1> display) {
                getExistingProviderData().setStartRow(display.getVisibleRange().getStart());
                getExistingProviderData().setItems(new ArrayList<RESTTagInCategoryCollectionItemV1>());

                /* Zero results can be a null list. Also selecting a new tag will reset getProviderData(). */
                if (entity != null && entity.getTags() != null) {
                    /* Don't display removed tags */
                    for (final RESTTagInCategoryCollectionItemV1 tagInCategory : entity.getTags().returnExistingAddedAndUpdatedCollectionItems()) {
                        getExistingProviderData().getItems().add(tagInCategory);
                    }
                }

                Collections.sort(getExistingProviderData().getItems(), new RESTTagCategoryCollectionItemV1SortComparator());

                displayNewFixedList(getExistingProviderData().getItems());
            }
        };
    }

    /**
     * The interface that defines the category tag view.
     */
    public interface Display extends
            BaseOrderedChildrenViewInterface<
                    RESTCategoryV1,
                    RESTCategoryV1,
                    RESTTagCollectionItemV1,
                    RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1> {


    }

}
