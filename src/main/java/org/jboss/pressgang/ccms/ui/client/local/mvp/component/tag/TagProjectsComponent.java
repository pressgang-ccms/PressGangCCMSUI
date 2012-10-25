package org.jboss.pressgang.ccms.ui.client.local.mvp.component.tag;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;

public class TagProjectsComponent extends ComponentBase<TagProjectsPresenter.Display> implements
        TagProjectsPresenter.LogicComponent {

    /** Holds the data required to populate and refresh the projects list */
    private ProviderUpdateData<RESTProjectCollectionItemV1> projectProviderData = new ProviderUpdateData<RESTProjectCollectionItemV1>();
    
    @Override
    public ProviderUpdateData<RESTProjectCollectionItemV1> getProjectProviderData() {
        return projectProviderData;
    }

    @Override
    public void setProjectProviderData(final ProviderUpdateData<RESTProjectCollectionItemV1> projectProviderData) {
        this.projectProviderData = projectProviderData;
    }

    @Override
    public void bind(final TagProjectsPresenter.Display display, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display, waitDisplay);
        display.setPossibleChildrenProvider(generateProjectListProvider());
        getProjects();
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
    
    /**
     * @return true if the categories have any unsaved changes to their tags
     */
    @Override
    public boolean unsavedProjectChanges() {
        /* It is possible that the list of categories has not loaded yet, in which case no changes could have been made */
        if (projectProviderData.getItems() != null) {
            for (final RESTProjectCollectionItemV1 project : projectProviderData.getItems()) {
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
    public void getProjects() {
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
                    projectProviderData.setStartRow(0);
                    projectProviderData.setItems(retValue.getItems());

                    /* Refresh the list */
                    display.getPossibleChildrenProvider().displayNewFixedList(projectProviderData.getItems());

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
        projectProviderData.reset();
        display.getPossibleChildrenProvider().resetProvider();

        RESTCalls.getProjects(callback);
    }

}


