package org.jboss.pressgang.ccms.ui.client.local.ui.search.field;

import java.util.Date;

import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchViewBase;

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

    public Date getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(Date createdAfter) {
        this.createdAfter = createdAfter;
    }

    public Date getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(Date createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Date getEditedAfter() {
        return editedAfter;
    }

    public void setEditedAfter(Date editedAfter) {
        this.editedAfter = editedAfter;
    }

    public Date getEditedBefore() {
        return editedBefore;
    }

    public void setEditedBefore(Date editedBefore) {
        this.editedBefore = editedBefore;
    }

    public Integer getEditedInLastXDays() {
        return editedInLastXDays;
    }

    public void setEditedInLastXDays(Integer editedInLastXDays) {
        this.editedInLastXDays = editedInLastXDays;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getNotIds() {
        return notIds;
    }

    public void setNotIds(String notIds) {
        this.notIds = notIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotTitle() {
        return notTitle;
    }

    public void setNotTitle(String notTitle) {
        this.notTitle = notTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotDescription() {
        return notDescription;
    }

    public void setNotDescription(String notDescription) {
        this.notDescription = notDescription;
    }

    public String getIncludedInContentSpecs() {
        return includedInContentSpecs;
    }

    public void setIncludedInContentSpecs(String includedInContentSpecs) {
        this.includedInContentSpecs = includedInContentSpecs;
    }

    public String getNotIncludedInContentSpecs() {
        return notIncludedInContentSpecs;
    }

    public void setNotIncludedInContentSpecs(String notIncludedInContentSpecs) {
        this.notIncludedInContentSpecs = notIncludedInContentSpecs;
    }

    public String getFreeTextSearch() {
        return freeTextSearch;
    }

    public void setFreeTextSearch(String freeTextSearch) {
        this.freeTextSearch = freeTextSearch;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getNotEditedInLastXDays() {
        return notEditedInLastXDays;
    }

    public void setNotEditedInLastXDays(Integer notEditedInLastXDays) {
        this.notEditedInLastXDays = notEditedInLastXDays;
    }

    public String getNotContents() {
        return notContents;
    }

    public void setNotContents(String notContents) {
        this.notContents = notContents;
    }

    public TriStateSelectionState getHasBugzillaBugs() {
        return hasBugzillaBugs;
    }

    public void setHasBugzillaBugs(TriStateSelectionState hasBugzillaBugs) {
        this.hasBugzillaBugs = hasBugzillaBugs;
    }

    public TriStateSelectionState getHasOpenBugzillaBugs() {
        return hasOpenBugzillaBugs;
    }

    public void setHasOpenBugzillaBugs(TriStateSelectionState hasOpenBugzillaBugs) {
        this.hasOpenBugzillaBugs = hasOpenBugzillaBugs;
    }

    public boolean isMatchAll() {
        return matchAll;
    }

    public void setMatchAll(boolean matchAll) {
        this.matchAll = matchAll;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getSearchQuery(final boolean includeQueryPrefix) {
        
        final StringBuilder retValue = new StringBuilder(includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON : "");

        retValue.append(";" + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ids);
        retValue.append(";" + CommonFilterConstants.TOPIC_IDS_NOT_FILTER_VAR + "=" + notIds);
        retValue.append(";" + CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR + "=" + description);
        retValue.append(";" + CommonFilterConstants.TOPIC_DESCRIPTION_NOT_FILTER_VAR + "=" + notDescription);
        retValue.append(";" + CommonFilterConstants.TOPIC_TITLE_FILTER_VAR + "=" + title);
        retValue.append(";" + CommonFilterConstants.TOPIC_TITLE_NOT_FILTER_VAR + "=" + notTitle);
        retValue.append(";" + CommonFilterConstants.TOPIC_EDITED_IN_LAST_DAYS + "=" + editedInLastXDays);
        retValue.append(";" + CommonFilterConstants.TOPIC_NOT_EDITED_IN_LAST_DAYS + "=" + notEditedInLastXDays);
        retValue.append(";" + CommonFilterConstants.TOPIC_XML_FILTER_VAR + "=" + contents);
        retValue.append(";" + CommonFilterConstants.TOPIC_XML_NOT_FILTER_VAR + "=" + notContents);
        retValue.append(";" + CommonFilterConstants.TOPIC_ENDDATE_FILTER_VAR + "=" + dateformat.format(createdBefore));
        retValue.append(";" + CommonFilterConstants.TOPIC_ENDEDITDATE_FILTER_VAR + "=" + dateformat.format(editedBefore));
        retValue.append(";" + CommonFilterConstants.TOPIC_STARTEDITDATE_FILTER_VAR + "=" + dateformat.format(editedAfter));
        retValue.append(";" + CommonFilterConstants.TOPIC_STARTDATE_FILTER_VAR + "=" + dateformat.format(createdAfter));
        retValue.append(";" + CommonFilterConstants.TOPIC_IS_INCLUDED_IN_SPEC + "=" + includedInContentSpecs);
        retValue.append(";" + CommonFilterConstants.TOPIC_IS_NOT_INCLUDED_IN_SPEC + "=" + notIncludedInContentSpecs);
        retValue.append(";" + CommonFilterConstants.TOPIC_TEXT_SEARCH_FILTER_VAR + "=" + freeTextSearch);
        if (hasBugzillaBugs == TriStateSelectionState.SELECTED)
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_BUGZILLA_BUGS + "=true");
        else if (hasBugzillaBugs == TriStateSelectionState.UNSELECTED)
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_NOT_BUGZILLA_BUGS + "=true");
        if (hasOpenBugzillaBugs == TriStateSelectionState.SELECTED)
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_OPEN_BUGZILLA_BUGS + "=true");
        else if (hasOpenBugzillaBugs == TriStateSelectionState.UNSELECTED)
            retValue.append(";" + CommonFilterConstants.TOPIC_HAS_NOT_OPEN_BUGZILLA_BUGS + "=true");

        if (matchAll)
            retValue.append(";" + CommonFilterConstants.LOGIC_FILTER_VAR + "=" + CommonFilterConstants.AND_LOGIC);
        else
            retValue.append(";" + CommonFilterConstants.LOGIC_FILTER_VAR + "=" + CommonFilterConstants.OR_LOGIC);

        return retValue.toString();
    }
}
