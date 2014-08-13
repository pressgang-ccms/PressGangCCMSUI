/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.settings;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.isStringNullOrEmpty;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLocaleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUndefinedSettingCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLocaleCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTServerUndefinedEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTServerUndefinedSettingCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTZanataServerSettingsCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerEntitiesV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerUndefinedEntityV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerUndefinedSettingV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTZanataServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTLocaleV1Sort;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.OtherSettingsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.RESTLocaleCollectionV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.RESTServerEntitiesV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.RESTServerSettingsV1DetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.RESTServerUndefinedEntitiesCollectionV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.RESTServerUndefinedSettingsCollectionV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings.RESTZanataServerSettingsCollectionV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

@Dependent
public class ServerSettingsPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "SettingsView";
    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ServerSettingsPresenter.class.getName());

    @Inject
    private Display display;

    private final RESTLocaleV1Sort localesSort = new RESTLocaleV1Sort();
    private RESTServerSettingsV1 currentServerSettings;
    private RESTLocaleCollectionV1 currentLocales;
    private SimplePanel displayedView;

    final RESTCallBack<RESTLocaleCollectionV1> localeCallback = new RESTCallBack<RESTLocaleCollectionV1>() {
        @Override
        public void success(@NotNull final RESTLocaleCollectionV1 locales) {
            setLocales(locales);

            getServerSettings(new ServerSettingsCallback() {
                @Override
                public void serverSettingsLoaded(@NotNull RESTServerSettingsV1 serverSettings, @NotNull RESTLocaleCollectionV1 locales) {
                    // Load the server setting and initialise the views
                    loadData(serverSettings, locales);
                    // Bind the editor buttons
                    bindEditorButtons();

                    // Display the success message
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.SaveSuccess());

                    // If readonly was changed then we need to disable the shortcut buttons
                    disableTopShortcutButtonsInReadOnlyMode();
                }
            });
        }
    };

    @Override
    public void parseToken(@NotNull String historyToken) {

    }

    public Display getDisplay() {
        return display;
    }

    private boolean localesUpdated;
    private boolean localesCreated;

    @Override
    public void bindExtended() {
        getServerSettings(new ServerSettingsCallback() {
            @Override
            public void serverSettingsLoaded(@NotNull RESTServerSettingsV1 serverSettings, RESTLocaleCollectionV1 locales) {
                // Load the server setting and initialise the views
                loadData(serverSettings, locales);

                // Bind the buttons
                bindActionButtons();
                bindEditorButtons();
            }
        });

        switchView(display.getServerSettingsPanel());
    }

    @Override
    protected void go() {
        display.setViewShown(true);
        bindExtended();
    }

    @Override
    public void close() {
    }

    protected void loadData(final RESTServerSettingsV1 serverSettings, final RESTLocaleCollectionV1 locales) {
        currentServerSettings = serverSettings.clone(true);
        currentLocales = new RESTLocaleCollectionV1();
        locales.cloneInto(currentLocales, true);
        display.display(currentServerSettings, currentLocales, false);

        display.getSettingsEditor().defaultLocaleEditor().setAcceptableValues(getDefaultLocales());
    }

    protected void bindEditorButtons() {
        bindLocalesButtons();
        bindZanataServerButtons();
        bindUndefinedSettingsButtons();
        bindUndefinedEntitiesButtons();
        bindOtherSettingsButtons();
    }

    private void enableAndDisableActionButtons(@NotNull final SimplePanel displayedView) {
        display.replaceTopActionButton(display.getAppSettingsDownButton(), display.getAppSettingsButton());
        display.replaceTopActionButton(display.getEntitySettingsDownButton(), display.getEntitySettingsButton());

        if (displayedView == display.getServerSettingsPanel()) {
            display.replaceTopActionButton(display.getAppSettingsButton(), display.getAppSettingsDownButton());
        } else if (displayedView == display.getServerEntitiesPanel()) {
            display.replaceTopActionButton(display.getEntitySettingsButton(), display.getEntitySettingsDownButton());
        }
    }

    protected void switchView(final SimplePanel view) {
        if (displayedView != null) {
            displayedView.removeFromParent();
        }

        display.getPanel().setWidget(view);
        displayedView = view;

        enableAndDisableActionButtons(view);
    }

    protected void bindActionButtons() {
        display.getAppSettingsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                switchView(display.getServerSettingsPanel());
            }
        });

        display.getEntitySettingsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                switchView(display.getServerEntitiesPanel());
            }
        });

        display.getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getDisplay().getServerSettingsDriver().flush();
                getDisplay().getServerEntitiesDriver().flush();
                getDisplay().getLocalesDriver().flush();

                getServerSettings(new ServerSettingsCallback() {
                    @Override
                    public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 defaultSettings,
                            final RESTLocaleCollectionV1 defaultLocales) {
                        final boolean hasUnsavedSettings = hasUnsavedChanges(defaultSettings);
                        final boolean hasUnsavedLocales = hasUnsavedChanges(defaultLocales);
                        if (hasUnsavedLocales || hasUnsavedSettings) {
                            final RESTCallBack<RESTServerSettingsV1> callback = new RESTCallBack<RESTServerSettingsV1>() {
                                @Override
                                public void success(@NotNull final RESTServerSettingsV1 retValue) {
                                    setServerSettings(retValue);

                                    if (hasUnsavedLocales) {
                                        /*
                                         * Locales need to be updated in a two step process (one to create new values and one to create
                                         * old values), so we store the state in a global variable and when move on when both values
                                         * have been set to true.
                                         */
                                        localesCreated = false;
                                        localesUpdated = false;

                                        final RESTLocaleCollectionV1 updatedLocales = getLocaleUpdateChanges(currentLocales);
                                        final RESTLocaleCollectionV1 newLocales = getLocaleCreateChanges(currentLocales);

                                        final RESTCallBack<RESTLocaleCollectionV1> updatedLocalesCallback = new RESTCallBack<RESTLocaleCollectionV1>() {
                                            @Override
                                            public void success(final RESTLocaleCollectionV1 retValue) {
                                                localesUpdated = true;
                                                if (localesUpdated && localesCreated) {
                                                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getLocales(), localeCallback, display);
                                                }
                                            }
                                        };

                                        final RESTCallBack<RESTLocaleCollectionV1> createdLocalesCallback = new
                                                RESTCallBack<RESTLocaleCollectionV1>() {
                                            @Override
                                            public void success(final RESTLocaleCollectionV1 retValue) {
                                                localesCreated = true;
                                                if (localesUpdated && localesCreated) {
                                                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getLocales(), localeCallback, display);
                                                }
                                            }
                                        };

                                        if (updatedLocales.getItems().isEmpty()) {
                                            updatedLocalesCallback.success(null);
                                        } else {
                                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateLocales(updatedLocales),
                                                    updatedLocalesCallback, display);
                                        }

                                        if (newLocales.getItems().isEmpty()) {
                                            createdLocalesCallback.success(null);
                                        } else {
                                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createLocales(newLocales),
                                                    createdLocalesCallback, display);
                                        }
                                    } else {
                                        localeCallback.success(currentLocales);
                                    }
                                }
                            };

                            if (hasUnsavedSettings) {
                                final RESTServerSettingsV1 newServerSettings = syncChanges(currentServerSettings, defaultSettings);
                                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateServerSettings(newServerSettings),
                                        callback, display);
                            } else {
                                callback.success(currentServerSettings);
                            }
                        } else {
                            AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                        }
                    }
                });
            }
        });
    }

    protected void bindLocalesButtons() {
        final RESTLocaleCollectionV1Editor localesEditor = display.getLocalesEditor();
        localesEditor.getRemoveColumn().setFieldUpdater(new FieldUpdater<RESTLocaleCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTLocaleCollectionItemV1 object, String value) {
                localesEditor.itemsEditor().removeValue(object);
                localesEditor.getDataProvider().refresh();
            }
        });

        display.getLocalesEditor().getAddButtonFooter().setUpdater(new ValueUpdater<String>() {
            @Override
            public void update(String value) {
                final RESTLocaleV1 newLocale = localesEditor.getNewItemFromFooter();

                if (isStringNullOrEmpty(newLocale.getValue()) || isStringNullOrEmpty(
                        newLocale.getBuildValue()) || isStringNullOrEmpty(newLocale.getTranslationValue())) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.MandatoryValuesMissing());
                } else if (doesLocaleAlreadyExist(localesEditor.getDataProvider().getList(), newLocale.getValue())) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.LocaleAlreadyExists() + " " + newLocale.getValue());
                } else {
                    final RESTLocaleCollectionItemV1 item = new RESTLocaleCollectionItemV1();
                    item.setItem(newLocale);
                    item.setState(RESTLocaleCollectionItemV1.ADD_STATE);

                    localesEditor.itemsEditor().addValue(item);
                    localesEditor.resetFooter();
                    localesEditor.getDataProvider().refresh();
                }

                // Add the value from the default locales
                display.getSettingsEditor().defaultLocaleEditor().setAcceptableValues(getDefaultLocales());
            }
        });
    }

    protected void bindZanataServerButtons() {
        final RESTZanataServerSettingsCollectionV1Editor zanataServersEditor = display.getSettingsEditor().zanataSettingsEditor();
        zanataServersEditor.getRemoveColumn().setFieldUpdater(new FieldUpdater<RESTZanataServerSettingsCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTZanataServerSettingsCollectionItemV1 object, String value) {
                zanataServersEditor.itemsEditor().removeValue(object);
                zanataServersEditor.getDataProvider().refresh();
            }
        });

        zanataServersEditor.getAddButtonFooter().setUpdater(new ValueUpdater<String>() {
            @Override
            public void update(String value) {
                final RESTZanataServerSettingsV1 newValue = zanataServersEditor.getNewItemFromFooter();

                if (isStringNullOrEmpty(newValue.getId()) || isStringNullOrEmpty(newValue.getName()) || isStringNullOrEmpty(
                        newValue.getUrl()) || isStringNullOrEmpty(newValue.getProject()) || isStringNullOrEmpty(
                        newValue.getProjectVersion())) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.MandatoryValuesMissing());
                } else if (doesZanataServerAlreadyExist(zanataServersEditor.getDataProvider().getList(), newValue.getId())) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.ZanataServerAlreadyExists() + " " + newValue.getId());
                } else {
                    final RESTZanataServerSettingsCollectionItemV1 item = new RESTZanataServerSettingsCollectionItemV1();
                    item.setItem(newValue);
                    item.setState(RESTZanataServerSettingsCollectionItemV1.ADD_STATE);

                    zanataServersEditor.itemsEditor().addValue(item);
                    zanataServersEditor.resetFooter();
                    zanataServersEditor.getDataProvider().refresh();
                }
            }
        });
    }

    protected void bindUndefinedSettingsButtons() {
        final RESTServerUndefinedSettingsCollectionV1Editor undefinedSettingsEditor = display.getSettingsEditor().undefinedSettingsEditor();
        undefinedSettingsEditor.getRemoveColumn().setFieldUpdater(new FieldUpdater<RESTServerUndefinedSettingCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTServerUndefinedSettingCollectionItemV1 object, String value) {
                undefinedSettingsEditor.itemsEditor().removeValue(object);
                undefinedSettingsEditor.getDataProvider().refresh();
            }
        });

        undefinedSettingsEditor.getAddButtonFooter().setUpdater(new ValueUpdater<String>() {
            @Override
            public void update(String value) {
                final RESTServerUndefinedSettingV1 newValue = undefinedSettingsEditor.getNewItemFromFooter();

                if (isStringNullOrEmpty(newValue.getKey()) || isStringNullOrEmpty(newValue.getValue())) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.MandatoryValuesMissing());
                } else if (doesSettingAlreadyExist(undefinedSettingsEditor.getDataProvider().getList(), newValue.getKey())) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.UndefinedSettingAlreadyExists() + " " + newValue.getKey());
                } else {
                    final RESTServerUndefinedSettingCollectionItemV1 item = new RESTServerUndefinedSettingCollectionItemV1();
                    item.setItem(newValue);
                    item.setState(RESTServerUndefinedSettingCollectionItemV1.ADD_STATE);

                    undefinedSettingsEditor.itemsEditor().addValue(item);
                    undefinedSettingsEditor.resetFooter();
                    undefinedSettingsEditor.getDataProvider().refresh();
                }
            }
        });
    }

    protected void bindUndefinedEntitiesButtons() {
        final RESTServerUndefinedEntitiesCollectionV1Editor undefinedEntitiesEditor = display.getEntitiesEditor().undefinedEntitiesEditor();
        undefinedEntitiesEditor.getRemoveColumn().setFieldUpdater(new FieldUpdater<RESTServerUndefinedEntityCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTServerUndefinedEntityCollectionItemV1 object, String value) {
                undefinedEntitiesEditor.itemsEditor().removeValue(object);
                undefinedEntitiesEditor.getDataProvider().refresh();
            }
        });

        undefinedEntitiesEditor.getAddButtonFooter().setUpdater(new ValueUpdater<String>() {
            @Override
            public void update(String value) {
                final RESTServerUndefinedEntityV1 newValue = undefinedEntitiesEditor.getNewItemFromFooter();

                if (isStringNullOrEmpty(newValue.getKey()) || newValue.getValue() == null) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.MandatoryValuesMissing());
                } else if (doesSettingAlreadyExist(undefinedEntitiesEditor.getDataProvider().getList(), newValue.getKey())) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.UndefinedSettingAlreadyExists() + " " + newValue.getKey());
                } else {
                    final RESTServerUndefinedEntityCollectionItemV1 item = new RESTServerUndefinedEntityCollectionItemV1();
                    item.setItem(newValue);
                    item.setState(RESTServerUndefinedEntityCollectionItemV1.ADD_STATE);

                    undefinedEntitiesEditor.itemsEditor().addValue(item);
                    undefinedEntitiesEditor.resetFooter();
                    undefinedEntitiesEditor.getDataProvider().refresh();
                }
            }
        });
    }

    protected void bindOtherSettingsButtons() {
        final OtherSettingsEditor docBookTemplateIdsEditor = display.getSettingsEditor().docBookTemplateIdsEditor();
        docBookTemplateIdsEditor.getAddButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final Integer value = docBookTemplateIdsEditor.getNewValueBox().getValue();
                if (value != null) {
                    if (!docBookTemplateIdsEditor.asEditor().getList().contains(value)) {
                        docBookTemplateIdsEditor.asEditor().addValue(value);
                        Collections.sort(docBookTemplateIdsEditor.getDataProvider().getList());
                        docBookTemplateIdsEditor.getDataProvider().refresh();
                    }
                    docBookTemplateIdsEditor.getNewValueBox().setValue(null);
                }
            }
        });
        docBookTemplateIdsEditor.getRemoveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final SingleSelectionModel<Integer> selectionModel = (SingleSelectionModel<Integer>) docBookTemplateIdsEditor
                        .getOtherSettingTable().getSelectionModel();

                if (selectionModel.getSelectedObject() != null) {
                    docBookTemplateIdsEditor.asEditor().removeValue(selectionModel.getSelectedObject());
                    docBookTemplateIdsEditor.getDataProvider().refresh();
                }
            }
        });

        final OtherSettingsEditor seoCategoryIdsEditor = display.getSettingsEditor().seoCategoryIdsEditor();
        seoCategoryIdsEditor.getAddButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final Integer value = seoCategoryIdsEditor.getNewValueBox().getValue();
                if (value != null) {
                    if (!seoCategoryIdsEditor.asEditor().getList().contains(value)) {
                        seoCategoryIdsEditor.asEditor().addValue(value);
                        Collections.sort(seoCategoryIdsEditor.getDataProvider().getList());
                        seoCategoryIdsEditor.getDataProvider().refresh();
                    }
                    seoCategoryIdsEditor.getNewValueBox().setValue(null);
                }
            }
        });
        seoCategoryIdsEditor.getRemoveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final SingleSelectionModel<Integer> selectionModel = (SingleSelectionModel<Integer>) seoCategoryIdsEditor
                        .getOtherSettingTable().getSelectionModel();

                if (selectionModel.getSelectedObject() != null) {
                    seoCategoryIdsEditor.asEditor().removeValue(selectionModel.getSelectedObject());
                    seoCategoryIdsEditor.getDataProvider().refresh();
                }
            }
        });
    }

    /**
     * Copies any changes into a new RESTServerSettingsV1 object so that it can be saved.
     *
     * @param displayedItem   The displayed server settings.
     * @param defaultSettings The default/unchanged server settings.
     * @return A new RESTServerSettingsV1 object containing the changes.
     */
    protected RESTServerSettingsV1 syncChanges(final RESTServerSettingsV1 displayedItem, final RESTServerSettingsV1 defaultSettings) {
        final RESTServerSettingsV1 newServerSettings = new RESTServerSettingsV1();

        if (!stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(displayedItem.getUiUrl(), defaultSettings.getUiUrl())) {
            newServerSettings.explicitSetUiUrl(displayedItem.getUiUrl());
        }
        if (!stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(displayedItem.getDocBuilderUrl(),
                defaultSettings.getDocBuilderUrl())) {
            newServerSettings.explicitSetDocBuilderUrl(displayedItem.getDocBuilderUrl());
        }
        if (!GWTUtilities.booleanEquals(displayedItem.isReadOnly(), defaultSettings.isReadOnly())) {
            newServerSettings.explicitSetReadOnly(displayedItem.isReadOnly());
        }
        if (!GWTUtilities.integerEquals(displayedItem.getJmsUpdateFrequency(), defaultSettings.getJmsUpdateFrequency())) {
            newServerSettings.explicitSetJmsUpdateFrequency(displayedItem.getJmsUpdateFrequency());
        }
        if (!GWTUtilities.objectEquals(displayedItem.getDefaultLocale(), defaultSettings.getDefaultLocale())) {
            newServerSettings.explicitSetDefaultLocale(displayedItem.getDefaultLocale());
        }
        if (!GWTUtilities.listsEqual(displayedItem.getDocBookTemplateIds(), defaultSettings.getDocBookTemplateIds())) {
            newServerSettings.explicitSetDocBookTemplateIds(displayedItem.getDocBookTemplateIds());
        }
        if (!GWTUtilities.listsEqual(displayedItem.getSeoCategoryIds(), defaultSettings.getSeoCategoryIds())) {
            newServerSettings.explicitSetSeoCategoryIds(displayedItem.getSeoCategoryIds());
        }
        if (!GWTUtilities.listsEqual(displayedItem.getZanataSettings().getItems(), defaultSettings.getZanataSettings().getItems())) {
            for (final RESTZanataServerSettingsCollectionItemV1 item : defaultSettings.getZanataSettings().getItems()) {
                // If an zanata server is removed, then it won't be in the displayed item, so we need to add it
                if (!displayedItem.getZanataSettings().getItems().contains(item)) {
                    // Check that its not set as a different state
                    boolean found = false;
                    for (final RESTZanataServerSettingsCollectionItemV1 displayItem : displayedItem.getZanataSettings().getItems()) {
                        if (GWTUtilities.compareStrings(displayItem.getItem().getId(), item.getItem().getId())) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        displayedItem.getZanataSettings().addRemoveItem(item.getItem());
                    }
                }
            }

            newServerSettings.explicitSetZanataSettings(displayedItem.getZanataSettings());
        }
        if (!GWTUtilities.listsEqual(displayedItem.getUndefinedSettings().getItems(), defaultSettings.getUndefinedSettings().getItems())) {
            for (final RESTServerUndefinedSettingCollectionItemV1 item : defaultSettings.getUndefinedSettings().getItems()) {
                // If a value is removed, then it won't be in the displayed item, so we need to add it
                if (!displayedItem.getUndefinedSettings().getItems().contains(item)) {
                    // Check that its not set as a different state
                    boolean found = false;
                    for (final RESTServerUndefinedSettingCollectionItemV1 displayItem : displayedItem.getUndefinedSettings().getItems()) {
                        if (GWTUtilities.compareStrings(displayItem.getItem().getKey(), item.getItem().getKey())) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        displayedItem.getUndefinedSettings().addRemoveItem(item.getItem());
                    }
                }
            }

            newServerSettings.explicitSetUndefinedSettings(displayedItem.getUndefinedSettings());
        }
        if (!GWTUtilities.listsEqual(displayedItem.getEntities().getUndefinedEntities().getItems(),
                defaultSettings.getEntities().getUndefinedEntities().getItems())) {
            for (final RESTServerUndefinedEntityCollectionItemV1 item : defaultSettings.getEntities().getUndefinedEntities().getItems()) {
                // If a value is removed, then it won't be in the displayed item, so we need to add it
                if (!displayedItem.getEntities().getUndefinedEntities().getItems().contains(item)) {
                    // Check that its not set as a different state
                    boolean found = false;
                    for (final RESTServerUndefinedEntityCollectionItemV1 displayItem : displayedItem.getEntities().getUndefinedEntities()
                            .getItems()) {
                        if (GWTUtilities.compareStrings(displayItem.getItem().getKey(), item.getItem().getKey())) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        displayedItem.getEntities().getUndefinedEntities().addRemoveItem(item.getItem());
                    }
                }
            }

            newServerSettings.getEntities().explicitSetUndefinedEntities(displayedItem.getEntities().getUndefinedEntities());
        }

        return newServerSettings;
    }

    protected RESTLocaleCollectionV1 getLocaleUpdateChanges(final RESTLocaleCollectionV1 displayedItem) {
        final RESTLocaleCollectionV1 updatedLocales = new RESTLocaleCollectionV1();

        for (final RESTLocaleCollectionItemV1 item : displayedItem.getItems()) {
            if (!(item.returnIsAddItem() || item.returnIsRemoveItem())
                    && item.getItem().getConfiguredParameters() != null
                    && !item.getItem().getConfiguredParameters().isEmpty()) {
                updatedLocales.getItems().add(item);
            }
        }

        return updatedLocales;
    }

    protected RESTLocaleCollectionV1 getLocaleCreateChanges(final RESTLocaleCollectionV1 displayedItem) {
        final RESTLocaleCollectionV1 createdLocales = new RESTLocaleCollectionV1();

        for (final RESTLocaleCollectionItemV1 item : displayedItem.getItems()) {
            if (item.returnIsAddItem()) {
                createdLocales.getItems().add(item);
            }
        }

        return createdLocales;
    }

    public boolean hasUnsavedChanges(final RESTServerSettingsV1 defaultSettings) {
        // sync the UI with the underlying value
        getDisplay().getServerSettingsDriver().flush();
        getDisplay().getServerEntitiesDriver().flush();

        final RESTServerSettingsV1 displayedItem = currentServerSettings;

        if (!stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(displayedItem.getUiUrl(), defaultSettings.getUiUrl())) {
            return true;
        }
        if (!stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(displayedItem.getDocBuilderUrl(),
                defaultSettings.getDocBuilderUrl())) {
            return true;
        }
        if (!GWTUtilities.booleanEquals(displayedItem.isReadOnly(), defaultSettings.isReadOnly())) {
            return true;
        }
        if (!GWTUtilities.integerEquals(displayedItem.getJmsUpdateFrequency(), defaultSettings.getJmsUpdateFrequency())) {
            return true;
        }
        if (!GWTUtilities.objectEquals(displayedItem.getDefaultLocale(), defaultSettings.getDefaultLocale())) {
            return true;
        }
        if (!GWTUtilities.listsEqual(displayedItem.getDocBookTemplateIds(), defaultSettings.getDocBookTemplateIds())) {
            return true;
        }
        if (!GWTUtilities.listsEqual(displayedItem.getSeoCategoryIds(), defaultSettings.getSeoCategoryIds())) {
            return true;
        }
        if (!GWTUtilities.listsEqual(displayedItem.getZanataSettings().getItems(), defaultSettings.getZanataSettings().getItems())) {
            return true;
        }
        if (!GWTUtilities.listsEqual(displayedItem.getUndefinedSettings().getItems(), defaultSettings.getUndefinedSettings().getItems())) {
            return true;
        }
        if (!GWTUtilities.listsEqual(displayedItem.getEntities().getUndefinedEntities().getItems(),
                defaultSettings.getEntities().getUndefinedEntities().getItems())) {
            return true;
        }

        return false;
    }

    public boolean hasUnsavedChanges(final RESTLocaleCollectionV1 defaultLocales) {
        // Sync the changes
        getDisplay().getLocalesDriver().flush();

        final RESTLocaleCollectionV1 displayedLocales = currentLocales;

        return !GWTUtilities.listsEqual(displayedLocales.getItems(), defaultLocales.getItems());
    }

    protected List<RESTLocaleV1> getDefaultLocales() {
        final List<RESTLocaleCollectionItemV1> localeItems = new ArrayList<RESTLocaleCollectionItemV1>(display.getLocalesEditor().getDataProvider().getList());
        final LinkedList<RESTLocaleV1> locales = new LinkedList<RESTLocaleV1>();

        for (final RESTLocaleCollectionItemV1 localeItem : localeItems) {
            if (localeItem.getState() != RESTLocaleCollectionItemV1.REMOVE_STATE) {
                locales.add(localeItem.getItem());
            }
        }

        Collections.sort(locales, localesSort);
        locales.addFirst(null);
        return locales;
    }

    protected boolean doesLocaleAlreadyExist(final List<RESTLocaleCollectionItemV1> locales, final String value) {
        for (final RESTLocaleCollectionItemV1 item : locales) {
            if (value.equals(item.getItem().getValue())) {
                return true;
            }
        }

        return false;
    }

    protected boolean doesZanataServerAlreadyExist(final List<RESTZanataServerSettingsCollectionItemV1> servers, final String id) {
        for (final RESTZanataServerSettingsCollectionItemV1 item : servers) {
            if (id.equals(item.getItem().getId())) {
                return true;
            }
        }

        return false;
    }

    protected <U extends RESTBaseUndefinedSettingCollectionItemV1<?, ?, U>> boolean doesSettingAlreadyExist(final List<U> settings,
            final String key) {
        for (final U item : settings) {
            if (key.equals(item.getItem().getKey())) {
                return true;
            }
        }

        return false;
    }

    // Empty interface declaration, similar to UiBinder
    public interface ServerSettingsPresenterDriver extends SimpleBeanEditorDriver<RESTServerSettingsV1, RESTServerSettingsV1DetailsEditor> {
    }

    // Empty interface declaration, similar to UiBinder
    public interface ServerEntitiesPresenterDriver extends SimpleBeanEditorDriver<RESTServerEntitiesV1, RESTServerEntitiesV1DetailsEditor> {
    }

    // Empty interface declaration, similar to UiBinder
    public interface LocalesPresenterDriver extends SimpleBeanEditorDriver<RESTLocaleCollectionV1, RESTLocaleCollectionV1Editor> {
    }

    public interface Display extends BaseTemplateViewInterface {
        SimplePanel getServerSettingsPanel();

        SimplePanel getServerEntitiesPanel();

        PushButton getAppSettingsButton();

        Label getAppSettingsDownButton();

        PushButton getEntitySettingsButton();

        Label getEntitySettingsDownButton();

        PushButton getSaveButton();

        void display(RESTServerSettingsV1 serverSettings, RESTLocaleCollectionV1 locales, boolean readonly);

        SimpleBeanEditorDriver<RESTServerSettingsV1, RESTServerSettingsV1DetailsEditor> getServerSettingsDriver();

        RESTServerSettingsV1DetailsEditor getSettingsEditor();

        SimpleBeanEditorDriver<RESTServerEntitiesV1, RESTServerEntitiesV1DetailsEditor> getServerEntitiesDriver();

        RESTServerEntitiesV1DetailsEditor getEntitiesEditor();

        SimpleBeanEditorDriver<RESTLocaleCollectionV1, RESTLocaleCollectionV1Editor> getLocalesDriver();

        RESTLocaleCollectionV1Editor getLocalesEditor();
    }
}
