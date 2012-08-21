package org.jboss.pressgangccms.client.local.ui.search;

import com.google.gwt.user.client.ui.TriStateSelectionState;

/**
 * This class represents a single tag under a parent category.
 * @author Matthew Casperson
 */
public class SearchUITag extends SearchUIBase
{
	private TriStateSelectionState state = TriStateSelectionState.NONE;
	private Integer id;
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public SearchUITag(final String name, final Integer id)
	{
		super(name);
		this.id = id;
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
