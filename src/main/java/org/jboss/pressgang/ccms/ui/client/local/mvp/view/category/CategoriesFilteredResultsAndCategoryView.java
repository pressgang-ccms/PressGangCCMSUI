package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class CategoriesFilteredResultsAndCategoryView extends
        BaseSearchAndEditView<RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1> implements
        CategoriesFilteredResultsAndCategoryPresenter.Display {

    public CategoriesFilteredResultsAndCategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());
    }
}
