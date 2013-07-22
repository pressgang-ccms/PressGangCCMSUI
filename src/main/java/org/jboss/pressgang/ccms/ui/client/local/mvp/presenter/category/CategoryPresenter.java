package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter used to add logic to the category view.
 */
@Dependent
public class CategoryPresenter extends
        BaseTemplatePresenter
        implements BaseTemplatePresenterInterface {

    // Empty interface declaration, similar to UiBinder
    public interface CategoryPresenterDriver extends SimpleBeanEditorDriver<RESTCategoryV1, RESTCategoryV1BasicDetailsEditor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTCategoryV1, RESTCategoryV1, RESTCategoryV1BasicDetailsEditor> {

    }

    /**
     * The history token
     */
    public static final String HISTORY_TOKEN = "CategoryView";

    /**
     * The id of the category that is being viewed
     */
    @Nullable
    private Integer categoryId;

    /**
     * The category view
     */
    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindExtended() {
        super.bind(display);
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            categoryId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            categoryId = null;
        }
    }
}
