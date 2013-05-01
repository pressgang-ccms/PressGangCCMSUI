package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search;

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchFilterResultsAndFilterPresenter;
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
        implements SearchFilterResultsAndFilterPresenter.Display {

    private final PushButton create = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Create());
    private final PushButton overwrite = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Overwrite());
    private final PushButton load = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Load());
    private final PushButton loadAndSearch = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.LoadAndSearch());

    public SearchFilterResultsAndFilterView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Filters(), false);
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
