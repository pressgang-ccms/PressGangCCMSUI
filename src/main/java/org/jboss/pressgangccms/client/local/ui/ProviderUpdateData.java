package org.jboss.pressgangccms.client.local.ui;

import java.util.List;

/**
 * Calling updateRowData on a provider requires knowing the start row and the
 * list of items that should be displayed. This class keeps those pieces of
 * information.
 * 
 * @author Matthew Casperson
 * 
 */
public class ProviderUpdateData<T>
{
	/** References the providers start row */ 
	private Integer startRow;
	/** References the items that the provider will display */
	private List<T> items;
	/** References the currently selected item */
	private T selectedItem;
	/** References the currently displayed item */
	private T displayedItem;

	public T getSelectedItem()
	{
		return selectedItem;
	}

	public void setSelectedItem(T selectedItem)
	{
		this.selectedItem = selectedItem;
	}

	public Integer getStartRow()
	{
		return startRow;
	}

	public void setStartRow(Integer startRow)
	{
		this.startRow = startRow;
	}

	public List<T> getItems()
	{
		return items;
	}

	public void setItems(List<T> items)
	{
		this.items = items;
	}
	
	public ProviderUpdateData()
	{
		
	}
	
	public ProviderUpdateData(final Integer startRow, final List<T> items)
	{
		this.startRow = startRow;
		this.items = items;
	}

	public T getDisplayedItem()
	{
		return displayedItem;
	}

	public void setDisplayedItem(T displayedItem)
	{
		this.displayedItem = displayedItem;
	}
}
