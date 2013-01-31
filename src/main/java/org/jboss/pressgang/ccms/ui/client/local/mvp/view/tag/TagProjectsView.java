package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.PushButton;

public class TagProjectsView extends
        BaseChildrenView<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, 
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1,
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> implements
        TagProjectsPresenter.Display {

    private final TextColumn<RESTProjectCollectionItemV1> idColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> nameColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            return object.getItem().getName();
        }
    };

    private final TextColumn<RESTProjectCollectionItemV1> descriptionColumn = new TextColumn<RESTProjectCollectionItemV1>() {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
            return object.getItem().getDescription();
        }
    };

    private final Column<RESTProjectCollectionItemV1, String> buttonColumn = new Column<RESTProjectCollectionItemV1, String>(
            new ButtonCell()) {
        @Override
        public String getValue(final RESTProjectCollectionItemV1 object) {
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

    @Override
    public Column<RESTProjectCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return buttonColumn;
    }

    public TagProjectsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TagProjects());

        getPossibleChildrenResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProjectID());
        getPossibleChildrenResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProjectName());
        getPossibleChildrenResults().addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ProjectDescription());
        getPossibleChildrenResults().addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

    }

}
