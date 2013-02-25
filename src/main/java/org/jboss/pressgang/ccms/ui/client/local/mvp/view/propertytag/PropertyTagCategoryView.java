package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertytag;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
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

public class PropertyTagCategoryView extends BaseChildrenView<
        RESTPropertyTagV1,                                                                                                                          // The main REST types
        RESTPropertyCategoryCollectionItemV1,                                                                                                       // The possible children types
        RESTPropertyCategoryInPropertyTagV1, RESTPropertyCategoryInPropertyTagCollectionV1, RESTPropertyCategoryInPropertyTagCollectionItemV1>       // The existing children types
        implements PropertyTagCategoryPresenter.Display {

    private final TextColumn<RESTPropertyCategoryCollectionItemV1> tagsIdColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTPropertyCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    private final TextColumn<RESTPropertyCategoryCollectionItemV1> tagsNameColumn = new TextColumn<RESTPropertyCategoryCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTPropertyCategoryCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };

    private final Column<RESTPropertyCategoryCollectionItemV1, String> tagsButtonColumn = new Column<RESTPropertyCategoryCollectionItemV1, String>(
            new ButtonCell()) {
        @Override
        public String getValue(@NotNull final RESTPropertyCategoryCollectionItemV1 object) {
            if (getOriginalEntity() != null && object != null && object.getItem().getId() != null) {
                if (ComponentPropertyTagV1.isInCategory(getOriginalEntity(), object.getItem().getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @Override
    public Column<RESTPropertyCategoryCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return tagsButtonColumn;
    }

    public PropertyTagCategoryView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());

        getPossibleChildrenResults().addColumn(tagsIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        getPossibleChildrenResults().addColumn(tagsNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        getPossibleChildrenResults().addColumn(tagsButtonColumn, PressGangCCMSUI.INSTANCE.AddRemove());
    }

    public void display(final RESTPropertyTagV1 entity, final boolean readOnly) {
        super.displayChildren(entity, readOnly);
    }
}