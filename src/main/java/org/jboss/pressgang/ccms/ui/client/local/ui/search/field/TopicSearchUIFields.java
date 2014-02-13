package org.jboss.pressgang.ccms.ui.client.local.ui.search.field;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base.BaseTopicSearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TopicSearchUIFields extends BaseTopicSearchUIFields {

    private String createdBy;
    private String notCreatedBy;
    private String editedBy;
    private String notEditedBy;

    @Nullable
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@Nullable final String createdBy) {
        this.createdBy = createdBy;
    }

    @Nullable
    public String getNotCreatedBy() {
        return notCreatedBy;
    }

    public void setNotCreatedBy(@Nullable final String notCreatedBy) {
        this.notCreatedBy = notCreatedBy;
    }

    @Nullable
    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(@Nullable final String editedBy) {
        this.editedBy = editedBy;
    }

    @Nullable
    public String getNotEditedBy() {
        return notEditedBy;
    }

    public void setNotEditedBy(@Nullable final String notEditedBy) {
        this.notEditedBy = notEditedBy;
    }

    public TopicSearchUIFields() {

    }

    public TopicSearchUIFields(@NotNull final RESTFilterV1 filter) {
        super(filter);
    }

    @Override
    public void populateFilter(@NotNull final RESTFilterV1 filter) {
        super.populateFilter(filter);

        if (!GWTUtilities.isStringNullOrEmpty(getCreatedBy())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.CREATED_BY_VAR, getCreatedBy()));
        }

        if (!GWTUtilities.isStringNullOrEmpty(getNotCreatedBy())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.NOT_CREATED_BY_VAR, getNotCreatedBy()));
        }

        if (!GWTUtilities.isStringNullOrEmpty(getEditedBy())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.EDITED_BY_VAR, getEditedBy()));
        }

        if (!GWTUtilities.isStringNullOrEmpty(getNotEditedBy())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.NOT_EDITED_BY_VAR, getNotEditedBy()));
        }
    }

    @Override
    public void initialize(@Nullable final RESTFilterV1 filter) {
        super.initialize(filter);

        if (filter != null) {
            createdBy = "";
            notCreatedBy = "";
            editedBy = "";
            notEditedBy = "";

            if (filter.getFilterFields_OTM() != null) {

                for (@NotNull final RESTFilterFieldCollectionItemV1 field : filter.getFilterFields_OTM().getItems()) {

                    final RESTFilterFieldV1 fieldItem = field.getItem();

                    if (fieldItem.getName().equals(CommonFilterConstants.CREATED_BY_VAR)) {
                        setCreatedBy(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.NOT_CREATED_BY_VAR)) {
                        setNotCreatedBy(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.EDITED_BY_VAR)) {
                        setEditedBy(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.NOT_EDITED_BY_VAR)) {
                        setNotEditedBy(fieldItem.getValue());
                    }
                }
            }
        }
    }

    @NotNull
    @Override
    public String getSearchQuery(final boolean includeQueryPrefix) {
        final StringBuilder retValue = new StringBuilder(super.getSearchQuery(includeQueryPrefix));

        if (!GWTUtilities.isStringNullOrEmpty(createdBy)) {
            retValue.append(";").append(CommonFilterConstants.CREATED_BY_VAR).append("=").append(encodeQueryParameter(createdBy));
        }

        if (!GWTUtilities.isStringNullOrEmpty(notCreatedBy)) {
            retValue.append(";").append(CommonFilterConstants.NOT_CREATED_BY_VAR).append("=").append(encodeQueryParameter(notCreatedBy));
        }

        if (!GWTUtilities.isStringNullOrEmpty(editedBy)) {
            retValue.append(";").append(CommonFilterConstants.EDITED_BY_VAR).append("=").append(encodeQueryParameter(editedBy));
        }

        if (!GWTUtilities.isStringNullOrEmpty(notEditedBy)) {
            retValue.append(";").append(CommonFilterConstants.NOT_EDITED_BY_VAR).append("=").append(encodeQueryParameter(notEditedBy));
        }

        return retValue.toString();
    }
}
