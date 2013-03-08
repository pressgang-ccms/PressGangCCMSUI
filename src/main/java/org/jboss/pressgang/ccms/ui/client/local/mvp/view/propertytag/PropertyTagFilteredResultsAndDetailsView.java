package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertytag;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

public class PropertyTagFilteredResultsAndDetailsView extends
        BaseSearchAndEditView<RESTPropertyTagV1, RESTPropertyTagCollectionV1, RESTPropertyTagCollectionItemV1> implements
        PropertyTagFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ProjectDetails());
    private final PushButton children = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ProjectTags());

    private final Label detailsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ProjectDetails());
    private final Label childrenDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ProjectTags());

    @Override
    public PushButton getChildren() {
        return children;
    }

    @Override
    public PushButton getDetails() {
        return details;
    }

    @Override
    public Label getChildrenDown() {
        return childrenDown;
    }

    @Override
    public Label getDetailsDown() {
        return detailsDown;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    public PropertyTagFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedProperties());
        populateTopActionBar();
        addSpacerToShortcutPanels();
        super.initialize(true);
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getDetails());
        this.addActionButton(this.getChildren());
        this.addActionButton(this.getSave());
    }
}