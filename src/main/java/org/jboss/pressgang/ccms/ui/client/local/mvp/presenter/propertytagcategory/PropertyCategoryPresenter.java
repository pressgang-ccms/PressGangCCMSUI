package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertycategory.RESTPropertyCategoryV1DetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter for the property tag details view.
 */
@Dependent
public class PropertyCategoryPresenter extends BaseTemplatePresenter {

    // Empty interface declaration, similar to UiBinder
    public interface PropertyCategoryPresenterDriver extends SimpleBeanEditorDriver<RESTPropertyCategoryV1, RESTPropertyCategoryV1DetailsEditor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTPropertyCategoryV1, RESTPropertyCategoryV1, RESTPropertyCategoryV1DetailsEditor> {

    }

    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "PropertyCategoryView";

    @Nullable
    private Integer entityId;

    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            entityId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            entityId = null;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int topicId, @NotNull final String pageId) {
        super.bind(topicId, pageId, display);
    }

    /**
     * Get the category from the database and use it to populate the editor in the view
     */
    public void getEntity(@NotNull final Integer entityId) {
        @NotNull final RESTCalls.RESTCallback<RESTPropertyCategoryV1> callback = new BaseRestCallback<RESTPropertyCategoryV1, PropertyCategoryPresenter.Display>(display,
                new BaseRestCallback.SuccessAction<RESTPropertyCategoryV1, PropertyCategoryPresenter.Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTPropertyCategoryV1 retValue, @NotNull final PropertyCategoryPresenter.Display display) {
                        display.display(retValue, false);
                    }
                });
        RESTCalls.getPropertyCategory(callback, entityId);
    }
}
