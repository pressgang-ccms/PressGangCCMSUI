package org.jboss.pressgangccms.client.local.presenter;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.ServiceConstants;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.editor.image.RESTImageV1Editor;
import org.jboss.pressgangccms.client.local.ui.editor.image.RESTLanguageImageV1Editor;
import org.jboss.pressgangccms.client.local.utilities.GWTUtilities;
import org.jboss.pressgangccms.client.local.view.ImageView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTStringConstantV1;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.events.ErrorHandler;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
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
	private String[] locales;

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

		RESTImageV1Editor getEditor();
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
					/* Get the list of locales from the StringConstant */
					locales = retValue.getValue().replaceAll("\\n", "").replaceAll(" ", "").split(",");
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
	 * Potentially two REST calls have to finish before we can display the page.
	 * This function will be called as each REST call finishes, and when all the
	 * information has been gathered, the page will be displayed.
	 */
	private void finishLoading()
	{
		if (locales != null && image != null)
		{
			reInitialise();
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

		display.getRemoveLocale().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				if (Window.confirm(PressGangCCMSUI.INSTANCE.ConfirmDelete()))
				{

					final int selectedTab = display.getEditor().languageImages_OTMEditor().getSelectedIndex();
					if (selectedTab != -1)
					{
						final RESTLanguageImageV1 selectedImage = display.getEditor().languageImages_OTMEditor().itemsEditor().getList().get(selectedTab);

						/*
						 * Create the image to be modified. This is so we don't
						 * send off unnessessary data.
						 */
						final RESTImageV1 updateImage = new RESTImageV1();
						updateImage.setId(image.getId());

						/* Create the language image */
						final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
						languageImage.setRemoveItem(true);
						languageImage.setId(selectedImage.getId());

						/* Add the langauge image */
						updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
						updateImage.getLanguageImages_OTM().addItem(languageImage);

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
									reInitialise();
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

						RESTCalls.saveImage(callback, updateImage);
					}
				}
			}
		});

		display.getAddLocaleDialog().getOk().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				display.getAddLocaleDialog().getDialogBox().hide();

				final String selectedLocale = display.getAddLocaleDialog().getLocales().getItemText(display.getAddLocaleDialog().getLocales().getSelectedIndex());

				/* Don't add locales twice */
				if (image.getLanguageImages_OTM() != null && image.getLanguageImages_OTM().getItems() != null)
					for (final RESTLanguageImageV1 langImage : image.getLanguageImages_OTM().getItems())
						if (langImage.getLocale().equals(selectedLocale))
							return;

				/*
				 * Create the image to be modified. This is so we don't send off
				 * unnessessary data.
				 */
				final RESTImageV1 updateImage = new RESTImageV1();
				updateImage.setId(image.getId());

				/* Create the language image */
				final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
				languageImage.setAddItem(true);
				languageImage.explicitSetLocale(selectedLocale);

				/* Add the langauge image */
				updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
				updateImage.getLanguageImages_OTM().addItem(languageImage);

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
							reInitialise();
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

				RESTCalls.saveImage(callback, updateImage);
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

	/**
	 * Each Language Image has an upload button that needs to be bound to some
	 * behaviour.
	 */
	private void bindImageUploadButtons()
	{
		if (display.getEditor() == null)
			throw new IllegalStateException("display.getEditor() cannot be null");

		for (final RESTLanguageImageV1Editor editor : display.getEditor().languageImages_OTMEditor().itemsEditor().getEditors())
		{
			editor.getUploadButton().addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(final ClickEvent event)
				{
					for (final File file : editor.getUpload().getFiles())
					{
						startProcessing();

						final FileReader reader = new FileReader();

						reader.addErrorHandler(new ErrorHandler()
						{
							@Override
							public void onError(final org.vectomatic.file.events.ErrorEvent event)
							{
								stopProcessing();
							}
						});

						reader.addLoadEndHandler(new LoadEndHandler()
						{
							@Override
							public void onLoadEnd(final LoadEndEvent event)
							{
								try
								{
									final String result = reader.getStringResult();
									final byte[] buffer = GWTUtilities.getByteArray(result, 1);

									/*
									 * Create the image to be modified. This is
									 * so we don't send off unnessessary data.
									 */
									final RESTImageV1 updateImage = new RESTImageV1();
									updateImage.setId(image.getId());

									/* Create the language image */
									final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
									languageImage.setAddItem(true);
									languageImage.explicitSetLocale(editor.self.getLocale());
									languageImage.explicitSetImageData(buffer);
									languageImage.explicitSetFilename(file.getName());

									/* Delete the old language image */
									final RESTLanguageImageV1 deleteLanguageImage = new RESTLanguageImageV1();
									deleteLanguageImage.setRemoveItem(true);
									deleteLanguageImage.setId(editor.self.getId());

									/* Add the langauge image */
									updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
									updateImage.getLanguageImages_OTM().addItem(languageImage);
									updateImage.getLanguageImages_OTM().addItem(deleteLanguageImage);

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
												reInitialise();
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
											Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
										}
									};

									RESTCalls.saveImage(callback, updateImage);
								}
								finally
								{
									stopProcessing();
								}
							}
						});

						reader.readAsBinaryString(file);
					}
				}
			});
		}

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
		/*
		 * Create a call back that resets the exception handler on any normal
		 * error or exception
		 */
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
				Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
			}
		};

		if (imageId != null)
			RESTCalls.getImage(callback, imageId);
	}

	private void reInitialise()
	{
		final List<String> newLocales = Arrays.asList(locales);

		/* Make it so you can't add a locale if it already exists */
		if (image.getLanguageImages_OTM() != null && image.getLanguageImages_OTM().getItems() != null)
			for (final RESTLanguageImageV1 langImage : image.getLanguageImages_OTM().getItems())
				newLocales.remove(langImage.getLocale());

		display.initialize(image, newLocales.toArray(new String[0]));

		bindImageUploadButtons();
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