package org.jboss.pressgangccms.client.local.ui.search;

public class SearchUIBase
{
	private final String name;
	
	public String getName()
	{
		return name;
	}

	public SearchUIBase(final String name)
	{
		this.name = name;
	}
	
	@Override
	public boolean equals(final Object other)
	{
		if (!(other instanceof SearchUIBase))
			return false;
		
		final SearchUIBase otherCasted = (SearchUIBase)other;
		
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
