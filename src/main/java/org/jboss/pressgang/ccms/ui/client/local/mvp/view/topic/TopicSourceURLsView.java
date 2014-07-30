/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import javax.enterprise.context.Dependent;
import java.util.logging.Logger;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.DisableTextInputCell;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicSourceURLsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The view that displays the source urls.
 */
@Dependent
public class TopicSourceURLsView extends BaseChildrenView<
        RESTBaseTopicV1<?, ?, ?>,
        RESTTopicSourceUrlCollectionItemV1,
        RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1>
        implements TopicSourceURLsPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicSourceURLsView.class.getName());

    /**
     * The column to display the source url.
     */
    private final DisableTextInputCell urlTextCell = new DisableTextInputCell();
    @NotNull
    private final Column<RESTTopicSourceUrlCollectionItemV1, String> urlValueColumn = new Column<RESTTopicSourceUrlCollectionItemV1, String>(urlTextCell) {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTopicSourceUrlCollectionItemV1 object) {
            urlTextCell.setEnabled(!isReadOnly());
            if (object != null && object.getItem() != null && object.getItem().getUrl() != null) {
                return object.getItem().getUrl();
            }
            return "";
        }
    };

    /**
     * The column to display the source url.
     */
    private final DisableTextInputCell nameTextCell = new DisableTextInputCell();
    @NotNull
    private final Column<RESTTopicSourceUrlCollectionItemV1, String> nameValueColumn = new Column<RESTTopicSourceUrlCollectionItemV1, String>(nameTextCell) {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTopicSourceUrlCollectionItemV1 object) {
            nameTextCell.setEnabled(!isReadOnly());
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
    @NotNull
    private final Column<RESTTopicSourceUrlCollectionItemV1, String> removeSourceUrlColumn = new Column<RESTTopicSourceUrlCollectionItemV1, String>(removeButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTTopicSourceUrlCollectionItemV1 object) {
            removeButtonCell.setEnabled(!isReadOnly());

            if (getOriginalEntity() != null && object != null) {
                return PressGangCCMSUI.INSTANCE.Remove();
            }

            return PressGangCCMSUI.INSTANCE.NoAction();
        }
    };

    @NotNull
    private final Column<RESTTopicSourceUrlCollectionItemV1, String> openSourceUrlColumn = new Column<RESTTopicSourceUrlCollectionItemV1, String>(new ButtonCell()) {
        @NotNull
        @Override
        public String getValue(final RESTTopicSourceUrlCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.OpenURL();
        }
    };

    @NotNull
    private final PushButton add = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Add());

    /**
     * Constructor. Sets the page and title name.
     */
    public TopicSourceURLsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TopicSourceUrls());

        LOGGER.info("ENTER TopicSourceURLsView()");

        getPossibleChildrenResults().addColumn(urlValueColumn, PressGangCCMSUI.INSTANCE.URL());
        getPossibleChildrenResults().addColumn(nameValueColumn, PressGangCCMSUI.INSTANCE.URLTitle());
        getPossibleChildrenResults().addColumn(removeSourceUrlColumn, PressGangCCMSUI.INSTANCE.Remove());
        getPossibleChildrenResults().addColumn(openSourceUrlColumn, PressGangCCMSUI.INSTANCE.OpenURL());

        @NotNull final HorizontalPanel addButtonPanel = new HorizontalPanel();
        addButtonPanel.add(add);
        this.getPossibleChildrenResultsPanel().add(addButtonPanel);

        // Add styling
        getPossibleChildrenResults().addStyleName(CSSConstants.TopicSourceURLView.TOPIC_SOURCE_URL_RESULTS);
        getPossibleChildrenResultsPanel().addStyleName(CSSConstants.TopicSourceURLView.TOPIC_SOURCE_URL_RESULTS);
        getPossibleChildrenResults().addColumnStyleName(0, CSSConstants.TopicSourceURLView.TOPIC_SOURCE_URL_RESULTS_URL_COLUMN);
        getPossibleChildrenResults().addColumnStyleName(1, CSSConstants.TopicSourceURLView.TOPIC_SOURCE_URL_RESULTS_TITLE_COLUMN);
    }

    @NotNull
    @Override
    public Column<RESTTopicSourceUrlCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return removeSourceUrlColumn;
    }

    @Override
    public void display(@NotNull final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {
        super.displayChildren(topic, readOnly);
        add.setEnabled(!readOnly);
    }

    @NotNull
    @Override
    public Column<RESTTopicSourceUrlCollectionItemV1, String> getURLValueColumn() {
        return urlValueColumn;
    }

    @NotNull
    @Override
    public Column<RESTTopicSourceUrlCollectionItemV1, String> getNameValueColumn() {
        return nameValueColumn;
    }

    @NotNull
    @Override
    public Column<RESTTopicSourceUrlCollectionItemV1, String> getOpenUrlColumn() {
        return openSourceUrlColumn;
    }


    @Override
    @NotNull
    public PushButton getAdd() {
        return add;
    }
}
