// Copyright (c) 2011-2012, David H. Hovemeyer <david.hovemeyer@gmail.com>
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package edu.ycp.cs.dh.acegwt.client.ace;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RequiresResize;

/**
 * A GWT widget for the Ajax.org Code Editor (ACE).
 * 
 * Matthew Casperson - This class has been edited to remove the startEditor
 * function. The ACE editor is created and destroyed using the attach events,
 * values for things like text, mode, theme etc are cached until the ACE editor
 * is available.
 * 
 * The IsEditor interface has been implemented, to allow the ACE editor to bind to
 * POJOs with the Editor framework. And references to "require" have been removed.
 * 
 * @see <a href="http://ace.ajax.org/">Ajax.org Code Editor</a>
 */
public class AceEditor extends Composite implements RequiresResize, IsEditor<LeafValueEditor<String>>
{
	// Used to generate unique element ids for Ace widgets.
	private static int nextId = 0;

	private final String elementId;

	private JavaScriptObject editor;

	private JsArray<AceAnnotation> annotations = JavaScriptObject.createArray().cast();

	/**
	 * This value is used as a buffer to hold the text before the editor is
	 * created
	 */
	private String text = null;
	/**
	 * This value is used as a buffer to hold the theme before the editor is
	 * created
	 */
	private AceEditorTheme theme = null;
	/**
	 * This value is used as a buffer to hold the mode before the editor is
	 * created
	 */
	private AceEditorMode mode = null;
	/**
	 * This value is used as a buffer to hold the readonly state before the
	 * editor is created
	 */
	private boolean readOnly = false;
	/**
	 * This value is used as a buffer to hold the "use soft tabs" state before the
	 * editor is created
	 */
	private boolean useSoftTabs = false;
	/**
	 * This value is used as a buffer to hold the "tab size" state before the
	 * editor is created
	 */
	private int tabSize = 2;

	/**
	 * This constructor will only work if the <code>.ace_editor</code> CSS class
	 * is set with <code>position: relative !important;</code>. A better idea is
	 * to use the {@link AceEditor#AceEditor(boolean)} constructor and pass it
	 * the value <code>true</code>; this will work without any changes to the
	 * <code>.ace_editor</code> class.
	 */
	@Deprecated
	public AceEditor()
	{
		this(false);
	}

	/**
	 * Preferred constructor. You should pass <code>true</code> to this
	 * constructor, unless you did something special to redefine the
	 * <code>.ace_editor</code> CSS class.
	 * 
	 * @param positionAbsolute
	 *            true if the <code>.ace_editor</code> CSS class is set with
	 *            <code>position: absolute;</code>, which is the default; false
	 *            if <code>.ace_editor</code> is set to use
	 *            <code>position: relative;</code>
	 */
	public AceEditor(final boolean positionAbsolute)
	{
		elementId = "_aceGWT" + nextId;
		nextId++;

		HTML html;

		if (!positionAbsolute)
		{
			// Create a single div with width/height 100% with the generated
			// element id. The ACE editor will replace this div.
			// Note that the .ace_editor style must be set with
			// "position: relative !important;" for this this to work.
			html = new HTML("<div style=\"width: 100%; height: 100%;\" id=\"" + elementId + "\"></div>");
		}
		else
		{
			// Create a div with "position: relative;" that will expand to fill
			// its parent.
			// Then nest a div with the generated element id inside it.
			// The ACE editor will replace the inner div. Because ACE defaults
			// to absolute positioning, we can set left/right/top/bottom to 0,
			// causing ACE to completely expand to fill the outer div.
			html = new HTML("<div style=\"width: 100%; height: 100%; position: relative;\">" + "<div style=\"top: 0px; bottom: 0px; left: 0px; right: 0px;\" id=\"" + elementId + "\"></div>" + "</div>");
		}

		initWidget(html);

		/*
		 * Create the ACE widget when this widget is attached to something.
		 * Destroy it when it is detached.
		 */
		this.addAttachHandler(new Handler()
		{
			@Override
			public void onAttachOrDetach(final AttachEvent event)
			{
				if (AceEditor.this.isAttached())
					startEditorNative(text, theme != null ? theme.getName() : null, mode != null ? mode.getName(): null, readOnly, useSoftTabs, tabSize);
				else
					destroy();
			}
		});
	}

	/**
	 * Call this method to start the editor. Make sure that the widget has been
	 * attached to the DOM tree before calling this method.
	 * 
	 * @param text
	 *            The initial text to be placed into the editor
	 */
	private native void startEditorNative(final String text, final String themeName, final String shortModeName, final boolean readOnly, final boolean useSoftTabs, final int tabSize) /*-{
		if ($wnd.ace == undefined) {
			$wnd
					.alert("window.ace is undefined! Please make sure you have included the appropriate JavaScript files.");
			return;
		}

		var editor = $wnd.ace
				.edit(this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::elementId);
		editor.getSession().setUseWorker(false);
		this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor = editor;

		// I have been noticing sporadic failures of the editor
		// to display properly and receive key/mouse events.
		// Try to force the editor to resize and display itself fully.  See:
		//    https://groups.google.com/group/ace-discuss/browse_thread/thread/237262b521dcea33
		editor.resize();
		this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::redisplay();

		// Set test 
		if (text != null)
			editor.getSession().setValue(text);

		// Set theme
		if (themeName != null)
			editor.setTheme("ace/theme/" + themeName);

		// Set mode
		if (shortModeName != null)
			editor.getSession().setMode("ace/mode/" + shortModeName);

		// Set read only
		editor.setReadOnly(readOnly);
		
		// Set soft tabs
		editor.getSession().setUseSoftTabs(useSoftTabs);
		
		// Set the tab size
		editor.getSession().setTabSize(tabSize);
	}-*/;

	/**
	 * Call this to force the editor contents to be redisplayed. There seems to
	 * be a problem when an AceEditor is embedded in a LayoutPanel: the editor
	 * contents don't appear, and it refuses to accept focus and mouse events,
	 * until the browser window is resized. Calling this method works around the
	 * problem by forcing the underlying editor to redisplay itself fully. (?)
	 */
	public native void redisplay() /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;

		if (editor != null) {
			editor.renderer.onResize(true);
			editor.renderer.updateFull();
			editor.resize();
			editor.focus();
		} else {
			console
					.log("editor == null. redisplay() was not called successfully.");
		}
	}-*/;

	/**
	 * Cleans up the entire editor.
	 */
	public native void destroy() /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null) {
			editor.destroy();
		} else {
			console
					.log("editor == null. destory() was not called successfully.");
		}
	}-*/;

	/**
	 * Set the theme.
	 * 
	 * @param theme
	 *            the theme (one of the values in the {@link AceEditorTheme}
	 *            enumeration)
	 */
	public void setTheme(final AceEditorTheme theme)
	{
		this.theme = theme;
		setThemeByName(theme.getName());
	}

	/**
	 * Set the theme by name.
	 * 
	 * @param themeName
	 *            the theme name (e.g., "twilight")
	 */
	public native void setThemeByName(String themeName) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null)
			editor.setTheme("ace/theme/" + themeName);
	}-*/;

	/**
	 * Set the mode.
	 * 
	 * @param mode
	 *            the mode (one of the values in the {@link AceEditorMode}
	 *            enumeration)
	 */
	public void setMode(final AceEditorMode mode)
	{
		this.mode = mode;
		setModeByName(mode.getName());
	}

	/**
	 * Set the mode by name.
	 * 
	 * @param shortModeName
	 *            name of mode (e.g., "eclipse")
	 */
	public native void setModeByName(String shortModeName) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null) {
			var modeName = "ace/mode/" + shortModeName;
			editor.getSession().setMode(modeName);
		}
	}-*/;

	/**
	 * Register a handler for change events generated by the editor.
	 * 
	 * @param callback
	 *            the change event handler
	 */
	public native void addOnChangeHandler(AceEditorCallback callback) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		editor
				.getSession()
				.on(
						"change",
						function(e) {
							callback.@edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback::invokeAceCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
						});
	}-*/;

	/**
	 * Register a handler for cursor position change events generated by the
	 * editor.
	 * 
	 * @param callback
	 *            the cursor position change event handler
	 */
	public native void addOnCursorPositionChangeHandler(AceEditorCallback callback) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		editor.getSession().selection
				.on(
						"changeCursor",
						function(e) {
							callback.@edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback::invokeAceCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
						});
	}-*/;

	/**
	 * Set font size.
	 */
	public native void setFontSize(String fontSize) /*-{
		var elementId = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::elementId;
		var elt = $doc.getElementById(elementId);
		elt.style.fontSize = fontSize;
	}-*/;

	/**
	 * Get the complete text in the editor as a String.
	 * 
	 * @return the text in the editor
	 */
	private native String getTextNative() /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null)
			return editor.getSession().getValue();
		return null;
	}-*/;

	public String getText()
	{
		final String thisText = getTextNative();
		return thisText == null ? this.text : thisText;
	};

	public void setText(String text)
	{
		this.text = text;
		setTextNative(text);
	};

	/**
	 * Set the complete text in the editor from a String.
	 * 
	 * @param text
	 *            the text to set in the editor
	 */
	private native void setTextNative(String text) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null) {
			editor.getSession().setValue(text);
		} else {
			console
					.log("editor == null. setTextNative() was not called successfully.");
		}
	}-*/;

	/**
	 * Insert given text at the cursor.
	 * 
	 * @param text
	 *            text to insert at the cursor
	 */
	public native void insertAtCursor(String text) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null) {
			editor.insert(text);
		} else {
			console
					.log("editor == null. insertAtCursor() was not called successfully.");
		}
	}-*/;

	/**
	 * Get the current cursor position.
	 * 
	 * @return the current cursor position
	 */
	public native AceEditorCursorPosition getCursorPosition() /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null) {
			var pos = editor.getCursorPosition();
			return this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getCursorPositionImpl(DD)(pos.row, pos.column);
		} else {
			console
					.log("editor == null. getCursorPosition() was not called successfully.");
			return 0;
		}
	}-*/;

	private AceEditorCursorPosition getCursorPositionImpl(final double row, final double column)
	{
		return new AceEditorCursorPosition((int) row, (int) column);
	}

	/**
	 * Set whether or not soft tabs should be used.
	 * 
	 * @param useSoftTabs
	 *            true if soft tabs should be used, false otherwise
	 */
	private native void setUseSoftTabsNative(boolean useSoftTabs) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null) {
			editor.getSession().setUseSoftTabs(useSoftTabs);
		} else {
			console
					.log("editor == null. setUseSoftTabsNative() was not called successfully.");
		}
	}-*/;
	
	public void setUseSoftTabs(boolean useSoftTabs) 
	{
		this.useSoftTabs = useSoftTabs;
		setUseSoftTabsNative(useSoftTabs);
	};

	
	/**
	 * Set tab size. (Default is 4.)
	 * 
	 * @param tabSize
	 *            the tab size to set
	 */
	private native void setTabSizeNative(int tabSize) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null)
		{
			editor.getSession().setTabSize(tabSize);
		} else {
			console
					.log("editor == null. setTabSizeNative() was not called successfully.");
		}
	}-*/;

	public void setTabSize(int tabSize)
	{
		this.tabSize = tabSize;
		setTabSizeNative(tabSize);
	}
	
	/**
	 * Go to given line.
	 * 
	 * @param line
	 *            the line to go to
	 */
	public native void gotoLine(int line) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null)
		{
			editor.gotoLine(line);
		}
		 else {
			console
					.log("editor == null. gotoLine() was not called successfully.");
		}
	}-*/;

	/**
	 * Set whether or not the horizontal scrollbar is always visible.
	 * 
	 * @param hScrollBarAlwaysVisible
	 *            true if the horizontal scrollbar is always visible, false if
	 *            it is hidden when not needed
	 */
	public native void setHScrollBarAlwaysVisible(boolean hScrollBarAlwaysVisible) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		editor.renderer.setHScrollBarAlwaysVisible(hScrollBarAlwaysVisible);
	}-*/;

	/**
	 * Set whether or not the gutter is shown.
	 * 
	 * @param showGutter
	 *            true if the gutter should be shown, false if it should be
	 *            hidden
	 */
	public native void setShowGutter(boolean showGutter) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		editor.renderer.setShowGutter(showGutter);
	}-*/;

	/**
	 * Set or unset read-only mode.
	 * 
	 * @param readOnly
	 *            true if editor should be set to readonly, false if the editor
	 *            should be set to read-write
	 */
	private native void setReadOnlyNative(boolean readOnly) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		if (editor != null)
			editor.setReadOnly(readOnly);
	}-*/;

	public void setReadOnly(final boolean readOnly)
	{
		this.readOnly = readOnly;
		setReadOnlyNative(readOnly);
	}

	/**
	 * Set or unset highlighting of currently selected word.
	 * 
	 * @param highlightSelectedWord
	 *            true to highlight currently selected word, false otherwise
	 */
	public native void setHighlightSelectedWord(boolean highlightSelectedWord) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		editor.setHighlightSelectedWord(highlightSelectedWord);
	}-*/;

	/**
	 * Set or unset the visibility of the print margin.
	 * 
	 * @param showPrintMargin
	 *            true if the print margin should be shown, false otherwise
	 */
	public native void setShowPrintMargin(boolean showPrintMargin) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		editor.renderer.setShowPrintMargin(showPrintMargin);
	}-*/;

	/**
	 * Add an annotation to a the local <code>annotations</code>
	 * JsArray<AceAnnotation>, but does not set it on the editor
	 * 
	 * @param row
	 *            to which the annotation should be added
	 * @param column
	 *            to which the annotation applies
	 * @param text
	 *            to display as a tooltip with the annotation
	 * @param type
	 *            to be displayed (one of the values in the
	 *            {@link AceAnnotationType} enumeration)
	 */
	public void addAnnotation(final int row, final int column, final String text, final AceAnnotationType type)
	{
		annotations.push(AceAnnotation.create(row, column, text, type.getName()));
	}

	/**
	 * Set any annotations which have been added via <code>addAnnotation</code>
	 * on the editor
	 */
	public native void setAnnotations() /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		var annotations = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::annotations;
		editor.getSession().setAnnotations(annotations);
	}-*/;

	/**
	 * Clear any annotations from the editor and reset the local
	 * <code>annotations</code> JsArray<AceAnnotation>
	 */
	public native void clearAnnotations() /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		editor.getSession().clearAnnotations();
		this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::resetAnnotations();
	}-*/;

	/**
	 * Reset any annotations in the local <code>annotations</code>
	 * JsArray<AceAnnotation>
	 */
	private void resetAnnotations()
	{
		annotations = JavaScriptObject.createArray().cast();
	}

	/**
	 * Remove a command from the editor.
	 * 
	 * @param command
	 *            the command (one of the values in the {@link AceCommand}
	 *            enumeration)
	 */
	public void removeCommand(final AceCommand command)
	{
		removeCommandByName(command.getName());
	}

	/**
	 * Remove commands, that may not me required, from the editor
	 * 
	 * @param command
	 *            to be removed, one of "gotoline", "findnext", "findprevious",
	 *            "find", "replace", "replaceall"
	 */
	public native void removeCommandByName(String command) /*-{
		var editor = this.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::editor;
		editor.commands.removeCommand(command);
	}-*/;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ResizeComposite#onResize()
	 */
	@Override
	public void onResize()
	{
		redisplay();
	}

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