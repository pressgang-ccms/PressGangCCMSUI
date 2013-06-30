package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.detailedchildren;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.BaseOrderedChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;

/**
 * @see BaseDetailedChildrenPresenterInterface
 */
abstract public class BaseDetailedChildrenPresenter<
        T extends RESTBaseEntityV1<?, ?, ?>,
        W extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseCollectionV1<D, E, F>,
        F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseChildrenPresenter<T, C, D, E, F>
        implements BaseDetailedChildrenPresenterInterface<T, W, C, D, E, F> {

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BaseOrderedChildrenPresenter.class.getName());

    /**
     * The provider data for the list of existing children.
     */
    private final ProviderUpdateData<F> existingProviderData = new ProviderUpdateData<F>();

    /**
     * The ordered children display.
     */
    private BaseExtendedChildrenViewInterface display;

    /**
     * @return The provider that exposes the existing children children of an entity.
     */
    @Override
    @NotNull
    public ProviderUpdateData<F> getExistingProviderData() {
        return this.existingProviderData;
    }

    /**
     * An empty implementation of the extended bind method. Classes extending BaseDetailedChildrenPresenter should implement
     * bindChildrenExtended().
     *
     * @param topicId The help topic for the view
     * @param pageId  The history token of the page
     */
    public void bindChildrenExtended(final int topicId, @NotNull final String pageId) {
        throw new UnsupportedOperationException("bindChildrenExtended() is not supported. Use bindDetailedChildren() instead.");
    }

    /**
     * Initialize this presenter.
     *
     * @param topicId        The help topic id for this view.
     * @param pageId         The id for this page, used for the survey link.
     * @param preferencesKey The key that saves the width of the split panel which separates the childen and their details.
     * @param display        The view to display the wait dialog.
     */
    protected void bindDetailedChildren(final int topicId, @NotNull final String pageId, @NotNull final String preferencesKey, @NotNull final BaseExtendedChildrenViewInterface display) {

        this.display = display;

        super.bindChildren(topicId, pageId, display);
        loadChildSplitResize(preferencesKey);
        bindChildSplitResize(preferencesKey);
    }

    @Override
    public void displayChildrenExtended(@NotNull final T parent, final boolean readOnly) {
        throw new UnsupportedOperationException("displayChildrenExtended() is not supported. Use displayDetailedChildren() instead.");
    }

    protected void displayDetailedChildren(@NotNull final T parent, final boolean readOnly) {
        super.displayChildren(parent, readOnly);
    }

    /**
     * Used to bind logic to the selection of an existing child. Optional, as most of the time this won't trigger any action.
     */
    protected void initLifecycleBindExistingChildrenRowClick(@NotNull final RESTTagV1 editingParent) {
    }

    /**
     * Save the size of the split ui component
     *
     * @param preferencesKey The key against which the previous size was saved
     */
    private void bindChildSplitResize(@NotNull final String preferencesKey) {

        this.display.getSplit().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(preferencesKey,
                        BaseDetailedChildrenPresenter.this.display.getSplit().getSplitPosition(BaseDetailedChildrenPresenter.this.display.getPossibleChildrenResultsPanel()) + "");
            }
        });
    }

    /**
     * Restores the size of the child split screen
     *
     * @param preferencesKey The key against which the previous size was saved
     */
    private void loadChildSplitResize(@NotNull final String preferencesKey) {

        this.display.getSplit().setSplitPosition(this.display.getPossibleChildrenResultsPanel(),
                Preferences.INSTANCE.getInt(preferencesKey, Constants.SPLIT_PANEL_SIZE), false);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void refreshExistingChildList(@NotNull final W parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseOrderedChildrenPresenter.refreshExistingChildList()");

            checkState(display != null, "display cannot be null");

            this.display.setExistingChildrenProvider(generateExistingProvider(parent));
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseOrderedChildrenPresenter.refreshExistingChildList()");
        }
    }
}
