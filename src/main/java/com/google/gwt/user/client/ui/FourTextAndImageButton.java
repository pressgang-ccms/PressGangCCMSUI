package com.google.gwt.user.client.ui;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class FourTextAndImageButton extends TextAndImageButton
{
	private String text2;
	private String text3;
	private String text4;
	protected final Element div2 = DOM.createElement("div");
	protected final Element div3 = DOM.createElement("div");
	protected final Element div4 = DOM.createElement("div");
	
	public FourTextAndImageButton()
	{
		super(CSSConstants.CUSTOMBUTTONTEXTBOLD);
		
		div2.setAttribute("class", CSSConstants.CUSTOMBUTTONTEXT);
		DOM.insertChild(getElement(), div2, 1);

		div3.setAttribute("class", CSSConstants.CUSTOMBUTTONTEXT);
		DOM.insertChild(getElement(), div3, 2);
		
		div4.setAttribute("class", CSSConstants.CUSTOMBUTTONTEXT);
		DOM.insertChild(getElement(), div4, 3);
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

	public String getText4()
	{
		return text4;
	}

	public void setText4(final String text4)
	{
		this.text4 = text4;
		div4.setInnerText(text4);
	}
}
