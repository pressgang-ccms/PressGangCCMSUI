package org.jboss.pressgang.ccms.ui.client.local.mvp.view.settings;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerEntitiesV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.settings.ServerSettingsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.RESTServerEntitiesV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.RESTServerSettingsV1DetailsEditor;
import org.jetbrains.annotations.NotNull;

public class ServerSettingsView extends BaseTemplateView implements ServerSettingsPresenter.Display {
    private final ScrollPanel serverSettingsPanel = new ScrollPanel();
    private final ScrollPanel serverEntitiesPanel = new ScrollPanel();
    private RESTServerSettingsV1DetailsEditor settingsEditor;
    private RESTServerEntitiesV1DetailsEditor entitiesEditor;

    private final PushButton appSettings = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Application());
    private final Label appSettingsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Application());
    private final PushButton entitySettings = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Entities());
    private final Label entitySettingsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Entities());
    private final PushButton saveButton = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Save());

    private final ServerSettingsPresenter.ServerSettingsPresenterDriver settingsDriver = GWT.create(
            ServerSettingsPresenter.ServerSettingsPresenterDriver.class);
    private final ServerSettingsPresenter.ServerEntitiesPresenterDriver entitiesDriver = GWT.create(
            ServerSettingsPresenter.ServerEntitiesPresenterDriver.class);

    private boolean readOnly = false;

    @Override
    public SimplePanel getServerSettingsPanel() {
        return serverSettingsPanel;
    }

    @Override
    public SimplePanel getServerEntitiesPanel() {
        return serverEntitiesPanel;
    }

    @Override
    public PushButton getAppSettingsButton() {
        return appSettings;
    }

    @Override
    public Label getAppSettingsDownButton() {
        return appSettingsDown;
    }

    @Override
    public PushButton getEntitySettingsButton() {
        return entitySettings;
    }

    @Override
    public Label getEntitySettingsDownButton() {
        return entitySettingsDown;
    }

    @Override
    public PushButton getSaveButton() {
        return saveButton;
    }

    public ServerSettingsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ServerSettings());

        addActionButton(appSettingsDown);
        addActionButton(entitySettings);
        addActionButton(saveButton);

        serverEntitiesPanel.addStyleName(CSSConstants.SettingsView.SETTINGS_PANEL);
        serverSettingsPanel.addStyleName(CSSConstants.SettingsView.SETTINGS_PANEL);
        getTopActionGrandParentPanel().addStyleName(CSSConstants.SettingsView.SETTINGS_ACTION_PANEL);
    }

    @Override
    public void display(@NotNull final RESTServerSettingsV1 entity, final boolean readOnly) {
        this.readOnly = readOnly;

        settingsEditor = new RESTServerSettingsV1DetailsEditor(readOnly);
        // Initialize the driver with the top-level editor
        settingsDriver.initialize(settingsEditor);
        // Copy the data in the object into the UI
        settingsDriver.edit(entity);
        serverSettingsPanel.setWidget(settingsEditor);

        entitiesEditor = new RESTServerEntitiesV1DetailsEditor(readOnly);
        // Initialize the driver with the top-level editor
        entitiesDriver.initialize(entitiesEditor);
        // Copy the data in the object into the UI
        entitiesDriver.edit(entity.getEntities());
        serverEntitiesPanel.setWidget(entitiesEditor);
    }

    @Override
    public SimpleBeanEditorDriver<RESTServerSettingsV1, RESTServerSettingsV1DetailsEditor> getServerSettingsDriver() {
        return settingsDriver;
    }

    @Override
    public RESTServerSettingsV1DetailsEditor getSettingsEditor() {
        return settingsEditor;
    }

    @Override
    public SimpleBeanEditorDriver<RESTServerEntitiesV1, RESTServerEntitiesV1DetailsEditor> getServerEntitiesDriver() {
        return entitiesDriver;
    }

    @Override
    public RESTServerEntitiesV1DetailsEditor getEntitiesEditor() {
        return entitiesEditor;
    }
}
