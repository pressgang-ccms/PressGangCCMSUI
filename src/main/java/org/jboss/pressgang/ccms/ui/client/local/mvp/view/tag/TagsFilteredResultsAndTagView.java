package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

public class TagsFilteredResultsAndTagView
        extends BaseSearchAndEditView<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>
        implements TagsFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton extendedProperties = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ExtendedProperties());
    private final PushButton tagDetails = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.TagDetails());
    private final PushButton tagProjects = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.TagProjects());
    private final PushButton tagCategories = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.TagCategories());

    private final Label extendedPropertiesDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ExtendedProperties());
    private final Label tagDetailsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.TagDetails());
    private final Label tagProjectsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.TagProjects());
    private final Label tagCategoriesDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.TagCategories());

    @Override
    public PushButton getTagCategories() {
        return tagCategories;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    @Override
    public PushButton getTagProjects() {
        return tagProjects;
    }

    @Override
    public PushButton getTagDetails() {
        return tagDetails;
    }

    @Override
    public Label getTagCategoriesDown() {
        return tagCategoriesDown;
    }

    @Override
    public Label getTagProjectsDown() {
        return tagProjectsDown;
    }

    @Override
    public Label getTagDetailsDown() {
        return tagDetailsDown;
    }

    public TagsFilteredResultsAndTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags());
        populateTopActionBar();
        addSpacerToShortcutPanels();
        super.initialize(true);
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getTagDetails());
        this.addActionButton(this.getTagProjects());
        this.addActionButton(this.getTagCategories());
        this.addActionButton(this.getExtendedProperties());
        this.addActionButton(this.getSave());
    }

    @Override
    public PushButton getExtendedProperties() {
        return extendedProperties;
    }

    @Override
    public Label getExtendedPropertiesDown() {
        return extendedPropertiesDown;
    }
}
