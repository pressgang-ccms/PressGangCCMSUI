package org.jboss.pressgang.ccms.ui.client.local.mvp.component.search;

import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

public class SearchFieldComponent extends ComponentBase<SearchFieldPresenter.Display> implements
        SearchFieldPresenter.LogicComponent {

    /**
     * @inheritDoc
     */
    @Override
    public void bind(final SearchFieldPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        
        super.bind(display, waitDisplay);
        
    }

}
