package org.jboss.pressgang.ccms.ui.client.local.mvp.view.image;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter.ImagePresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTImageV1Editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImageView extends BaseTemplateView implements ImagePresenter.Display {

    private RESTImageV1Editor editor;
    private final ImagePresenterDriver driver = GWT.create(ImagePresenterDriver.class);

    private final PushButton addLocale = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.AddLocale());
    private final PushButton removeLocale = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RemoveLocale());
    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton viewImage = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ViewImage());
    private final PushButton findTopics = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.FindTopics());
    private final AddLocaleDialog addLocaleDialog = new AddLocaleDialog();

    /**
     * The dialog box that presents the list of locales for the user to select from.
     *
     * @author Matthew Casperson
     */
    public final static class AddLocaleDialog extends DialogBox implements ImagePresenter.Display.AddLocaleInterface {
        private final FlexTable layout = new FlexTable();
        private final ListBox locales = new ListBox();
        private final Label localesLabel = new Label(PressGangCCMSUI.INSTANCE.Locales());
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @Override
        public PushButton getCancel() {
            return cancel;
        }

        @Override
        public PushButton getOk() {
            return ok;
        }

        @Override
        public ListBox getLocales() {
            return locales;
        }

        public Label getLocalesLabel() {
            return localesLabel;
        }

        public AddLocaleDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.AddLocale());

            layout.setWidget(0, 0, localesLabel);
            layout.setWidget(0, 1, locales);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            layout.setWidget(1, 0, buttonPanel);

            layout.getFlexCellFormatter().setColSpan(1, 0, 2);

            this.add(layout);
        }

    }

    @Override
    public PushButton getFindTopics() {
        return findTopics;
    }

    @Override
    public PushButton getViewImage() {
        return viewImage;
    }

    @Override
    public ImagePresenterDriver getDriver() {
        return driver;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    @Override
    public RESTImageV1Editor getEditor() {
        return editor;
    }

    public void setEditor(final RESTImageV1Editor editor) {
        this.editor = editor;
    }

    @Override
    public AddLocaleDialog getAddLocaleDialog() {
        return addLocaleDialog;
    }

    @Override
    public PushButton getRemoveLocale() {
        return removeLocale;
    }

    @Override
    public PushButton getAddLocale() {
        return addLocale;
    }

    public ImageView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images());

        this.addActionButton(addLocale);
        this.addActionButton(removeLocale);
        this.addActionButton(viewImage);
        this.addActionButton(findTopics);
        this.addActionButton(save);
    }

    @Override
    public final void display(final RESTImageV1 image, final boolean readOnly) {

    }

    @Override
    public final void displayExtended(final RESTImageV1 image, final boolean readOnly, final String[] locales) {
        if (image == null) {
            throw new IllegalArgumentException("image cannot be null");
        }
        if (locales == null) {
            throw new IllegalArgumentException("locales cannot be null");
        }

        /* SearchUIProjectsEditor is a grid */
        editor = new RESTImageV1Editor(); /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(image);
        /* Add the projects */
        this.getPanel().setWidget(editor);

        /* Sort the array */
        final List<String> localesList = new ArrayList<String>(Arrays.asList(locales));
        Collections.sort(localesList);

        /* populate the locales listbox */
        this.addLocaleDialog.locales.clear();
        for (final String locale : localesList) {
            this.addLocaleDialog.locales.addItem(locale);
        }

        /* Make sure the dialog box is closed */
        addLocaleDialog.hide();
    }
}
