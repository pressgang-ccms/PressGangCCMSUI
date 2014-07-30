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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec;

import javax.enterprise.context.Dependent;

import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterFilteredResultsPresenter;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;

@Dependent
public class ContentSpecSearchFilterFilteredResultsPresenter extends BaseSearchFilterFilteredResultsPresenter {
    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "ContentSpecSearchFilterFilteredResultsView";

    @NotNull
    @Override
    public String getQuery() {
        return Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILTER_TYPE_FILTER_VAR + "=" + CommonConstants.FILTER_CONTENT_SPEC;
    }

    @Override
    protected void go() {
        bindExtendedFilteredResults(getQuery());
    }
}
