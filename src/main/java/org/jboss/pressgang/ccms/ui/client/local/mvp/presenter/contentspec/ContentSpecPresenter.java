package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import com.google.gwt.user.client.ui.HasWidgets;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * Displays the text of a content spec.
 */
@Dependent
public class ContentSpecPresenter extends BaseTemplatePresenter {

    public static final String HISTORY_TOKEN = "ContentSpecTextEditor";

    @Nullable
    private Integer contentSpecId;

    @Inject
    private Display display;

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            contentSpecId = Integer.parseInt(removeHistoryToken(historyToken, HISTORY_TOKEN));
        } catch (@NotNull final Exception ex) {
            // bad history token. silently fail
            contentSpecId = null;
        }
    }

    @Override
    public void bindExtended(final int topicId, @NotNull final String pageId) {
        super.bind(topicId, pageId, display);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.CONTENT_SPEC_TEXT_EDIT_HELP_TOPIC, HISTORY_TOKEN);
    }

    @NotNull
    public Display getDisplay() {
        return display;
    }

    public interface Display extends BaseTemplateViewInterface {
        @NotNull AceEditor getEditor();
        void display(@NotNull final String contentSpecText, final boolean readOnly);
    }
}
