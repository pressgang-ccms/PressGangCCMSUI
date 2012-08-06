package org.pressgangccms.ui.client.local;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The base interface for all presenters
 */
public abstract interface Presenter 
{
	public abstract void go(final HasWidgets container);
}
