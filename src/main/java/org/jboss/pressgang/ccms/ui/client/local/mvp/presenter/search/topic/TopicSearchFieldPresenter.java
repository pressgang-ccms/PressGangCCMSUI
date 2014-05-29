package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.topic.TopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TopicSearchFieldPresenter extends BaseSearchFieldPresenter {

    public static final String HISTORY_TOKEN = "TopicSearchFieldView";

    public interface Display extends BaseSearchFieldPresenter.Display<TopicSearchUIFields, TopicSearchFieldUIEditor> {
        interface SearchFieldPresenterDriver extends SimpleBeanEditorDriver<TopicSearchUIFields, TopicSearchFieldUIEditor> {
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
