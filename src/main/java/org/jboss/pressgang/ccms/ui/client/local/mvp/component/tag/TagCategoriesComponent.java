package org.jboss.pressgang.ccms.ui.client.local.mvp.component.tag;

import java.util.ArrayList;
import java.util.Collections;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

public class TagCategoriesComponent extends ComponentBase<TagCategoriesPresenter.Display> implements
        TagCategoriesPresenter.LogicComponent {

    /** Holds the data required to populate and refresh the categories list */
    private ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData = new ProviderUpdateData<RESTCategoryCollectionItemV1>();
    /** Holds the data required to populate and refresh the category tags list */
    private ProviderUpdateData<RESTTagInCategoryCollectionItemV1> categoryTagsProviderData = new ProviderUpdateData<RESTTagInCategoryCollectionItemV1>();

    public void bind(final String queryString, final TagCategoriesPresenter.Display display,
            final BaseTemplateViewInterface waitDisplay) {
        
        super.bind(display, waitDisplay);
        
        display.setProvider(generateCategoriesListProvider(queryString, display));
        display.setTagsProvider(generateCategoriesTagListProvider());
        bindCategoryListRowClicks(display);
    }

    /**
     * This provider pages over a collection of categories that was returned when the page was built. This is because changes to
     * the tag category relationships are done to the categories, not to the tag. This means we need to keep a list of the
     * categories instead of losing them when the table is paged through.
     * 
     * @return A provider to be used for the category display list.
     */
    private EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generateCategoriesListProvider(final String queryString,
            final TagCategoriesPresenter.Display display) {
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
    private EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> generateCategoriesTagListProvider() {
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
    private void bindCategoryListRowClicks(final TagCategoriesPresenter.Display display) {
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
    private boolean unsavedCategoryChanges() {
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

}
