package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.translatedtopic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.topic.TranslatedTopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TranslatedTopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TranslatedTopicSearchFieldPresenter extends BaseSearchFieldPresenter {

    public static final String HISTORY_TOKEN = "TranslatedTopicSearchFieldView";

    public interface Display extends BaseSearchFieldPresenter.Display<TranslatedTopicSearchUIFields, TranslatedTopicSearchFieldUIEditor> {
        interface SearchFieldPresenterDriver extends SimpleBeanEditorDriver<TranslatedTopicSearchUIFields, TranslatedTopicSearchFieldUIEditor> {
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
