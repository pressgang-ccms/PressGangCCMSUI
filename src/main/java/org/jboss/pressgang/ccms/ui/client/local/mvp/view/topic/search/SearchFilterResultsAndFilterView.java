package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

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

    public SearchFilterResultsAndFilterView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Filters());
    }
}
