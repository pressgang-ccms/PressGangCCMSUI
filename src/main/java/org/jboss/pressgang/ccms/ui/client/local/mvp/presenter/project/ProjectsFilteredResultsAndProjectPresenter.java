package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ProjectsFilteredResultsAndProjectViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.UpdateAfterChildModfiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.projectview.RESTProjectV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.*;

@Dependent
public class ProjectsFilteredResultsAndProjectPresenter
        extends
        BaseSearchAndEditComponent<
                ProjectFilteredResultsPresenter.Display,
                RESTProjectV1,
                RESTProjectCollectionV1,
                RESTProjectCollectionItemV1,
                ProjectPresenter.Display,
                RESTProjectV1BasicDetailsEditor>
        implements BaseTemplatePresenterInterface {

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     *
     * @author Matthew Casperson
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {
        PushButton getChildren();

        PushButton getDetails();

        PushButton getSave();

        Label getChildrenDown();

        Label getDetailsDown();
    }

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "ProjectsFilteredResultsAndProjectView";

    @Inject
    private HandlerManager eventBus;

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    /** An Errai injected instance of a class that implements ProjectFilteredResultsPresenter.LogicCompnent */
    @Inject
    private ProjectFilteredResultsPresenter filteredResultsComponent;

    /** An Errai injected instance of a class that implements ProjectPresenter.LogicComponent */
    @Inject
    private ProjectPresenter resultComponent;

    @Inject
    private ProjectTagPresenter tagComponent;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    public void go(@NotNull final HasWidgets container) {


        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTProjectV1> getNewEntityCallback = new GetNewEntityCallback<RESTProjectV1>() {

            @Override
            public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTProjectV1> displayCallback) {
                final RESTCallback<RESTProjectV1> callback = new BaseRestCallback<RESTProjectV1, BaseTemplateViewInterface>(display,
                        new BaseRestCallback.SuccessAction<RESTProjectV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(final RESTProjectV1 retValue, final BaseTemplateViewInterface display) {
                                displayCallback.displayNewEntity(retValue);
                            }
                        });
                RESTCalls.getProject(callback, id);
            }
        };

        clearContainerAndAddTopLevelPanel(container, display);
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        filteredResultsComponent.bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
        resultComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        tagComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        super.bindSearchAndEdit(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, Preferences.PROJECT_VIEW_MAIN_SPLIT_WIDTH, resultComponent.getDisplay(), resultComponent.getDisplay(),
                filteredResultsComponent.getDisplay(), filteredResultsComponent, display, display, getNewEntityCallback);

        /* Bind the logic to add and remove possible children */
        tagComponent.bindPossibleChildrenListButtonClicks(
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
                        filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags().addNewItem(newChild);
                    }

                }, new UpdateAfterChildModfiedCallback() {

                    @Override
                    public void updateAfterChidModfied() {
                        /*
                         * refresh the list of possible tags
                         */
                        tagComponent.refreshPossibleChildList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
                    }

                });
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }

        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("entityIds")) {
                    this.filteredResultsComponent.getDisplay().getIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("entityName")) {
                    this.filteredResultsComponent.getDisplay().getNameFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("entityDesc")) {
                    this.filteredResultsComponent.getDisplay().getDescriptionFilter().setText(queryElements[1]);
                }
            }
        }
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {
        /* Get a new collection of tags */
        tagComponent.refreshPossibleChildrenDataAndList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to display the project fields view
         */
        final ClickHandler projectDetailsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                switchView(resultComponent.getDisplay());
            }

        };

        /**
         * A click handler used to display the project tags view
         */
        final ClickHandler projectTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                switchView(tagComponent.getDisplay());
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
                resultComponent.getDisplay().getDriver().flush();

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
                                filteredResultsComponent.getDisplay().getProvider().updateRowData(
                                        filteredResultsComponent.getProviderData().getStartRow(),
                                        filteredResultsComponent.getProviderData().getItems());

                                tagComponent.getDisplay().display(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
                                tagComponent.refreshPossibleChildrenDataAndList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());

                                updateDisplayAfterSave(wasNewEntity);

                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        });

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

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

        display.getDetails().addClickHandler(projectDetailsClickHandler);
        display.getChildren().addClickHandler(projectTagsClickHandler);
        display.getSave().addClickHandler(saveClickHandler);
    }

    /**
     * Binds behaviour to the tag search and list view
     */
    @Override
    protected void bindFilteredResultsButtons() {
        filteredResultsComponent.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ProjectsFilteredResultsAndProjectViewEvent(filteredResultsComponent.getQuery(),
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        filteredResultsComponent.getDisplay().getCreate().addClickHandler(new ClickHandler() {
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

                initializeViews();

                switchView(lastDisplayedView == null ? resultComponent.getDisplay() : lastDisplayedView);

                tagComponent.refreshPossibleChildrenDataAndList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
            }
        });
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            resultComponent.getDisplay().getDriver().flush();

            return (unsavedProjectChanges() || unsavedTagChanges());
        }
        return false;
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

    /**
     * Called when the selected tag is changed, or the selected view is changed.
     */
    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {

        this.enableAndDisableActionButtons(displayedView);
    }

    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView)
    {
        this.display.replaceTopActionButton(this.display.getChildrenDown(), this.display.getChildren());
        this.display.replaceTopActionButton(this.display.getDetailsDown(), this.display.getDetails());

        if (displayedView == this.resultComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getDetails(), this.display.getDetailsDown());
        } else if (displayedView == this.tagComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getChildren(), this.display.getChildrenDown());
        }
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {

        final List<BaseCustomViewInterface<RESTProjectV1>> displayableViews = new ArrayList<BaseCustomViewInterface<RESTProjectV1>>();
        displayableViews.add(resultComponent.getDisplay());
        displayableViews.add(tagComponent.getDisplay());

        for (final BaseCustomViewInterface<RESTProjectV1> view : displayableViews) {
            if (viewIsInFilter(filter, view)) {
                view.display(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
            }
        }

        if (viewIsInFilter(filter, tagComponent.getDisplay())) {
            tagComponent.displayChildrenExtended(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
        }

    }
}