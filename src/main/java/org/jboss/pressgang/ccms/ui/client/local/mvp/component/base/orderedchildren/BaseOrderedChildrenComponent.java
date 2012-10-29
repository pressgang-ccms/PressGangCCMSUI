package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;

/**
 * 
 * @author matthew
 *
 * @param <S> The view type
 * @param <T> The entity being modified
 * @param <W> The entity type modified as an ordered collection of children
 * @param <U> The potential children type
 * @param <V> The existing children type
 */
abstract public class BaseOrderedChildrenComponent<S extends BaseOrderedChildrenViewInterface<T, U, V>, T extends RESTBasePrimaryEntityV1, W extends RESTBasePrimaryEntityV1, U extends RESTBaseCollectionItemV1, V extends RESTBaseCollectionItemV1>
        extends BaseChildrenComponent<S, T, U> implements BaseOrderedChildrenComponentInterface<S, T, W, U, V> {

    protected ProviderUpdateData<V> existingProviderData = new ProviderUpdateData<V>();


    @Override
    public ProviderUpdateData<V> getExistingProviderData() {
        return existingProviderData;
    }

    @Override
    public void setExistingProviderData(final ProviderUpdateData<V> existingProviderData) {
        this.existingProviderData = existingProviderData;
    }

    /**
     * Save the size of the split ui component
     */
    protected void bindChildSplitResize(final String preferencesKey) {
        display.getSplit().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, display.getSplit()
                        .getSplitPosition(display.getPossibleChildrenResultsPanel()) + "");
            }
        });
    }

    abstract protected void getExistingEntityList();

    abstract protected void bindExistingChildrenRowClick();

}
