/*
  Copyright 2011-2014 Red Hat, Inc

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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Logger;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jetbrains.annotations.NotNull;

@Dependent
public class SearchTagPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public interface Display extends BasePopulatedEditorViewInterface<RESTTagCollectionV1, SearchUIProjects, SearchUIProjectsEditor> {
        // Empty interface declaration, similar to UiBinder
        interface SearchPresenterDriver extends SimpleBeanEditorDriver<SearchUIProjects, SearchUIProjectsEditor> {
        }

        void displayExtended(@NotNull final RESTProjectCollectionV1 projectCollection, final RESTFilterV1 filter, final boolean readOnly,
                final boolean showBulkTags);

        void displayExtended(@NotNull final RESTTagCollectionV1 tagCollection, final RESTFilterV1 filter, final boolean readOnly,
                final boolean showBulkTags);

        SearchUIProjects getSearchUIProjects();

        SearchUIProjectsEditor getEditor();
    }

    public static final String HISTORY_TOKEN = "SearchView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchTagPresenter.class.getName());

    @Inject
    private Display display;

    /**
     * The filter that will be used to set the tag's initial state
     */
    private RESTFilterV1 filter;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go() {
        display.setViewShown(true);
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindExtended() {

    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

}
