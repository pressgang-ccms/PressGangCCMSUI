package org.jboss.pressgang.ccms.ui.client.local.ui.search.field;

import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchViewBase;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * The backing object for the search fields view. Instance of this class will be manipulated by a GWT Editor
 * 
 * @author Matthew Casperson
 * 
 */
public class SearchUIFields implements SearchViewBase {
    private static final boolean MATCH_ALL_DEFAULT = true;

    private Date createdAfter;
    private Date createdBefore;
    private Date editedAfter;
    private Date editedBefore;
    private Integer editedInLastXDays;
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
    private boolean matchAll = MATCH_ALL_DEFAULT;
    private final DateTimeFormat dateformat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);

    public final Date getCreatedAfter() {
        return GWTUtilities.createDateCopy(this.createdAfter);
    }

    public final void setCreatedAfter(final Date createdAfter) {
        this.createdAfter = GWTUtilities.createDateCopy(createdAfter);
    }

    public final Date getCreatedBefore() {
        return GWTUtilities.createDateCopy(this.createdBefore);
    }

    public final void setCreatedBefore(final Date createdBefore) {
        this.createdBefore = GWTUtilities.createDateCopy(createdBefore);
    }

    public final Date getEditedAfter() {
        return GWTUtilities.createDateCopy(this.editedAfter);
    }

    public final void setEditedAfter(final Date editedAfter) {
        this.editedAfter = GWTUtilities.createDateCopy(editedAfter);
    }

    public final Date getEditedBefore() {
        return GWTUtilities.createDateCopy(this.editedBefore);
    }

    public final void setEditedBefore(final Date editedBefore) {
        this.editedBefore = GWTUtilities.createDateCopy(editedBefore);
    }

    public final Integer getEditedInLastXDays() {
        return this.editedInLastXDays;
    }

    public final void setEditedInLastXDays(final Integer editedInLastXDays) {
        this.editedInLastXDays = editedInLastXDays;
    }

    public final String getIds() {
        return this.ids;
    }

    public final void setIds(final String ids) {
        this.ids = ids;
    }

    public final String getNotIds() {
        return this.notIds;
    }

    public final void setNotIds(final String notIds) {
        this.notIds = notIds;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(final String title) {
        this.title = title;
    }

    public final String getNotTitle() {
        return this.notTitle;
    }

    public final void setNotTitle(final String notTitle) {
        this.notTitle = notTitle;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final String getNotDescription() {
        return this.notDescription;
    }

    public final void setNotDescription(final String notDescription) {
        this.notDescription = notDescription;
    }

    public final String getIncludedInContentSpecs() {
        return this.includedInContentSpecs;
    }

    public final void setIncludedInContentSpecs(final String includedInContentSpecs) {
        this.includedInContentSpecs = includedInContentSpecs;
    }

    public final String getNotIncludedInContentSpecs() {
        return this.notIncludedInContentSpecs;
    }

    public final void setNotIncludedInContentSpecs(final String notIncludedInContentSpecs) {
        this.notIncludedInContentSpecs = notIncludedInContentSpecs;
    }

    public final String getFreeTextSearch() {
        return this.freeTextSearch;
    }

    public final void setFreeTextSearch(final String freeTextSearch) {
        this.freeTextSearch = freeTextSearch;
    }

    public final String getContents() {
        return this.contents;
    }

    public final void setContents(final String contents) {
        this.contents = contents;
    }

    public final Integer getNotEditedInLastXDays() {
        return this.notEditedInLastXDays;
    }

    public final void setNotEditedInLastXDays(final Integer notEditedInLastXDays) {
        this.notEditedInLastXDays = notEditedInLastXDays;
    }

    public final String getNotContents() {
        return this.notContents;
    }

    public final void setNotContents(final String notContents) {
        this.notContents = notContents;
    }

    public final TriStateSelectionState getHasBugzillaBugs() {
        return this.hasBugzillaBugs;
    }

    public final void setHasBugzillaBugs(final TriStateSelectionState hasBugzillaBugs) {
        this.hasBugzillaBugs = hasBugzillaBugs;
    }

    public final TriStateSelectionState getHasOpenBugzillaBugs() {
        return this.hasOpenBugzillaBugs;
    }

    public final void setHasOpenBugzillaBugs(final TriStateSelectionState hasOpenBugzillaBugs) {
        this.hasOpenBugzillaBugs = hasOpenBugzillaBugs;
    }

    public final boolean isMatchAll() {
        return this.matchAll;
    }

    public final void setMatchAll(final boolean matchAll) {
        this.matchAll = matchAll;
    }

    public SearchUIFields() {

    }

    /**
     *
     * @param filter The filter that defines the state of the tags
     */
    public SearchUIFields(@Nullable final RESTFilterV1 filter) {
        initialize(filter);
    }

    public void populateFilter(@NotNull final RESTFilterV1 filter) {

        if (!GWTUtilities.isStringNullOrEmpty(this.getIds()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_IDS_FILTER_VAR);
            field.explicitSetValue(this.getIds());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotIds()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR);
            field.explicitSetValue(this.getNotIds());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getDescription()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR);
            field.explicitSetValue(this.getDescription());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotDescription()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR);
            field.explicitSetValue(this.getNotDescription());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getTitle()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_TITLE_FILTER_VAR);
            field.explicitSetValue(this.getTitle());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotTitle()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR);
            field.explicitSetValue(this.getNotTitle());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getEditedInLastXDays() != null)
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS);
            field.explicitSetValue(this.getEditedInLastXDays().toString());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getNotEditedInLastXDays() != null)
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS);
            field.explicitSetValue(this.getNotEditedInLastXDays().toString());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getContents()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_XML_FILTER_VAR);
            field.explicitSetValue(this.getContents());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotContents()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR);
            field.explicitSetValue(this.getNotContents());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getIncludedInContentSpecs()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC);
            field.explicitSetValue(this.getIncludedInContentSpecs());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getNotIncludedInContentSpecs()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC);
            field.explicitSetValue(this.getNotIncludedInContentSpecs());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (!GWTUtilities.isStringNullOrEmpty(this.getFreeTextSearch()))
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR);
            field.explicitSetValue(this.getFreeTextSearch());
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (this.isMatchAll() != MATCH_ALL_DEFAULT)
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.LOGIC_FILTER_VAR);
            field.explicitSetValue(this.isMatchAll() + "");
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getCreatedBefore() != null)
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_ENDDATE_FILTER_VAR);
            field.explicitSetValue(this.dateformat.format(getCreatedBefore()));
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getEditedBefore() != null)
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_ENDEDITDATE_FILTER_VAR);
            field.explicitSetValue(this.dateformat.format(getEditedBefore()));
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getCreatedAfter() != null)
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_STARTDATE_FILTER_VAR);
            field.explicitSetValue(this.dateformat.format(getCreatedAfter()));
            filter.getFilterFields_OTM().addNewItem(field);
        }

        if (getEditedAfter() != null)
        {
            final RESTFilterFieldV1 field = new RESTFilterFieldV1();
            field.explicitSetName(CommonFilterConstants.TOPIC_STARTEDITDATE_FILTER_VAR);
            field.explicitSetValue(this.dateformat.format(getEditedAfter()));
            filter.getFilterFields_OTM().addNewItem(field);
        }
    }
    
    public void initialize(@Nullable final RESTFilterV1 filter)
    {
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
            matchAll = true;

            if (filter.getFilterFields_OTM() != null) {

                for (final RESTFilterFieldCollectionItemV1 field : filter.getFilterFields_OTM().getItems()) {

                    final RESTFilterFieldV1 fieldItem = field.getItem();

                    if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_IDS_FILTER_VAR)) {
                        this.setIds(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR)) {
                        this.setNotIds(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR)) {
                        this.setDescription(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR)) {
                        this.setNotDescription(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_TITLE_FILTER_VAR)) {
                        this.setTitle(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR)) {
                        this.setNotTitle(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS)) {
                        try {
                            this.setEditedInLastXDays(Integer.parseInt(fieldItem.getValue()));
                        } catch (final NumberFormatException ex) {
                            // do nothing
                        }
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS)) {
                        try {
                            this.setNotEditedInLastXDays(Integer.parseInt(fieldItem.getValue()));
                        } catch (final NumberFormatException ex) {
                            // do nothing
                        }
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_XML_FILTER_VAR)) {
                        this.setContents(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR)) {
                        this.setNotContents(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC)) {
                        this.setIncludedInContentSpecs(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC)) {
                        this.setNotIncludedInContentSpecs(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR)) {
                        this.setFreeTextSearch(fieldItem.getValue());
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.LOGIC_FILTER_VAR)) {
                        this.setMatchAll(Boolean.parseBoolean(fieldItem.getValue()));
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_ENDDATE_FILTER_VAR)) {
                        this.setCreatedBefore(this.dateformat.parse(fieldItem.getValue()));
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_ENDEDITDATE_FILTER_VAR)) {
                        this.setEditedBefore(this.dateformat.parse(fieldItem.getValue()));
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_STARTDATE_FILTER_VAR)) {
                        this.setCreatedAfter(this.dateformat.parse(fieldItem.getValue()));
                    } else  if (fieldItem.getName().equals(CommonFilterConstants.TOPIC_STARTEDITDATE_FILTER_VAR)) {
                        this.setEditedAfter(this.dateformat.parse(fieldItem.getValue()));
                    }
                }
            }
        }
    }

    @Override
    public final String getSearchQuery(final boolean includeQueryPrefix) {

        final StringBuilder retValue = new StringBuilder(includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON
                : "");

        if (!GWTUtilities.isStringNullOrEmpty(this.ids)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.ids) : this.ids));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notIds)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notIds) : this.notIds));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.description)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.description) : this.description));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notDescription)) {
            retValue.append(";"
                    + CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notDescription) : this.notDescription));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.title)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_TITLE_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.title) : this.title));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notTitle)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notTitle) : this.notTitle));
        }
        if (this.editedInLastXDays != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.editedInLastXDays.toString()) : this.editedInLastXDays.toString()));
        }
        if (this.notEditedInLastXDays != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notEditedInLastXDays.toString()) : this.notEditedInLastXDays.toString()));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.contents)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_XML_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.contents) : this.contents));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notContents)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notContents) : this.notContents));
        }
        if (this.createdBefore != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_ENDDATE_FILTER_VAR + "="
                    + this.dateformat.format(this.createdBefore));
        }
        if (this.editedBefore != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_ENDEDITDATE_FILTER_VAR
                    + "=" + this.dateformat.format(this.editedBefore));
        }
        if (this.editedAfter != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_STARTEDITDATE_FILTER_VAR
                    + "=" + this.dateformat.format(this.editedAfter));
        }
        if (this.createdAfter != null) {
            retValue.append(";" + CommonFilterConstants.TOPIC_STARTDATE_FILTER_VAR
                    + "=" + this.dateformat.format(this.createdAfter));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.includedInContentSpecs)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.includedInContentSpecs) : this.includedInContentSpecs));
        }
        if (!GWTUtilities.isStringNullOrEmpty(this.notIncludedInContentSpecs)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.notIncludedInContentSpecs) : this.notIncludedInContentSpecs));
        }
        if (!GWTUtilities.isStringNullOrEmpty(freeTextSearch)) {
            retValue.append(";" + CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(this.freeTextSearch) : this.freeTextSearch));
        }
        if (this.hasBugzillaBugs == TriStateSelectionState.SELECTED) {
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_BUGZILLA_BUGS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString("true") : "true"));
        } else if (this.hasBugzillaBugs == TriStateSelectionState.UNSELECTED) {
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_NOT_BUGZILLA_BUGS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString("true") : "true"));
        }
        if (this.hasOpenBugzillaBugs == TriStateSelectionState.SELECTED) {
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_OPEN_BUGZILLA_BUGS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString("true") : "true"));
        } else if (this.hasOpenBugzillaBugs == TriStateSelectionState.UNSELECTED) {
            retValue.append(";"
                    + CommonFilterConstants.TOPIC_HAS_NOT_OPEN_BUGZILLA_BUGS
                    + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString("true") : "true"));
        }

        if (this.matchAll) {
            retValue.append(";" + CommonFilterConstants.LOGIC_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(CommonFilterConstants.AND_LOGIC) : CommonFilterConstants.AND_LOGIC));
        } else {
            retValue.append(";" + CommonFilterConstants.LOGIC_FILTER_VAR + "="
                    + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(CommonFilterConstants.OR_LOGIC) : CommonFilterConstants.OR_LOGIC));
        }

        return retValue.toString();
    }

}
