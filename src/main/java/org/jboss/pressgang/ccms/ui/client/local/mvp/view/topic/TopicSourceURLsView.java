package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicSourceURLsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import javax.enterprise.context.Dependent;
import java.util.List;

/**
 * The view that displays the source urls.
 */
@Dependent
public class TopicSourceURLsView extends BaseChildrenView<
        RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1,
        RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1,
        RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1>
        implements TopicSourceURLsPresenter.Display {

    /**
     * The column to display the source url.
     */
    private final Column<RESTTopicSourceUrlCollectionItemV1, String> urlValueColumn = new Column<RESTTopicSourceUrlCollectionItemV1, String>(
            new EditTextCell()) {
        @Override
        public String getValue(final RESTTopicSourceUrlCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getUrl() != null) {
                return object.getItem().getUrl();
            }
            return "";
        }
    };

    /**
     * The column to display the source url.
     */
    private final Column<RESTTopicSourceUrlCollectionItemV1, String> nameValueColumn = new Column<RESTTopicSourceUrlCollectionItemV1, String>(
            new EditTextCell()) {
        @Override
        public String getValue(final RESTTopicSourceUrlCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getTitle() != null) {
                return object.getItem().getTitle();
            }
            return "";
        }
    };

    /**
     * The column that provides the remove url button.
     */
    private final DisableableButtonCell removeButtonCell = new DisableableButtonCell();
    private final Column<RESTTopicSourceUrlCollectionItemV1, String> sourceUrlRemoveColumn = new Column<RESTTopicSourceUrlCollectionItemV1, String>(
            removeButtonCell) {
        @Override
        public String getValue(final RESTTopicSourceUrlCollectionItemV1 object) {
            removeButtonCell.setEnabled(!isReadOnly());

            if (getOriginalEntity() != null && object != null && object.getItem().getId() != null) {
                return PressGangCCMSUI.INSTANCE.Remove();
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    /**
        Constructor. Sets the page and title name.
     */
    public TopicSourceURLsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TopicSourceUrls());

        getPossibleChildrenResults().addColumn(urlValueColumn, PressGangCCMSUI.INSTANCE.URLTitle());
        getPossibleChildrenResults().addColumn(nameValueColumn, PressGangCCMSUI.INSTANCE.URL());
        getPossibleChildrenResults().addColumn(sourceUrlRemoveColumn, PressGangCCMSUI.INSTANCE.Remove());
    }

    @Override
    public final Column<RESTTopicSourceUrlCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return sourceUrlRemoveColumn;
    }

    @Override
    public final void displayExtended(final RESTTopicV1 topic, final boolean readOnly) {
        super.display(topic, readOnly);
    }

    @Override
    public final Column<RESTTopicSourceUrlCollectionItemV1, String> getURLValueColumn() {
        return urlValueColumn;
    }

    @Override
    public final Column<RESTTopicSourceUrlCollectionItemV1, String> getNameValueColumn() {
        return nameValueColumn;
    }
}
