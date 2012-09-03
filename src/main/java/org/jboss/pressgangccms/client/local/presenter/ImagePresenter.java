package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.editor.image.RESTImageV1Editor;
import org.jboss.pressgangccms.client.local.view.ImageView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;

@Dependent
public class ImagePresenter extends TemplatePresenter
{
	@Inject
	private Display display;

	private Integer imageId;

	public interface Display extends BaseTemplateViewInterface
	{
		// Empty interface declaration, similar to UiBinder
		public interface ImagePresenterDriver extends SimpleBeanEditorDriver<RESTImageV1, RESTImageV1Editor>
		{
		}

		public interface AddLocaleInterface
		{
			PushButton getCancel();

			PushButton getOk();

			ListBox getLocales();
			
			DialogBox getDialogBox();
		}

		void initialize(final RESTImageV1 image);

		PushButton getRemoveLocale();

		PushButton getAddLocale();

		AddLocaleInterface getAddLocaleDialog();
	}

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());
		
		bind();

		getImage();
	}

	private void bind()
	{
		display.getAddLocale().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				display.getAddLocaleDialog().getDialogBox().center();
				display.getAddLocaleDialog().getDialogBox().show();
			}
		});
		
		display.getAddLocaleDialog().getOk().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				display.getAddLocaleDialog().getDialogBox().hide();
			}
		});
		
		display.getAddLocaleDialog().getCancel().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				display.getAddLocaleDialog().getDialogBox().hide();
			}
		});
	}

	@Override
	public void parseToken(final String historyToken)
	{
		try
		{
			imageId = Integer.parseInt(historyToken.replace(ImageView.HISTORY_TOKEN + ";", ""));
		}
		catch (final Exception ex)
		{
			// bad history token. silently fail
			imageId = null;
		}
	}

	private void getImage()
	{
		final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>()
		{
			@Override
			public void begin()
			{
				startProcessing();
			}

			@Override
			public void generalException(final Exception ex)
			{
				stopProcessing();
			}

			@Override
			public void success(final RESTImageV1 retValue)
			{
				try
				{
					display.initialize(retValue);
				}
				finally
				{
					stopProcessing();
				}
			}

			@Override
			public void failed()
			{
				stopProcessing();
			}
		};

		try
		{
			if (imageId != null)
				RESTCalls.getImage(callback, imageId);
		}
		catch (final NumberFormatException ex)
		{
			stopProcessing();
		}
	}

	private void stopProcessing()
	{
		display.setSpinnerVisible(false);
	}

	private void startProcessing()
	{
		display.setSpinnerVisible(true);
	}

}