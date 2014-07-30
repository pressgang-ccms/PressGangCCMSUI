/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyCategoryInPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyCategoryInPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.PropertyCategoryFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.UpdateAfterChildModifiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertytag.RESTPropertyTagV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class PropertyTagFilteredResultsAndDetailsPresenter
        extends
        BaseSearchAndEditPresenter<
                RESTPropertyTagV1,
                RESTPropertyTagCollectionV1,
                RESTPropertyTagCollectionItemV1,
                RESTPropertyTagV1DetailsEditor>
        implements BaseTemplatePresenterInterface {


    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     *
     * @author Matthew Casperson
     */
    public interface Display extends BaseSearchAndEditViewInterface<RESTPropertyTagV1, RESTPropertyTagCollectionV1, RESTPropertyTagCollectionItemV1> {
        PushButton getChildren();

        PushButton getDetails();

        PushButton getSave();

        Label getChildrenDown();

        Label getDetailsDown();
    }

    /**
     * The history token used to identify this view.
     */
    public static final String HISTORY_TOKEN = "PropertyTagFilteredResultsAndDetailView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PropertyTagFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    /**
     * An Errai injected instance of a class that implements PropertyTagFilteredResultsPresenter
     */
    @Inject
    private PropertyTagFilteredResultsPresenter filteredResultsComponent;

    /**
     * An Errai injected instance of a class that implements PropertyTagPresenter
     */
    @Inject
    private PropertyTagPresenter resultComponent;

    @Inject
    private PropertyTagCategoryPresenter categoryPresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    protected void go() {
        bindSearchAndEditExtended(queryString);
    }

    @Override
    public void close() {

    }

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void bindSearchAndEditExtended(@NotNull final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTPropertyTagV1> getNewEntityCallback = new GetNewEntityCallback<RESTPropertyTagV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTPropertyTagV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTPropertyTagV1> displayCallback) {
                final RESTCallBack<RESTPropertyTagV1> callback = new RESTCallBack<RESTPropertyTagV1>() {
                    @Override
                    public void success(@NotNull final RESTPropertyTagV1 retValue) {
                        checkArgument(retValue.getPropertyCategories() != null, "The initially retrieved entity should have an expanded categories collection");
                        displayCallback.displayNewEntity(retValue);
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getPropertyTag(selectedEntity.getId()), callback, display);
            }
        };

        filteredResultsComponent.bindExtendedFilteredResults(queryString);
        resultComponent.bindExtended();
        categoryPresenter.bindChildrenExtended();
        super.bindSearchAndEdit(Preferences.PROPERTY_TAG_VIEW_MAIN_SPLIT_WIDTH, resultComponent.getDisplay(), resultComponent.getDisplay(),
                filteredResultsComponent.getDisplay(), filteredResultsComponent, display, display, getNewEntityCallback);

        /* Bind the logic to add and remove possible children */
        categoryPresenter.bindPossibleChildrenListButtonClicks(
                new GetExistingCollectionCallback<RESTPropertyCategoryInPropertyTagV1, RESTPropertyCategoryInPropertyTagCollectionV1, RESTPropertyCategoryInPropertyTagCollectionItemV1>() {
                    @NotNull
                    @Override
                    public RESTPropertyCategoryInPropertyTagCollectionV1 getExistingCollection() {
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyCategories() != null, "The displayed collection item to reference a valid entity and have a valid collection of property catgegories.");

                        return filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyCategories();
                    }
                },
                new AddPossibleChildCallback<RESTPropertyCategoryCollectionItemV1>() {
                    @Override
                    public void createAndAddChild(@NotNull final RESTPropertyCategoryCollectionItemV1 copy) {
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyCategories() != null, "The displayed collection item to reference a valid entity and have a valid collection of property catgegories.");

                        @NotNull final RESTPropertyCategoryInPropertyTagV1 newChild = new RESTPropertyCategoryInPropertyTagV1();
                        newChild.setId(copy.getItem().getId());
                        newChild.setName(copy.getItem().getName());
                        filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyCategories().addNewItem(newChild);
                    }
                },
                new UpdateAfterChildModifiedCallback() {
                    @Override
                    public void updateAfterChildModified() {
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                    /*
                     * refresh the list of possible tags
                     */
                        categoryPresenter.redisplayPossibleChildList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
                    }
                }
        );

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                display.getSave().setEnabled(!readOnly);
                filteredResultsComponent.getDisplay().getCreate().setEnabled(!readOnly);
            }
        });
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
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        /* Get a new collection of tags */
        categoryPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to display the project fields view
         */
        @NotNull final ClickHandler projectDetailsClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(resultComponent.getDisplay());
            }

        };

        /**
         * A click handler used to display the project tags view
         */
        @NotNull final ClickHandler projectTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(categoryPresenter.getDisplay());
            }

        };

        /**
         * A click handler used to save any changes to the project
         */
        @NotNull final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "An item should have been displayed.");
                checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed item should have a valid entity.");
                checkState(filteredResultsComponent.getProviderData().getSelectedItem() != null, "An item should have been selected.");
                checkState(filteredResultsComponent.getProviderData().getSelectedItem().getItem() != null, "The selected item should have a valid entity.");

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = filteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                resultComponent.getDisplay().getDriver().flush();

                final RESTCallBack<RESTPropertyTagV1> callback = new RESTCallBack<RESTPropertyTagV1>() {
                    @Override
                    public void success(@NotNull final RESTPropertyTagV1 retValue) {
                        checkState(filteredResultsComponent.getProviderData().isValid(), "The filtered results data provider should be valid.");

                        retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);
                        retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);

                                /* This project is no longer a new project */
                        filteredResultsComponent.getProviderData().getDisplayedItem().setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);
                        filteredResultsComponent.getDisplay().getProvider().updateRowData(
                                filteredResultsComponent.getProviderData().getStartRow(),
                                filteredResultsComponent.getProviderData().getItems());

                        isReadOnlyMode(new ReadOnlyCallback() {
                            @Override
                            public void readonlyCallback(boolean readOnly) {
                                categoryPresenter.getDisplay().display(filteredResultsComponent.getProviderData().getDisplayedItem()
                                        .getItem(), readOnly);
                            }
                        });

                        categoryPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());

                        updateDisplayWithNewEntityData(wasNewEntity);

                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.SaveSuccess());
                    }
                };



                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

                        final RESTPropertyTagV1 displayedItem = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();
                        final RESTPropertyTagV1 propertyTag = new RESTPropertyTagV1();

                        propertyTag.setId(displayedItem.getId());
                        propertyTag.explicitSetName(displayedItem.getName());
                        propertyTag.explicitSetDescription(displayedItem.getDescription());
                        propertyTag.explicitSetPropertyCategories(displayedItem.getPropertyCategories());
                        propertyTag.explicitSetCanBeNull(displayedItem.getCanBeNull());
                        propertyTag.explicitSetIsUnique(displayedItem.getIsUnique());
                        propertyTag.explicitSetRegex(displayedItem.getRegex());

                        if (wasNewEntity) {
                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createPropertyTag(propertyTag), callback, display);
                        } else {
                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.savePropertyTag(propertyTag), callback, display);
                        }
                    } else {
                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                }
            }
        };

        display.getDetails().addClickHandler(projectDetailsClickHandler);
        display.getChildren().addClickHandler(projectTagsClickHandler);
        display.getSave().addClickHandler(saveClickHandler);
    }

    private void doSearch(final boolean newWindow) {
        if (isOKToProceed()) {
            getEventBus().fireEvent(new PropertyCategoryFilteredResultsAndDetailsViewEvent(filteredResultsComponent.getQuery(), newWindow));
        }
    }

    /**
     * Binds behaviour to the tag search and list view
     */
    @Override
    protected void bindFilteredResultsButtons() {
        filteredResultsComponent.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                doSearch(GWTUtilities.isEventToOpenNewWindow(event));
            }
        });

        final KeyPressHandler searchKeyPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(@NotNull final KeyPressEvent event) {
                if (GWTUtilities.enterKeyWasPressed(event)) {
                    doSearch(false);
                }
            }
        };

        filteredResultsComponent.getDisplay().getDescriptionFilter().addKeyPressHandler(searchKeyPressHandler);
        filteredResultsComponent.getDisplay().getIdFilter().addKeyPressHandler(searchKeyPressHandler);
        filteredResultsComponent.getDisplay().getNameFilter().addKeyPressHandler(searchKeyPressHandler);

        filteredResultsComponent.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                @NotNull final RESTPropertyTagV1 selectedEntity = new RESTPropertyTagV1();
                selectedEntity.setId(Constants.NULL_ID);
                @NotNull final RESTPropertyTagCollectionItemV1 selectedTagWrapper = new RESTPropertyTagCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                @NotNull final RESTPropertyTagV1 displayedEntity = new RESTPropertyTagV1();
                displayedEntity.setId(Constants.NULL_ID);
                displayedEntity.setPropertyCategories(new RESTPropertyCategoryInPropertyTagCollectionV1());
                @NotNull final RESTPropertyTagCollectionItemV1 displayedTagWrapper = new RESTPropertyTagCollectionItemV1(displayedEntity, RESTBaseEntityCollectionItemV1.ADD_STATE);

                filteredResultsComponent.setSelectedItem(selectedTagWrapper);
                filteredResultsComponent.getProviderData().setDisplayedItem(displayedTagWrapper);

                initializeViews();

                switchView(lastDisplayedView == null ? resultComponent.getDisplay() : lastDisplayedView);

                categoryPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
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

        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "An item should have been displayed.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed item should have a valid entity.");
        checkState(filteredResultsComponent.getProviderData().getSelectedItem() != null, "An item should have been selected.");
        checkState(filteredResultsComponent.getProviderData().getSelectedItem().getItem() != null, "The selected item should have a valid entity.");

        final RESTPropertyTagV1 selectedItem = filteredResultsComponent.getProviderData().getSelectedItem().getItem();
        final RESTPropertyTagV1 displayedItem = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();

        return !(stringEqualsEquatingNullWithEmptyString(selectedItem.getName(), displayedItem.getName())
                && stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(selectedItem.getDescription(), displayedItem.getDescription())
                && selectedItem.getCanBeNull() == displayedItem.getCanBeNull()
                && selectedItem.getIsUnique() == displayedItem.getIsUnique()
                && stringEqualsEquatingNullWithEmptyString(selectedItem.getRegex(), displayedItem.getRegex()));
    }

    /**
     * Check to see if there are any added, removed or modified tags in the project.
     *
     * @return true if there are modified tags, false otherwise
     */
    private boolean unsavedCategoryChanges() {
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "An item should have been displayed.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed item should have a valid entity.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyCategories() != null, "The property tag's collection of categories should have been populated.");

        return !filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getPropertyCategories().returnDeletedAddedAndUpdatedCollectionItems().isEmpty();
    }

    /**
     * Called when the selected tag is changed, or the selected view is changed.
     */
    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        this.enableAndDisableActionButtons(displayedView);
    }

    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        this.display.replaceTopActionButton(this.display.getChildrenDown(), this.display.getChildren());
        this.display.replaceTopActionButton(this.display.getDetailsDown(), this.display.getDetails());

        if (displayedView == this.resultComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getDetails(), this.display.getDetailsDown());
        } else if (displayedView == this.categoryPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getChildren(), this.display.getChildrenDown());
        }
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {

        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "An item should have been displayed.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed item should have a valid entity.");

        @NotNull final List<BaseCustomViewInterface<RESTPropertyTagV1>> displayableViews = new ArrayList<BaseCustomViewInterface<RESTPropertyTagV1>>();
        displayableViews.add(resultComponent.getDisplay());
        displayableViews.add(categoryPresenter.getDisplay());

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                for (@NotNull final BaseCustomViewInterface<RESTPropertyTagV1> view : displayableViews) {
                    if (viewIsInFilter(filter, view)) {
                        view.display(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), readOnly);
                    }
                }

                if (viewIsInFilter(filter, categoryPresenter.getDisplay())) {
                    categoryPresenter.displayChildrenExtended(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                            readOnly);
                }
            }
        });
    }
}