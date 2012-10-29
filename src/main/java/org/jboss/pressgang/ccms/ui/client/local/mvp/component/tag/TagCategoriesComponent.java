package org.jboss.pressgang.ccms.ui.client.local.mvp.component.tag;

import java.util.ArrayList;
import java.util.Collections;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren.BaseOrderedChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

public class TagCategoriesComponent
        extends
        BaseOrderedChildrenComponent<TagCategoriesPresenter.Display, RESTTagV1, RESTCategoryV1, RESTCategoryCollectionItemV1, RESTTagInCategoryCollectionItemV1>
        implements TagCategoriesPresenter.LogicComponent {

    @Override
    public void bind(final TagCategoriesPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        super.bind(display, waitDisplay);

        display.setPossibleChildrenProvider(generatePossibleChildrenrovider());
        // display.setExistingChildrenProvider(generateExistingProvider());
        bindPossibleChildrenRowClick();
        bindExistingChildrenRowClick();
        bindChildSplitResize(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH);
        getEntityList();
    }

    /**
     * This provider pages over a collection of categories that was returned when the page was built. This is because changes to
     * the tag category relationships are done to the categories, not to the tag. This means we need to keep a list of the
     * categories instead of losing them when the table is paged through.
     * 
     * @return A provider to be used for the category display list.
     */
    @Override
    public EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generatePossibleChildrenrovider() {
        final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTCategoryCollectionItemV1> display) {

                getPossibleChildrenProviderData().setStartRow(display.getVisibleRange().getStart());

                if (getPossibleChildrenProviderData().getItems() != null)
                    displayNewFixedList(getPossibleChildrenProviderData().getItems());
                else
                    resetProvider();

            }
        };
        return provider;
    }

    /**
     * @return A provider to be used for the tag display list
     */
    @Override
    public EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> generateExistingProvider(final RESTCategoryV1 entity) {
        final EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagInCategoryCollectionItemV1> display) {
                getExistingProviderData().setStartRow(display.getVisibleRange().getStart());
                getExistingProviderData().setItems(new ArrayList<RESTTagInCategoryCollectionItemV1>());

                /* Zero results can be a null list. Also selecting a new tag will reset getProviderData(). */
                if (entity != null && entity.getTags() != null) {
                    /* Don't display removed tags */
                    for (final RESTTagInCategoryCollectionItemV1 tagInCategory : entity.getTags()
                            .returnExistingAddedAndUpdatedCollectionItems()) {
                        getExistingProviderData().getItems().add(tagInCategory);
                    }
                }

                Collections.sort(getExistingProviderData().getItems(), new RESTTagCategoryCollectionItemV1SortComparator());

                displayNewFixedList(getExistingProviderData().getItems());
            }
        };

        return provider;
    }

    /**
     * Bind behaviour to the buttons found in the celltable listing the categories
     */
    @Override
    protected void bindPossibleChildrenRowClick() {
        display.getPossibleChildrenResults().addCellPreviewHandler(new Handler<RESTCategoryCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTCategoryCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    /*
                     * getProviderData().getSelectedItem() will be null until a category is selected for the first time
                     */
                    final boolean needToAddImageView = getPossibleChildrenProviderData().getSelectedItem() == null;

                    /*
                     * Normally a list is populated with an un-expanded collection of entities. However, in this case we have
                     * expanded the categories to include all the tags.
                     * 
                     * Because the categories that are displayed in the category list have all the expanded topics, we don't
                     * need to get an expanded category in response to a category being selected. This means the displayed and
                     * selected items are the same.
                     */
                    getPossibleChildrenProviderData().setSelectedItem(event.getValue());
                    getPossibleChildrenProviderData().setDisplayedItem(event.getValue());

                    /*
                     * If this is the first category selected, display the tags list
                     */
                    if (needToAddImageView) {
                        display.getSplit().add(display.getExistingChildrenResultsPanel());
                    }

                    /*
                     * reset the provider, which will refresh the list of tags
                     */
                    display.setExistingChildrenProvider(generateExistingProvider(getPossibleChildrenProviderData()
                            .getDisplayedItem().getItem()));
                }
            }
        });
    }

    /**
     * @return true if the categories have any unsaved changes to their tags
     */
    @Override
    public boolean checkForUnsavedChanges() {
        /* It is possible that the list of categories has not loaded yet, in which case no changes could have been made */
        if (getPossibleChildrenProviderData().getItems() != null) {
            for (final RESTCategoryCollectionItemV1 category : getPossibleChildrenProviderData().getItems()) {
                if (category.getItem().getTags().returnDeletedAddedAndUpdatedCollectionItems().size() != 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private void moveTagsUpAndDown(final RESTTagInCategoryCollectionItemV1 object, final boolean down) {

        final int size = getExistingProviderData().getItems().size();

        boolean modifiedSort = false;

        /* if moving down, start at the beginning, and end on the second last item. If moving up, start the second item */
        for (int i = (down ? 0 : 1); i < (down ? size - 1 : size); ++i) {

            /* Get the item from the collection for convenience */
            final RESTTagInCategoryCollectionItemV1 tagInCatgeory = getExistingProviderData().getItems().get(i);

            if (tagInCatgeory.getItem().getId().equals(object.getItem().getId())) {

                /*
                 * The sort values are exposed directly in the old UI. This means that it is possible for two tags to have the
                 * same or no sort value assigned to them, or have sort values that increment in odd values.
                 * 
                 * If we are changing the sort order in the new UI, we need a consistent progression of the sort field. So, now
                 * that we have found a tag that needs changing, we start by reassigning sort values based on the location of
                 * the tag in the collection.
                 */

                for (int j = 0; j < size; ++j) {
                    /* get the item from the collection */
                    final RESTTagInCategoryCollectionItemV1 existingTagInCatgeory = getExistingProviderData().getItems().get(j);

                    /* set the sort value (the list was previously sorted on this value) */
                    existingTagInCatgeory.getItem().setRelationshipSort(j);

                    /* we need to mark the joiner entity as updated */
                    if (!existingTagInCatgeory.returnIsAddItem())
                        existingTagInCatgeory.setState(RESTBaseUpdateCollectionItemV1.UPDATE_STATE);
                }

                /* The next item is either the item before (if moving up) of the item after (if moving down) */
                final int nextItemIndex = down ? i + 1 : i - 1;

                /* get the next item in the list */
                final RESTTagInCategoryCollectionItemV1 nextTagInCatgeory = getExistingProviderData().getItems().get(
                        nextItemIndex);

                /* swap the sort field */
                tagInCatgeory.getItem().setRelationshipSort(nextItemIndex);
                nextTagInCatgeory.getItem().setRelationshipSort(i);

                modifiedSort = true;
                break;
            }
        }

        if (modifiedSort) {
            /* redisplay the fixed list */
            display.setExistingChildrenProvider(generateExistingProvider(getPossibleChildrenProviderData().getDisplayedItem()
                    .getItem()));
        }
    }

    @Override
    protected void bindExistingChildrenRowClick() {
        display.getTagUpButtonColumn().setFieldUpdater(new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

            @Override
            public void update(final int index, final RESTTagInCategoryCollectionItemV1 object, final String value) {
                moveTagsUpAndDown(object, false);
            }

        });

        display.getTagDownButtonColumn().setFieldUpdater(new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

            /**
             * Swap the sort value for the tag that was selected with the tag below it.
             */
            @Override
            public void update(final int index, final RESTTagInCategoryCollectionItemV1 object, final String value) {
                moveTagsUpAndDown(object, true);
            }
        });
    }

    /**
     * Get the collection of categories, to which we will add or remove the currently selected tag. Note that the changes made
     * to this collection will be synced in reverse to the tag when the save button is clicked i.e. where the displayed tag is
     * added to a project, that will actually be persisted through the REST interface as a category added to the displayed tag.
     */
    @Override
    public void getEntityList() {
        final RESTCalls.RESTCallback<RESTCategoryCollectionV1> callback = new RESTCalls.RESTCallback<RESTCategoryCollectionV1>() {
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
            public void success(final RESTCategoryCollectionV1 retValue) {
                try {
                    getPossibleChildrenProviderData().setStartRow(0);
                    /* Zero results can be a null list */
                    getPossibleChildrenProviderData().setItems(retValue.getItems());

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

        RESTCalls.getCategories(callback);
    }

    @Override
    protected void getExistingEntityList() {
        // Does nothing
    }

}
