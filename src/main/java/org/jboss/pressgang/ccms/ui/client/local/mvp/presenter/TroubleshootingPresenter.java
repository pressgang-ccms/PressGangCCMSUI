package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TroubleshootingPresenter extends BaseTemplatePresenter {

    public static final String HISTORY_TOKEN = "TroubleshootingView";

    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject
    private Display display;

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

    @Override
    public void parseToken(@NotNull final String historyToken) {


    }
}