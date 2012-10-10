package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TagProjectsPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TagProjectsView";

    public interface Display extends TagViewInterface {
        EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> provider);

        CellTable<RESTProjectCollectionItemV1> getResults();

        SimplePager getPager();

        Column<RESTProjectCollectionItemV1, String> getButtonColumn();
    }

    public interface LogicComponent extends Component<Display> {

    }

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Override
    public void parseToken(final String searchToken) {

    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(display,  display);
    }
}
