package org.jboss.pressgang.ccms.ui.client.local.mvp.component.category;

import java.util.ArrayList;
import java.util.Collections;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren.BaseOrderedChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;

public class CategoryTagComponent
    extends BaseOrderedChildrenComponent<CategoryTagPresenter.Display, 
        RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1, 
        RESTCategoryV1,
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, 
        RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>
    implements CategoryTagPresenter.LogicComponent {

    @Override
    public void bind(final CategoryTagPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);
        loadChildSplitResize(Preferences.CATEGORY_TAG_VIEW_MAIN_SPLIT_WIDTH);
        bindChildSplitResize(Preferences.CATEGORY_TAG_VIEW_MAIN_SPLIT_WIDTH);
        display.setPossibleChildrenProvider(generatePossibleChildrenProvider());
        getEntityList();
    }

    

    @Override
    public void getEntityList() {
        final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTTagCollectionV1>() {
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
            public void success(final RESTTagCollectionV1 retValue) {
                try {
                    /* Zero results can be a null list */
                    providerData.setStartRow(0);
                    providerData.setItems(retValue.getItems());
                    providerData.setSize(retValue.getItems().size());

                    /* Refresh the list */
                    display.getPossibleChildrenProvider().displayNewFixedList(providerData.getItems());

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

    @Override
    protected void setSort(final RESTTagInCategoryCollectionItemV1 child, final int index) {
        child.getItem().explicitSetRelationshipSort(index);

    }
}
