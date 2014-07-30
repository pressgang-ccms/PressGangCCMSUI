/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.translatedtopic;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics
        .TranslatedTopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.LogMessageView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.base.BaseSearchResultsAndTopicView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * Displays the translated topics results and topic details. This class does not add anything over the base class, and
 * exists as a seperate class to satisfy injection.
 */
public class TranslatedTopicResultsAndTranslatedTopicView extends BaseSearchResultsAndTopicView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1>
        implements TranslatedTopicFilteredResultsAndDetailsPresenter.Display {

    /**
     * An instance of the message log dialog box
     */
    private final LogMessageView messageLogDialog = new LogMessageView();

    /**
     * The save button.
     */
    private final PushButton save;
    /**
     * The additional xml button.
     */
    private final PushButton additionalXML;
    /**
     * The label used to represent the additional xml button in a down state.
     */
    private final Label additionalXMLDown;

    @NotNull
    @Override
    public PushButton getSave() {
        return save;
    }

    @NotNull
    @Override
    public PushButton getAdditionalXML() {
        return additionalXML;
    }

    @NotNull
    @Override
    public Label getAdditionalXMLDown() {
        return additionalXMLDown;
    }

    @NotNull
    @Override
    public LogMessageView getMessageLogDialog() {
        return messageLogDialog;
    }

    public TranslatedTopicResultsAndTranslatedTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

        /* Build the action bar icons */
        save = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Save(), Constants.ElementIDs.SAVE_TOPIC_EDIT_BUTTON_ID.getId());
        additionalXML = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.AdditionalXml());
        additionalXMLDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.AdditionalXml());

        getXml().setText(PressGangCCMSUI.INSTANCE.OriginalXML());
        getXmlDown().setText(PressGangCCMSUI.INSTANCE.OriginalXML());

        addActionButton(this.getShowHideSearchResults());
        addActionButton(this.getRenderedSplit(), true);
        addActionButton(this.getRendered());
        addActionButton(this.getXml());
        addActionButton(this.getFields());
        addActionButton(this.getExtendedProperties());
        addActionButton(this.getUrls());
        addActionButton(this.getTopicTags(), true);
        //addActionButton(this.getBugs());

        addActionButton(save);
    }
}
