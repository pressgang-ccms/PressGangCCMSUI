package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertytag.RESTPropertyTagV1DetailsEditor;
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
public class PropertyTagPresenter extends BaseTemplatePresenter {

    // Empty interface declaration, similar to UiBinder
    public interface PropertyTagPresenterDriver extends SimpleBeanEditorDriver<RESTPropertyTagV1, RESTPropertyTagV1DetailsEditor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTPropertyTagV1, RESTPropertyTagV1, RESTPropertyTagV1DetailsEditor> {

    }

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "PropertyTagView";

    @Inject private FailOverRESTCall failOverRESTCall;

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
        bindExtended();
    }

    @Override
    public void close() {

    }

    public void bindExtended() {
        super.bind(display);
    }

    /**
     * Get the category from the database and use it to populate the editor in the view
     */
    public void getEntity(@NotNull final Integer entityId) {
        final RESTCallBack<RESTPropertyTagV1> callback = new RESTCallBack<RESTPropertyTagV1>() {
            @Override
            public void success(@NotNull final RESTPropertyTagV1 retValue) {
                display.display(retValue, false);
            }
        };

        failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getPropertyTag(entityId), callback, display);
    }
}
