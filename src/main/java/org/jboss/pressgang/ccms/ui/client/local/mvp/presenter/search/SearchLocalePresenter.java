package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUILocaleEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.locale.SearchUILocales;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.List;

/**
  The presenter used to display the locales in a search.
 */
public class SearchLocalePresenter  extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "SearchLocaleView";

    // Empty interface declaration, similar to UiBinder
    public interface LocalesPresenterDriver extends SimpleBeanEditorDriver<SearchUILocales, SearchUILocaleEditor> {
    }

    /**
     * The display.
     */
    @Inject
    private Display display;

    /**
     * @return The display
     */
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        /*
            Nothing to do
        */
    }

    @Override
    public void bindExtended(final int topicId, @NotNull final String pageId) {
        bind(topicId, pageId, display);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        GWTUtilities.clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.SEARCH_LOCALES_HELP_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void close() {

    }

    public interface Display extends BaseTemplateViewInterface, BasePopulatedEditorViewInterface<List<String>, SearchUILocales, SearchUILocaleEditor> {
        SearchUILocales getSearchUILocales();
    }
}
