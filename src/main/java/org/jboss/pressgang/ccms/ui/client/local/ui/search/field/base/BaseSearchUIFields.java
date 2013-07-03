package org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base;

import com.google.gwt.http.client.URL;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchViewBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseSearchUIFields implements SearchViewBase {

    public BaseSearchUIFields() {

    }

    /**
     * @param filter The filter that defines the state of the tags
     */
    public BaseSearchUIFields(@Nullable final RESTFilterV1 filter) {
        initialize(filter);
    }

    public abstract void initialize(@Nullable final RESTFilterV1 filter);

    public abstract void populateFilter(@NotNull final RESTFilterV1 filter);

    protected String encodeQueryParameter(final String value) {
        return Constants.ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(value) : value;
    }

    protected RESTFilterFieldV1 createFilterField(@NotNull final String name, @NotNull final String value) {
        @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
        field.explicitSetName(name);
        field.explicitSetValue(value);
        return field;
    }
}
