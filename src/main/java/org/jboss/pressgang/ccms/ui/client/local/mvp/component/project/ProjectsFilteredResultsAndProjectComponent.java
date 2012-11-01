package org.jboss.pressgang.ccms.ui.client.local.mvp.component.project;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.UpdateAfterChildModfiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.ProjectsFilteredResultsAndProjectViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndProjectPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.project.ProjectViewInterface;
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

public class ProjectsFilteredResultsAndProjectComponent
        extends BaseSearchAndEditComponent<
        ProjectFilteredResultsPresenter.Display, 
        ProjectsFilteredResultsAndProjectPresenter.Display, 
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1, 
        ProjectViewInterface, 
        ProjectPresenter.Display>
        implements ProjectsFilteredResultsAndProjectPresenter.LogicComponent {

    @Inject
    private HandlerManager eventBus;

    private ProjectTagPresenter.Display tagDisplay;
    private ProjectTagPresenter.LogicComponent tagComponent;

    @Override
    public void bind(final ProjectFilteredResultsPresenter.Display filteredResultsDisplay,
            final ProjectFilteredResultsPresenter.LogicCompnent filteredResultsComponent,
            final ProjectPresenter.Display entityPropertiesView, final ProjectTagPresenter.Display tagDisplay,
            final ProjectTagPresenter.LogicComponent tagComponent,
            final ProjectsFilteredResultsAndProjectPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        this.tagDisplay = tagDisplay;
        this.tagComponent = tagComponent;

        super.bind(Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, entityPropertiesView, entityPropertiesView,
                filteredResultsDisplay, filteredResultsComponent, display, waitDisplay);

        /* Bind the logic to add and remove possible children */
        tagComponent
                .bindPossibleChildrenListButtonClicks(
                        new GetExistingCollectionCallback<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>() {

                            @Override
                            public RESTTagCollectionV1 getExistingCollection() {
                                return filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags();
                            }

                        }, new AddPossibleChildCallback<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>() {

                            @Override
                            public void createAndAddChild(final RESTTagCollectionItemV1 copy) {
                                final RESTTagV1 newChild = new RESTTagV1();
                                newChild.setId(copy.getItem().getId());
                                newChild.setName(copy.getItem().getName());      
                                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags()
                                        .addItem(newChild);
                            }

                        }, new UpdateAfterChildModfiedCallback() {

                            @Override
                            public void updateAfterChidModfied() {
                                /*
                                 * refresh the list of possible tags
                                 */
                                tagComponent.refreshPossibleChildList();
                            }

                        });
    }

    @Override
    protected void newEntitySelected() {
        /* Get a new collection of tags */
        tagComponent.getEntityList();
    }

        @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to display the project fields view
         */
        final ClickHandler projectDetailsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                reInitialiseView(entityPropertiesView);
            }

        };

        /**
         * A click handler used to display the project tags view
         */
        final ClickHandler projectTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                reInitialiseView(tagDisplay);
            }

        };

        /**
         * A click handler used to save any changes to the project
         */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = filteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                entityPropertiesView.getDriver().flush();

                final RESTCallback<RESTProjectV1> callback = new BaseRestCallback<RESTProjectV1, Display>(display,
                        new BaseRestCallback.SuccessAction<RESTProjectV1, Display>() {
                            @Override
                            public void doSuccessAction(final RESTProjectV1 retValue, final Display display) {
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                                        true);

                                /* This project is no longer a new project */
                                filteredResultsComponent.getProviderData().getDisplayedItem()
                                        .setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                                filteredResultsDisplay.getProvider().updateRowData(
                                        filteredResultsComponent.getProviderData().getStartRow(),
                                        filteredResultsComponent.getProviderData().getItems());

                                tagComponent.getEntityList();

                                updateDisplayAfterSave(wasNewEntity);

                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        }) {
                };

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {

                    /*
                     * If this is a new project, it needs to be saved in order to get the tag id to complete the project
                     * updates. Upon success, the categories will be updated.
                     */
                    final boolean unsavedTagChanges = unsavedProjectChanges() || unsavedTagChanges();

                    if (unsavedTagChanges) {

                        final RESTProjectV1 project = new RESTProjectV1();
                        project.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                        project.explicitSetName(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                .getName());
                        project.explicitSetDescription(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                .getDescription());
                        project.explicitSetTags(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                .getTags());

                        if (wasNewEntity) {
                            RESTCalls.createProject(callback, project);
                        } else {
                            RESTCalls.saveProject(callback, project);
                        }
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                }
            }
        };

        for (final ProjectViewInterface view : new ProjectViewInterface[] { entityPropertiesView, tagDisplay }) {
            view.getDetails().addClickHandler(projectDetailsClickHandler);
            view.getChildren().addClickHandler(projectTagsClickHandler);
            view.getSave().addClickHandler(saveClickHandler);
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
                    eventBus.fireEvent(new ProjectsFilteredResultsAndProjectViewEvent(filteredResultsComponent.getQuery()));
            }
        });

        filteredResultsDisplay.getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTProjectV1 selectedEntity = new RESTProjectV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTProjectCollectionItemV1 selectedTagWrapper = new RESTProjectCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTProjectV1 displayedEntity = new RESTProjectV1();
                displayedEntity.setId(Constants.NULL_ID);
                displayedEntity.setTags(new RESTTagCollectionV1());
                final RESTProjectCollectionItemV1 displayedTagWrapper = new RESTProjectCollectionItemV1(displayedEntity,
                        RESTBaseCollectionItemV1.ADD_STATE);

                filteredResultsComponent.getProviderData().setSelectedItem(selectedTagWrapper);
                filteredResultsComponent.getProviderData().setDisplayedItem(displayedTagWrapper);

                tagComponent.getEntityList();

                reInitialiseView(lastDisplayedView == null ? entityPropertiesView : lastDisplayedView);
            }
        });
    }

    /**
     * Compare the displayed project (the one that is edited) with the selected project (the one that exists in the collection
     * used to build the project list). If there are unsaved changes, prompt the user.
     * 
     * @return true if the user wants to ignore the unsaved changes, false otherwise
     */
    @Override
    public boolean checkForUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            entityPropertiesView.getDriver().flush();

            if (unsavedProjectChanges() || unsavedTagChanges()) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }
        return true;
    }

    /**
     * Compare the selected and displayed project, and see if any of the fields have changed
     * 
     * @return true if there are unsaved changes, false otherwise
     */
    private boolean unsavedProjectChanges() {
        return !(stringEqualsEquatingNullWithEmptyString(filteredResultsComponent.getProviderData().getSelectedItem().getItem()
                .getName(), filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName()) && stringEqualsEquatingNullWithEmptyString(
                filteredResultsComponent.getProviderData().getSelectedItem().getItem().getDescription(),
                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getDescription()));
    }

    /**
     * Check to see if there are any added, removed or modified tags in the project
     * 
     * @return true if there are modified tags, false otherwise
     */
    private boolean unsavedTagChanges() {
        return !filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags()
                .returnDeletedAddedAndUpdatedCollectionItems().isEmpty();
    }

    @Override
    protected void reInitialiseView(final ProjectViewInterface displayedView) {

        super.reInitialiseView(displayedView);

        /* Show any wait dialogs from the new view, and update the view with the currently displayed entity */
        if (displayedView != null) {
            displayedView.setViewShown(true);
            displayedView.initialize(this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
        }

        lastDisplayedView = displayedView;
    }
}