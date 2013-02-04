package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPropertyTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import javax.enterprise.context.Dependent;
import java.util.List;

/**

 */
@Dependent
public final class TopicPropertyTagsView extends BaseExtendedChildrenView<
        RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1,
        RESTTopicV1,
        RESTPropertyTagV1, RESTPropertyTagCollectionV1, RESTPropertyTagCollectionItemV1,
        RESTAssignedPropertyTagV1, RESTAssignedPropertyTagCollectionV1, RESTAssignedPropertyTagCollectionItemV1>
        implements TopicPropertyTagsPresenter.Display {

    /**
     * The column to display the assigned property tag's value.
     */
    private final Column<RESTAssignedPropertyTagCollectionItemV1, String> propertyTagValueColumn = new Column<RESTAssignedPropertyTagCollectionItemV1, String>(
            new EditTextCell()) {
        @Override
        public String getValue(RESTAssignedPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getValue() != null) {
                return object.getItem().getValue();
            }
            return "";
        }
    };

    private final TextColumn<RESTAssignedPropertyTagCollectionItemV1> propertyTagNameColumn = new TextColumn<RESTAssignedPropertyTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTAssignedPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return "";
        }
    };

    private final TextColumn<RESTAssignedPropertyTagCollectionItemV1> propertyTagDescriptionColumn = new TextColumn<RESTAssignedPropertyTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTAssignedPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getDescription() != null) {
                return object.getItem().getDescription();
            }
            return "";
        }
    };

    private final Column<RESTAssignedPropertyTagCollectionItemV1, String> propertyTagRemoveColumn = new Column<RESTAssignedPropertyTagCollectionItemV1, String>(
        new ButtonCell()) {
            @Override
            public String getValue(final RESTAssignedPropertyTagCollectionItemV1 object) {
                if (getOriginalEntity() != null && object != null && object.getItem().getId() != null) {
                    return PressGangCCMSUI.INSTANCE.Remove();
                }

                return PressGangCCMSUI.INSTANCE.NoAction();
            }
        };

    private final TextColumn<RESTPropertyTagCollectionItemV1> possiblePropertyTagNameColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return "";
        }
    };

    private final TextColumn<RESTPropertyTagCollectionItemV1> possiblePropertyTagDescriptionColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getDescription() != null) {
                return object.getItem().getDescription();
            }
            return "";
        }
    };

    private final Column<RESTPropertyTagCollectionItemV1, String> propertyTagAddColumn = new Column<RESTPropertyTagCollectionItemV1, String>(
            new ButtonCell()) {
        @Override
        public String getValue(final RESTPropertyTagCollectionItemV1 object) {
            if (getOriginalEntity() != null && object != null && object.getItem().getId() != null) {
                return PressGangCCMSUI.INSTANCE.Add();
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @Override
    public Column<RESTPropertyTagCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return propertyTagAddColumn;
    }

    @Override
    public Column<RESTAssignedPropertyTagCollectionItemV1, String> getPropertyTagRemoveColumn() {
        return propertyTagRemoveColumn;
    }

    @Override
    public Column<RESTAssignedPropertyTagCollectionItemV1, String> getPropertyTagValueColumn() {
        return propertyTagValueColumn;
    }

    public TopicPropertyTagsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.PropertyTags());

        getExistingChildrenResults().addColumn(propertyTagNameColumn, PressGangCCMSUI.INSTANCE.PropertyTagName());
        getExistingChildrenResults().addColumn(propertyTagDescriptionColumn, PressGangCCMSUI.INSTANCE.PropertyTagDescription());
        getExistingChildrenResults().addColumn(propertyTagValueColumn, PressGangCCMSUI.INSTANCE.PropertyTagValue());
        getExistingChildrenResults().addColumn(propertyTagRemoveColumn, PressGangCCMSUI.INSTANCE.Remove());

        getPossibleChildrenResults().addColumn(possiblePropertyTagNameColumn, PressGangCCMSUI.INSTANCE.PropertyTagName());
        getPossibleChildrenResults().addColumn(possiblePropertyTagDescriptionColumn, PressGangCCMSUI.INSTANCE.PropertyTagDescription());
        getPossibleChildrenResults().addColumn(propertyTagAddColumn, PressGangCCMSUI.INSTANCE.Add());


        propertyTagValueColumn.setFieldUpdater(new FieldUpdater<RESTAssignedPropertyTagCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTAssignedPropertyTagCollectionItemV1 object, final String value) {
                // Called when the user changes the value.
                object.getItem().setValue(value);
            }
        });

        addExistingChildrenPanel();
    }


    @Override
    public void initialize(RESTTopicV1 topic, boolean readOnly, boolean newTopic, SplitType splitType, List<String> locales, Boolean showImages) {
        super.initialize(topic, readOnly);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return null;
    }

}