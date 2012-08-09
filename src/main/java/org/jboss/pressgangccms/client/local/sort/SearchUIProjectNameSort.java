package org.jboss.pressgangccms.client.local.sort;

import java.util.Comparator;

import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;

public class SearchUIProjectNameSort implements Comparator<SearchUIProject>
{

	@Override
	public int compare(final SearchUIProject arg0, final SearchUIProject arg1)
	{
		if (arg0 == null && arg1 == null)
			return 0;
		
		if (arg0 == arg1)
			return 0;
		
		if (arg0 == null)
			return -1;
		
		if (arg1 == null)
			return 1;
		
		if (arg0.getName() == null && arg1.getName() == null)
			return 0;
		
		if (arg0.getName() == null)
			return -1;
		
		if (arg1.getName() == null)
			return 1;
		
		return arg0.getName().compareTo(arg1.getName());
	}

}
