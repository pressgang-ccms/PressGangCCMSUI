package org.jboss.pressgangccms.client.local.ui.editor.topicview.tags;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.TopicTagsPresenterDriver;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.SimpleEditor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

public class TopicTagViewTagEditor implements Editor<SearchUITag>
{
	/** bound to the SearchUITag itself */
	@Path("")
	final SimpleEditor<SearchUITag> self = SimpleEditor.of();
	/** bound to SearchUITag.getName() */
	final Label name = new Label();
	/** A reference to the collection that was used to build this Editor */
	private final SearchUIProjects searchUIProjects;
	/** A button used to delete this tag */
	private final PushButton delete = UIUtilities.createPushButton(ImageResources.INSTANCE.crossRed32(), ImageResources.INSTANCE.crossRedDown32(), ImageResources.INSTANCE.crossRedHover32(), ImageResources.INSTANCE.crossDisabled32(), CSSConstants.SPACEDBUTTON);

	/** @return a reference to the SearchUITag that was used to bind this Editor */
	public SearchUITag getTag()
	{
		if (self != null)
			return self.getValue();
		return null;
	}

	public PushButton getDelete()
	{
		return delete;
	}

	public TopicTagViewTagEditor(final TopicTagsPresenterDriver driver, final SearchUIProjects searchUIProjects)
	{
		this.searchUIProjects = searchUIProjects;

		name.addStyleName(CSSConstants.TOPICVIEWTAGLABEL);
	}
}
