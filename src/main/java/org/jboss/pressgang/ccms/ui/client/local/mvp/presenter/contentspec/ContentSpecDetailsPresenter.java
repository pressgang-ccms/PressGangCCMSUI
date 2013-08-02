package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTTextContentSpecV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter that displays the content spec details.
 */
@Dependent
public class ContentSpecDetailsPresenter extends BaseTemplatePresenter {
    public static final String HISTORY_TOKEN = "ContentSpecDetailsView";

    // Empty interface declaration, similar to UiBinder
    public interface ContentSpecPresenterDriver extends SimpleBeanEditorDriver<RESTTextContentSpecV1, RESTTextContentSpecV1BasicDetailsEditor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTTextContentSpecV1, RESTTextContentSpecV1, RESTTextContentSpecV1BasicDetailsEditor> {
        void displayContentSpecDetails(final RESTTextContentSpecV1 topic, final boolean readOnly, final List<String> locales);
    }

    @Nullable
    private Integer contentSpec;

    @Inject
    private Display display;

    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            contentSpec = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            contentSpec = null;
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
        bind(display);
    }
}
