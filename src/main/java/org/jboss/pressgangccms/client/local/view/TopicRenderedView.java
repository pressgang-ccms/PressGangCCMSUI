package org.jboss.pressgangccms.client.local.view;

import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;

import org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter;
import org.jboss.pressgangccms.client.local.resources.xsl.DocbookToHTML;
import org.jboss.pressgangccms.client.local.view.base.TopicViewBase;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HTML;

public class TopicRenderedView extends TopicViewBase implements TopicRenderedPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicRenderedView";
	
	private final HTML div = new HTML("div");
	
	@Override
	public SimpleBeanEditorDriver getDriver()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public TopicRenderedView()
	{
		addRightAlignedActionButtonPaddingPanel();
	}

	@Override
	public void initialize(final RESTTopicV1 topic)
	{
		try
		{
			// Any number of processors can be created, they will behave
			// independently. Every stylesheet have to have its own processor.
			final XsltProcessor processor = new XsltProcessor();

			// Setting the stylesheet to transform with

			processor.importStyleSheet(DocbookToHTML.XSL);

			// Setting the document to be transformed
			processor.importSource(topic.getXml());

			// Order of setting the stylesheet and document is indifferent.

			// Optional, must be called after importStyleSheet
			// processor.setParameter(paramNameString, paramValueString);

			// Getting the result
			String resultString = processor.transform();
			div.setHTML(resultString);
			
			this.getPanel().setWidget(div);
		}
		catch (final XsltProcessingException ex)
		{
			
		}
	}


}