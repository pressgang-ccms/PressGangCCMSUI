package org.jboss.pressgangccms.client.local.ui.search;

/**
 * The base class for all the entites that make up the tag search screen.
 * @author matthew
 *
 */
public class SearchUIBase
{
	/** Each entity has a name */
	private final String name;
	/** Each entity has an ID */
	private final String id;
	
	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public SearchUIBase(final String name, final String id)
	{
		this.name = name;
		this.id = id;
	}
	
	@Override
	public boolean equals(final Object other)
	{
		if (!(other instanceof SearchUIBase))
			return false;
		
		final SearchUIBase otherCasted = (SearchUIBase) other;
		
		if (this.name == null && otherCasted.name == null)
			return true;
		
		if (this.name == null || otherCasted.name == null)
			return false;
		
		return (this.name.equals(otherCasted.name));
	}
	
	@Override
	public int hashCode()
	{
		if (this.name == null)
			return 0;
		return name.hashCode();
	}
}
