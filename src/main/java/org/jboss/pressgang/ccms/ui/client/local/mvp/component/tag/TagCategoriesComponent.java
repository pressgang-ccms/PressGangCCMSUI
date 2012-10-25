package org.jboss.pressgang.ccms.ui.client.local.mvp.component.tag;

import java.util.ArrayList;
import java.util.Collections;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
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

public class TagCategoriesComponent extends ComponentBase<TagCategoriesPresenter.Display> implements
        TagCategoriesPresenter.LogicComponent {

    /** Holds the data required to populate and refresh the categories list */
    private ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData = new ProviderUpdateData<RESTCategoryCollectionItemV1>();
    /** Holds the data required to populate and refresh the category tags list */
    private ProviderUpdateData<RESTTagInCategoryCollectionItemV1> categoryTagsProviderData = new ProviderUpdateData<RESTTagInCategoryCollectionItemV1>();

    public ProviderUpdateData<RESTTagInCategoryCollectionItemV1> getCategoryTagsProviderData() {
        return categoryTagsProviderData;
    }

    public void setCategoryTagsProviderData(ProviderUpdateData<RESTTagInCategoryCollectionItemV1> categoryTagsProviderData) {
        this.categoryTagsProviderData = categoryTagsProviderData;
    }

    @Override
    public ProviderUpdateData<RESTCategoryCollectionItemV1> getCategoryProviderData() {
        return categoryProviderData;
    }

    @Override
    public void setCategoryProviderData(ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData) {
        this.categoryProviderData = categoryProviderData;
    }

    @Override
    public void bind(final TagCategoriesPresenter.Display display,
            final BaseTemplateViewInterface waitDisplay) {

        super.bind(display, waitDisplay);

        display.setProvider(generateCategoriesListProvider());
        display.setTagsProvider(generateCategoriesTagListProvider());
        bindCategoryListRowClicks();
        bindCategoryTagsColumnButtons();
        bindCategorySplitResize();
        getCategories();
    }

    /**
     * This provider pages over a collection of categories that was returned when the page was built. This is because changes to
     * the tag category relationships are done to the categories, not to the tag. This means we need to keep a list of the
     * categories instead of losing them when the table is paged through.
     * 
     * @return A provider to be used for the category display list.
     */
    @Override
    public EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generateCategoriesListProvider() {
        final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTCategoryCollectionItemV1> display) {

                categoryProviderData.setStartRow(display.getVisibleRange().getStart());

                if (categoryProviderData.getItems() != null)
                    displayNewFixedList(categoryProviderData.getItems());
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
    public EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> generateCategoriesTagListProvider() {
        final EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagInCategoryCollectionItemV1> display) {
                categoryTagsProviderData.setStartRow(display.getVisibleRange().getStart());
                categoryTagsProviderData.setItems(new ArrayList<RESTTagInCategoryCollectionItemV1>());

                /* Zero results can be a null list. Also selecting a new tag will reset categoryProviderData. */
                if (categoryProviderData.getDisplayedItem() != null
                        && categoryProviderData.getDisplayedItem().getItem().getTags() != null) {
                    /* Don't display removed tags */
                    for (final RESTTagInCategoryCollectionItemV1 tagInCategory : categoryProviderData.getDisplayedItem()
                            .getItem().getTags().returnExistingAddedAndUpdatedCollectionItems()) {
                        categoryTagsProviderData.getItems().add(tagInCategory);
                    }
                }

                Collections.sort(categoryTagsProviderData.getItems(), new RESTTagCategoryCollectionItemV1SortComparator());

                displayNewFixedList(categoryTagsProviderData.getItems());
            }
        };

        return provider;
    }

    /**
     * Bind behaviour to the buttons found in the celltable listing the categories
     */
    private void bindCategoryListRowClicks() {
        display.getResults().addCellPreviewHandler(new Handler<RESTCategoryCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTCategoryCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    /*
                     * categoryProviderData.getSelectedItem() will be null until a category is selected for the first time
                     */
                    final boolean needToAddImageView = categoryProviderData.getSelectedItem() == null;

                    /*
                     * Normally a list is populated with an un-expanded collection of entities. However, in this case we have
                     * expanded the categories to include all the tags.
                     * 
                     * Because the categories that are displayed in the category list have all the expanded topics, we don't
                     * need to get an expanded category in response to a category being selected. This means the displayed and
                     * selected items are the same.
                     */
                    categoryProviderData.setSelectedItem(event.getValue());
                    categoryProviderData.setDisplayedItem(event.getValue());

                    /*
                     * If this is the first category selected, display the tags list
                     */
                    if (needToAddImageView) {
                        display.getSplit().add(display.getTagsResultsPanel());
                    }

                    /*
                     * reset the provider, which will refresh the list of tags
                     */
                    display.setTagsProvider(generateCategoriesTagListProvider());
                }
            }
        });
    }

    /**
     * @return true if the categories have any unsaved changes to their tags
     */
    @Override
    public boolean unsavedCategoryChanges() {
        /* It is possible that the list of categories has not loaded yet, in which case no changes could have been made */
        if (categoryProviderData.getItems() != null) {
            for (final RESTCategoryCollectionItemV1 category : categoryProviderData.getItems()) {
                if (category.getItem().getTags().returnDeletedAddedAndUpdatedCollectionItems().size() != 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private void moveTagsUpAndDown(final RESTTagInCategoryCollectionItemV1 object, final boolean down) {

        final int size = categoryTagsProviderData.getItems().size();

        boolean modifiedSort = false;

        /* if moving down, start at the beginning, and end on the second last item. If moving up, start the second item */
        for (int i = (down ? 0 : 1); i < (down ? size - 1 : size); ++i) {

            /* Get the item from the collection for convenience */
            final RESTTagInCategoryCollectionItemV1 tagInCatgeory = categoryTagsProviderData.getItems().get(i);

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
                    final RESTTagInCategoryCollectionItemV1 existingTagInCatgeory = categoryTagsProviderData.getItems().get(j);

                    /* set the sort value (the list was previously sorted on this value) */
                    existingTagInCatgeory.getItem().setRelationshipSort(j);

                    /* we need to mark the joiner entity as updated */
                    if (!existingTagInCatgeory.returnIsAddItem())
                        existingTagInCatgeory.setState(RESTBaseUpdateCollectionItemV1.UPDATE_STATE);
                }

                /* The next item is either the item before (if moving up) of the item after (if moving down) */
                final int nextItemIndex = down ? i + 1 : i - 1;

                /* get the next item in the list */
                final RESTTagInCategoryCollectionItemV1 nextTagInCatgeory = categoryTagsProviderData.getItems().get(
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
            display.setTagsProvider(generateCategoriesTagListProvider());
        }
    }

    

    private void bindCategoryTagsColumnButtons() {
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
    public void getCategories() {
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
                    categoryProviderData.setStartRow(0);
                    /* Zero results can be a null list */
                    categoryProviderData.setItems(retValue.getItems());

                    display.getProvider().displayNewFixedList(categoryProviderData.getItems());

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
        categoryProviderData.reset();
        display.getProvider().resetProvider();

        RESTCalls.getCategories(callback);
    }

    /**
     * Saves the width of the split screen in the category view
     */
    private void bindCategorySplitResize() {
        display.getSplit().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, display.getSplit()
                        .getSplitPosition(display.getSearchResultsPanel()) + "");
            }
        });
    }

}
