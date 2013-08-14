package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * Constructs the shortcut panel to be displayed at the top of the screen.
 */
public class TopShortcutView extends HorizontalPanel {
    private final PushButton home = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Home(), Constants.ElementIDs.HOME_NAVIGATION_BUTTON_ID.getId());
    private final PushButton docbuilder = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.DocBuilder(), Constants.ElementIDs.DOCBUILDER_NAVIGATION_BUTTON_ID.getId());
    private final PushButton createTopic = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.CreateTopic(), Constants.ElementIDs.CREATE_TOPIC_NAVIGATION_BUTTON_ID.getId());
    private final PushButton createContentSpec = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.CreateContentSpec(), Constants.ElementIDs.CREATE_SPEC_NAVIGATION_BUTTON_ID.getId());
    private final PushButton bug = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CreateBug(), false, true, Constants.ElementIDs.CREATE_BUG_NAVIGATION_BUTTON_ID.getId());
    private final PushButton reports = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Reports(), false, true, Constants.ElementIDs.REPORTS_NAVIGATION_BUTTON_ID.getId());
    private final MenuBar menus = new MenuBar();
    private final MenuBar search = new MenuBar(true);
    private final MenuBar entities = new MenuBar(true);
    private final MenuBar advanced = new MenuBar(true);

    private final MenuItem bulkTagging = new MenuItem(PressGangCCMSUI.INSTANCE.BulkTagging(), false, (Command)null);
    private final MenuItem stringConstants = new MenuItem(PressGangCCMSUI.INSTANCE.StringConstants(), false, (Command)null);
    private final MenuItem integerConstants = new MenuItem(PressGangCCMSUI.INSTANCE.IntegerConstants(), false, (Command)null);
    private final MenuItem blobConstants = new MenuItem(PressGangCCMSUI.INSTANCE.BlobConstants(), false, (Command)null);
    private final MenuItem propertyTags = new MenuItem(PressGangCCMSUI.INSTANCE.PropertyTags(), false, (Command)null);
    private final MenuItem propertyTagCategories = new MenuItem(PressGangCCMSUI.INSTANCE.PropertyTagCategories(), false, (Command)null);
    private final MenuItem monitoring = new MenuItem(PressGangCCMSUI.INSTANCE.Monitoring(), false, (Command)null);

    private final MenuItem images = new MenuItem(PressGangCCMSUI.INSTANCE.Images(), false, (Command)null);
    private final MenuItem files = new MenuItem(PressGangCCMSUI.INSTANCE.Files(), false, (Command)null);
    private final MenuItem tags = new MenuItem(PressGangCCMSUI.INSTANCE.Tags(), false, (Command)null);
    private final MenuItem categories = new MenuItem(PressGangCCMSUI.INSTANCE.Categories(), false, (Command)null);
    private final MenuItem projects = new MenuItem(PressGangCCMSUI.INSTANCE.Projects(), false, (Command)null);

    private final MenuItem searchTopics = new MenuItem(PressGangCCMSUI.INSTANCE.SearchTopics(), false, (Command)null);
    private final MenuItem searchContentSpec = new MenuItem(PressGangCCMSUI.INSTANCE.SearchContentSpecs(), false, (Command)null);
    private final MenuItem searchTranslations = new MenuItem(PressGangCCMSUI.INSTANCE.SearchTranslations(), false, (Command)null);


    public TopShortcutView() {
        menus.setAutoOpen(true);

        final MenuItem searchSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.Search(), search);
        final MenuItem entitiesSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.Entities(), entities);
        final MenuItem advancedSubMenu = new MenuItem(PressGangCCMSUI.INSTANCE.Advanced(), advanced);

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
    }

    public PushButton getHome() {
        return home;
    }

    public PushButton getDocbuilder() {
        return docbuilder;
    }

    public PushButton getCreateTopic() {
        return createTopic;
    }

    public PushButton getCreateContentSpec() {
        return createContentSpec;
    }

    public PushButton getBug() {
        return bug;
    }

    public PushButton getReports() {
        return reports;
    }

    public MenuItem getBulkTagging() {
        return bulkTagging;
    }

    public MenuItem getStringConstants() {
        return stringConstants;
    }

    public MenuItem getIntegerConstants() {
        return integerConstants;
    }

    public MenuItem getBlobConstants() {
        return blobConstants;
    }

    public MenuItem getPropertyTags() {
        return propertyTags;
    }

    public MenuItem getPropertyTagCategories() {
        return propertyTagCategories;
    }

    public MenuItem getMonitoring() {
        return monitoring;
    }

    public MenuItem getImages() {
        return images;
    }

    public MenuItem getFiles() {
        return files;
    }

    public MenuItem getTags() {
        return tags;
    }

    public MenuItem getCategories() {
        return categories;
    }

    public MenuItem getProjects() {
        return projects;
    }

    public MenuItem getSearchTopics() {
        return searchTopics;
    }

    public MenuItem getSearchContentSpec() {
        return searchContentSpec;
    }

    public MenuItem getSearchTranslations() {
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
}
