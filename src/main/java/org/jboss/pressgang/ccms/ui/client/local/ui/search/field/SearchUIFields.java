package org.jboss.pressgang.ccms.ui.client.local.ui.search.field;

import java.util.Date;

import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchViewBase;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.TriStateSelectionState;

/**
 * The backing object for the search fields view. Instance of this class will be manipulated by a GWT Editor
 * 
 * @author Matthew Casperson
 * 
 */
public class SearchUIFields implements SearchViewBase {
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
    private boolean matchAll = true;
    private final DateTimeFormat dateformat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);

    public final Date getCreatedAfter() {
        return this.createdAfter;
    }

    public final void setCreatedAfter(final Date createdAfter) {
        this.createdAfter = createdAfter;
    }

    public final Date getCreatedBefore() {
        return this.createdBefore;
    }

    public final void setCreatedBefore(final Date createdBefore) {
        this.createdBefore = createdBefore;
    }

    public final Date getEditedAfter() {
        return this.editedAfter;
    }

    public final void setEditedAfter(final Date editedAfter) {
        this.editedAfter = editedAfter;
    }

    public final Date getEditedBefore() {
        return this.editedBefore;
    }

    public final void setEditedBefore(final Date editedBefore) {
        this.editedBefore = editedBefore;
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

    /**
     * @inheritDoc
     */
    @Override
    public final String getSearchQuery(final boolean includeQueryPrefix) {

        final StringBuilder retValue = new StringBuilder(includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON
                : "");

        if (!GWTUtilities.isStringNullOrEmpty(ids)) {
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ids);
        }
        if (!GWTUtilities.isStringNullOrEmpty(notIds))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR + "=" + notIds);
        if (!GWTUtilities.isStringNullOrEmpty(description))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR + "=" + description);
        if (!GWTUtilities.isStringNullOrEmpty(notDescription))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR + "=" + notDescription);
        if (!GWTUtilities.isStringNullOrEmpty(title))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_TITLE_FILTER_VAR + "=" + title);
        if (!GWTUtilities.isStringNullOrEmpty(notTitle))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR + "=" + notTitle);
        if (editedInLastXDays != null)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS + "=" + editedInLastXDays.toString());
        if (notEditedInLastXDays != null)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS + "=" + notEditedInLastXDays.toString());
        if (!GWTUtilities.isStringNullOrEmpty(contents))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_XML_FILTER_VAR + "=" + contents);
        if (!GWTUtilities.isStringNullOrEmpty(notContents))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR + "=" + notContents);
        if (createdBefore != null)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_ENDDATE_FILTER_VAR + "=" + dateformat.format(createdBefore));
        if (editedBefore != null)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_ENDEDITDATE_FILTER_VAR + "=" + dateformat.format(editedBefore));
        if (editedAfter != null)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_STARTEDITDATE_FILTER_VAR + "=" + dateformat.format(editedAfter));
        if (createdAfter != null)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_STARTDATE_FILTER_VAR + "=" + dateformat.format(createdAfter));
        if (!GWTUtilities.isStringNullOrEmpty(includedInContentSpecs))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC + "=" + includedInContentSpecs);
        if (!GWTUtilities.isStringNullOrEmpty(notIncludedInContentSpecs))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC + "=" + notIncludedInContentSpecs);
        if (!GWTUtilities.isStringNullOrEmpty(freeTextSearch))
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR + "=" + freeTextSearch);
        if (hasBugzillaBugs == TriStateSelectionState.SELECTED)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_HAS_BUGZILLA_BUGS + "=true");
        else if (hasBugzillaBugs == TriStateSelectionState.UNSELECTED)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_HAS_NOT_BUGZILLA_BUGS + "=true");
        if (hasOpenBugzillaBugs == TriStateSelectionState.SELECTED)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_HAS_OPEN_BUGZILLA_BUGS + "=true");
        else if (hasOpenBugzillaBugs == TriStateSelectionState.UNSELECTED)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_HAS_NOT_OPEN_BUGZILLA_BUGS + "=true");

        if (matchAll)
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.LOGIC_FILTER_VAR + "=" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.AND_LOGIC);
        else
            retValue.append(";" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.LOGIC_FILTER_VAR + "=" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.OR_LOGIC);

        return retValue.toString();
    }

}
