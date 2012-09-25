package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class CategoryPresenter extends TemplatePresenter implements EditableView {
   
    public static final String HISTORY_TOKEN = "CategoryView";
    
    // Empty interface declaration, similar to UiBinder
    public interface CategoryPresenterDriver extends SimpleBeanEditorDriver<RESTCategoryV1, RESTCategoryV1BasicDetailsEditor> {
    }

    public interface Display extends BaseTemplateViewInterface {
        @SuppressWarnings("rawtypes")
        SimpleBeanEditorDriver getDriver();

        public void initialize(final RESTCategoryV1 category, final boolean readOnly);
    }

    private String categoryId;

    @Inject
    private Display display;

    @Override
    public void parseToken(final String searchToken) {
        categoryId = searchToken.replace(HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        getCategory();

        bind();
    }

    private void getCategory() {
        final RESTCalls.RESTCallback<RESTCategoryV1> callback = new RESTCalls.RESTCallback<RESTCategoryV1>() {
            @Override
            public void begin() {
                display.addWaitOperation();
            }

            @Override
            public void generalException(final Exception ex) {
                display.removeWaitOperation();
            }

            @Override
            public void success(final RESTCategoryV1 retValue) {
                try {
                    display.initialize(retValue, false);
                } finally {
                    display.removeWaitOperation();
                }
            }

            @Override
            public void failed() {
                display.removeWaitOperation();
            }
        };

        RESTCalls.getUnexpandedCategory(callback, Integer.parseInt(categoryId));
    }

    private void bind() {
        super.bind(display, this);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
