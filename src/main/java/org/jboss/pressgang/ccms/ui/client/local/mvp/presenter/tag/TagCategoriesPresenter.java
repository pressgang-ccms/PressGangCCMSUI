package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.HasData;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter that provides the logic for the tag category relationships.
 *
 * @author matthew
 */
@Dependent
public class TagCategoriesPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TagCategoriesView";

    public interface Display extends TagViewInterface {
        EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider);

        CellTable<RESTCategoryCollectionItemV1> getResults();

        SimplePager getPager();

        Column<RESTCategoryCollectionItemV1, String> getButtonColumn();

        Column<RESTTagInCategoryCollectionItemV1, String> getTagDownButtonColumn();

        Column<RESTTagInCategoryCollectionItemV1, String> getTagUpButtonColumn();

        EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> getTagsProvider();

        void setTagsProvider(EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> tagsProvider);

        VerticalPanel getTagsResultsPanel();

        HandlerSplitLayoutPanel getSplit();

        VerticalPanel getSearchResultsPanel();
    }
    
    public interface LogicComponent extends Component<Display>
    {
        void bind(final String queryString, final TagCategoriesPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
        boolean unsavedCategoryChanges();
        ProviderUpdateData<RESTCategoryCollectionItemV1> getCategoryProviderData();
        void setCategoryProviderData(ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData);
        void getCategories();
        EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generateCategoriesListProvider();
        EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> generateCategoriesTagListProvider();
    }

    @Inject
    private Display display;
    
    @Inject private LogicComponent component;

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
}
