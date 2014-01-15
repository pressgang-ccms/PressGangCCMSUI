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
    private String editedBy;

    @Nullable
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@Nullable final String createdBy) {
        this.createdBy = createdBy;
    }

    @Nullable
    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(@Nullable final String editedBy) {
        this.editedBy = editedBy;
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

        if (!GWTUtilities.isStringNullOrEmpty(getEditedBy())) {
            filter.getFilterFields_OTM().addNewItem(createFilterField(CommonFilterConstants.EDITED_BY_VAR, getEditedBy()));
        }
    }

    @Override
    public void initialize(@Nullable final RESTFilterV1 filter) {
        super.initialize(filter);

        if (filter != null) {
            createdBy = "";
            editedBy = "";

            if (filter.getFilterFields_OTM() != null) {

                for (@NotNull final RESTFilterFieldCollectionItemV1 field : filter.getFilterFields_OTM().getItems()) {

                    final RESTFilterFieldV1 fieldItem = field.getItem();

                    if (fieldItem.getName().equals(CommonFilterConstants.CREATED_BY_VAR)) {
                        setCreatedBy(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.EDITED_BY_VAR)) {
                        setEditedBy(fieldItem.getValue());
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

        if (!GWTUtilities.isStringNullOrEmpty(editedBy)) {
            retValue.append(";").append(CommonFilterConstants.EDITED_BY_VAR).append("=").append(encodeQueryParameter(editedBy));
        }

        return retValue.toString();
    }
}
