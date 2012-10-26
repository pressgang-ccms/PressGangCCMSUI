package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.category.CategoryViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class CategoryTagPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "CategoryTagView";
    
    public interface Display extends
            BaseOrderedChildrenViewInterface<RESTCategoryV1, RESTTagCollectionItemV1, RESTTagInCategoryCollectionItemV1>, CategoryViewInterface {

    }

    public interface LogicComponent extends Component<Display> {

    }

    @Inject
    private Display display;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
    }

    @Override
    public void parseToken(final String historyToken) {

    }

}
