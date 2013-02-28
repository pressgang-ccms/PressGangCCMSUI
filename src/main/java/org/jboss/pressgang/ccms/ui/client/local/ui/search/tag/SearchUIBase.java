package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

/**
 * The base class for all the entites that make up the tag search screen.
 *
 * @author matthew
 */
public class SearchUIBase {
    /**
     * Each entity has a name
     */
    private final String name;
    /**
     * Each entity has an ID
     */
    private final Integer id;

    public final Integer getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public SearchUIBase(final String name, final Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SearchUIBase)) {
            return false;
        }

        final SearchUIBase otherCasted = (SearchUIBase) other;

        if (this.name == null && otherCasted.name == null) {
            return true;
        }

        if (this.name == null || otherCasted.name == null) {
            return false;
        }

        return (this.name.equals(otherCasted.name));
    }

    @Override
    public int hashCode() {
        if (this.name == null) {
            return 0;
        }
        return name.hashCode();
    }
}
