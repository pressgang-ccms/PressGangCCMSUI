package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.stringconstant.RESTStringConstantV1DetailsEditor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The presenter used to display a list of string constants and their details.
 */
public class StringConstantFilteredResultsAndDetailsPresenter extends
        BaseSearchAndEditComponent<
                RESTStringConstantV1,
                RESTStringConstantCollectionV1,
                RESTStringConstantCollectionItemV1,
                RESTStringConstantV1DetailsEditor>
implements BaseTemplatePresenterInterface {
    @Override
    protected void loadAdditionalDisplayedItemData() {
        /*
            Do Nothing.
         */
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
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
    public void bindSearchAndEditExtended(final int topicId, final String pageId, final String queryString) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void parseToken(final String historyToken) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void go(final HasWidgets container) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public interface Display extends BaseSearchAndEditViewInterface<
                RESTStringConstantV1,
                RESTStringConstantCollectionV1,
                RESTStringConstantCollectionItemV1> {

    }
}
