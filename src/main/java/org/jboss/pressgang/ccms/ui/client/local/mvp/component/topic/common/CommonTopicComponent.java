package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicXMLView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Window;

/**
 * Includes logic common to the views used to edit topics
 * 
 * @author Matthew Casperson
 * 
 */
public class CommonTopicComponent {

    private static final String LINE_BREAK_ESCAPED = "\\n";
    private static final String CARRIAGE_RETURN_AND_LINE_BREAK_ESCAPED = "\\r\\n";
    private static final String LINE_BREAK = "\n";
    private static final String CARRIAGE_RETURN_AND_LINE_BREAK = "\r\n";
    private static final String COMMA = ",";

    /**
     * Private constructor to prevent instantiation.
     */
    private CommonTopicComponent() {

    }

    /**
     * Retrieve a list of locales from the server.
     * @param waitDisplay The view used to notify the user that an ongoin operation is in progress
     * @param loadedCallback The callback to call when the locales are loaded
     */
    public static void populateLocales(final BaseTemplateViewInterface waitDisplay, final StringListLoaded loadedCallback) {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                        /* Get the list of locales from the StringConstant */
                        final List<String> locales = new LinkedList<String>(Arrays.asList(retValue.getValue()
                                .replaceAll(CARRIAGE_RETURN_AND_LINE_BREAK_ESCAPED, "").replaceAll(LINE_BREAK_ESCAPED, "")
                                .replaceAll(" ", "").split(COMMA)));

                        /* Clean the list */
                        while (locales.contains("")) {
                            locales.remove("");
                        }

                        Collections.sort(locales);

                        loadedCallback.stringListLoaded(locales);
                    }
                }) {
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRING_CONSTANT);
    }

    /**
     * Retrieve a list of xml elements from the server.
     * @param waitDisplay The view used to notify the user that an ongoing operation is in progress
     * @param loadedCallback The callback to call when the data is loaded
     */
    public static void populateXMLElements(final BaseTemplateViewInterface waitDisplay, final StringListLoaded loadedCallback) {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {

                        /* Get the list of locales from the StringConstant */
                        final List<String> xmlElements = new LinkedList<String>(Arrays.asList(retValue.getValue()
                                .replaceAll(CARRIAGE_RETURN_AND_LINE_BREAK, LINE_BREAK).replaceAll(" ", "").split(LINE_BREAK)));

                        /* Clean the list */
                        while (xmlElements.contains("")) {
                            xmlElements.remove("");
                        }

                        Collections.sort(xmlElements);

                        loadedCallback.stringListLoaded(xmlElements);

                    }
                }) {
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.DOCBOOK_ELEMENTS_STRING_CONSTANT_ID);
    }

    /**
     * Retrieve a list of xml elements from the server.
     * @param waitDisplay The view used to notify the user that an ongoing operation is in progress
     * @param loadedCallback The callback to call when the data is loaded
     */
    public static void populateXMLTemplates(final BaseTemplateViewInterface waitDisplay, final StringMapLoaded loadedCallback) {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {

                        /* Get the list of template string constant ids from the StringConstant */
                        final Set<String> xmlElements = new HashSet<String>(Arrays.asList(GWTUtilities.fixUpIdSearchString(
                                retValue.getValue()).split(COMMA)));
                        final Map<String, String> data = new TreeMap<String, String>();

                        /* work around the inability to modify an int from an anonymous class */
                        final int[] counter = new int[] { 0 };

                        for (final String id : xmlElements) {
                            try {
                                final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                                        waitDisplay,
                                        new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                                            @Override
                                            public void doSuccessAction(final RESTStringConstantV1 retValue,
                                                    final BaseTemplateViewInterface display) {

                                                ++counter[0];

                                                data.put(retValue.getName(), retValue.getValue());

                                                /*
                                                 * If this was the last call, return the data to the callee.
                                                 */
                                                if (counter[0] == xmlElements.size()) {
                                                    loadedCallback.stringMapLoaded(data);
                                                }
                                            }
                                        }, new BaseRestCallback.FailureAction<BaseTemplateViewInterface>() {

                                            @Override
                                            public void doFailureAction(final BaseTemplateViewInterface display) {
                                                ++counter[0];
                                                if (counter[0] == xmlElements.size()) {
                                                    loadedCallback.stringMapLoaded(data);
                                                }
                                            }
                                        });

                                RESTCalls.getStringConstant(callback, Integer.parseInt(id));
                            } catch (final NumberFormatException ex) {
                                // this should not happen if GWTUtilities.fixUpIdSearchString() does its job properly
                            }
                        }

                    }
                });

        RESTCalls.getStringConstant(callback, ServiceConstants.DOCBOOK_TEMPLATES_STRING_CONSTANT_ID);
    }

    static private boolean isAnyDialogBoxesOpen(final TopicXMLPresenter.Display topicXMLDisplay) {
        if (topicXMLDisplay.getXmlTagsDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().isShowing()) {
            return true;
        }

        return false;
    }

    /**
     * Add listeners for keyboard events
     * 
     * @param topicXMLDisplay The XML editing view
     * @param display The main view
     * @param currentTopicCallback Called to get the currently displayed topic
     */
    public static void addKeyboardShortcutEventHandler(final TopicXMLPresenter.Display topicXMLDisplay,
            final BaseTemplateViewInterface display, final GetCurrentTopic currentTopicCallback) {
        final NativePreviewHandler keyboardShortcutPreviewhandler = new NativePreviewHandler() {
            @Override
            public void onPreviewNativeEvent(final NativePreviewEvent event) {
                final NativeEvent ne = event.getNativeEvent();

                if (ne.getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()) {
                                if (topicXMLDisplay.getXmlTagsDialog().getDialogBox().isShowing()) {
                                    topicXMLDisplay.getXmlTagsDialog().getDialogBox().hide();
                                }

                                if (topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().isShowing()) {
                                    topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().hide();
                                }

                                if (topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().isShowing()) {
                                    topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().hide();
                                }

                                if (topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().isShowing()) {
                                    topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().hide();
                                }
                            }
                        }
                    });
                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'Q') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                topicXMLDisplay.getXmlTagsDialog().getDialogBox().center();
                                topicXMLDisplay.getXmlTagsDialog().getDialogBox().show();
                            }
                        }
                    });
                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'W') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().center();
                                topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().show();
                            }
                        }
                    });
                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'E') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().center();
                                topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().show();
                            }
                        }
                    });

                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'R') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                topicXMLDisplay.getPlainTextXMLDialog().setText(
                                        currentTopicCallback.getCurrentlyEditedTopic().getXml());
                                topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().center();
                                topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().show();
                            }
                        }
                    });
                }

            }
        };

        CommonTopicComponent.populateXMLElements(display, new StringListLoaded() {

            @Override
            public void stringListLoaded(final List<String> xmlElements) {
                topicXMLDisplay.getXmlTagsDialog().setSuggestions(xmlElements);
            }
        });

        populateXMLTemplates(display, new StringMapLoaded() {

            @Override
            public void stringMapLoaded(final Map<String, String> stringMap) {
                topicXMLDisplay.getXmlTemplatesDialog().setSuggestions(stringMap);

            }
        });

        Event.addNativePreviewHandler(keyboardShortcutPreviewhandler);
    }

    /**
     * Add event handlers to the buttons in the various dialog boxes.
     * 
     * @param topicXMLDisplay The XML editing view
     * @param display The main view
     */
    public static void addKeyboardShortcutEvents(final TopicXMLPresenter.Display topicXMLDisplay,
            final BaseTemplateViewInterface display) {
        topicXMLDisplay.getXmlTagsDialog().getOK().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                insertElement(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTagsDialog().getOptions().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(final KeyPressEvent event) {
                int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    int keyCode = event.getNativeEvent().getKeyCode();
                    // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                    // always check the keyCode against a list of "known to be buggy" codes!
                    if (keyCode == KeyCodes.KEY_ENTER) {
                        insertElement(topicXMLDisplay);
                    }
                } else if (charCode == KeyCodes.KEY_ENTER) {
                    insertElement(topicXMLDisplay);
                }
            }
        });

        topicXMLDisplay.getXmlTagsDialog().getCancel().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                hideElementDialogBox(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTagsDialog().getMoreInfo().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                final int selectedItem = topicXMLDisplay.getXmlTagsDialog().getOptions().getSelectedIndex();
                if (selectedItem != -1) {
                    final String value = topicXMLDisplay.getXmlTagsDialog().getOptions().getItemText(selectedItem);
                    Window.open(Constants.DOCBOOK_ELEMENT_URL_PREFIX + value + Constants.DOCBOOK_ELEMENT_URL_POSTFIX, "_blank",
                            "");
                }

            }
        });

        topicXMLDisplay.getCSPTopicDetailsDialog().getIds().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(final KeyPressEvent event) {
                int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    int keyCode = event.getNativeEvent().getKeyCode();
                    // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                    // always check the keyCode against a list of "known to be buggy" codes!
                    if (keyCode == KeyCodes.KEY_ENTER) {
                        insertCspDetails(topicXMLDisplay, display);
                    }
                } else if (charCode == KeyCodes.KEY_ENTER) {
                    insertCspDetails(topicXMLDisplay, display);
                }

            }
        });

        topicXMLDisplay.getCSPTopicDetailsDialog().getOK().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                insertCspDetails(topicXMLDisplay, display);
            }
        });

        topicXMLDisplay.getCSPTopicDetailsDialog().getCancel().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                hideCspDetailsDialogBox(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTemplatesDialog().getOptions().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(final KeyPressEvent event) {
                int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    int keyCode = event.getNativeEvent().getKeyCode();
                    // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                    // always check the keyCode against a list of "known to be buggy" codes!
                    if (keyCode == KeyCodes.KEY_ENTER) {
                        insertTemplate(topicXMLDisplay);
                    }
                } else if (charCode == KeyCodes.KEY_ENTER) {
                    insertTemplate(topicXMLDisplay);
                }

            }
        });

        topicXMLDisplay.getXmlTemplatesDialog().getOK().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                insertTemplate(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTemplatesDialog().getCancel().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                hideTemplateDialogBox(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getPlainTextXMLDialog().getOK().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                copyTextToTopic(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getPlainTextXMLDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                hidePlainTextXMLDialog(topicXMLDisplay);
            }
        });
    }

    private static void hidePlainTextXMLDialog(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private static void copyTextToTopic(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getEditor().setText(topicXMLDisplay.getPlainTextXMLDialog().getText());
        hidePlainTextXMLDialog(topicXMLDisplay);
    }

    private static void hideCspDetailsDialogBox(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private static void hideElementDialogBox(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getXmlTagsDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private static void hideTemplateDialogBox(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private static void insertElement(final TopicXMLPresenter.Display topicXMLDisplay) {
        final int selectedItem = topicXMLDisplay.getXmlTagsDialog().getOptions().getSelectedIndex();
        if (selectedItem != -1) {
            final String value = topicXMLDisplay.getXmlTagsDialog().getOptions().getItemText(selectedItem);
            topicXMLDisplay.getEditor().wrapSelection("<" + value + ">", "</" + value + ">");
        }
        hideElementDialogBox(topicXMLDisplay);
    }

    private static void insertTemplate(final TopicXMLPresenter.Display topicXMLDisplay) {
        final int selectedItem = topicXMLDisplay.getXmlTemplatesDialog().getOptions().getSelectedIndex();
        if (selectedItem != -1) {
            final String value = topicXMLDisplay.getXmlTemplatesDialog().getOptions().getValue(selectedItem);
            topicXMLDisplay.getEditor().insertAtCursor(value);
        }
        hideTemplateDialogBox(topicXMLDisplay);
    }

    private static void insertCspDetails(final TopicXMLPresenter.Display topicXMLDisplay,
            final BaseTemplateViewInterface display) {
        final String ids = GWTUtilities.fixUpIdSearchString(topicXMLDisplay.getCSPTopicDetailsDialog().getIds().getValue());
        if (!ids.isEmpty()) {
            final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new RESTCalls.RESTCallback<RESTTopicCollectionV1>() {
                @Override
                public void begin() {
                    display.addWaitOperation();
                }

                @Override
                public void generalException(final Exception e) {
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    display.removeWaitOperation();
                }

                @Override
                public void success(final RESTTopicCollectionV1 retValue) {
                    try {
                        final StringBuilder details = new StringBuilder();
                        for (final RESTTopicCollectionItemV1 topicCollectionItem : retValue.getItems()) {
                            final RESTTopicV1 topic = topicCollectionItem.getItem();
                            if (!details.toString().isEmpty())
                                details.append("\n");
                            details.append(topic.getTitle() + " [" + topic.getId() + "]");
                        }

                        topicXMLDisplay.getEditor().insertText(details.toString());

                    } finally {
                        display.removeWaitOperation();
                    }
                }

                @Override
                public void failed(final Message message, final Throwable throwable) {
                    display.removeWaitOperation();
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                }
            };

            RESTCalls.getTopicsFromQuery(callback, Constants.QUERY_PATH_SEGMENT_PREFIX
                    + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ids);
            hideCspDetailsDialogBox(topicXMLDisplay);
        }

    }

    public static void setHelpTopicForView(final Component<?> component, final BaseTemplateViewInterface view) {
        /* Set the help link topic ids */
        if (view instanceof TopicXMLErrorsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_VALIDATION_ERRORS_TOPIC);
        } else if (view instanceof TopicPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_PROPERTIES_TOPIC);
        } else if (view instanceof TopicTagsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_TAGS_TOPIC);
        } else if (view instanceof TopicRevisionsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_REVISIONS_TOPIC);
        } else if (view instanceof TopicBugsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_BUGS_TOPIC);
        } else if (view instanceof TopicXMLView) {
            component.setHelpTopicId(ServiceConstants.TOPIC_XML_EDIT_TOPIC);
        }
    }
}
