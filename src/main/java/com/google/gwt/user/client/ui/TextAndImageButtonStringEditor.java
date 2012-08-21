package com.google.gwt.user.client.ui;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;

public class TextAndImageButtonStringEditor extends TextAndImageButton implements IsEditor<LeafValueEditor<String>>
{
	@Override
	public LeafValueEditor<String> asEditor()
	{
		return new LeafValueEditor<String>()
		{
			@Override
			public void setValue(final String value)
			{
				setText(value);
			}

			@Override
			public String getValue()
			{
				return getText();
			}
		};
	}
}
