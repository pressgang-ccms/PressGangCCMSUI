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

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

public class SearchFilterResultsAndFilterView extends BaseSearchAndEditView<
        RESTFilterV1,
        RESTFilterCollectionV1,
        RESTFilterCollectionItemV1>
        implements BaseSearchFilterResultsAndFilterPresenter.Display {

    private final PushButton create = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Create());
    private final PushButton overwrite = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Overwrite());
    private final PushButton load = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Load());
    private final PushButton loadAndSearch = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.LoadAndSearch());

    public SearchFilterResultsAndFilterView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SavedFilters(), false);
        addActionButton(getCreate());
        addActionButton(getLoad());
        addActionButton(getLoadAndSearch());
        addActionButton(getOverwrite());

        load.setEnabled(false);
        overwrite.setEnabled(false);
        loadAndSearch.setEnabled(false);

        super.initialize(true);
    }

    @Override
    @NotNull
    public PushButton getCreate() {
        return create;
    }

    @Override
    @NotNull
    public PushButton getOverwrite() {
        return overwrite;
    }

    @Override
    @NotNull
    public PushButton getLoad() {
        return load;
    }

    @Override
    @NotNull
    public PushButton getLoadAndSearch() {
        return loadAndSearch;
    }
}
