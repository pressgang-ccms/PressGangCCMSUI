/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseAuditedEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.Nullable;

/**
 * @param <T> The entity type
 * @param <C> The collection item type for entity A
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
public interface BaseExtendedChildrenViewInterface<
        T extends RESTBaseAuditedEntityV1<?, ?, ?>,
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseAuditedEntityV1<D, E, F>,
        E extends RESTBaseEntityCollectionV1<D, E, F>,
        F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends BaseChildrenViewInterface<T, C, D, E, F> {

    /**
     * @return The table that displays the existing children.
     */
    CellTable<F> getExistingChildrenResults();

    /**
     * @return The pager this is used to page over the table of existing children
     */
    SimplePager getExistingChildrenPager();

    /**
     * @return The panel this is used to holds the table and pager for the existing children
     */
    VerticalPanel getExistingChildrenResultsPanel();

    /**
     * @return The table that lists the existing children
     */
    EnhancedAsyncDataProvider<F> getExistingChildrenProvider();

    /**
     * @param possibleExistingProvider The provider that exposes the existing children
     */
    void setExistingChildrenProvider(final EnhancedAsyncDataProvider<F> possibleExistingProvider);

    /**
     * @return The split panel between the possible children and the existing children
     */
    HandlerSplitLayoutPanel getSplit();

    /**
     * @return The parent entity that holds the existing children
     */
    @Nullable
    T getOriginalEntity();

    /**
     * @param originalEntity The entity that will have the possible children added to it, and that holds the existing
     *                       children that will be modified.
     */
    void setOriginalEntity(final T originalEntity);
}
