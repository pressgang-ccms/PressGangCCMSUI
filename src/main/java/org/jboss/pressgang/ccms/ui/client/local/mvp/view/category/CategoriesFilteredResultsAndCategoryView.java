package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

public class CategoriesFilteredResultsAndCategoryView extends
        BaseSearchAndEditView<RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1> implements
        CategoriesFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.CategoryDetails());
    private final PushButton children = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.CategoryTags());

    private final Label detailsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.CategoryDetails());
    private final Label childrenDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.CategoryTags());

    @Override
    public PushButton getChildren() {
        return children;
    }

    @Override
    public PushButton getDetails() {
        return details;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    @Override
    public Label getChildrenDown() {
        return childrenDown;
    }

    @Override
    public Label getDetailsDown() {
        return detailsDown;
    }

    public CategoriesFilteredResultsAndCategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());
        populateTopActionBar();
        addSpacerToShortcutPanels();
    }


    private void populateTopActionBar() {
        this.addActionButton(this.getDetails());
        this.addActionButton(this.getChildren());
        this.addActionButton(this.getSave());
    }
}
