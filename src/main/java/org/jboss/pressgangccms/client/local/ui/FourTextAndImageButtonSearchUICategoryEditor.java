package org.jboss.pressgangccms.client.local.ui;

import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
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
public class FourTextAndImageButtonSearchUICategoryEditor extends FourTextAndImageButton implements IsEditor<LeafValueEditor<SearchUICategory.TagSummary>>
{
	private SearchUICategory.TagSummary details;

	public FourTextAndImageButtonSearchUICategoryEditor()
	{
		
	}

	@Override
	public LeafValueEditor<SearchUICategory.TagSummary> asEditor()
	{
		return new LeafValueEditor<SearchUICategory.TagSummary>()
		{
			@Override
			public void setValue(final SearchUICategory.TagSummary value)
			{
				details = value;

				setText(value.getName());
				setText2(PressGangCCMSUI.INSTANCE.Tags() + ": " + value.getTagCount());
				setText3(PressGangCCMSUI.INSTANCE.Included() + ": " + value.getIncludedTags());
				setText4(PressGangCCMSUI.INSTANCE.Excluded() + ": " + value.getExcludedTags());
				
				div3.setAttribute("class", value.getIncludedTags() != 0 ? "CustomButtonTagsInclued" + "" : "CustomButtonText");
				div4.setAttribute("class", value.getExcludedTags() != 0 ? "CustomButtonTagsExcluded" + "" : "CustomButtonText");
					
			}

			@Override
			public SearchUICategory.TagSummary getValue()
			{
				return details;
			}
		};
	}

	
}
