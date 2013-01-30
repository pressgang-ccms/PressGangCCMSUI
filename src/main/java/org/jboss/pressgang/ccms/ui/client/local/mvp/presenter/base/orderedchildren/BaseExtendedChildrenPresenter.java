package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

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
abstract public class BaseExtendedChildrenPresenter<
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
        implements BaseExtendedChildrenPresenterInterface<T, U, V, W, A, B, C, D, E, F> {

    /**
     * A logger.
     */
    private static final Logger logger = Logger.getLogger(BaseOrderedChildrenComponent.class.getName());
    protected ProviderUpdateData<F> existingProviderData = new ProviderUpdateData<F>();

    /**
     * The ordered children display.
     */
    private BaseExtendedChildrenViewInterface display;

    @Override
    public ProviderUpdateData<F> getExistingProviderData() {
        return existingProviderData;
    }

    @Override
    public void setExistingProviderData(final ProviderUpdateData<F> existingProviderData) {
        this.existingProviderData = existingProviderData;
    }

    public void bind(final int topicId, final String pageId, final String preferencesKey, final BaseExtendedChildrenViewInterface display) {
        if (pageId == null) {
            throw new NullPointerException("BaseExtendedChildrenPresenter.bind(): pageId cannot be null");
        }
        if (preferencesKey == null) {
            throw new NullPointerException("BaseExtendedChildrenPresenter.bind(): preferencesKey cannot be null");
        }
        if (display == null) {
            throw new NullPointerException("BaseExtendedChildrenPresenter.bind(): display cannot be null");
        }

        this.display = display;

        super.bind(topicId, pageId, display);
        display.setPossibleChildrenProvider(generatePossibleChildrenProvider());
        refreshPossibleChildrenDataAndList();
        loadChildSplitResize(preferencesKey);
        bindChildSplitResize(preferencesKey);
    }



    /**
     * Save the size of the split ui component
     *
     * @param preferencesKey The key against which the previous size was saved
     */
    private void bindChildSplitResize(final String preferencesKey) {
        if (preferencesKey == null)
            throw new NullPointerException("preferencesKey cannot be null");

        display.getSplit().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(preferencesKey,
                        display.getSplit().getSplitPosition(display.getPossibleChildrenResultsPanel()) + "");
            }
        });
    }

    /**
     * Restores the size of the child split screen
     *
     * @param preferencesKey The key against which the previous size was saved
     */
    private void loadChildSplitResize(final String preferencesKey) {
        if (preferencesKey == null)
            throw new NullPointerException("preferencesKey cannot be null");

        display.getSplit().setSplitPosition(display.getPossibleChildrenResultsPanel(),
                Preferences.INSTANCE.getInt(preferencesKey, Constants.SPLIT_PANEL_SIZE), false);
    }

    /**
     * Used to bind logic to the selection of an existing child. Optional, as most of the time this won't trigger any action.
     */
    protected void bindExistingChildrenRowClick() {
    }

    @Override
    public void refreshExistingChildList(final W parent) {
        try {
            logger.log(Level.INFO, "ENTER BaseOrderedChildrenComponent.refreshExistingChildList()");

            if (parent == null) {
                throw new NullPointerException("BaseOrderedChildrenComponent.refreshExistingChildList(): parent cannot be null");
            }

            if (display == null) {
                throw new NullPointerException("BaseOrderedChildrenComponent.refreshExistingChildList(): display cannot be null");
            }

            display.setExistingChildrenProvider(generateExistingProvider(parent));
        } finally {
            logger.log(Level.INFO, "EXIT BaseOrderedChildrenComponent.refreshExistingChildList()");
        }
    }
}
