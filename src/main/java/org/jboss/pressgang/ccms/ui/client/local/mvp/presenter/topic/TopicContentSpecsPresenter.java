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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.sort.contentspec.RESTContentSpecCollectionItemIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.contentspec.RESTContentSpecCollectionItemProductSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.contentspec.RESTContentSpecCollectionItemTitleSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.contentspec.RESTContentSpecCollectionItemVersionSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Displays the topic's source urls.
 */
@Dependent
public class TopicContentSpecsPresenter extends BaseChildrenPresenter<RESTTopicV1, RESTContentSpecCollectionItemV1, RESTContentSpecV1,
        RESTContentSpecCollectionV1, RESTContentSpecCollectionItemV1> {

    /**
     * The interface that defines the display used by this presenter.
     */
    public interface Display extends BaseChildrenViewInterface<RESTTopicV1, RESTContentSpecCollectionItemV1, RESTContentSpecV1,
            RESTContentSpecCollectionV1, RESTContentSpecCollectionItemV1> {
        /**
         * @return The column that holds the id.
         */
        Column<RESTContentSpecCollectionItemV1, String> getIdColumn();

        /**
         * @return The column that holds the Content Spec title.
         */
        Column<RESTContentSpecCollectionItemV1, String> getTitleColumn();

        /**
         * @return The column that holds the Content Spec Product
         */
        Column<RESTContentSpecCollectionItemV1, String> getProductColumn();

        /**
         * @return The column that holds the Content Spec Version
         */
        Column<RESTContentSpecCollectionItemV1, String> getVersionColumn();

        /**
         * @return The column that holds the link to the book on docbuilder
         */
        Column<RESTContentSpecCollectionItemV1, String> getDocbuilderColumn();
    }

    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "TopicContentSpecView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TopicContentSpecsPresenter.class.getName());

    /**
     * The view that displays the source urls.
     */
    @Inject
    private Display display;

    /**
     * @return The view that displays the source urls
     */
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTContentSpecCollectionItemV1> generatePossibleChildrenProvider(@Nullable final RESTTopicV1 parent) {
        return new EnhancedAsyncDataProvider<RESTContentSpecCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTContentSpecCollectionItemV1> data) {

                getPossibleChildrenProviderData().setStartRow(data.getVisibleRange().getStart());
                getPossibleChildrenProviderData().setItems(new ArrayList<RESTContentSpecCollectionItemV1>());

                if (parent != null && parent.getContentSpecs_OTM() != null) {
                    getPossibleChildrenProviderData().getItems().addAll(
                            parent.getContentSpecs_OTM().returnExistingAndAddedCollectionItems());
                }

                /*
                    Implement sorting
                */
                final ColumnSortList sortList = display.getPossibleChildrenResults().getColumnSortList();
                if (sortList.size() != 0) {
                    final Column<?, ?> column = sortList.get(0).getColumn();
                    final boolean ascending = sortList.get(0).isAscending();

                    /*
                        Sort the collection
                    */
                    if (column == display.getTitleColumn()) {
                        Collections.sort(getPossibleChildrenProviderData().getItems(),
                                new RESTContentSpecCollectionItemTitleSort(ascending));
                    } else if (column == display.getProductColumn()) {
                        Collections.sort(getPossibleChildrenProviderData().getItems(),
                                new RESTContentSpecCollectionItemProductSort(ascending));
                    } else if (column == display.getVersionColumn()) {
                        Collections.sort(getPossibleChildrenProviderData().getItems(),
                                new RESTContentSpecCollectionItemVersionSort(ascending));
                    } else {
                        Collections.sort(getPossibleChildrenProviderData().getItems(),
                                new RESTContentSpecCollectionItemIDSort(ascending));
                    }
                } else {
                    Collections.sort(getPossibleChildrenProviderData().getItems(),
                            new RESTContentSpecCollectionItemIDSort(true));
                }

                displayNewFixedList(getPossibleChildrenProviderData().getItems());
            }
        };
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    protected void go() {
        bindChildrenExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindChildrenExtended() {
        super.bindChildren(display);
    }

    @Override
    public void displayChildrenExtended(@NotNull final RESTTopicV1 parent, final boolean readOnly) {
        super.displayChildren(parent, readOnly);
    }
}
