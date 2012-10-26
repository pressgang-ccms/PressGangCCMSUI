package org.jboss.pressgang.ccms.ui.client.local.mvp.view.category;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.PushButton;

public class CategoryTagView extends
        BaseOrderedChildrenView<RESTCategoryV1, RESTTagCollectionItemV1, RESTTagInCategoryCollectionItemV1> implements
        CategoryTagPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CategoryDetails());
    private final PushButton children = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CategoryTags());

    private final TextColumn<RESTTagCollectionItemV1> tagsIdColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null)
                return object.getItem().getId().toString();
            return null + "";
        }
    };

    private final TextColumn<RESTTagCollectionItemV1> tagsNameColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null)
                return object.getItem().getName();
            return null + "";
        }
    };

    private final Column<RESTTagCollectionItemV1, String> tagsButtonColumn = new Column<RESTTagCollectionItemV1, String>(
            new ButtonCell()) {
        @Override
        public String getValue(final RESTTagCollectionItemV1 object) {
            if (getOriginalEntity() != null && object != null && object.getItem().getId() != null) {
                if (ComponentCategoryV1.containsTag(getOriginalEntity(), object.getItem().getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagIdColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null)
                return object.getItem().getId().toString();
            return null + "";

        }
    };

    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagNameColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null)
                return object.getItem().getName();
            return null + "";
        }
    };

    private final Column<RESTTagInCategoryCollectionItemV1, String> tagUpButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(
            new ButtonCell()) {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.Up();
        }
    };

    private final Column<RESTTagInCategoryCollectionItemV1, String> tagDownButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(
            new ButtonCell()) {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.Down();
        }
    };

    public PushButton getChildren() {
        return children;
    }

    public PushButton getDetails() {
        return details;
    }

    public PushButton getSave() {
        return save;
    }

    public CategoryTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());
        
        populateTopActionBar();

        getPossibleChildrenResults().addColumn(tagsIdColumn, PressGangCCMSUI.INSTANCE.CategoryID());
        getPossibleChildrenResults().addColumn(tagsNameColumn, PressGangCCMSUI.INSTANCE.CategoryName());
        getPossibleChildrenResults().addColumn(tagsButtonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        getExistingChildrenResults().addColumn(tagIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        getExistingChildrenResults().addColumn(tagNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        getExistingChildrenResults().addColumn(tagUpButtonColumn, PressGangCCMSUI.INSTANCE.Up());
        getExistingChildrenResults().addColumn(tagDownButtonColumn, PressGangCCMSUI.INSTANCE.Down());
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getDetails());
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.CategoryTags()));
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
    }

}
