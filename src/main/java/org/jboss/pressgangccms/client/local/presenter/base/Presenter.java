package org.jboss.pressgangccms.client.local.presenter.base;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The base interface for all presenters
 */
public interface Presenter
{
	/**
	 * Called when the presenter is to be displayed
	 * 
	 * @param container
	 *            The container that will host the presneter
	 */
	void go(final HasWidgets container);
	

}
