package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.ImagePresenter;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.editor.image.RESTImageV1Editor;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;

import com.google.gwt.core.client.GWT;

public class ImageView extends BaseTemplateView implements ImagePresenter.Display
{
	public static final String HISTORY_TOKEN = "ImageView";
	
	private RESTImageV1Editor editor;
	private ImagePresenterDriver driver = GWT.create(ImagePresenterDriver.class);
	
	public ImageView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images());
	}
	
	public void initialize(final RESTImageV1 image)
	{
		/* SearchUIProjectsEditor is a grid */
		editor = new RESTImageV1Editor();	/* Initialize the driver with the top-level editor */
		driver.initialize(editor);
		/* Copy the data in the object into the UI */
		driver.edit(image);
		/* Add the projects */
		this.getPanel().setWidget(editor);
	}
}
