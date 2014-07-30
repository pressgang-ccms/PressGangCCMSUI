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

package org.jboss.pressgang.ccms.ui.client.local.ui.search.field;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base.BaseTopicSearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TranslatedTopicSearchUIFields extends BaseTopicSearchUIFields {
    private TriStateSelectionState latestTranslations = TriStateSelectionState.NONE;
    private TriStateSelectionState latestCompletedTranslations = TriStateSelectionState.NONE;

    public TranslatedTopicSearchUIFields() {

    }

    public TranslatedTopicSearchUIFields(@NotNull final RESTFilterV1 filter) {
        super(filter);
    }

    public TriStateSelectionState getLatestTranslations() {
        return latestTranslations;
    }

    public void setLatestTranslations(TriStateSelectionState latestTranslations) {
        this.latestTranslations = latestTranslations;
    }

    public TriStateSelectionState getLatestCompletedTranslations() {
        return latestCompletedTranslations;
    }

    public void setLatestCompletedTranslations(TriStateSelectionState latestCompletedTranslations) {
        this.latestCompletedTranslations = latestCompletedTranslations;
    }

    @Override
    public void populateFilter(@NotNull final RESTFilterV1 filter) {
        super.populateFilter(filter);

        if (!getLatestTranslations().equals(TriStateSelectionState.NONE)) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            if (getLatestTranslations().equals(TriStateSelectionState.SELECTED)) {
                field.explicitSetName(CommonFilterConstants.TOPIC_LATEST_TRANSLATIONS_FILTER_VAR);
            } else {
                field.explicitSetName(CommonFilterConstants.TOPIC_NOT_LATEST_TRANSLATIONS_FILTER_VAR);
            }
            field.explicitSetValue("true");
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!getLatestCompletedTranslations().equals(TriStateSelectionState.NONE)) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            if (getLatestCompletedTranslations().equals(TriStateSelectionState.SELECTED)) {
                field.explicitSetName(CommonFilterConstants.TOPIC_LATEST_COMPLETED_TRANSLATIONS_FILTER_VAR);
            } else {
                field.explicitSetName(CommonFilterConstants.TOPIC_NOT_LATEST_COMPLETED_TRANSLATIONS_FILTER_VAR);
            }
            field.explicitSetValue("true");
            filter.getFilterFields_OTM().addNewItem(field);
        }
    }

    @Override
    public void initialize(@Nullable final RESTFilterV1 filter) {
        super.initialize(filter);

        if (filter != null) {
            latestTranslations = TriStateSelectionState.NONE;
            latestCompletedTranslations = TriStateSelectionState.NONE;

            if (filter.getFilterFields_OTM() != null) {

                for (@NotNull final RESTFilterFieldCollectionItemV1 field : filter.getFilterFields_OTM().getItems()) {

                    final RESTFilterFieldV1 fieldItem = field.getItem();

                    if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_LATEST_TRANSLATIONS_FILTER_VAR)) {
                        setLatestTranslations(TriStateSelectionState.SELECTED);
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_NOT_LATEST_TRANSLATIONS_FILTER_VAR)) {
                        setLatestTranslations(TriStateSelectionState.UNSELECTED);
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_LATEST_COMPLETED_TRANSLATIONS_FILTER_VAR)) {
                        setLatestCompletedTranslations(TriStateSelectionState.SELECTED);
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_NOT_LATEST_COMPLETED_TRANSLATIONS_FILTER_VAR)) {
                        setLatestCompletedTranslations(TriStateSelectionState.UNSELECTED);
                    }
                }
            }
        }
    }

    @NotNull
    @Override
    public String getSearchQuery(final boolean includeQueryPrefix) {
        final StringBuilder retValue = new StringBuilder(super.getSearchQuery(includeQueryPrefix));

        if (latestTranslations == TriStateSelectionState.SELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_LATEST_TRANSLATIONS_FILTER_VAR).append("=").append(
                    GWTUtilities.encodeQueryParameter("true"));
        } else if (latestTranslations == TriStateSelectionState.UNSELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_NOT_LATEST_TRANSLATIONS_FILTER_VAR).append("=").append(
                    GWTUtilities.encodeQueryParameter("true"));
        }

        if (latestCompletedTranslations == TriStateSelectionState.SELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_LATEST_COMPLETED_TRANSLATIONS_FILTER_VAR).append("=").append(
                    GWTUtilities.encodeQueryParameter("true"));
        } else if (latestCompletedTranslations == TriStateSelectionState.UNSELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_NOT_LATEST_COMPLETED_TRANSLATIONS_FILTER_VAR).append("=").append(
                    GWTUtilities.encodeQueryParameter("true"));
        }

        return retValue.toString();
    }
}
