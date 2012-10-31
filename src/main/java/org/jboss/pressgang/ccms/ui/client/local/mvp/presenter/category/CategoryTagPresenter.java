package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren.BaseOrderedChildrenComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.category.CategoryViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class CategoryTagPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "CategoryTagView";

    public interface Display extends
            BaseOrderedChildrenViewInterface<RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1, RESTTagCollectionItemV1, RESTTagInCategoryCollectionItemV1>,
            CategoryViewInterface {
        Column<RESTTagInCategoryCollectionItemV1, String> getTagUpButtonColumn();

        Column<RESTTagInCategoryCollectionItemV1, String> getTagDownButtonColumn();
    }

    public interface LogicComponent
            extends
            BaseOrderedChildrenComponentInterface<Display, RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1, RESTTagCollectionItemV1, RESTTagInCategoryCollectionItemV1, RESTCategoryV1> {

    }

    private Integer entityId;

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        display.initialize(null, false);
        component.bind(display, display);
    }

    @Override
    public void parseToken(final String historyToken) {
        try {
            entityId = Integer.parseInt(GWTUtilities.removeHistoryToken(HISTORY_TOKEN, historyToken));
        } catch (final NumberFormatException ex) {
            entityId = null;
        }
    }

}
