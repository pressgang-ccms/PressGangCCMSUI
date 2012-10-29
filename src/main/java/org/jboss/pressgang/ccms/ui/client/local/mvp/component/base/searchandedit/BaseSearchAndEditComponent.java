package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;

/**
 * The base class for all components adding logic to search and edit views
 * 
 * @author Matthew Casperson
 * 
 * @param <T> The search and edit view interface
 * @param <U> The filtered search results view
 * @param <V> The filtered search results view data type
 * @param <W> The base class for all the detail views
 */
abstract public class BaseSearchAndEditComponent<T extends BaseSearchAndEditViewInterface, U extends BaseFilteredResultsViewInterface<V>, V extends RESTBaseCollectionItemV1, W extends BaseTemplateViewInterface>
        extends ComponentBase<T> {

    /** The last displayed view */
    protected W lastDisplayedView;

    /** Called once an entity has been saved */
    abstract protected void updateDisplayAfterSave(final boolean wasNewEntity);

    /** Binds logic to the search results list row click event */
    abstract protected void bindResultsListRowClicks();
    
    /** Binds logic to the action buttons */
    abstract protected void bindActionButtons();

    /** Saves the position of the main split */
    protected void bindMainSplitResize(final String saveKey) {
        display.getSplitPanel().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(saveKey, display.getSplitPanel().getSplitPosition(display.getResultsPanel())
                        + "");
            }
        });
    }
    
    /**
     * Restores the size of the main split screen
     * @param preferencesKey The key against which the previous size was saved
     */
    protected void loadMainSplitResize(final String preferencesKey)
    {
        display.getSplitPanel().setSplitPosition(display.getResultsPanel(),
                Preferences.INSTANCE.getInt(preferencesKey, Constants.SPLIT_PANEL_SIZE), false);
    }

    /** Called when displaying changes to a entity or when changing views */
    abstract protected void reInitialiseView(final W displayedView);

    /**
     * @return The query string that can be appended to the history token
     */
    abstract protected String getQuery();
}
