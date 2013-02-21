package org.jboss.pressgang.ccms.ui.client.local.mvp.view.stringconstant;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants.StringConstantPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.stringconstant.RESTStringConstantV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: matthew
 * Date: 2/21/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringConstantView extends BaseTemplateView implements StringConstantPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final StringConstantPresenter.StringConstantPresenterDriver driver = GWT.create(StringConstantPresenter.StringConstantPresenterDriver.class);

    private boolean readOnly = false;

    public StringConstantView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.StringConstantDetails());
    }

    @Override
    public void display(@NotNull final RESTStringConstantV1 entity, final boolean readOnly) {
        this.readOnly = readOnly;

        /* SearchUIProjectsEditor is a grid */
        final RESTStringConstantV1DetailsEditor editor = new RESTStringConstantV1DetailsEditor(this.readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(entity);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

    @Override
    public StringConstantPresenter.StringConstantPresenterDriver getDriver() {
        return driver;
    }
}
