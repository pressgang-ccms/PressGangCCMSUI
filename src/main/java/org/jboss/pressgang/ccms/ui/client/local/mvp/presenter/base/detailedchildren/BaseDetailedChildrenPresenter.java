/*
  Copyright 2011-2014 Red Hat

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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.detailedchildren;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
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
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseEntityCollectionV1<D, E, F>,
        F extends RESTBaseEntityCollectionItemV1<D, E, F>>
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
     */
    public void bindChildrenExtended() {
        throw new UnsupportedOperationException("bindChildrenExtended() is not supported. Use bindDetailedChildren() instead.");
    }

    /**
     * Initialize this presenter.
     *
     * @param preferencesKey The key that saves the width of the split panel which separates the childen and their details.
     * @param display        The view to display the wait dialog.
     */
    protected void bindDetailedChildren(@NotNull final String preferencesKey, @NotNull final BaseExtendedChildrenViewInterface display) {

        this.display = display;

        super.bindChildren(display);
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
                try {
                    LOGGER.log(Level.INFO, "ENTER BaseOrderedChildrenPresenter.refreshExistingChildList() ResizeHandler.onResize()");
                    Preferences.INSTANCE.saveSetting(preferencesKey,
                            BaseDetailedChildrenPresenter.this.display.getSplit().getSplitPosition(BaseDetailedChildrenPresenter.this.display.getPossibleChildrenResultsPanel()) + "");
                } finally {
                    LOGGER.log(Level.INFO, "EXIT BaseOrderedChildrenPresenter.refreshExistingChildList() ResizeHandler.onResize()");
                }
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
                Preferences.INSTANCE.getDouble(preferencesKey, Constants.SPLIT_PANEL_SIZE), false);
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
