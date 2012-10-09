package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;

@Dependent
public class CategoryFilteredResultsPresenter implements EditableView, TemplatePresenter {
    public static final String HISTORY_TOKEN = "CategoryFilteredResultsView";

    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider);

        CellTable<RESTCategoryCollectionItemV1> getResults();

        SimplePager getPager();

        TextBox getIdFilter();

        TextBox getDescriptionFilter();

        TextBox getNameFilter();

        @Override
        PushButton getSearch();
    }
    
    public interface LogicCompnent extends Component<Display>
    {
        void bind(final String queryString, final CategoryFilteredResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
        ProviderUpdateData<RESTCategoryCollectionItemV1> getCategoryProviderData();
        void setCategoryProviderData(ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData);
    }
   

    @Inject
    private Display display;
    
    @Inject
    private LogicCompnent component;

    private String queryString;

    @Override
    public void parseToken(final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(queryString, display, display);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
