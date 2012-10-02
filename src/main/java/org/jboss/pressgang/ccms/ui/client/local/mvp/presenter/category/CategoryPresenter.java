package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

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
        categoryId = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        getCategory();
        bind();
    }

    private void getCategory() {
        final RESTCalls.RESTCallback<RESTCategoryV1> callback = new BaseRestCallback<RESTCategoryV1,
                Display>(display, new BaseRestCallback.SuccessAction<RESTCategoryV1, Display>() {
            @Override
            public void doSuccessAction(RESTCategoryV1 retValue, Display display) {
                display.initialize(retValue, false);
            }
        }) {
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
