package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.filter.RESTFilterV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * The presenter used to display the information about a filter.
 */
public class SearchFilterPresenter extends BaseTemplatePresenter {

    // Empty interface declaration, similar to UiBinder
    public interface FilterPresenterDriver extends SimpleBeanEditorDriver<RESTFilterV1, RESTFilterV1BasicDetailsEditor> {
    }

    public interface Display extends BaseTemplateViewInterface, BasePopulatedEditorViewInterface<RESTFilterV1, RESTFilterV1, RESTFilterV1BasicDetailsEditor> {
    }

    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "SearchFilterView";

    /**
     * This display.
     */
    @Inject
    private Display display;

    /**
     * @return The display.
     */
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public void bindExtended() {
        super.bind(display);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        bindExtended();
    }

    @Override
    public void close() {

    }
}
