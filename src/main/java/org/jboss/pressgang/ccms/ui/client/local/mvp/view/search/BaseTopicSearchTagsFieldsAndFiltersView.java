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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseTopicSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * This is the view that combines all the other search views.
 */
public class BaseTopicSearchTagsFieldsAndFiltersView extends
        BaseTemplateView implements
        BaseTopicSearchTagsFieldsAndFiltersPresenter.Display {

    /**
     * The dialog that is presented when the view is unavailable.
     */
    private final WaitingDialog waiting = new WaitingDialog();

    private final PushButton searchTopics = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.SearchTopics());
    private final PushButton downloadZip = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.DownloadTopicZip());
    private final PushButton downloadCSV = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.DownloadTopicCSV());
    private final PushButton applyBulkTags = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.ApplyBulkTags());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton filters = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.SavedFilters());
    private final PushButton fields = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Fields());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());

    private final Label tagsSearchDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Tags());
    private final Label filtersDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.SavedFilters());
    private final Label fieldsDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Fields());
    private final Label localesDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Locales());

    public BaseTopicSearchTagsFieldsAndFiltersView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Search());

        addActionButton(searchTopics);
        addActionButton(downloadZip);
        addActionButton(downloadCSV);
        addActionButton(applyBulkTags);
        addActionButton(fields, false, true);
        addActionButton(tagsSearch);
        addActionButton(locales);
        addActionButton(filters, false, true);
    }

    @Override
    protected void showWaiting() {
        waiting.center();
    }

    @Override
    protected void hideWaiting() {
        waiting.hide();
    }

    @Override
    @NotNull
    public PushButton getSearchButton() {
        return searchTopics;
    }

    @Override
    @NotNull
    public PushButton getDownloadZip() {
        return downloadZip;
    }

    @Override
    @NotNull
    public PushButton getDownloadCSV() {
        return downloadCSV;
    }

    @Override
    @NotNull
    public PushButton getApplyBulkTags() {
        return applyBulkTags;
    }

    @Override
    @NotNull
    public PushButton getTagsButton() {
        return tagsSearch;
    }

    @Override
    @NotNull
    public PushButton getFilters() {
        return filters;
    }

    @Override
    @NotNull
    public PushButton getFields() {
        return fields;
    }

    @Override
    @NotNull
    public PushButton getLocales() {
        return locales;
    }

    @Override
    @NotNull
    public Label getTagsButtonDownLabel() {
        return tagsSearchDownLabel;
    }

    @Override
    @NotNull
    public Label getFiltersDownLabel() {
        return filtersDownLabel;
    }

    @Override
    @NotNull
    public Label getFieldsDownLabel() {
        return fieldsDownLabel;
    }

    @Override
    @NotNull
    public Label getLocalesDownLabel() {
        return localesDownLabel;
    }
}
