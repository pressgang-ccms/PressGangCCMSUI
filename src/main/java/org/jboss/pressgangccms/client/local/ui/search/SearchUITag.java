package org.jboss.pressgangccms.client.local.ui.search;

import org.jboss.pressgangccms.client.local.ui.TriStateSelectionState;

/**
 * This class represents a single tag under a parent category.
 * @author Matthew Casperson
 */
public class SearchUITag extends SearchUIBase
{
	private TriStateSelectionState state = TriStateSelectionState.NONE;
	
	public SearchUITag(final String name)
	{
		super(name);
	}

	public TriStateSelectionState getState()
	{
		return state;
	}

	public void setState(TriStateSelectionState state)
	{
		this.state = state;
	}
}
