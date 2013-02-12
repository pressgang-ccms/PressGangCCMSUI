package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.filter.RESTFilterV1BasicDetailsEditor;

import javax.enterprise.context.Dependent;
import java.util.List;

/**

 */
@Dependent
public class SearchFilterResultsAndFilterPresenter extends BaseSearchAndEditComponent<
        SearchFilterFilteredResultsPresenter.Display,
        RESTFilterV1,
        RESTFilterCollectionV1,
        RESTFilterCollectionItemV1,
        SearchFilterPresenter.Display,
        RESTFilterV1BasicDetailsEditor> {

    @Override
    protected final void loadAdditionalDisplayedItemData() {
        /* There are no additional details that need to be loaded */
    }

    @Override
    protected void initializeViews(final List<BaseTemplateViewInterface> filter) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void bindActionButtons() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void bindFilteredResultsButtons() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void parseToken(final String historyToken) {

    }

    @Override
    public void go(final HasWidgets container) {

    }
}
