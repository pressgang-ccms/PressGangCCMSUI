package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.contentspec.ContentSpecSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.ContentSpecSearchUIFields;
import org.jetbrains.annotations.NotNull;

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
    protected void go() {
        bindExtended();
    }

    @Override
    public void close() {

    }
}
