package org.jboss.pressgang.ccms.ui.client.local.mvp.component.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.CategoriesFilteredResultsAndCategoryViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.category.CategoryViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;

/**
 * The component that adds logic to the category search and edit view.
 * 
 * @author Matthew Casperson
 * 
 */
@Dependent
public class CategoriesFilteredResultsAndCategoryComponent
        extends
        BaseSearchAndEditComponent<CategoryFilteredResultsPresenter.Display, CategoriesFilteredResultsAndCategoryPresenter.Display, RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1, CategoryViewInterface, CategoryPresenter.Display>
        implements CategoriesFilteredResultsAndCategoryPresenter.LogicComponent {

    @Inject
    private HandlerManager eventBus;

    private CategoryTagPresenter.Display tagDisplay;
    private CategoryTagPresenter.LogicComponent tagComponent;

    /**
     * A click handler used to display the category fields view
     */
    private final ClickHandler categoryDetailsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            reInitialiseView(entityPropertiesView);
        }

    };

    /**
     * A click handler used to display the category tags view
     */
    private final ClickHandler categoryTagsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            reInitialiseView(tagDisplay);
        }

    };

    /**
     * A click handler used to save any changes to the category
     */
    private final ClickHandler saveClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {

            /* Was the tag we just saved a new tag? */
            final boolean wasNewEntity = filteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem();

            /* Sync the UI to the underlying object */
            entityPropertiesView.getDriver().flush();

            final RESTCallback<RESTCategoryV1> callback = new BaseRestCallback<RESTCategoryV1, Display>(display,
                    new BaseRestCallback.SuccessAction<RESTCategoryV1, Display>() {
                        @Override
                        public void doSuccessAction(final RESTCategoryV1 retValue, final Display display) {
                            retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);
                            retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);

                            /* This category is no longer a new category */
                            filteredResultsComponent.getProviderData().getDisplayedItem()
                                    .setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                            filteredResultsDisplay.getProvider().updateRowData(
                                    filteredResultsComponent.getProviderData().getStartRow(),
                                    filteredResultsComponent.getProviderData().getItems());

                            displayExistingTagList();
                            tagComponent.getEntityList();

                            updateDisplayAfterSave(wasNewEntity);

                            Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                        }
                    }) {
            };

            if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {

                /*
                 * If this is a new category, it needs to be saved in order to get the tag id to complete the category updates.
                 * Upon success, the categories will be updated.
                 */
                final boolean unsavedTagChanges = unsavedCategoryChanges() || unsavedTagChanges();

                if (unsavedTagChanges) {

                    final RESTCategoryV1 category = new RESTCategoryV1();
                    category.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                    category.explicitSetName(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName());
                    category.explicitSetDescription(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                            .getDescription());
                    category.explicitSetTags(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags());

                    if (wasNewEntity) {
                        RESTCalls.createCategory(callback, category);
                    } else {
                        RESTCalls.saveCategory(callback, category);
                    }
                } else {
                    Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                }
            }
        }
    };

    @Override
    public void bind(final CategoryFilteredResultsPresenter.Display filteredResultsDisplay,
            final CategoryFilteredResultsPresenter.LogicCompnent filteredResultsComponent,
            final CategoryPresenter.Display entityPropertiesView, final CategoryTagPresenter.Display tagDisplay,
            final CategoryTagPresenter.LogicComponent tagComponent,
            final CategoriesFilteredResultsAndCategoryPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        this.tagDisplay = tagDisplay;
        this.tagComponent = tagComponent;
        
        super.bind(Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, entityPropertiesView, entityPropertiesView,
                filteredResultsDisplay, filteredResultsComponent, display, waitDisplay);

        bindTagListButtonClicks();
        bindExistingChildrenRowClick();

    }

    protected void bindExistingChildrenRowClick() {
        tagDisplay.getTagUpButtonColumn().setFieldUpdater(new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

            @Override
            public void update(final int index, final RESTTagInCategoryCollectionItemV1 object, final String value) {
                if (tagComponent.moveTagsUpAndDown(object, false)) {
                    tagDisplay.setExistingChildrenProvider(tagComponent.generateExistingProvider(filteredResultsComponent
                            .getProviderData().getDisplayedItem().getItem()));
                }
            }

        });

        tagDisplay.getTagDownButtonColumn().setFieldUpdater(new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

            /**
             * Swap the sort value for the tag that was selected with the tag below it.
             */
            @Override
            public void update(final int index, final RESTTagInCategoryCollectionItemV1 object, final String value) {
                if (tagComponent.moveTagsUpAndDown(object, true)) {
                    tagDisplay.setExistingChildrenProvider(tagComponent.generateExistingProvider(filteredResultsComponent
                            .getProviderData().getDisplayedItem().getItem()));
                }
            }
        });
    }

    @Override
    protected void newEntitySelected() {
        /* Display the tags that are added to the category */
        displayExistingTagList();

        /* Get a new collection of tags */
        tagComponent.getEntityList();
    }

    /**
     * Refresh the display with the tags that have been assigned to the category
     */
    private void displayExistingTagList() {
        tagDisplay.initialize(this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
        tagDisplay.setExistingChildrenProvider(tagComponent.generateExistingProvider(filteredResultsComponent.getProviderData()
                .getDisplayedItem().getItem()));
    }

    /**
     * Refresh the display with the tags that can be assigned to the category
     */
    private void displayPossibleTagList() {
        tagDisplay.getPossibleChildrenProvider().displayNewFixedList(tagComponent.getPossibleChildrenProviderData().getItems());
    }

    /**
     * Binds behaviour to the tag list buttons
     */
    private void bindTagListButtonClicks() {
        tagDisplay.getPossibleChildrenButtonColumn().setFieldUpdater(new FieldUpdater<RESTTagCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTTagCollectionItemV1 object, final String value) {

                /* find the tag if it exists in the category */
                boolean found = false;
                final RESTTagInCategoryCollectionV1 currentCategoryTags = filteredResultsComponent.getProviderData()
                        .getDisplayedItem().getItem().getTags();
                for (final RESTTagInCategoryCollectionItemV1 tag : currentCategoryTags.getItems()) {
                    /* we've found a matching tag */
                    if (tag.getItem().getId().equals(object.getItem().getId())) {
                        /* Tag was added and then removed */
                        if (tag.returnIsAddItem()) {
                            currentCategoryTags.getItems().remove(tag);
                        }
                        /* Tag existed, was removed and then was added again */
                        else if (tag.returnIsRemoveItem()) {
                            tag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                        }
                        /* Tag existed and was removed */
                        else {
                            tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
                            tag.getItem().setRelationshipSort(0);
                        }

                        found = true;
                        break;
                    }
                }

                /* The tag did not exist, so add it to the collection */
                if (!found) {
                    final RESTTagInCategoryV1 newChild = new RESTTagInCategoryV1();
                    newChild.setId(object.getItem().getId());
                    newChild.setName(object.getItem().getName());
                    newChild.setRelationshipSort(0);

                    currentCategoryTags.addNewItem(newChild);
                }

                /*
                 * refresh the list of tags in the category
                 */
                displayExistingTagList();

                /*
                 * refresh the list of possible tags
                 */
                displayPossibleTagList();
            }
        });

    }

    /**
     * Compare the displayed category (the one that is edited) with the selected category (the one that exists in the collection
     * used to build the category list). If there are unsaved changes, prompt the user.
     * 
     * @return true if the user wants to ignore the unsaved changes, false otherwise
     */
    @Override
    public boolean checkForUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            entityPropertiesView.getDriver().flush();

            if (unsavedCategoryChanges() || unsavedTagChanges()) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }
        return true;
    }

    /**
     * Compare the selected and displayed category, and see if any of the fields have changed
     * 
     * @return true if there are unsaved changes, false otherwise
     */
    private boolean unsavedCategoryChanges() {
        return !(stringEqualsEquatingNullWithEmptyString(filteredResultsComponent.getProviderData().getSelectedItem().getItem()
                .getName(), filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName()) && stringEqualsEquatingNullWithEmptyString(
                filteredResultsComponent.getProviderData().getSelectedItem().getItem().getDescription(),
                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getDescription()));
    }

    /**
     * Check to see if there are any added, removed or modified tags in the category
     * 
     * @return true if there are modified tags, false otherwise
     */
    private boolean unsavedTagChanges() {
        return !filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags()
                .returnDeletedAddedAndUpdatedCollectionItems().isEmpty();
    }

    @Override
    protected void bindActionButtons() {
        for (final CategoryViewInterface view : new CategoryViewInterface[] { entityPropertiesView, tagDisplay }) {
            view.getDetails().addClickHandler(this.categoryDetailsClickHandler);
            view.getChildren().addClickHandler(this.categoryTagsClickHandler);
            view.getSave().addClickHandler(this.saveClickHandler);
        }

    }

    /**
     * Binds behaviour to the tag search and list view
     */
    @Override
    protected void bindFilteredResultsButtons() {
        filteredResultsDisplay.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (checkForUnsavedChanges())
                    eventBus.fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(filteredResultsComponent.getQuery()));
            }
        });

        filteredResultsDisplay.getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTCategoryV1 selectedEntity = new RESTCategoryV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTCategoryCollectionItemV1 selectedTagWrapper = new RESTCategoryCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTCategoryV1 displayedEntity = new RESTCategoryV1();
                displayedEntity.setId(Constants.NULL_ID);
                displayedEntity.setTags(new RESTTagInCategoryCollectionV1());
                final RESTCategoryCollectionItemV1 displayedTagWrapper = new RESTCategoryCollectionItemV1(displayedEntity,
                        RESTBaseCollectionItemV1.ADD_STATE);

                filteredResultsComponent.getProviderData().setSelectedItem(selectedTagWrapper);
                filteredResultsComponent.getProviderData().setDisplayedItem(displayedTagWrapper);

                displayExistingTagList();
                tagComponent.getEntityList();

                reInitialiseView(lastDisplayedView == null ? entityPropertiesView : lastDisplayedView);
            }
        });
    }

    @Override
    protected void reInitialiseView(final CategoryViewInterface displayedView) {

        super.reInitialiseView(displayedView);

        /* Show any wait dialogs from the new view, and update the view with the currently displayed entity */
        if (displayedView != null) {
            displayedView.setViewShown(true);
            displayedView.initialize(this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
        }

        lastDisplayedView = displayedView;
    }
}
