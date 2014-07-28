/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * Constructs the shortcut panel to be displayed at the top of the screen.
 */
public class TopShortcutView extends FlexTable {
    private final AnchorButton docbuilder = UIUtilities.createMenuButton(PressGangCCMSUI.INSTANCE.DocBuilder(),
            Constants.ElementIDs.DOCBUILDER_NAVIGATION_BUTTON_ID.getId());

    private final AnchorMenuBar menus = new AnchorMenuBar();

    private final AnchorMenuBar create = new AnchorMenuBar(true);
    private final AnchorMenuBar search = new AnchorMenuBar(true);
    private final AnchorMenuBar entities = new AnchorMenuBar(true);
    private final AnchorMenuBar advanced = new AnchorMenuBar(true);

    private final MenuItem createSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.Create(), create);
    private final MenuItem searchSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.SearchMenu(), search);
    private final MenuItem entitiesSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.EntitiesMenu(), entities);
    private final MenuItem adminSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.Administration(), advanced);

    private final AnchorMenuItem createTopic = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.CreateTopic(), false, (Command) null);
    private final AnchorMenuItem createContentSpec = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.CreateContentSpec(), false, (Command) null);

    private final AnchorMenuItem bulkTagging = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.BulkTagging(), false, (Command) null);
    private final AnchorMenuItem stringConstants = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.StringConstants(), false, (Command) null);
    private final AnchorMenuItem integerConstants = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.IntegerConstants(), false, (Command) null);
    private final AnchorMenuItem blobConstants = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.BlobConstants(), false, (Command) null);
    private final AnchorMenuItem propertyTags = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.PropertyTags(), false, (Command) null);
    private final AnchorMenuItem propertyTagCategories = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.PropertyTagCategories(), false, (Command) null);
    private final AnchorMenuItem processes = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.Processes(), false, (Command) null);
    private final AnchorMenuItem monitoring = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.Monitoring(), false, (Command) null);
    private final AnchorMenuItem sysinfo = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.SystemInfo(), false, (Command) null);
    private final AnchorMenuItem serverSettings = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.ServerSettings(), false, (Command) null);

    private final AnchorMenuItem images = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.Images(), false, (Command) null);
    private final AnchorMenuItem files = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.Files(), false, (Command) null);
    private final AnchorMenuItem tags = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.Tags(), false, (Command) null);
    private final AnchorMenuItem categories = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.Categories(), false, (Command) null);
    private final AnchorMenuItem projects = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.Projects(), false, (Command) null);

    private final AnchorMenuItem searchTopics = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.SearchTopics(), false, (Command) null);
    private final AnchorMenuItem searchContentSpec = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.SearchContentSpecs(), false, (Command) null);
    private final AnchorMenuItem searchTranslations = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.SearchTranslations(), false, (Command) null);

    boolean hasFocus = false;

    public TopShortcutView() {
        menus.setAutoOpen(true);
        menus.setAnimationEnabled(true);

        menus.addItem(createSubMenu);
        menus.addItem(entitiesSubMenu);
        menus.addItem(searchSubMenu);
        menus.addItem(adminSubMenu);

        create.addItem(createTopic);
        create.addItem(createContentSpec);

        search.addItem(searchTopics);
        search.addItem(searchContentSpec);
        search.addItem(searchTranslations);

        entities.addItem(images);
        entities.addItem(files);
        entities.addItem(tags);
        entities.addItem(categories);
        entities.addItem(projects);

        advanced.addItem(bulkTagging);
        advanced.addItem(stringConstants);
        advanced.addItem(integerConstants);
        advanced.addItem(blobConstants);
        advanced.addItem(propertyTags);
        advanced.addItem(propertyTagCategories);
        advanced.addItem(processes);
        advanced.addItem(monitoring);
        advanced.addItem(sysinfo);
        advanced.addItem(serverSettings);

        setWidget(0, 0, docbuilder);
        setWidget(0, 1, menus);

        addStyleName(CSSConstants.Template.TOP_SHORTCUT_PANEL);

        menus.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU);

        create.addStyleName(CSSConstants.Template.TOP_SHORTCUT_SUB_MENU);
        search.addStyleName(CSSConstants.Template.TOP_SHORTCUT_SUB_MENU);
        entities.addStyleName(CSSConstants.Template.TOP_SHORTCUT_SUB_MENU);
        advanced.addStyleName(CSSConstants.Template.TOP_SHORTCUT_SUB_MENU);

        createSubMenu.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU_ITEM);
        searchSubMenu.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU_ITEM);
        entitiesSubMenu.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU_ITEM);
        adminSubMenu.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU_ITEM);

        // Set up auto hiding
        final Timer timer = new Timer() {
            @Override
            public void run() {
                // If the menu regained focus return, otherwise close the menus
                if (hasFocus) {
                    return;
                } else {
                    menus.closeAllChildren(false);
                }
            }
        };

        final MouseOutHandler mouseOutHandler = new MouseOutHandler() {
            @Override
            public void onMouseOut(final MouseOutEvent event) {
                hasFocus = false;
                timer.schedule(200);
            }
        };

        final FocusHandler focusHandler = new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                // Some item in the menu has regained focus so set that we have focus and cancel the timer
                hasFocus = true;
                timer.cancel();
            }
        };

        menus.addDomHandler(mouseOutHandler, MouseOutEvent.getType());
        create.addDomHandler(mouseOutHandler, MouseOutEvent.getType());
        search.addDomHandler(mouseOutHandler, MouseOutEvent.getType());
        entities.addDomHandler(mouseOutHandler, MouseOutEvent.getType());
        advanced.addDomHandler(mouseOutHandler, MouseOutEvent.getType());
        menus.addDomHandler(focusHandler, FocusEvent.getType());
        create.addDomHandler(focusHandler, FocusEvent.getType());
        search.addDomHandler(focusHandler, FocusEvent.getType());
        entities.addDomHandler(focusHandler, FocusEvent.getType());
        advanced.addDomHandler(focusHandler, FocusEvent.getType());
    }

    public AnchorButton getDocbuilder() {
        return docbuilder;
    }

    public AnchorMenuItem getCreateTopic() {
        return createTopic;
    }

    public AnchorMenuItem getCreateContentSpec() {
        return createContentSpec;
    }

    public AnchorMenuItem getBulkTagging() {
        return bulkTagging;
    }

    public AnchorMenuItem getStringConstants() {
        return stringConstants;
    }

    public AnchorMenuItem getIntegerConstants() {
        return integerConstants;
    }

    public AnchorMenuItem getBlobConstants() {
        return blobConstants;
    }

    public AnchorMenuItem getPropertyTags() {
        return propertyTags;
    }

    public AnchorMenuItem getPropertyTagCategories() {
        return propertyTagCategories;
    }

    public AnchorMenuItem getMonitoring() {
        return monitoring;
    }

    public AnchorMenuItem getImages() {
        return images;
    }

    public AnchorMenuItem getFiles() {
        return files;
    }

    public AnchorMenuItem getTags() {
        return tags;
    }

    public AnchorMenuItem getCategories() {
        return categories;
    }

    public AnchorMenuItem getProjects() {
        return projects;
    }

    public AnchorMenuItem getSearchTopics() {
        return searchTopics;
    }

    public AnchorMenuItem getSearchContentSpec() {
        return searchContentSpec;
    }

    public AnchorMenuItem getSearchTranslations() {
        return searchTranslations;
    }

    public MenuBar getSearch() {
        return search;
    }

    public MenuBar getEntities() {
        return entities;
    }

    public MenuBar getAdvanced() {
        return advanced;
    }

    public MenuItem getSearchSubMenu() {
        return searchSubMenu;
    }

    public MenuItem getEntitiesSubMenu() {
        return entitiesSubMenu;
    }

    public MenuItem getAdminSubMenu() {
        return adminSubMenu;
    }

    public AnchorMenuItem getSysinfo() {
        return sysinfo;
    }

    public AnchorMenuItem getProcesses() {
        return processes;
    }

    public AnchorMenuItem getServerSettings() {
        return serverSettings;
    }

    public MenuItem getCreateSubMenu() {
        return createSubMenu;
    }
}