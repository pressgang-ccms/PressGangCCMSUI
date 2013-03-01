package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @param <T> The entity type
 * @param <C> The collection item type for entity A
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
abstract public class BaseChildrenView<
        T extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseCollectionV1<D, E, F>,
        F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseTemplateView implements BaseChildrenViewInterface<T, C, D, E, F> {

    /**
     * A reference to the tag that this view will be modifying
     */
    private T originalEntity;

    private boolean readOnly;

    private final VerticalPanel possibleChildrenResultsPanel = new VerticalPanel();
    private final SimplePager possibleChildrenPager = UIUtilities.createSimplePager();
    private final CellTable<C> possibleChildrenResults = UIUtilities.<C>createCellTable();
    private EnhancedAsyncDataProvider<C> possibleChildrenProvider;

    @Nullable
    @Override
    public final T getOriginalEntity() {
        return this.originalEntity;
    }

    @NotNull
    @Override
    public final void setOriginalEntity(@NotNull final T originalEntity) {
        this.originalEntity = originalEntity;
    }

    @NotNull
    @Override
    public final VerticalPanel getPossibleChildrenResultsPanel() {
        return this.possibleChildrenResultsPanel;
    }

    @NotNull
    @Override
    public final SimplePager getPossibleChildrenPager() {
        return this.possibleChildrenPager;
    }

    @NotNull
    @Override
    public final CellTable<C> getPossibleChildrenResults() {
        return this.possibleChildrenResults;
    }

    @Nullable
    @Override
    public final EnhancedAsyncDataProvider<C> getPossibleChildrenProvider() {
        return this.possibleChildrenProvider;
    }

    @Override
    public final void setPossibleChildrenProvider(@NotNull final EnhancedAsyncDataProvider<C> possibleChildrenProvider) {

        if (this.possibleChildrenProvider != null) {
            this.possibleChildrenProvider.removeDataDisplay(this.possibleChildrenResults);
        }

        this.possibleChildrenProvider = possibleChildrenProvider;
        possibleChildrenProvider.addDataDisplay(this.possibleChildrenResults);
    }

    /**
     * @param applicationName The name of the application, which will be added to the page's title field
     * @param pageName        The name of the page that is being displayed, which will be added to the page's title field
     */
    public BaseChildrenView(@NotNull final String applicationName, @NotNull final String pageName) {
        super(applicationName, pageName);

        this.possibleChildrenPager.setDisplay(this.possibleChildrenResults);

        this.possibleChildrenResultsPanel.add(this.possibleChildrenResults);
        this.possibleChildrenResultsPanel.add(this.possibleChildrenPager);

        this.getPanel().setWidget(this.possibleChildrenResultsPanel);
    }

    public final void displayChildren(@NotNull final T originalEntity, final boolean readOnly) {
        this.originalEntity = originalEntity;
        this.readOnly = readOnly;
    }

    /**
     * true if this view is readonly, false otherwise
     */
    public boolean isReadOnly() {
        return readOnly;
    }
}
