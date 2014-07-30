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

import javax.inject.Inject;
import java.util.List;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUILocaleEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.locale.SearchUILocales;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter used to display the locales in a search.
 */
public class SearchLocalePresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "SearchLocaleView";

    // Empty interface declaration, similar to UiBinder
    public interface LocalesPresenterDriver extends SimpleBeanEditorDriver<SearchUILocales, SearchUILocaleEditor> {
    }

    /**
     * The display.
     */
    @Inject
    private Display display;

    /**
     * @return The display
     */
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        /*
            Nothing to do
        */
    }

    @Override
    public void bindExtended() {

    }

    @Override
    protected void go() {
        bindExtended();
    }

    @Override
    public void close() {

    }

    public interface Display extends BaseTemplateViewInterface, BasePopulatedEditorViewInterface<List<String>, SearchUILocales, SearchUILocaleEditor> {
        SearchUILocales getSearchUILocales();
    }
}
