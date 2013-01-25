package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren.BaseOrderedChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.category.CategoryViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * The presenter used to add logic to the category tag view.
 */
@Dependent
public class CategoryTagPresenter
        extends BaseOrderedChildrenComponent<CategoryTagPresenter.Display,
        RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1,
        RESTCategoryV1,
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,
        RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>
        implements TemplatePresenter {

    /**
     * This history token.
     */
    public static final String HISTORY_TOKEN = "CategoryTagView";
    /**
     * A logger.
     */
    private static final Logger logger = Logger.getLogger(CategoryTagPresenter.class.getName());
    /**
     * The id of the category to display.
     */
    private Integer entityId;
    /**
     * The category tag view.
     */
    @Inject
    private Display display;

    /**
     * The category tag view.
     */
    public Display getDisplay() {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public CategoryTagPresenter() {

    }

    @Override
    public final void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, this.getDisplay());
        bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, this.getDisplay());
    }

    public final void bind(final int topicId, final String pageId, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(topicId, pageId, display, waitDisplay);
        display.initialize(null, false);
    }

    @Override
    public final void parseToken(final String historyToken) {
        try {
            entityId = Integer.parseInt(GWTUtilities.removeHistoryToken(HISTORY_TOKEN, historyToken));
        } catch (final NumberFormatException ex) {
            entityId = null;
        }
    }

    @Override
    public void refreshPossibleChildrenDataAndList() {
        try {
            logger.log(Level.INFO, "ENTER CategoryTagPresenter.refreshPossibleChildrenDataAndList()");

            final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTTagCollectionV1>() {
                @Override
                public void begin() {
                    getDisplay().addWaitOperation();
                }

                @Override
                public void generalException(final Exception ex) {
                    logger.log(Level.SEVERE, "RESTCallback.generalException()\n\tException: " + ex.toString());
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    getDisplay().removeWaitOperation();
                }

                @Override
                public void success(final RESTTagCollectionV1 retValue) {
                    try {
                        logger.log(Level.INFO, "RESTCallback.success(). retValue.getSize(): " + retValue.getSize() + " retValue.getItems().size(): " + retValue.getItems().size());
                        /* Zero results can be a null list */
                        providerData.setStartRow(0);
                        providerData.setItems(retValue.getItems());
                        providerData.setSize(retValue.getItems().size());

                        /* Refresh the list */
                        getDisplay().getPossibleChildrenProvider().displayNewFixedList(providerData.getItems());

                    } finally {
                        getDisplay().removeWaitOperation();
                    }
                }

                @Override
                public void failed(final Message message, final Throwable throwable) {
                    getDisplay().removeWaitOperation();
                    logger.log(Level.SEVERE, "RESTCallback.failed()\n\tMessage: " + message.toString() + "\n\t Throwable: " + throwable.toString());
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                }
            };

            /* Redisplay the loading widget. updateRowCount(0, false) is used to display the cell table loading widget. */
            providerData.reset();
            this.getDisplay().getPossibleChildrenProvider().resetProvider();

            RESTCalls.getTags(callback);
        } finally {
            logger.log(Level.INFO, "EXIT CategoryTagPresenter.refreshPossibleChildrenDataAndList()");
        }
    }

    @Override
    public final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> generatePossibleChildrenProvider() {

        return new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagCollectionItemV1> display) {

                providerData.setStartRow(display.getVisibleRange().getStart());

                if (providerData.getItems() != null) {
                    displayNewFixedList(providerData.getItems());
                } else {
                    resetProvider();
                }

            }
        };
    }

    @Override
    public final EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> generateExistingProvider(final RESTCategoryV1 entity) {
        return new EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagInCategoryCollectionItemV1> display) {
                getExistingProviderData().setStartRow(display.getVisibleRange().getStart());
                getExistingProviderData().setItems(new ArrayList<RESTTagInCategoryCollectionItemV1>());

                /* Zero results can be a null list. Also selecting a new tag will reset getProviderData(). */
                if (entity != null && entity.getTags() != null) {
                    /* Don't display removed tags */
                    for (final RESTTagInCategoryCollectionItemV1 tagInCategory : entity.getTags()
                            .returnExistingAddedAndUpdatedCollectionItems()) {
                        getExistingProviderData().getItems().add(tagInCategory);
                    }
                }

                Collections.sort(getExistingProviderData().getItems(), new RESTTagCategoryCollectionItemV1SortComparator());

                displayNewFixedList(getExistingProviderData().getItems());
            }
        };
    }

    /**
     * The interface that defines the category tag view.
     */
    public interface Display extends
            BaseOrderedChildrenViewInterface<
                    RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1,
                    RESTCategoryV1,
                    RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,
                    RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>,
            CategoryViewInterface {


    }

}
