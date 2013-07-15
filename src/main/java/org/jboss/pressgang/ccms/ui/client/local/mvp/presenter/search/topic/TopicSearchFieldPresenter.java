package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.topic.TopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TopicSearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

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
    public void go(@NotNull final HasWidgets container) {
        GWTUtilities.clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.SEARCH_FIELDS_HELP_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void close() {

    }
}
