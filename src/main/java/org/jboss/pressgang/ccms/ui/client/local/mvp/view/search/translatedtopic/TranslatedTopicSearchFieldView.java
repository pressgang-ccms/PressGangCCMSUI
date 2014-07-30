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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search.translatedtopic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.translatedtopic.TranslatedTopicSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.topic.TranslatedTopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TranslatedTopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

public class TranslatedTopicSearchFieldView extends BaseTemplateView implements TranslatedTopicSearchFieldPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final SearchFieldPresenterDriver driver = GWT.create(SearchFieldPresenterDriver.class);
    /**
     * The UI hierarchy
     */
    private final TranslatedTopicSearchUIFields topicSearchUIFields = new TranslatedTopicSearchUIFields();

    private final PushButton searchTopics = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton filters = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.SavedFilters());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());
    private final Label fields = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Fields());

    @Override
    public SearchFieldPresenterDriver getDriver() {
        return driver;
    }

    @NotNull
    @Override
    public TranslatedTopicSearchUIFields getFields() {
        return topicSearchUIFields;
    }

    @Override
    public PushButton getSearchButton() {
        return searchTopics;
    }

    @Override
    public PushButton getTagsButton() {
        return tagsSearch;
    }

    @Override
    public PushButton getFiltersButton() {
        return filters;
    }

    @Override
    public PushButton getLocales() {
        return locales;
    }

    public TranslatedTopicSearchFieldView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchFields());

        /* Build the action bar icons */
        addActionButton(searchTopics);
        addActionButton(fields, true);
        addActionButton(tagsSearch);
        addActionButton(locales);
        addActionButton(filters, true);
    }

    @Override
    public void display(final RESTFilterV1 filter, final boolean readOnly) {
        topicSearchUIFields.initialize(filter);
        /* SearchUIProjectsEditor is a grid */
        final TranslatedTopicSearchFieldUIEditor editor = new TranslatedTopicSearchFieldUIEditor();
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topicSearchUIFields);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }


}
