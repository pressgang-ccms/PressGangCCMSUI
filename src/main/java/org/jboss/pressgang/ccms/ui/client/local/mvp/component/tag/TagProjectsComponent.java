package org.jboss.pressgang.ccms.ui.client.local.mvp.component.tag;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.view.client.HasData;

public class TagProjectsComponent extends ComponentBase<TagProjectsPresenter.Display> implements
        TagProjectsPresenter.LogicComponent {

    /** Holds the data required to populate and refresh the projects list */
    private ProviderUpdateData<RESTProjectCollectionItemV1> projectProviderData = new ProviderUpdateData<RESTProjectCollectionItemV1>();
    
    public void bind(final TagProjectsPresenter.Display display, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display, waitDisplay);
        display.setProvider(generateProjectListProvider());
    }
    
    /**
     * @return A provider to be used for the project display list
     */
    private EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> generateProjectListProvider() {

        final EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTProjectCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTProjectCollectionItemV1> display) {

                projectProviderData.setStartRow(display.getVisibleRange().getStart());

                if (projectProviderData.getItems() != null)
                    displayNewFixedList(projectProviderData.getItems());
                else
                    resetProvider();

            }
        };
        return provider;
    }

}


