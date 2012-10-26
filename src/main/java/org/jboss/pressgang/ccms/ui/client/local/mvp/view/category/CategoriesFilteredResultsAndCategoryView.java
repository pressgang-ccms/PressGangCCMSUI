package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class CategoriesFilteredResultsAndCategoryView extends BaseSearchAndEditView implements CategoriesFilteredResultsAndCategoryPresenter.Display {
    
    public CategoriesFilteredResultsAndCategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());        
    }
}
