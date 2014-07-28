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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic;

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
public class TopicSearchTagsFieldsAndFiltersPresenter extends BaseTopicSearchTagsFieldsAndFiltersPresenter {

    /**
     * The presenter used to display the list of filters
     */
    @Inject
    private TopicSearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;

    @Inject
    private TopicSearchFieldPresenter fieldsPresenter;

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
