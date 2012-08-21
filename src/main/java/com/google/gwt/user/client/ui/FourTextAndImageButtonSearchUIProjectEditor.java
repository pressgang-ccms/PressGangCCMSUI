package com.google.gwt.user.client.ui;

import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;

/**
 * This is a custom button that can be databound to a
 * SearchUIProject.CategorySummary object. This button is used to display a
 * project selection button.
 * 
 * @author Matthew Casperson
 */
public class FourTextAndImageButtonSearchUIProjectEditor extends FourTextAndImageButton implements IsEditor<LeafValueEditor<SearchUIProject.CategorySummary>>
{
	private SearchUIProject.CategorySummary details;

	public FourTextAndImageButtonSearchUIProjectEditor()
	{

	}


	@Override
	public LeafValueEditor<SearchUIProject.CategorySummary> asEditor()
	{
		return new LeafValueEditor<SearchUIProject.CategorySummary>()
		{
			@Override
			public void setValue(final SearchUIProject.CategorySummary value)
			{
				details = value;
				
				setText(value.getName());
				setText2(PressGangCCMSUI.INSTANCE.Categories() + ": " + value.getCategoryCount());
				setText3(PressGangCCMSUI.INSTANCE.Included() + ": " + value.getIncludedTags());
				setText4(PressGangCCMSUI.INSTANCE.Excluded() + ": " + value.getExcludedTags());
				
				div3.setAttribute("class", value.getIncludedTags() != 0 ? "CustomButtonTagsInclued" + "" : "CustomButtonText");
				div4.setAttribute("class", value.getExcludedTags() != 0 ? "CustomButtonTagsExcluded" + "" : "CustomButtonText");
			}

			@Override
			public SearchUIProject.CategorySummary getValue()
			{
				return details;
			}
		};
	}

	
}
