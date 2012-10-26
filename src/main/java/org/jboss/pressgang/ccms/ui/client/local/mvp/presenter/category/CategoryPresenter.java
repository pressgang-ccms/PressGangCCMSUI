package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.category.CategoryViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class CategoryPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "CategoryView";

    // Empty interface declaration, similar to UiBinder
    public interface CategoryPresenterDriver extends SimpleBeanEditorDriver<RESTCategoryV1, RESTCategoryV1BasicDetailsEditor> {
    }

    public interface Display extends CategoryViewInterface {
        @SuppressWarnings("rawtypes")
        SimpleBeanEditorDriver getDriver();

        public void initialize(final RESTCategoryV1 category, final boolean readOnly);
    }

    public interface LogicComponent extends Component<Display> {
        void getCategory(final Integer categoryId);
    }

    private Integer categoryId;

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Override
    public void parseToken(final String searchToken) {
        try
        {
            categoryId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        }
        catch (final NumberFormatException ex)
        {
            categoryId = null;
        }
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(display, display);
        component.getCategory(categoryId);
    }

}
