package org.jboss.pressgangccms.client.local.ui;

import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * This is a custom button that can be databound to a
 * SearchUIProject.CategorySummary object. This button is used to display a
 * project selection button.
 * 
 * @author Matthew Casperson
 */
public class TextAndImageButtonSearchUIProjectEditor extends TextAndImageButton implements IsEditor<LeafValueEditor<SearchUIProject.CategorySummary>>
{
	private SearchUIProject.CategorySummary details;

	private String text2;
	private String text3;
	private final Element div2 = DOM.createElement("div");
	private final Element div3 = DOM.createElement("div");

	public TextAndImageButtonSearchUIProjectEditor()
	{
		div2.setAttribute("style", DIV_STYLE);
		DOM.insertChild(getElement(), div2, 1);

		div3.setAttribute("style", DIV_STYLE);
		DOM.insertChild(getElement(), div3, 2);
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
				setText3(PressGangCCMSUI.INSTANCE.Included() + ": " + value.getIncludedTags() + " " + PressGangCCMSUI.INSTANCE.Excluded() + ": " + value.getExcludedTags());
			}

			@Override
			public SearchUIProject.CategorySummary getValue()
			{
				return details;
			}
		};
	}

	public void setText2(final String text)
	{
		this.text2 = text;
		div2.setInnerText(text);
	}

	public String getText2()
	{
		return this.text2;
	}

	public void setText3(final String text)
	{
		this.text3 = text;
		div3.setInnerText(text);
	}

	public String getText3()
	{
		return this.text3;
	}
}
