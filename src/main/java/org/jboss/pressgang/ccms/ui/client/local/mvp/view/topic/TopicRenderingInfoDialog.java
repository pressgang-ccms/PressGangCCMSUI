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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ClosablePopup;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleIntegerLabel;
import com.google.gwt.user.client.ui.TextArea;
import com.sun.istack.NotNull;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

public class TopicRenderingInfoDialog extends ClosablePopup {
    private FlexTable layoutPanel = new FlexTable();
    private SimpleIntegerLabel id = new SimpleIntegerLabel();
    private Label condition = new Label();
    private TextArea entities = new TextArea();
    private TextArea customEntities = new TextArea();

    private Label idLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecID() + ":");
    private Label conditionLabel = new Label(PressGangCCMSUI.INSTANCE.Condition());
    private Label entitiesLabel = new Label(PressGangCCMSUI.INSTANCE.DefaultEntities());
    private Label customEntitiesLabel = new Label(PressGangCCMSUI.INSTANCE.CustomEntities());

    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());

    public TopicRenderingInfoDialog() {
        super(PressGangCCMSUI.INSTANCE.RenderingInfo(), true);
        setGlassEnabled(true);

        entities.setReadOnly(true);
        entities.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_DESCRIPTION_FIELD);
        customEntities.setReadOnly(true);
        customEntities.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_DESCRIPTION_FIELD);

        int row = 0;
        layoutPanel.setWidget(row, 0, idLabel);
        layoutPanel.setWidget(row, 1, id);

        layoutPanel.setWidget(++row, 0, conditionLabel);
        layoutPanel.setWidget(row, 1, condition);

        layoutPanel.setWidget(++row, 0, entitiesLabel);
        layoutPanel.setWidget(row, 1, entities);

        layoutPanel.setWidget(++row, 0, customEntitiesLabel);
        layoutPanel.setWidget(row, 1, customEntities);

        layoutPanel.setWidget(++row, 0, ok);
        layoutPanel.getFlexCellFormatter().setColSpan(row, 0, 2);

        for (int i = 0; i < row; ++i) {
            layoutPanel.getCellFormatter().addStyleName(i, 0, CSSConstants.TopicView.TOPIC_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < row - 2; ++i) {
            layoutPanel.getCellFormatter().addStyleName(i, 1, CSSConstants.TopicView.TOPIC_VIEW_DETAIL_CELL);
        }
        layoutPanel.getCellFormatter().addStyleName(row - 1, 1, CSSConstants.TopicView.TOPIC_RENDERING_INFO_ENTITIES_CELL);
        layoutPanel.getCellFormatter().addStyleName(row - 2, 1, CSSConstants.TopicView.TOPIC_RENDERING_INFO_ENTITIES_CELL);

        add(layoutPanel);

        ok.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });
    }

    @NotNull
    public SimpleIntegerLabel getId() {
        return id;
    }

    @NotNull
    public Label getCondition() {
        return condition;
    }

    @NotNull
    public TextArea getEntities() {
        return entities;
    }

    @NotNull
    public TextArea getCustomEntities() {
        return customEntities;
    }

    @NotNull
    public PushButton getOk() {
        return ok;
    }

    public void reset() {
        id.setValue(null);
        condition.setText(null);
        entities.setText(null);
        customEntities.setText(null);
    }
}
