package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
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
public class ImageFilteredResultsPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "ImageFilteredResultsView";

    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTImageCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTImageCollectionItemV1> provider);

        CellTable<RESTImageCollectionItemV1> getResults();

        SimplePager getPager();

        TextBox getImageIdFilter();

        TextBox getImageDescriptionFilter();

        @Override
        PushButton getSearch();

        TextBox getImageOriginalFileNameFilter();
    }
    
    public interface LogicComponent extends Component<Display>
    {
        ProviderUpdateData<RESTImageCollectionItemV1> getProviderData();
        void setProviderData(ProviderUpdateData<RESTImageCollectionItemV1> providerData);
        void bind(final String queryString, final ImageFilteredResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
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
        component.setFeedbackLink(HISTORY_TOKEN);
    }
}
