package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.integerconstants;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.integerconstant.RESTIntegerConstantV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter used to display the integer constants details.
 */
@Dependent
public class IntegerConstantPresenter extends BaseTemplatePresenter {

    public static final String HISTORY_TOKEN = "IntegerConstantView";

    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public void bindExtended() {

    }

    @Override
    protected void go() {
        bindExtended();
    }

    @Override
    public void close() {

    }

    // Empty interface declaration, similar to UiBinder
    public interface IntegerConstantPresenterDriver extends SimpleBeanEditorDriver<RESTIntegerConstantV1, RESTIntegerConstantV1DetailsEditor> {
    }

    public interface Display extends BasePopulatedEditorViewInterface<RESTIntegerConstantV1, RESTIntegerConstantV1, RESTIntegerConstantV1DetailsEditor> {

    }
}
