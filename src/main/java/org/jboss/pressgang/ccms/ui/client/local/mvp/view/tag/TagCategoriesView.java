package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TagCategoriesView
        extends
        BaseExtendedChildrenView<
                RESTTagV1,
                RESTCategoryCollectionItemV1,
                RESTTagInCategoryV1,
                RESTTagInCategoryCollectionV1,
                RESTTagInCategoryCollectionItemV1>
        implements TagCategoriesPresenter.Display {

    boolean readOnly = false;

    @NotNull
    private final TextColumn<RESTCategoryCollectionItemV1> idColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTCategoryCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getId().toString();

        }
    };

    @NotNull
    private final TextColumn<RESTCategoryCollectionItemV1> nameColumn = new TextColumn<RESTCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTCategoryCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getName();
        }
    };

    private final DisableableButtonCell button = new DisableableButtonCell();
    @NotNull
    private final Column<RESTCategoryCollectionItemV1, String> buttonColumn = new Column<RESTCategoryCollectionItemV1, String>(button) {
        @NotNull
        @Override
        public String getValue(@NotNull final RESTCategoryCollectionItemV1 object) {
            button.setEnabled(!readOnly);
            if (getOriginalEntity() != null) {
                if (ComponentCategoryV1.containsTag(object.getItem(), getOriginalEntity().getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            button.setEnabled(false);
            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @NotNull
    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagIdColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagInCategoryCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getId().toString();

        }
    };

    @NotNull
    private final TextColumn<RESTTagInCategoryCollectionItemV1> tagNameColumn = new TextColumn<RESTTagInCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTagInCategoryCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getName();
        }
    };

    @NotNull
    final DisableableButtonCell up = new DisableableButtonCell();

    @NotNull
    private final Column<RESTTagInCategoryCollectionItemV1, String> tagUpButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(up) {
        @NotNull
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            up.setEnabled(!readOnly);
            return PressGangCCMSUI.INSTANCE.Up();
        }
    };

    @NotNull
    final DisableableButtonCell down = new DisableableButtonCell();

    @NotNull
    private final Column<RESTTagInCategoryCollectionItemV1, String> tagDownButtonColumn = new Column<RESTTagInCategoryCollectionItemV1, String>(down) {
        @NotNull
        @Override
        public String getValue(final RESTTagInCategoryCollectionItemV1 object) {
            down.setEnabled(!readOnly);
            return PressGangCCMSUI.INSTANCE.Down();
        }
    };

    @NotNull
    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getExistingChildDownButtonColumn() {
        return tagDownButtonColumn;
    }

    @NotNull
    @Override
    public Column<RESTTagInCategoryCollectionItemV1, String> getExistingChildUpButtonColumn() {
        return tagUpButtonColumn;
    }

    @NotNull
    @Override
    public Column<RESTCategoryCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return buttonColumn;
    }

    @NotNull
    @Override
    public TextColumn<RESTCategoryCollectionItemV1> getIdColumn() {
        return idColumn;
    }

    @NotNull
    @Override
    public TextColumn<RESTCategoryCollectionItemV1> getNameColumn() {
        return nameColumn;
    }

    @Override
    public void display(@NotNull final RESTTagV1 originalEntity, final boolean readOnly) {
        super.displayChildren(originalEntity, readOnly);
        this.readOnly = readOnly;
    }

    public TagCategoriesView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagCategories());

        getPossibleChildrenResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.CategoryID());
        getPossibleChildrenResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.CategoryName());
        getPossibleChildrenResults().addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        idColumn.setSortable(true);
        nameColumn.setSortable(true);
        buttonColumn.setSortable(true);

        /*
            Allow the table to be sorted.
         */
        @NotNull final ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(getPossibleChildrenResults());
        getPossibleChildrenResults().addColumnSortHandler(columnSortHandler);

        getExistingChildrenResults().addColumn(tagIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        getExistingChildrenResults().addColumn(tagNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        getExistingChildrenResults().addColumn(tagUpButtonColumn, PressGangCCMSUI.INSTANCE.Up());
        getExistingChildrenResults().addColumn(tagDownButtonColumn, PressGangCCMSUI.INSTANCE.Down());

    }
}
