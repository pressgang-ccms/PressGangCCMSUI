package org.jboss.pressgang.ccms.ui.client.local.mvp.component.category;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

public class CategoryComponent extends ComponentBase<CategoryPresenter.Display> implements CategoryPresenter.LogicComponent {

   

    public void bind(final Integer categoryID, final Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);
        getCategory(categoryID);

    }
    
    private void getCategory(final Integer categoryId) {
        final RESTCalls.RESTCallback<RESTCategoryV1> callback = new BaseRestCallback<RESTCategoryV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTCategoryV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTCategoryV1 retValue, Display display) {
                        display.initialize(retValue, false);
                    }
                }) {
        };
        RESTCalls.getUnexpandedCategory(callback, categoryId);
    }
    
    

}
