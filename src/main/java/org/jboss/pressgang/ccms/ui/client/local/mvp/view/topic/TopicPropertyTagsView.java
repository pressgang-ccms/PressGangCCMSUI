package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DisableEditTextCell;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPropertyTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;

/**

 */
@Dependent
public class TopicPropertyTagsView extends BaseExtendedChildrenView<
        RESTBaseTopicV1<?, ?, ?>,
        RESTTopicV1,
        RESTPropertyTagCollectionItemV1,
        RESTAssignedPropertyTagV1, RESTAssignedPropertyTagCollectionV1, RESTAssignedPropertyTagCollectionItemV1>
        implements TopicPropertyTagsPresenter.Display {

    /**
     * The column to display the assigned property tag's value.
     */
    private final DisableEditTextCell valueTextCell = new DisableEditTextCell();
    @NotNull
    private final Column<RESTAssignedPropertyTagCollectionItemV1, String> propertyTagValueColumn = new Column<RESTAssignedPropertyTagCollectionItemV1, String>(valueTextCell) {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTAssignedPropertyTagCollectionItemV1 object) {
            valueTextCell.setEnabled(!isReadOnly());
            if (object != null && object.getItem() != null && object.getItem().getValue() != null) {
                return object.getItem().getValue();
            }
            return "";
        }
    };

    @NotNull
    private final TextColumn<RESTAssignedPropertyTagCollectionItemV1> propertyTagNameColumn = new TextColumn<RESTAssignedPropertyTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTAssignedPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return "";
        }
    };

    @NotNull
    private final TextColumn<RESTAssignedPropertyTagCollectionItemV1> propertyTagDescriptionColumn = new TextColumn<RESTAssignedPropertyTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTAssignedPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getDescription() != null) {
                return object.getItem().getDescription();
            }
            return "";
        }
    };

    @NotNull
    private final DisableableButtonCell removeButtonCell = new DisableableButtonCell();
    @NotNull
    private final Column<RESTAssignedPropertyTagCollectionItemV1, String> propertyTagRemoveColumn = new Column<RESTAssignedPropertyTagCollectionItemV1, String>(removeButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTAssignedPropertyTagCollectionItemV1 object) {
            removeButtonCell.setEnabled(!isReadOnly());

            if (getOriginalEntity() != null && object != null) {
                return PressGangCCMSUI.INSTANCE.Remove();
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @NotNull
    private final TextColumn<RESTPropertyTagCollectionItemV1> possiblePropertyTagNameColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }
            return "";
        }
    };

    @NotNull
    private final TextColumn<RESTPropertyTagCollectionItemV1> possiblePropertyTagDescriptionColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getDescription() != null) {
                return object.getItem().getDescription();
            }
            return "";
        }
    };

    @NotNull
    private final DisableableButtonCell addButtonCell = new DisableableButtonCell();
    @NotNull
    private final Column<RESTPropertyTagCollectionItemV1, String> propertyTagAddColumn = new Column<RESTPropertyTagCollectionItemV1, String>(
            addButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTPropertyTagCollectionItemV1 object) {
            addButtonCell.setEnabled(!isReadOnly());

            if (getOriginalEntity() != null && object != null && object.getItem().getId() != null) {
                return PressGangCCMSUI.INSTANCE.Add();
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @NotNull
    @Override
    public Column<RESTPropertyTagCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return propertyTagAddColumn;
    }

    @NotNull
    @Override
    public Column<RESTAssignedPropertyTagCollectionItemV1, String> getPropertyTagRemoveColumn() {
        return propertyTagRemoveColumn;
    }

    @NotNull
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
            public void update(final int index, @NotNull final RESTAssignedPropertyTagCollectionItemV1 object, final String value) {
                // Called when the user changes the value.
                object.getItem().setValue(value);
            }
        });

        addExistingChildrenPanel();
    }


    @Override
    public void display(@NotNull final RESTBaseTopicV1 topic, final boolean readOnly) {
        super.displayChildren(topic, readOnly);
    }

}