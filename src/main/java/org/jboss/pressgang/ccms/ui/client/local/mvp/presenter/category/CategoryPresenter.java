package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.category.CategoryViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter used to add logic to the category view.
 */
@Dependent
public final class CategoryPresenter extends
        BaseTemplatePresenter
        implements BaseTemplatePresenterInterface {

    // Empty interface declaration, similar to UiBinder
    public interface CategoryPresenterDriver extends SimpleBeanEditorDriver<RESTCategoryV1, RESTCategoryV1BasicDetailsEditor> {
    }

    public interface Display extends CategoryViewInterface, BaseEditorViewInterface<RESTCategoryV1, RESTCategoryV1BasicDetailsEditor> {

    }

    /**
     * The history token
     */
    public static final String HISTORY_TOKEN = "CategoryView";

    /**
     * The id of the category that is being viewed
     */
    private Integer categoryId;

    /**
     * The category view
     */
    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int topicId, final String pageId)
    {
        super.bind(topicId, pageId, display);
        this.getEntity();
    }


    @Override
    public void parseToken(final String searchToken) {
        try {
            categoryId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (final NumberFormatException ex) {
            categoryId = null;
        }
    }

    /**
     * Get the category from the database and use it to populate the editor in the view
     */
    public void getEntity() {
        final RESTCalls.RESTCallback<RESTCategoryV1> callback = new BaseRestCallback<RESTCategoryV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTCategoryV1, Display>() {
                    @Override
                    public void doSuccessAction(final RESTCategoryV1 retValue, final Display display) {
                        display.initialize(retValue, false);
                    }
                });

        if (categoryId != null)
        {
            RESTCalls.getUnexpandedCategory(callback, categoryId);
        }
    }
}
