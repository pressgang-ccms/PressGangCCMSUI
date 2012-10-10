package org.jboss.pressgang.ccms.ui.client.local.mvp.component.tag;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentRESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

public class TagsFilteredResultsAndTagComponent extends ComponentBase<TagsFilteredResultsAndTagPresenter.Display> implements
        TagsFilteredResultsAndTagPresenter.LogicComponent {

    
    public void bind(final TagsFilteredResultsAndTagPresenter.Display display, BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display, waitDisplay);
        
    }
    
    /**
     * Bind the button click events for the topic editor screens
     */
    private void bindTagListRowClicks(final TagFilteredResultsPresenter.Display filteredResultsDisplay, final TagFilteredResultsComponent filteredResultsComponent) {
        filteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTTagCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTTagCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    if (!checkForUnsavedChanges()) {
                        return;
                    }

                    /* The selected item will be the tag from the list. This is the unedited, unexpanded copy of the tag */
                    filteredResultsComponent.getTagProviderData().setSelectedItem(event.getValue());
                    /* All editing is done in a clone of the selected tag. Any expanded collections will be copied into this tag */
                    filteredResultsComponent.getTagProviderData().setDisplayedItem(event.getValue().clone(true));

                    /*
                     * If this is the first image selected, display the image view
                     */
                    if (displayedView == null) {
                        displayedView = resultDisplay;
                    }

                    resetCategoryAndProjectsLists(true);

                    reInitialiseView();
                }
            }
        });
    }
    
    /**
     * Compare the displayed tag (the one that is edited) with the selected tag (the one that exists in the collection used to
     * build the tag list). If there are unsaved changes, prompt the user.
     * 
     * @return true if the user wants to ignore the unsaved changes, false otherwise
     */
    @Override
    public boolean checkForUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (tagProviderData.getDisplayedItem() != null) {
            resultDisplay.getDriver().flush();

            if (unsavedTagChanged() || unsavedCategoryChanges() || unsavedProjectChanges()) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }

        return true;
    }

    /**
     * 
     * @return true if the tag has any unsaved changes
     */
    private boolean unsavedTagChanged() {
        /*
         * See if any items have been added or removed from the project and category lists
         */
        final boolean unsavedCategoryChanges = categoryProviderData.getItems() != null
                && ComponentRESTBaseEntityV1.returnDirtyStateForCollectionItems(categoryProviderData.getItems());
        final boolean unsavedProjectChanges = projectProviderData.getItems() != null
                && ComponentRESTBaseEntityV1.returnDirtyStateForCollectionItems(projectProviderData.getItems());

        /* See if any of the fields were changed */
        final boolean unsavedDescriptionChanges = !GWTUtilities.compareStrings(tagProviderData.getSelectedItem().getItem()
                .getDescription(), tagProviderData.getDisplayedItem().getItem().getDescription());
        final boolean unsavedNameChanges = !GWTUtilities.compareStrings(tagProviderData.getSelectedItem().getItem().getName(),
                tagProviderData.getDisplayedItem().getItem().getName());

        return unsavedCategoryChanges || unsavedProjectChanges || unsavedDescriptionChanges || unsavedNameChanges;
    }


    
    /**
     * @return true if the categories have any unsaved changes to their tags
     */
    private boolean unsavedProjectChanges() {
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
}
