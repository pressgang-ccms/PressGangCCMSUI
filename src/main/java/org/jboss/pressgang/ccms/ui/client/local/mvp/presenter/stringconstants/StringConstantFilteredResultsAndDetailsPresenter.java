package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ProjectsFilteredResultsAndProjectViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.StringConstantFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.UpdateAfterChildModfiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.stringconstant.RESTStringConstantV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

/**
 * The presenter used to display a list of string constants and their details.
 */
@Dependent
public class StringConstantFilteredResultsAndDetailsPresenter extends
        BaseSearchAndEditComponent<
                RESTStringConstantV1,
                RESTStringConstantCollectionV1,
                RESTStringConstantCollectionItemV1,
                RESTStringConstantV1DetailsEditor>
implements BaseTemplatePresenterInterface {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "StringConstantFilteredResultsAndDetailsView";

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    @Inject private StringConstantFilteredResultsPresenter stringConstantFilteredResultsPresenter;
    @Inject private StringConstantPresenter stringConstantPresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Inject
    private HandlerManager eventBus;


    @Override
    protected void loadAdditionalDisplayedItemData() {
        /*
            Do Nothing.
         */
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        stringConstantPresenter.getDisplay().display(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false);
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to save any changes to the project
         */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Sync the UI to the underlying object */
                stringConstantPresenter.getDisplay().getDriver().flush();

                final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, Display>(display,
                        new BaseRestCallback.SuccessAction<RESTStringConstantV1, Display>() {
                            @Override
                            public void doSuccessAction(final RESTStringConstantV1 retValue, final Display display) {
                                retValue.cloneInto(stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);
                                retValue.cloneInto(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);

                                /* This project is no longer a new project */
                                stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                                stringConstantFilteredResultsPresenter.getDisplay().getProvider().updateRowData(
                                        stringConstantFilteredResultsPresenter.getProviderData().getStartRow(),
                                        stringConstantFilteredResultsPresenter.getProviderData().getItems());

                                updateDisplayAfterSave(wasNewEntity);

                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        });

                if (stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {

                    if (hasUnsavedChanges()) {

                        final RESTStringConstantV1 project = new RESTStringConstantV1();
                        project.setId(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
                        project.explicitSetName(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName());
                        project.explicitSetValue(stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getValue());

                        if (wasNewEntity) {
                            RESTCalls.createStringConstant(callback, project);
                        } else {
                            RESTCalls.updateStringConstant(callback, project);
                        }
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                }
            }
        };

        display.getSave().addClickHandler(saveClickHandler);
    }

    @Override
    protected void bindFilteredResultsButtons() {
        stringConstantFilteredResultsPresenter.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new StringConstantFilteredResultsAndDetailsViewEvent(stringConstantFilteredResultsPresenter.getQuery(),
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        stringConstantFilteredResultsPresenter.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTStringConstantV1 selectedEntity = new RESTStringConstantV1();
                selectedEntity.setId(Constants.NULL_ID);
                final RESTStringConstantCollectionItemV1 selectedTagWrapper = new RESTStringConstantCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTStringConstantV1 displayedEntity = new RESTStringConstantV1();
                displayedEntity.setId(Constants.NULL_ID);
                final RESTStringConstantCollectionItemV1 displayedTagWrapper = new RESTStringConstantCollectionItemV1(displayedEntity, RESTBaseCollectionItemV1.ADD_STATE);

                stringConstantFilteredResultsPresenter.getProviderData().setSelectedItem(selectedTagWrapper);
                stringConstantFilteredResultsPresenter.getProviderData().setDisplayedItem(displayedTagWrapper);

                initializeViews();

                switchView(lastDisplayedView == null ? stringConstantPresenter.getDisplay() : lastDisplayedView);
            }
        });
    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, final String pageId, final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTStringConstantV1> getNewEntityCallback = new GetNewEntityCallback<RESTStringConstantV1>() {

            @Override
            public void getNewEntity(final RESTStringConstantV1 selectedEntity, final DisplayNewEntityCallback<RESTStringConstantV1> displayCallback) {
                /*
                    There is nothing additional to load here, so just return the selected entity.
                 */
                displayCallback.displayNewEntity(selectedEntity);
            }
        };


        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        stringConstantFilteredResultsPresenter.bindExtendedFilteredResults(ServiceConstants.STRING_CONSTANT_HELP_TOPIC, pageId, queryString);
        stringConstantPresenter.bindExtended(ServiceConstants.STRING_CONSTANT_DETAILS_HELP_TOPIC, pageId);
        super.bindSearchAndEdit(topicId, pageId, Preferences.STRING_CONSTANTS_VIEW_MAIN_SPLIT_WIDTH, stringConstantPresenter.getDisplay(), stringConstantPresenter.getDisplay(),
                stringConstantFilteredResultsPresenter.getDisplay(), stringConstantFilteredResultsPresenter, display, display, getNewEntityCallback);
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindSearchAndEditExtended(ServiceConstants.STRING_CONSTANT_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
            stringConstantPresenter.getDisplay().getDriver().flush();

            return !(stringEqualsEquatingNullWithEmptyString(stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem().getName(),
                    stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName())
                    && stringEqualsEquatingNullWithEmptyString(stringConstantFilteredResultsPresenter.getProviderData().getSelectedItem().getItem().getValue(),
                    stringConstantFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getValue()));
        }
        return false;
    }

    public interface Display extends BaseSearchAndEditViewInterface<
                RESTStringConstantV1,
                RESTStringConstantCollectionV1,
                RESTStringConstantCollectionItemV1> {
        PushButton getSave();
    }
}
