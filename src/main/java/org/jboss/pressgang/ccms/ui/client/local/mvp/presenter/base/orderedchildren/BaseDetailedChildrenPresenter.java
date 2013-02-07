package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T> The type of the entity being edited by the view
 * @param <U> The collection type of T
 * @param <V> The collection Item type of T
 * @param <W> The type of the parent of A and D
 * @param <A> The type of the potential children
 * @param <B> The collection type of A
 * @param <C> The collection item type of A
 * @param <D> The type of the existing children
 * @param <E> The collection type of D
 * @param <F> The collection item type of D
 * @author Matthew Casperson
 */
abstract public class BaseDetailedChildrenPresenter<
            T extends RESTBaseEntityV1<T, U, V>,
            U extends RESTBaseCollectionV1<T, U, V>,
            V extends RESTBaseCollectionItemV1<T, U, V>,
            W extends RESTBaseEntityV1<?, ?, ?>,
            A extends RESTBaseEntityV1<A, B, C>,
            B extends RESTBaseCollectionV1<A, B, C>,
            C extends RESTBaseCollectionItemV1<A, B, C>,
            D extends RESTBaseEntityV1<D, E, F>,
            E extends RESTBaseCollectionV1<D, E, F>,
            F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseChildrenComponent<T, U, V, A, B, C, D, E, F>
        implements BaseDetailedChildrenPresenterInterface<T, U, V, W, A, B, C, D, E, F> {

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BaseOrderedChildrenComponent.class.getName());

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
    public final ProviderUpdateData<F> getExistingProviderData() {
        return this.existingProviderData;
    }

    /**
     * An empty implementation of the extended bind method. Classes extending BaseDetailedChildrenPresenter should implement
     * bindChildrenExtended().
     * @param topicId
     * @param pageId The history token of the page
     * @param parent A reference to the entity being edited
     */
    public final void bindChildrenExtended(final int topicId, final String pageId) {
        throw new UnsupportedOperationException("bindChildrenExtended() is not supported. Use bindDetailedChildren() instead.");
    }

    protected final void bindDetailedChildren(final int topicId, @NotNull final String pageId, @NotNull final String preferencesKey, @NotNull final BaseExtendedChildrenViewInterface display) {

        this.display = display;

        super.bindChildren(topicId, pageId, display);
        loadChildSplitResize(preferencesKey);
        bindChildSplitResize(preferencesKey);
    }

    @Override
    public final void displayChildrenExtended(@NotNull final T parent, final boolean readOnly) {
        throw new UnsupportedOperationException("displayChildrenExtended() is not supported. Use displayDetailedChildren() instead.");
    }

    protected final void displayDetailedChildren(@NotNull final T parent, final boolean readOnly) {
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
    public final void refreshExistingChildList(@NotNull final W parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseOrderedChildrenComponent.refreshExistingChildList()");

            if (this.display == null) {
                throw new NullPointerException("BaseOrderedChildrenComponent.refreshExistingChildList(): display cannot be null");
            }

            this.display.setExistingChildrenProvider(generateExistingProvider(parent));
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseOrderedChildrenComponent.refreshExistingChildList()");
        }
    }
}
