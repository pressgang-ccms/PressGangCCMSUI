package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TagProjectsView extends
        BaseChildrenView<RESTTagV1,
                RESTProjectCollectionItemV1,
                RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> implements
        TagProjectsPresenter.Display {

    private final TextColumn<RESTProjectCollectionItemV1> idColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTProjectCollectionItemV1 object) {
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> nameColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTProjectCollectionItemV1 object) {
            return object.getItem().getName();
        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> descriptionColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(@NotNull final RESTProjectCollectionItemV1 object) {
            return object.getItem().getDescription();
        }
    };

    @NotNull
    private final Column<RESTProjectCollectionItemV1, String> buttonColumn = new Column<RESTProjectCollectionItemV1, String>(
            new ButtonCell()) {
        @NotNull
        @Override
        public String getValue(@NotNull final RESTProjectCollectionItemV1 object) {
            if (getOriginalEntity() != null) {
                if (ComponentProjectV1.containsTag(object.getItem(), getOriginalEntity().getId())) {
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
    public Column<RESTProjectCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return buttonColumn;
    }

    @Override
    public void display(@NotNull final RESTTagV1 originalEntity, final boolean readOnly) {
        super.displayChildren(originalEntity, readOnly);
    }

    public TagProjectsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagProjects());

        getPossibleChildrenResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProjectID());
        getPossibleChildrenResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProjectName());
        getPossibleChildrenResults().addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ProjectDescription());
        getPossibleChildrenResults().addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

        idColumn.setSortable(true);
        nameColumn.setSortable(true);
        descriptionColumn.setSortable(true);
        buttonColumn.setSortable(true);

        /*
            Allow the table to be sorted.
         */
        final ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(getPossibleChildrenResults());
        getPossibleChildrenResults().addColumnSortHandler(columnSortHandler);

    }

    @NotNull
    @Override
    public TextColumn<RESTProjectCollectionItemV1> getIdColumn() {
        return idColumn;
    }

    @NotNull
    @Override
    public TextColumn<RESTProjectCollectionItemV1> getNameColumn() {
        return nameColumn;
    }

    @NotNull
    @Override
    public TextColumn<RESTProjectCollectionItemV1> getDescriptionColumn() {
        return descriptionColumn;
    }
}
