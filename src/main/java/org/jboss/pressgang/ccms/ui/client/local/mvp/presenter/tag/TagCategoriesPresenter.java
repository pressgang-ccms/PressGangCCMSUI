package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren.BaseOrderedChildrenComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The presenter that provides the logic for the tag category relationships.
 *
 * @author Matthew Casperson
 */
@Dependent
public class TagCategoriesPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TagCategoriesView";

    public interface Display extends BaseOrderedChildrenViewInterface<
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, 
        RESTCategoryV1,
        RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1, 
        RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>, TagViewInterface {
 
    }
    
    public interface LogicComponent extends BaseOrderedChildrenComponentInterface<TagCategoriesPresenter.Display, 
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, 
        RESTCategoryV1,
        RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1, 
        RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>
    {

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
        
        component.bind(display, display);
    }
}
