package org.jboss.pressgang.ccms.ui.client.local.mvp.component.project;

import java.util.ArrayList;
import java.util.Collections;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCollectionItemV1NameComparator;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren.BaseOrderedChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.view.client.HasData;

public class ProjectTagComponent
        extends
        BaseOrderedChildrenComponent<ProjectTagPresenter.Display,               // The display
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1,    // The main REST types 
        RESTProjectV1,                                                          // The parent of the existing children
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,                // The possible children types
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>                // The existing children types
        implements ProjectTagPresenter.LogicComponent {

    @Override
    public void bind(final ProjectTagPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);
        loadChildSplitResize(Preferences.CATEGORY_TAG_VIEW_MAIN_SPLIT_WIDTH);
        bindChildSplitResize(Preferences.CATEGORY_TAG_VIEW_MAIN_SPLIT_WIDTH);
        display.setPossibleChildrenProvider(generatePossibleChildrenProvider());
        getEntityList();
    }

    @Override
    public void getEntityList() {
        
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

    @Override
    public EnhancedAsyncDataProvider<RESTTagCollectionItemV1> generateExistingProvider(final RESTProjectV1 entity) {
        final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagCollectionItemV1> display) {
                getExistingProviderData().setStartRow(display.getVisibleRange().getStart());
                getExistingProviderData().setItems(new ArrayList<RESTTagCollectionItemV1>());

                /* Zero results can be a null list. Also selecting a new tag will reset getProviderData(). */
                if (entity != null && entity.getTags() != null) {
                    /* Don't display removed tags */
                    for (final RESTTagCollectionItemV1 tagInCategory : entity.getTags()
                            .returnExistingAddedAndUpdatedCollectionItems()) {
                        getExistingProviderData().getItems().add(tagInCategory);
                    }
                }

                Collections.sort(getExistingProviderData().getItems(), new RESTTagCollectionItemV1NameComparator());

                displayNewFixedList(getExistingProviderData().getItems());
            }
        };

        return provider;
    }
}
