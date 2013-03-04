package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertytag;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyCategoryInPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentProjectV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyCategoryInPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * The view used to display a property tag's categories.
 */
public class PropertyTagCategoryView extends BaseChildrenView<
        RESTPropertyTagV1,                                                                                                                          // The main REST types
        RESTPropertyCategoryCollectionItemV1,                                                                                                       // The possible children types
        RESTPropertyCategoryInPropertyTagV1, RESTPropertyCategoryInPropertyTagCollectionV1, RESTPropertyCategoryInPropertyTagCollectionItemV1>       // The existing children types
        implements PropertyTagCategoryPresenter.Display {

    @NotNull
    private final TextColumn<RESTPropertyCategoryCollectionItemV1> tagsIdColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyCategoryCollectionItemV1 object) {
            checkArgument(object == null || (object.getItem() != null && object.getItem().getName() != null), "object should be null or it should have a valid item and the item should have a valid id");

            if (object != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private final TextColumn<RESTPropertyCategoryCollectionItemV1> tagsNameColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyCategoryCollectionItemV1 object) {
            checkArgument(object == null || (object.getItem() != null && object.getItem().getName() != null), "object should be null or it should have a valid item and the item should have a valid name");

            if (object != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };


    /**
     * The column used to render the property tag category's id.
     */
    @NotNull
    @Override
    public TextColumn<RESTPropertyCategoryCollectionItemV1> getTagsIdColumn() {
        return tagsIdColumn;
    }

    /**
     * The column used to render the property tag category's name.
     */
    @NotNull
    @Override
    public TextColumn<RESTPropertyCategoryCollectionItemV1> getTagsNameColumn() {
        return tagsNameColumn;
    }

    /**
     * The column used to render the property tag category's add/remove button.
     */
    @NotNull
    private final Column<RESTPropertyCategoryCollectionItemV1, String> tagsButtonColumn = new Column<RESTPropertyCategoryCollectionItemV1, String>(new ButtonCell()) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTPropertyCategoryCollectionItemV1 object) {
            checkState(getOriginalEntity() != null, "getOriginalEntity() should not be null");
            checkArgument(object == null || (object.getItem() != null && object.getItem().getId() != null), "object should be null or it should have a valid item and the item should have a valid id");

            if (object != null) {

                if (ComponentPropertyTagV1.isInCategory(getOriginalEntity(), object.getItem().getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @NotNull
    @Override
    public Column<RESTPropertyCategoryCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return tagsButtonColumn;
    }

    public PropertyTagCategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedProperties());

        getPossibleChildrenResults().addColumn(tagsIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        getPossibleChildrenResults().addColumn(tagsNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        getPossibleChildrenResults().addColumn(tagsButtonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        tagsButtonColumn.setSortable(true);
        tagsNameColumn.setSortable(true);
        tagsIdColumn.setSortable(true);

        /*
            Allow the table to be sorted.
        */
        @NotNull final ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(getPossibleChildrenResults());
        getPossibleChildrenResults().addColumnSortHandler(columnSortHandler);
    }

    public void display(@NotNull final RESTPropertyTagV1 entity, final boolean readOnly) {
        super.displayChildren(entity, readOnly);
    }

}