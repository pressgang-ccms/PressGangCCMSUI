package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
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
public class TagFilteredResultsPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TagFilteredResultsView";
    
    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTTagCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider);

        CellTable<RESTTagCollectionItemV1> getResults();

        SimplePager getPager();

        TextBox getIdFilter();

        TextBox getDescriptionFilter();

        TextBox getNameFilter();

        @Override
        PushButton getSearch();
    }
    
    public interface LogicComponent extends Component<Display>
    {
        void bind(final String queryString, final TagFilteredResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
        ProviderUpdateData<RESTTagCollectionItemV1> getTagProviderData();
        void setTagProviderData(final ProviderUpdateData<RESTTagCollectionItemV1> tagProviderData);
    }

    @Inject
    private Display display;
    
    @Inject private LogicComponent component;

    private String queryString;

    @Override
    public void parseToken(final String searchToken) {
        queryString = searchToken.replace(HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(queryString, display, display);
    }

    
}
