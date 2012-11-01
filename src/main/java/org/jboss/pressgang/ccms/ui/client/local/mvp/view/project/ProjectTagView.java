package org.jboss.pressgang.ccms.ui.client.local.mvp.view.project;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.PushButton;

public class ProjectTagView
    extends BaseChildrenView<
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1,  // The main entity types
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1,             // The possible child types
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>             // The existing child types
    implements ProjectTagPresenter.Display {
    
    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton details = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ProjectDetails());
    private final PushButton children = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ProjectTags());

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
                if (ComponentProjectV1.containsTag(getOriginalEntity(), object.getItem().getId())) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                } else {
                    return PressGangCCMSUI.INSTANCE.Add();
                }
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @Override
    public Column<RESTTagCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return tagsButtonColumn;
    }

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

    public ProjectTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());

        populateTopActionBar();

        getPossibleChildrenResults().addColumn(tagsIdColumn, PressGangCCMSUI.INSTANCE.TagID());
        getPossibleChildrenResults().addColumn(tagsNameColumn, PressGangCCMSUI.INSTANCE.TagName());
        getPossibleChildrenResults().addColumn(tagsButtonColumn, PressGangCCMSUI.INSTANCE.AddRemove());
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getDetails());
        this.addActionButton(UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.ProjectTags()));
        this.addActionButton(this.getSave());
        addRightAlignedActionButtonPaddingPanel();
    }

}