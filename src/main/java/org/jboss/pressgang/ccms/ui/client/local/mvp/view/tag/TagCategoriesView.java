package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.PushButton;

public class TagCategoriesView
        extends
        BaseOrderedChildrenView<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, RESTCategoryV1, RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1, RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>
        implements TagCategoriesPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton tagDetails = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagDetails());
    private final PushButton tagProjects = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagProjects());
    private final PushButton tagCategories = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagCategories());

    private final TextColumn<RESTCategoryCollectionItemV1> idColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTCategoryCollectionItemV1 object) {
            if (object == null)
                return null + "";
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTCategoryCollectionItemV1> nameColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTCategoryCollectionItemV1 object) {
            if (object == null)
                return null + "";
            return object.getItem().getName();
        }
    };

    private final Column<RESTCategoryCollectionItemV1, String> buttonColumn = new Column<RESTCategoryCollectionItemV1, String>(
            new ButtonCell()) {
        @Override
        public String getValue(final RESTCategoryCollectionItemV1 object) {
            if (getOriginalEntity() != null) {
                if (ComponentCategoryV1.containsTag(object.getItem(), getOriginalEntity().getId())) {
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
            if (object == null)
                return null + "";
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagNameColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            if (object == null)
                return null + "";
            return object.getItem().getName();
        }
    };

    final DisableableButtonCell up = new DisableableButtonCell();
    private final Column<RESTTagInCategoryCollectionItemV1, String> tagUpButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(
            up) {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.Up();
        }
    };

    final DisableableButtonCell down = new DisableableButtonCell();
    private final Column<RESTTagInCategoryCollectionItemV1, String> tagDownButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(
            down) {
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.Down();
        }
    };

    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getExistingChildDownButtonColumn() {
        return tagDownButtonColumn;
    }

    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getExistingChildUpButtonColumn() {
        return tagUpButtonColumn;
    }

    @Override
    public Column<RESTCategoryCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return buttonColumn;
    }

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

    public TagCategoriesView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagCategories());
        populateTopActionBar();

        getPossibleChildrenResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.CategoryID());
        getPossibleChildrenResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.CategoryName());
        getPossibleChildrenResults().addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        getExistingChildrenResults().addColumn(tagIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        getExistingChildrenResults().addColumn(tagNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        getExistingChildrenResults().addColumn(tagUpButtonColumn, PressGangCCMSUI.INSTANCE.Up());
        getExistingChildrenResults().addColumn(tagDownButtonColumn, PressGangCCMSUI.INSTANCE.Down());

    }

    private void populateTopActionBar() {
        this.addActionButton(this.getTagDetails());
        this.addActionButton(this.getTagProjects());
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TagCategories()));
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
    }
}
