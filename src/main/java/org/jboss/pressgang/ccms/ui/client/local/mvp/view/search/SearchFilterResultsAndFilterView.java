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

/**
 * Created with IntelliJ IDEA.
 * User: matthew
 * Date: 2/13/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
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
