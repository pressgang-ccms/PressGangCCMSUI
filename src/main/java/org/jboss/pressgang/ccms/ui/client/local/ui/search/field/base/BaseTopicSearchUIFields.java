package org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The backing object for the search fields view. Instance of this class will be manipulated by a GWT Editor
 *
 * @author Matthew Casperson
 */
public class BaseTopicSearchUIFields extends BaseSearchUIFields {
    private static final boolean MATCH_ALL_DEFAULT = true;

    private final DateTimeFormat dateformat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);

    @Nullable
    private Date createdAfter;
    @Nullable
    private Date createdBefore;
    @Nullable
    private Date editedAfter;
    @Nullable
    private Date editedBefore;
    @Nullable
    private Integer editedInLastXDays;
    @Nullable
    private Integer notEditedInLastXDays;
    private String ids;
    private String notIds;
    private String title;
    private String contents;
    private String notContents;
    private String notTitle;
    private String description;
    private String notDescription;
    private String includedInContentSpecs;
    private String notIncludedInContentSpecs;
    private String freeTextSearch;
    private TriStateSelectionState hasBugzillaBugs = TriStateSelectionState.NONE;
    private TriStateSelectionState hasOpenBugzillaBugs = TriStateSelectionState.NONE;
    private TriStateSelectionState hasXMLErrors = TriStateSelectionState.NONE;
    private boolean matchAll = MATCH_ALL_DEFAULT;

    @Nullable
    public Date getCreatedAfter() {
        return GWTUtilities.createDateCopy(createdAfter);
    }

    public void setCreatedAfter(@Nullable final Date createdAfter) {
        this.createdAfter = GWTUtilities.createDateCopy(createdAfter);
    }

    @Nullable
    public Date getCreatedBefore() {
        return GWTUtilities.createDateCopy(createdBefore);
    }

    public void setCreatedBefore(@Nullable final Date createdBefore) {
        this.createdBefore = GWTUtilities.createDateCopy(createdBefore);
    }

    @Nullable
    public Date getEditedAfter() {
        return GWTUtilities.createDateCopy(editedAfter);
    }

    public void setEditedAfter(@Nullable final Date editedAfter) {
        this.editedAfter = GWTUtilities.createDateCopy(editedAfter);
    }

    @Nullable
    public Date getEditedBefore() {
        return GWTUtilities.createDateCopy(editedBefore);
    }

    public void setEditedBefore(@Nullable final Date editedBefore) {
        this.editedBefore = GWTUtilities.createDateCopy(editedBefore);
    }

    @Nullable
    public Integer getEditedInLastXDays() {
        return editedInLastXDays;
    }

    public void setEditedInLastXDays(@Nullable final Integer editedInLastXDays) {
        this.editedInLastXDays = editedInLastXDays;
    }

    @Nullable
    public String getIds() {
        return ids;
    }

    public void setIds(@Nullable final String ids) {
        this.ids = ids;
    }

    @Nullable
    public String getNotIds() {
        return notIds;
    }

    public void setNotIds(@Nullable final String notIds) {
        this.notIds = notIds;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable final String title) {
        this.title = title;
    }

    @Nullable
    public String getNotTitle() {
        return notTitle;
    }

    public void setNotTitle(@Nullable final String notTitle) {
        this.notTitle = notTitle;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable final String description) {
        this.description = description;
    }

    @Nullable
    public String getNotDescription() {
        return notDescription;
    }

    public void setNotDescription(@Nullable final String notDescription) {
        this.notDescription = notDescription;
    }

    @Nullable
    public String getIncludedInContentSpecs() {
        return includedInContentSpecs;
    }

    public void setIncludedInContentSpecs(@Nullable final String includedInContentSpecs) {
        this.includedInContentSpecs = includedInContentSpecs;
    }

    @Nullable
    public String getNotIncludedInContentSpecs() {
        return notIncludedInContentSpecs;
    }

    public void setNotIncludedInContentSpecs(@Nullable final String notIncludedInContentSpecs) {
        this.notIncludedInContentSpecs = notIncludedInContentSpecs;
    }

    @Nullable
    public String getFreeTextSearch() {
        return freeTextSearch;
    }

    public void setFreeTextSearch(@Nullable final String freeTextSearch) {
        this.freeTextSearch = freeTextSearch;
    }

    @Nullable
    public String getContents() {
        return contents;
    }

    public void setContents(@Nullable final String contents) {
        this.contents = contents;
    }

    @Nullable
    public Integer getNotEditedInLastXDays() {
        return notEditedInLastXDays;
    }

    public void setNotEditedInLastXDays(@Nullable final Integer notEditedInLastXDays) {
        this.notEditedInLastXDays = notEditedInLastXDays;
    }

    @Nullable
    public String getNotContents() {
        return notContents;
    }

    public void setNotContents(@Nullable final String notContents) {
        this.notContents = notContents;
    }

    public TriStateSelectionState getHasBugzillaBugs() {
        return hasBugzillaBugs;
    }

    public void setHasBugzillaBugs(final TriStateSelectionState hasBugzillaBugs) {
        this.hasBugzillaBugs = hasBugzillaBugs;
    }

    public TriStateSelectionState getHasOpenBugzillaBugs() {
        return hasOpenBugzillaBugs;
    }

    public void setHasOpenBugzillaBugs(final TriStateSelectionState hasOpenBugzillaBugs) {
        this.hasOpenBugzillaBugs = hasOpenBugzillaBugs;
    }

    public TriStateSelectionState getHasXMLErrors() {
        return hasXMLErrors;
    }

    public void setHasXMLErrors(final TriStateSelectionState hasXMLErrors) {
        this.hasXMLErrors = hasXMLErrors;
    }

    public final boolean isMatchAll() {
        return matchAll;
    }

    public void setMatchAll(final boolean matchAll) {
        this.matchAll = matchAll;
    }

    public BaseTopicSearchUIFields() {

    }

    /**
     * @param filter The filter that defines the state of the tags
     */
    public BaseTopicSearchUIFields(@Nullable final RESTFilterV1 filter) {
        super(filter);
    }

    @Override
    public void populateFilter(@NotNull final RESTFilterV1 filter) {

        if (!GWTUtilities.isStringNullOrEmpty(getIds())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_IDS_FILTER_VAR);
            field.explicitSetValue(this.getIds());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotIds())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR);
            field.explicitSetValue(this.getNotIds());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getDescription())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR);
            field.explicitSetValue(this.getDescription());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotDescription())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR);
            field.explicitSetValue(this.getNotDescription());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getTitle())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_TITLE_FILTER_VAR);
            field.explicitSetValue(this.getTitle());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotTitle())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR);
            field.explicitSetValue(this.getNotTitle());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getEditedInLastXDays() != null) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS);
            field.explicitSetValue(this.getEditedInLastXDays().toString());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getNotEditedInLastXDays() != null) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS);
            field.explicitSetValue(this.getNotEditedInLastXDays().toString());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getContents())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_XML_FILTER_VAR);
            field.explicitSetValue(this.getContents());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotContents())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR);
            field.explicitSetValue(this.getNotContents());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getIncludedInContentSpecs())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC);
            field.explicitSetValue(this.getIncludedInContentSpecs());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotIncludedInContentSpecs())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC);
            field.explicitSetValue(this.getNotIncludedInContentSpecs());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getFreeTextSearch())) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR);
            field.explicitSetValue(this.getFreeTextSearch());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (this.isMatchAll() != MATCH_ALL_DEFAULT) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.LOGIC_FILTER_VAR);
            field.explicitSetValue(this.isMatchAll() + "");
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getCreatedBefore() != null) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.ENDDATE_FILTER_VAR);
            field.explicitSetValue(this.dateformat.format(getCreatedBefore()));
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getEditedBefore() != null) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.ENDEDITDATE_FILTER_VAR);
            field.explicitSetValue(this.dateformat.format(getEditedBefore()));
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getCreatedAfter() != null) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.STARTDATE_FILTER_VAR);
            field.explicitSetValue(this.dateformat.format(getCreatedAfter()));
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getEditedAfter() != null) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.STARTEDITDATE_FILTER_VAR);
            field.explicitSetValue(this.dateformat.format(getEditedAfter()));
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!getHasXMLErrors().equals(TriStateSelectionState.NONE)) {
            @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            if (getHasXMLErrors().equals(TriStateSelectionState.SELECTED)) {
                field.explicitSetName(CommonFilterConstants.TOPIC_HAS_XML_ERRORS);
            } else {
                field.explicitSetName(CommonFilterConstants.TOPIC_HAS_NOT_XML_ERRORS);
            }
            field.explicitSetValue("true");
            filter.getFilterFields_OTM().addNewItem(field);
        }
    }

    @Override
    public void initialize(@Nullable final RESTFilterV1 filter) {
        if (filter != null) {

            createdAfter = null;
            createdBefore = null;
            editedAfter = null;
            editedBefore = null;
            editedInLastXDays = null;
            notEditedInLastXDays = null;
            ids = "";
            notIds = "";
            title = "";
            contents = "";
            notContents = "";
            notTitle = "";
            description = "";
            notDescription = "";
            includedInContentSpecs = "";
            notIncludedInContentSpecs = "";
            freeTextSearch = "";
            hasBugzillaBugs = TriStateSelectionState.NONE;
            hasOpenBugzillaBugs = TriStateSelectionState.NONE;
            hasXMLErrors = TriStateSelectionState.NONE;
            matchAll = true;

            if (filter.getFilterFields_OTM() != null) {

                for (@NotNull final RESTFilterFieldCollectionItemV1 field : filter.getFilterFields_OTM().getItems()) {

                    final RESTFilterFieldV1 fieldItem = field.getItem();

                    if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_IDS_FILTER_VAR)) {
                        setIds(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR)) {
                        setNotIds(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR)) {
                        setDescription(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR)) {
                        setNotDescription(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_TITLE_FILTER_VAR)) {
                        setTitle(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR)) {
                        setNotTitle(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS)) {
                        try {
                            setEditedInLastXDays(Integer.parseInt(fieldItem.getValue()));
                        } catch (@NotNull final NumberFormatException ex) {
                            // do nothing
                        }
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS)) {
                        try {
                            setNotEditedInLastXDays(Integer.parseInt(fieldItem.getValue()));
                        } catch (@NotNull final NumberFormatException ex) {
                            // do nothing
                        }
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_XML_FILTER_VAR)) {
                        setContents(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR)) {
                        setNotContents(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC)) {
                        setIncludedInContentSpecs(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC)) {
                        setNotIncludedInContentSpecs(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR)) {
                        setFreeTextSearch(fieldItem.getValue());
                    } else if (fieldItem.getName().equals(CommonFilterConstants.LOGIC_FILTER_VAR)) {
                        setMatchAll(Boolean.parseBoolean(fieldItem.getValue()));
                    } else if (fieldItem.getName().equals(CommonFilterConstants.ENDDATE_FILTER_VAR)) {
                        setCreatedBefore(dateformat.parse(fieldItem.getValue()));
                    } else if (fieldItem.getName().equals(CommonFilterConstants.ENDEDITDATE_FILTER_VAR)) {
                        setEditedBefore(dateformat.parse(fieldItem.getValue()));
                    } else if (fieldItem.getName().equals(CommonFilterConstants.STARTDATE_FILTER_VAR)) {
                        setCreatedAfter(dateformat.parse(fieldItem.getValue()));
                    } else if (fieldItem.getName().equals(CommonFilterConstants.STARTEDITDATE_FILTER_VAR)) {
                        setEditedAfter(dateformat.parse(fieldItem.getValue()));
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_HAS_XML_ERRORS)) {
                        setHasXMLErrors(TriStateSelectionState.SELECTED);
                    } else if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_HAS_NOT_XML_ERRORS)) {
                        setHasXMLErrors(TriStateSelectionState.UNSELECTED);
                    }
                }
            }
        }
    }

    @NotNull
    @Override
    public String getSearchQuery(final boolean includeQueryPrefix) {

        @NotNull final StringBuilder retValue = new StringBuilder(
                includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON : "");

        if (!GWTUtilities.isStringNullOrEmpty(ids)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_IDS_FILTER_VAR).append("=").append(encodeQueryParameter(ids));
        }
        if (!GWTUtilities.isStringNullOrEmpty(notIds)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR).append("=").append(encodeQueryParameter(notIds));
        }
        if (!GWTUtilities.isStringNullOrEmpty(description)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR).append("=").append(
                    encodeQueryParameter(description));
        }
        if (!GWTUtilities.isStringNullOrEmpty(notDescription)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR).append("=").append(
                    encodeQueryParameter(notDescription));
        }
        if (!GWTUtilities.isStringNullOrEmpty(title)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_TITLE_FILTER_VAR).append("=").append(encodeQueryParameter(title));
        }
        if (!GWTUtilities.isStringNullOrEmpty(notTitle)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR).append("=").append(
                    encodeQueryParameter(notTitle));
        }
        if (editedInLastXDays != null) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS).append("=").append(
                    encodeQueryParameter(editedInLastXDays.toString()));
        }
        if (notEditedInLastXDays != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS + "=" + encodeQueryParameter(
                    notEditedInLastXDays.toString()));
        }
        if (!GWTUtilities.isStringNullOrEmpty(contents)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_XML_FILTER_VAR + "=" + encodeQueryParameter(contents));
        }
        if (!GWTUtilities.isStringNullOrEmpty(notContents)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR).append("=").append(
                    encodeQueryParameter(notContents));
        }
        if (createdBefore != null) {
            retValue.append(";").append(CommonFilterConstants.ENDDATE_FILTER_VAR).append("=").append(
                    encodeQueryParameter(dateformat.format(createdBefore)));
        }
        if (editedBefore != null) {
            retValue.append(";").append(CommonFilterConstants.ENDEDITDATE_FILTER_VAR).append("=").append(
                    encodeQueryParameter(dateformat.format(editedBefore)));
        }
        if (editedAfter != null) {
            retValue.append(";").append(CommonFilterConstants.STARTEDITDATE_FILTER_VAR).append("=").append(
                    encodeQueryParameter(dateformat.format(editedAfter)));
        }
        if (createdAfter != null) {
            retValue.append(";").append(CommonFilterConstants.STARTDATE_FILTER_VAR).append("=").append(
                    encodeQueryParameter(dateformat.format(createdAfter)));
        }
        if (!GWTUtilities.isStringNullOrEmpty(includedInContentSpecs)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC).append("=").append(
                    encodeQueryParameter(includedInContentSpecs));
        }
        if (!GWTUtilities.isStringNullOrEmpty(notIncludedInContentSpecs)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC).append("=").append(
                    encodeQueryParameter(notIncludedInContentSpecs));
        }
        if (!GWTUtilities.isStringNullOrEmpty(freeTextSearch)) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR).append("=").append(
                    encodeQueryParameter(freeTextSearch));
        }
        if (hasBugzillaBugs == TriStateSelectionState.SELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_HAS_BUGZILLA_BUGS).append("=").append(encodeQueryParameter("true"));
        } else if (hasBugzillaBugs == TriStateSelectionState.UNSELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_HAS_NOT_BUGZILLA_BUGS).append("=").append(encodeQueryParameter("true"));
        }
        if (hasOpenBugzillaBugs == TriStateSelectionState.SELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_HAS_OPEN_BUGZILLA_BUGS).append("=").append(
                    encodeQueryParameter("true"));
        } else if (hasOpenBugzillaBugs == TriStateSelectionState.UNSELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_HAS_NOT_OPEN_BUGZILLA_BUGS).append("=").append(
                    encodeQueryParameter("true"));
        }
        if (hasXMLErrors == TriStateSelectionState.SELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_HAS_XML_ERRORS).append("=").append(
                    encodeQueryParameter("true"));
        } else if (hasXMLErrors == TriStateSelectionState.UNSELECTED) {
            retValue.append(";").append(CommonFilterConstants.TOPIC_HAS_NOT_XML_ERRORS).append("=").append(
                    encodeQueryParameter("true"));
        }

        if (matchAll) {
            retValue.append(";").append(CommonFilterConstants.LOGIC_FILTER_VAR).append("=").append(
                    encodeQueryParameter(CommonFilterConstants.AND_LOGIC));
        } else {
            retValue.append(";").append(CommonFilterConstants.LOGIC_FILTER_VAR).append("=").append(
                    encodeQueryParameter(CommonFilterConstants.OR_LOGIC));
        }

        return retValue.toString();
    }

}
