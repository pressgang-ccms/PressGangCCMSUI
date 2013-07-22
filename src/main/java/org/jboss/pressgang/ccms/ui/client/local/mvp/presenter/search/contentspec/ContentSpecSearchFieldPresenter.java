package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.contentspec.ContentSpecSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.ContentSpecSearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ContentSpecSearchFieldPresenter extends BaseSearchFieldPresenter {

    public static final String HISTORY_TOKEN = "ContentSpecSearchFieldView";

    public interface Display extends BaseSearchFieldPresenter.Display<ContentSpecSearchUIFields,
            ContentSpecSearchFieldUIEditor> {
        interface SearchFieldPresenterDriver extends SimpleBeanEditorDriver<ContentSpecSearchUIFields, ContentSpecSearchFieldUIEditor> {
        }
    }

    @Inject
    private Display display;

    @NotNull
    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        GWTUtilities.clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();
    }

    @Override
    public void close() {

    }
}
