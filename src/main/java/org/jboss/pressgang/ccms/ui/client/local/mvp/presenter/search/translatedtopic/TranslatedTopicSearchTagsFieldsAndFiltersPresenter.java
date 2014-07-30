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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.translatedtopic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseTopicSearchTagsFieldsAndFiltersPresenter;

/**
 * The presenter used to display the search screen, including the child tags, fields, locales and filters
 * presenters.
 */
@Dependent
public class TranslatedTopicSearchTagsFieldsAndFiltersPresenter extends BaseTopicSearchTagsFieldsAndFiltersPresenter {

    /**
     * The presenter used to display the list of filters
     */
    @Inject
    private TranslatedTopicSearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;

    @Inject
    private TranslatedTopicSearchFieldPresenter fieldsPresenter;

    @Inject
    private Display display;

    @Override
    protected BaseSearchFieldPresenter getFieldsPresenter() {
        return fieldsPresenter;
    }

    @Override
    protected BaseSearchFilterResultsAndFilterPresenter getSearchFilterResultsAndFilterPresenter() {
        return searchFilterResultsAndFilterPresenter;
    }

    @Override
    public Display getDisplay() {
        return display;
    }

    public interface Display extends BaseTopicSearchTagsFieldsAndFiltersPresenter.Display {}
}
