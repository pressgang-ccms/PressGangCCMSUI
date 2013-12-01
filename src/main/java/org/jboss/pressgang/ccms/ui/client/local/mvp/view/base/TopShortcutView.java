package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AnchorButton;
import com.google.gwt.user.client.ui.AnchorMenuItem;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * Constructs the shortcut panel to be displayed at the top of the screen.
 */
public class TopShortcutView extends HorizontalPanel {
    private final AnchorButton home = UIUtilities.createMenuButton(PressGangCCMSUI.INSTANCE.Home(),
            Constants.ElementIDs.HOME_NAVIGATION_BUTTON_ID.getId());
    private final AnchorButton docbuilder = UIUtilities.createMenuButton(PressGangCCMSUI.INSTANCE.DocBuilder(),
            Constants.ElementIDs.DOCBUILDER_NAVIGATION_BUTTON_ID.getId());
    private final AnchorButton createTopic = UIUtilities.createMenuButton(PressGangCCMSUI.INSTANCE.CreateTopic(),
            Constants.ElementIDs.CREATE_TOPIC_NAVIGATION_BUTTON_ID.getId());
    private final AnchorButton createContentSpec = UIUtilities.createMenuButton(PressGangCCMSUI.INSTANCE.CreateContentSpec(),
            Constants.ElementIDs.CREATE_SPEC_NAVIGATION_BUTTON_ID.getId());
    private final AnchorButton bug = UIUtilities.createMenuButton(PressGangCCMSUI.INSTANCE.CreateBug(), false, true,
            Constants.ElementIDs.CREATE_BUG_NAVIGATION_BUTTON_ID.getId());
    private final AnchorButton reports = UIUtilities.createMenuButton(PressGangCCMSUI.INSTANCE.Reports(), false, true,
            Constants.ElementIDs.REPORTS_NAVIGATION_BUTTON_ID.getId());

    private final MenuBar menus = new MenuBar();

    private final MenuBar search = new MenuBar(true);
    private final MenuBar entities = new MenuBar(true);
    private final MenuBar advanced = new MenuBar(true);

    private final MenuItem searchSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.Search(), search);
    private final MenuItem entitiesSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.Entities(), entities);
    private final MenuItem advancedSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.Advanced(), advanced);

    private final AnchorMenuItem bulkTagging = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.BulkTagging(), false, (Command) null);
    private final AnchorMenuItem stringConstants = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.StringConstants(), false, (Command) null);
    private final AnchorMenuItem integerConstants = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.IntegerConstants(), false, (Command) null);
    private final AnchorMenuItem blobConstants = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.BlobConstants(), false, (Command) null);
    private final AnchorMenuItem propertyTags = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.PropertyTags(), false, (Command) null);
    private final AnchorMenuItem propertyTagCategories = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.PropertyTagCategories(), false, (Command) null);
    private final AnchorMenuItem monitoring = new AnchorMenuItem(PressGangCCMSUI.INSTANCE.Monitoring(), false, (Command) null);

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

        menus.addItem(searchSubMenu);
        menus.addItem(entitiesSubMenu);
        menus.addItem(advancedSubMenu);

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
        advanced.addItem(monitoring);

        this.add(home);
        this.add(docbuilder);
        this.add(createTopic);
        this.add(createContentSpec);
        this.add(bug);
        this.add(reports);
        this.add(menus);

        this.addStyleName(CSSConstants.Template.TOP_SHORTCUT_PANEL);

        menus.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU);

        search.addStyleName(CSSConstants.Template.TOP_SHORTCUT_SUB_MENU);
        entities.addStyleName(CSSConstants.Template.TOP_SHORTCUT_SUB_MENU);
        advanced.addStyleName(CSSConstants.Template.TOP_SHORTCUT_SUB_MENU);

        searchSubMenu.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU_ITEM);
        entitiesSubMenu.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU_ITEM);
        advancedSubMenu.addStyleName(CSSConstants.Template.TOP_SHORTCUT_MENU_ITEM);

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
        search.addDomHandler(mouseOutHandler, MouseOutEvent.getType());
        entities.addDomHandler(mouseOutHandler, MouseOutEvent.getType());
        advanced.addDomHandler(mouseOutHandler, MouseOutEvent.getType());
        menus.addDomHandler(focusHandler, FocusEvent.getType());
        search.addDomHandler(focusHandler, FocusEvent.getType());
        entities.addDomHandler(focusHandler, FocusEvent.getType());
        advanced.addDomHandler(focusHandler, FocusEvent.getType());
    }

    public AnchorButton getHome() {
        return home;
    }

    public AnchorButton getDocbuilder() {
        return docbuilder;
    }

    public AnchorButton getCreateTopic() {
        return createTopic;
    }

    public AnchorButton getCreateContentSpec() {
        return createContentSpec;
    }

    public AnchorButton getBug() {
        return bug;
    }

    public AnchorButton getReports() {
        return reports;
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

    public MenuItem getAdvancedSubMenu() {
        return advancedSubMenu;
    }
}