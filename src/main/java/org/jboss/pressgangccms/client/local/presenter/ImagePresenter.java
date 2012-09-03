package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.ServiceConstants;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.editor.image.RESTImageV1Editor;
import org.jboss.pressgangccms.client.local.view.ImageView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTStringConstantV1;

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

	/** The id of the image to display, extracted from the history token */
	private Integer imageId;

	/** used to keep a track of how many rest calls are active */
	int count = 0;
	
	/** A reference to the StringConstants that holds the locales */
	private RESTStringConstantV1 locales;
	
	/** The image to be displayed */
	private RESTImageV1 image;

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

		void initialize(final RESTImageV1 image, final String[] locales);

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

		getLocales();

		getImage();
	}

	private void getLocales()
	{
		final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new RESTCalls.RESTCallback<RESTStringConstantV1>()
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
			public void success(final RESTStringConstantV1 retValue)
			{
				try
				{
					locales = retValue;
					finishLoading();
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

		RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRINGCONSTANT);
	}
	
	/**
	 * Potentially two REST calls have to finish before we can display the page. This function will be called
	 * as each REST call finishes, and when all the information has been gathered, the page will be displayed.
	 */
	private void finishLoading()
	{
		if (locales != null && image != null)
		{
			/* Get the list of locales from the StringConstant */
			final String[] localesArray = locales.getValue().replaceAll("\\n", "").replaceAll(" ", "").split(",");
			
			display.initialize(image, localesArray);
		}
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
					image = retValue;
					finishLoading();
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
		--count;
		if (count == 0)
			display.setSpinnerVisible(false);
	}

	private void startProcessing()
	{
		++count;
		display.setSpinnerVisible(true);
	}

}