package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertycategory;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

public class PropertyCategoryFilteredResultsAndDetailsView extends BaseSearchAndEditView<RESTPropertyCategoryV1, RESTPropertyCategoryCollectionV1, RESTPropertyCategoryCollectionItemV1>
        implements PropertyCategoryFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryDetails());
    private final PushButton children = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryExtendedProperties());

    private final Label detailsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryDetails());
    private final Label childrenDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ExtendedPropertyCategoryExtendedProperties());

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

    public PropertyCategoryFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedPropertyCategories());
        populateTopActionBar();
        super.initialize(true);
    }

    @Override
    protected void initialiseShortcuts() {
        super.initialiseShortcuts();
        getShortcuts().getAdvancedSubMenu().getPropertyTagCategoriesButton().setDown(true);
        getShortcuts().setSpacerEnabled(true);
        getShortcuts().getAdvancedSubMenu().setOpen(true);
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getDetails());
        this.addActionButton(this.getChildren());
        this.addActionButton(this.getSave());
    }
}