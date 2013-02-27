package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyTagInPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyTagInPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyTagInPropertyCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.PropertyCategoryFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.UpdateAfterChildModfiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
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
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertycategory.RESTPropertyCategoryV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.*;

@Dependent
public class PropertyCategoryFilteredResultsAndDetailsPresenter
        extends
        BaseSearchAndEditPresenter<
                    RESTPropertyCategoryV1,
                    RESTPropertyCategoryCollectionV1,
                    RESTPropertyCategoryCollectionItemV1,
                    RESTPropertyCategoryV1DetailsEditor>
        implements BaseTemplatePresenterInterface {


    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     *
     * @author Matthew Casperson
     */
    public interface Display extends BaseSearchAndEditViewInterface<RESTPropertyCategoryV1, RESTPropertyCategoryCollectionV1, RESTPropertyCategoryCollectionItemV1> {
        PushButton getChildren();

        PushButton getDetails();

        PushButton getSave();

        Label getChildrenDown();

        Label getDetailsDown();
    }

    /**
     * The history token used to identify this view.
     */
    public static final String HISTORY_TOKEN = "PropertyCategoryFilteredResultsAndDetailView";

    @Inject
    private HandlerManager eventBus;

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    /**
     * An Errai injected instance of a class that implements ProjectFilteredResultsPresenter.LogicCompnent
     */
    @Inject
    private PropertyCategoryFilteredResultsPresenter filteredResultsComponent;

    /**
     * An Errai injected instance of a class that implements PropertyTagPresenter.LogicComponent
     */
    @Inject
    private PropertyCategoryPresenter resultComponent;

    @Inject
    private PropertyCategoryTagPresenter tagComponent;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindSearchAndEditExtended(ServiceConstants.PROPERTY_CATEGORY_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, final String pageId, final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTPropertyCategoryV1> getNewEntityCallback = new GetNewEntityCallback<RESTPropertyCategoryV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTPropertyCategoryV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTPropertyCategoryV1> displayCallback) {
                final RESTCallback<RESTPropertyCategoryV1> callback = new BaseRestCallback<RESTPropertyCategoryV1, BaseTemplateViewInterface>(display,
                        new BaseRestCallback.SuccessAction<RESTPropertyCategoryV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(@NotNull final RESTPropertyCategoryV1 retValue, @NotNull final BaseTemplateViewInterface display) {
                                displayCallback.displayNewEntity(retValue);
                            }
                        });
                RESTCalls.getPropertyCategory(callback, selectedEntity.getId());
            }
        };


        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        filteredResultsComponent.bindExtendedFilteredResults(ServiceConstants.PROPERTY_CATEGORY_HELP_TOPIC, pageId, queryString);
        resultComponent.bindExtended(ServiceConstants.PROPERTY_CATEGORY_DETAILS_HELP_TOPIC, pageId);
        tagComponent.bindChildrenExtended(ServiceConstants.PROPERTY_CATEGORY_TAGS_HELP_TOPIC, pageId);
        super.bindSearchAndEdit(topicId, pageId, Preferences.PROPERTY_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, resultComponent.getDisplay(), resultComponent.getDisplay(),
                filteredResultsComponent.getDisplay(), filteredResultsComponent, display, display, getNewEntityCallback);

        /* Bind the logic to add and remove possible children */
        tagComponent.bindPossibleChildrenListButtonClicks(
            new GetExistingCollectionCallback<RESTPropertyTagInPropertyCategoryV1, RESTPropertyTagInPropertyCategoryCollectionV1, RESTPropertyTagInPropertyCategoryCollectionItemV1>() {
                @Override
                public RESTPropertyTagInPropertyCategoryCollectionV1 getExistingCollection() {
                    return filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyTags();
                }
            },
            new AddPossibleChildCallback<RESTPropertyTagCollectionItemV1>() {
                @Override
                public void createAndAddChild(@NotNull final RESTPropertyTagCollectionItemV1 copy) {
                    final RESTPropertyTagInPropertyCategoryV1 newChild = new RESTPropertyTagInPropertyCategoryV1();
                    newChild.setId(copy.getItem().getId());
                    newChild.setName(copy.getItem().getName());
                    filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyTags().addNewItem(newChild);
                }
            },
            new UpdateAfterChildModfiedCallback() {
                @Override
                public void updateAfterChidModfied() {
                    /*
                     * refresh the list of possible tags
                     */
                    tagComponent.redisplayPossibleChildList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
                }
            }
        );
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {
        /* Get a new collection of tags */
        tagComponent.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
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

                checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "An item should have been displayed.");
                checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed item should have a valid entity.");

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = filteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                resultComponent.getDisplay().getDriver().flush();

                final RESTCallback<RESTPropertyCategoryV1> callback = new BaseRestCallback<RESTPropertyCategoryV1, Display>(display,
                        new BaseRestCallback.SuccessAction<RESTPropertyCategoryV1, Display>() {
                            @Override
                            public void doSuccessAction(@NotNull final RESTPropertyCategoryV1 retValue, @NotNull final Display display) {
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);

                                /* This project is no longer a new project */
                                filteredResultsComponent.getProviderData().getDisplayedItem().setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                                filteredResultsComponent.getDisplay().getProvider().updateRowData(
                                        filteredResultsComponent.getProviderData().getStartRow(),
                                        filteredResultsComponent.getProviderData().getItems());

                                tagComponent.getDisplay().display(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
                                tagComponent.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());

                                updateDisplayAfterSave(wasNewEntity);

                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        });

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

                        final RESTPropertyCategoryV1 displayedItem = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();
                        final RESTPropertyCategoryV1 propertyTag = new RESTPropertyCategoryV1();

                        propertyTag.setId(displayedItem.getId());
                        propertyTag.explicitSetName(displayedItem.getName());
                        propertyTag.explicitSetDescription(displayedItem.getDescription());
                        propertyTag.explicitSetPropertyTags(displayedItem.getPropertyTags());

                        if (wasNewEntity) {
                            RESTCalls.createPropertyCategory(callback, propertyTag);
                        } else {
                            RESTCalls.savePropertyCategory(callback, propertyTag);
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
                    eventBus.fireEvent(new PropertyCategoryFilteredResultsAndDetailsViewEvent(filteredResultsComponent.getQuery(), GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        filteredResultsComponent.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTPropertyCategoryV1 selectedEntity = new RESTPropertyCategoryV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTPropertyCategoryCollectionItemV1 selectedTagWrapper = new RESTPropertyCategoryCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTPropertyCategoryV1 displayedEntity = new RESTPropertyCategoryV1();
                displayedEntity.setId(Constants.NULL_ID);
                displayedEntity.setPropertyTags(new RESTPropertyTagInPropertyCategoryCollectionV1());
                final RESTPropertyCategoryCollectionItemV1 displayedTagWrapper = new RESTPropertyCategoryCollectionItemV1(displayedEntity, RESTBaseCollectionItemV1.ADD_STATE);

                filteredResultsComponent.getProviderData().setSelectedItem(selectedTagWrapper);
                filteredResultsComponent.getProviderData().setDisplayedItem(displayedTagWrapper);

                initializeViews();

                switchView(lastDisplayedView == null ? resultComponent.getDisplay() : lastDisplayedView);

                tagComponent.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
            }
        });
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            resultComponent.getDisplay().getDriver().flush();

            return (unsavedProjectChanges() || unsavedCategoryChanges());
        }
        return false;
    }

    /**
     * Compare the selected and displayed project, and see if any of the fields have changed.
     *
     * @return true if there are unsaved changes, false otherwise
     */
    private boolean unsavedProjectChanges() {
        checkState(filteredResultsComponent.getProviderData().getSelectedItem() != null, "An item should have been selected");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "An item should have been displayed");

        final RESTPropertyCategoryV1 selectedItem = filteredResultsComponent.getProviderData().getSelectedItem().getItem();
        final RESTPropertyCategoryV1 displayedItem = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();

        return !(stringEqualsEquatingNullWithEmptyString(selectedItem.getName(), displayedItem.getName())
                && stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(selectedItem.getDescription(), displayedItem.getDescription()));
    }

    /**
     * Check to see if there are any added, removed or modified tags in the project.
     *
     * @return true if there are modified tags, false otherwise
     */
    private boolean unsavedCategoryChanges() {
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyTags() != null, "The property collection's collection of tags should have been populated.");

        return !filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty();
    }

    /**
     * Called when the selected tag is changed, or the selected view is changed.
     */
    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {

        this.enableAndDisableActionButtons(displayedView);
        setHelpTopicForView(displayedView);
    }

    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
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

        final List<BaseCustomViewInterface<RESTPropertyCategoryV1>> displayableViews = new ArrayList<BaseCustomViewInterface<RESTPropertyCategoryV1>>();
        displayableViews.add(resultComponent.getDisplay());
        displayableViews.add(tagComponent.getDisplay());

        for (final BaseCustomViewInterface<RESTPropertyCategoryV1> view : displayableViews) {
            if (viewIsInFilter(filter, view)) {
                view.display(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
            }
        }

        if (viewIsInFilter(filter, tagComponent.getDisplay())) {
            tagComponent.displayChildrenExtended(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
        }

    }

    private void setHelpTopicForView(@NotNull final BaseTemplateViewInterface view) {
        if (view == tagComponent.getDisplay()) {
            setHelpTopicId(tagComponent.getHelpTopicId());
        } else if (view == resultComponent.getDisplay()) {
            setHelpTopicId(resultComponent.getHelpTopicId());
        }
    }
}