package org.jboss.pressgangccms.client.local.mvp.presenter.tag;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;

abstract public class TagPresenterBase extends TemplatePresenter {
    /** Called when a new "tab" is selected */
    abstract protected void reInitialiseView();

    protected String getQuery(final TagFilteredResultsPresenter.Display searchDisplay) {
        final StringBuilder retValue = new StringBuilder(Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON);
        if (!searchDisplay.getIdFilter().getText().isEmpty())
            retValue.append(";tagIds=" + searchDisplay.getIdFilter().getText());
        if (!searchDisplay.getDescriptionFilter().getText().isEmpty())
            retValue.append(";tagName=" + searchDisplay.getNameFilter().getText());
        if (!searchDisplay.getNameFilter().getText().isEmpty())
            retValue.append(";tagDesc=" + searchDisplay.getDescriptionFilter().getText());

        return retValue.toString();
    }
}
