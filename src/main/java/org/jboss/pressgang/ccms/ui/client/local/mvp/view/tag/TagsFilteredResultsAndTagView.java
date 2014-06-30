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
import org.jetbrains.annotations.NotNull;

public class TagsFilteredResultsAndTagView
        extends BaseSearchAndEditView<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>
        implements TagsFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton extendedProperties = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ExtendedProperties());
    private final PushButton tagDetails = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.TagDetails());
    private final PushButton tagProjects = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.TagProjects());
    private final PushButton tagCategories = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.TagCategories());

    private final Label extendedPropertiesDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ExtendedProperties());
    private final Label tagDetailsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.TagDetails());
    private final Label tagProjectsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.TagProjects());
    private final Label tagCategoriesDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.TagCategories());

    @Override
    @NotNull
    public PushButton getTagCategories() {
        return tagCategories;
    }

    @Override
    @NotNull
    public PushButton getSave() {
        return save;
    }

    @Override
    @NotNull
    public PushButton getTagProjects() {
        return tagProjects;
    }

    @Override
    @NotNull
    public PushButton getTagDetails() {
        return tagDetails;
    }

    @Override
    @NotNull
    public Label getTagCategoriesDown() {
        return tagCategoriesDown;
    }

    @Override
    @NotNull
    public Label getTagProjectsDown() {
        return tagProjectsDown;
    }

    @Override
    @NotNull
    public Label getTagDetailsDown() {
        return tagDetailsDown;
    }

    public TagsFilteredResultsAndTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags());
        populateTopActionBar();
        super.initialize(true);
    }

    @Override
    protected void initialiseShortcuts() {
        super.initialiseShortcuts();
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getTagDetails());
        this.addActionButton(this.getTagProjects());
        this.addActionButton(this.getTagCategories());
        this.addActionButton(this.getExtendedProperties(), true);
        this.addActionButton(this.getSave());
    }

    @Override
    @NotNull
    public PushButton getExtendedProperties() {
        return extendedProperties;
    }

    @Override
    @NotNull
    public Label getExtendedPropertiesDown() {
        return extendedPropertiesDown;
    }
}
