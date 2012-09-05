package org.jboss.pressgangccms.client.local.mvp.presenter.base;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter;

abstract public class TagPresenterBase extends TemplatePresenter
{
	abstract protected void reInitialiseView();

	protected void bindViewButtons(final TagPresenter.Display display)
	{

	}

	protected String getQuery(final TagFilteredResultsPresenter.Display searchDisplay)
	{
		final StringBuilder retValue = new StringBuilder(Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON);
		if (!searchDisplay.getIdFilter().getText().isEmpty())
			retValue.append(";tagIds=" + searchDisplay.getIdFilter().getText());
		if (!searchDisplay.getDescriptionFilter().getText().isEmpty())
			retValue.append(";tagName=" + searchDisplay.getDescriptionFilter().getText());
		if (!searchDisplay.getNameFilter().getText().isEmpty())
			retValue.append(";tagDesc=" + searchDisplay.getNameFilter().getText());

		return retValue.toString();
	}
}
