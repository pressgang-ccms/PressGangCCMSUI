package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.blobconstant.RESTBlobConstantV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
    The presenter used to display the blob constants details.
 */
@Dependent
public class BlobConstantPresenter extends BaseTemplatePresenter {

    public static final String HISTORY_TOKEN = "BlobConstantView";

    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public void bindExtended(final int topicId, @NotNull final String pageId) {
        super.bind(topicId, pageId, display);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    // Empty interface declaration, similar to UiBinder
    public interface BlobConstantPresenterDriver extends SimpleBeanEditorDriver<RESTBlobConstantV1, RESTBlobConstantV1DetailsEditor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTBlobConstantV1, RESTBlobConstantV1, RESTBlobConstantV1DetailsEditor> {
        RESTBlobConstantV1DetailsEditor getEditor();
    }
}
