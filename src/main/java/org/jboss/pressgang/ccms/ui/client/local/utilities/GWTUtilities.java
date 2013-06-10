package org.jboss.pressgang.ccms.ui.client.local.utilities;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * GWT has some limitations, like not being able to bind an Editor to an array
 * (http://code.google.com/p/google-web-toolkit/issues/detail?id=6600) and not supporting conversion from a byte array to a
 * String.
 * <p/>
 * This class provides some GWT friendly methods to work around these issues, especially for the RESTLanguageImageV1 class.
 *
 * @author Matthew Casperson
 */
final public class GWTUtilities {
    private static final int BITS_PER_BYTE = 8;
    private static final int BYTE_MASK = 0x000000FF;
    private static final String DOUBLE_COMMA = ",,";
    private static final String COMMA = ",";

    private GWTUtilities() {
    }

    /**
     * Sets the browser's page title
     * @param newTitle The page title
     */
    public static void setBrowserWindowTitle (@NotNull final String newTitle) {
        if (Document.get() != null) {
            Document.get().setTitle (newTitle);
        }
    }

    /**
     * Open a new window with the results of a prettydiff comparison
     *
     * @param source      The source XML
     * @param sourceLabel The source XML label
     * @param diff        The diff XML
     * @param diffLabel   The diff XML label
     */
    native public static void displayDiff(final String source, final String sourceLabel, final String diff, final String diffLabel, final boolean isXML)
    /*-{
        var diffTable = $wnd.prettydiff({
            source: source,
            sourcelabel: sourceLabel,
            diff: diff,
            difflabel: diffLabel,
            lang: isXML ? "markup" : "text",
            mode: "diff",
            diffview: "sidebyside"
        })[0];

        var win = $wnd.open("", "_blank", "width=" + (screen.width - 200) + ", height=" + (screen.height - 200) + ",scrollbars=yes"); // a window object
        if (win != null) {
            win.document.open("text/html", "replace");
            win.document
                .write("<HTML><HEAD><TITLE>PressGangCCMS XML Diff</TITLE><link rel=\"stylesheet\" type=\"text/css\" href=\"../prettydiff.css\"><link rel=\"stylesheet\" type=\"text/css\" href=\"prettydiff.css\"></HEAD><BODY>"
                    + diffTable + "</BODY></HTML>");
            win.document.close();
        }
    }-*/;

    /**
     * Writes out a stack trace into a string.
     *
     * @param ex The exception to process
     * @return A string version of the stack trace
     */
    @NotNull
    public static String convertExceptionStackToString(@NotNull final Throwable ex) {
        @NotNull final StringBuilder sb = new StringBuilder();
        for (@NotNull final StackTraceElement element : ex.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * This does not work. See http://stackoverflow.com/questions/13406964/detect-ctrl-click-on-pushbutton
     *
     * @param event the event to test
     * @return true if the CTRL key is down
     */
    public static boolean isEventToOpenNewWindow(@NotNull final ClickEvent event) {
        return event.isControlKeyDown();
    }

    public static boolean enterKeyWasPressed(@NotNull final KeyPressEvent event) {
        final int charCode = event.getUnicodeCharCode();
        if (charCode == 0) {
            // it's probably Firefox
            final int keyCode = event.getNativeEvent().getKeyCode();
            // beware! keyCode=40 means "down arrow", while charCode=40 means '('
            // always check the keyCode against a list of "known to be buggy" codes!
            if (keyCode == KeyCodes.KEY_ENTER) {
                return true;
            }
        } else if (charCode == KeyCodes.KEY_ENTER) {
            return true;
        }

        return false;
    }

    /**
     * Creates a copy of the given date
     *
     * @param input The date to copy
     * @return null if input is null, and a copy of the date otherwise
     */
    @Nullable
    public static Date createDateCopy(@Nullable final Date input) {
        if (input == null) {
            return null;
        }
        return new Date(input.getTime());
    }

    /**
     * Test to see if a String is null or contains only whitespace.
     *
     * @param input The String to test
     * @return true if input is null or contains only whitespace, and false otherwise
     */
    public static boolean isStringNullOrEmpty(@Nullable final String input) {
        if (input == null || input.trim().isEmpty()) {
            return true;
        }
        return false;

    }

    /**
     * Takes a string and returns only integers separated by single commas. Useful for cleaning up a string used to search for a
     * list of IDs.
     *
     * @param input The string to be cleaned
     * @return The cleaned string
     */
    @NotNull
    public static String fixUpIdSearchString(@NotNull final String input) {
        final RegExp regex = RegExp.compile("[^0-9,-]", "g");

        String retValue = regex.replace(input, "");

        /* Cannot have sequential commas */
        while (retValue.contains(DOUBLE_COMMA)) {
            retValue = retValue.replaceAll(DOUBLE_COMMA, COMMA);
        }

        /* Cannot start with a comma */
        while (retValue.endsWith(COMMA)) {
            retValue = retValue.substring(0, retValue.length() - 1);
        }

        /* Cannot end with a comma */
        while (retValue.startsWith(COMMA)) {
            retValue = retValue.substring(1, retValue.length());
        }

        return retValue.trim();
    }

    /**
     * A GWT friendly way to turn a String into a byte[].
     *
     * @param string The source String
     * @return the string converted into a byte[]
     */
    @NotNull
    public static byte[] getBytesUTF8(@NotNull final String string) {
        return getBytes(string, 1);
    }

    /**
     * A GWT friendly way to turn a String into a byte[].
     *
     * @param string       The source String
     * @param bytesPerChar The number of bytes per character
     * @return the string converted into a byte[]
     */
    @NotNull
    public static byte[] getBytes(@NotNull final String string, final int bytesPerChar) {
        if (bytesPerChar < 1) {
            throw new IllegalArgumentException("bytesPerChar must be greater than 1");
        }

        final char[] chars = string.toCharArray();
        @NotNull final byte[] toReturn = new byte[chars.length * bytesPerChar];
        for (int i = 0; i < chars.length; ++i) {
            for (int j = 0; j < bytesPerChar; ++j) {
                toReturn[i * bytesPerChar + j] = (byte) (chars[i] >>> (BITS_PER_BYTE * (bytesPerChar - 1 - j)));
            }
        }
        return toReturn;
    }

    /**
     * A GWT friendly way to turn a String into a byte[].
     *
     * @param bytes The source byte[]
     * @return the string converted into a byte[]
     */
    @NotNull
    public static String getStringUTF8(@NotNull final byte[] bytes) {
        return getString(bytes, 1);
    }

    /**
     * A GWT friendly way to turn a byte[] into a String.
     *
     * @param bytes        The source byte[]
     * @param bytesPerChar The number of bytes per character
     * @return the string converted from a byte[]
     */
    @NotNull
    public static String getString(@NotNull final byte[] bytes, final int bytesPerChar) {
        if (bytesPerChar < 1) {
            throw new IllegalArgumentException("bytesPerChar must be greater than 1");
        }

        final int length = bytes.length / bytesPerChar;
        @NotNull final StringBuilder retValue = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            char thisChar = 0;

            for (int j = 0; j < bytesPerChar; ++j) {
                final int shift = (bytesPerChar - 1 - j) * BITS_PER_BYTE;
                thisChar |= (BYTE_MASK << shift) & (bytes[i * bytesPerChar + j] << shift);
            }

            retValue.append(thisChar);
        }

        return retValue.toString();
    }

    /**
     * Replacement for String.toByteArray().
     *
     * @param string       The string to convert
     * @param bytesPerChar The number of bytes per character
     * @return the same as the standard Java String.toByteArray() method
     */
    @NotNull
    public static byte[] getByteArray(@NotNull final String string, final int bytesPerChar) {
        final char[] chars = string.toCharArray();
        @NotNull final byte[] toReturn = new byte[chars.length * bytesPerChar];
        for (int i = 0; i < chars.length; ++i) {
            for (int j = 0; j < bytesPerChar; ++j) {
                toReturn[i * bytesPerChar + j] = (byte) (chars[i] >>> (BITS_PER_BYTE * (bytesPerChar - 1 - j)));
            }
        }
        return toReturn;
    }

    /**
     * Compares two strings.
     *
     * @param a The first string
     * @param b The second string
     * @return true if both strings are null, or if both strings are equal
     */
    public static boolean compareStrings(@Nullable final String a, @Nullable final String b) {
        if (a == null && b == null) {
            return true;
        }

        if (a != null) {
            return a.equals(b);
        }

        return b.equals(a);
    }

    /**
     * Compares two strings for equality, considering null to be equal.
     *
     * @param a The first string
     * @param b The second string
     * @return true if both strings are either null or empty string, or if both strings are equal
     */
    public static boolean integerEquals(@Nullable final Integer a, @Nullable final Integer b) {
        if (a == null && b == null) {
            return true;
        }

        if (a != null) {
            return a.equals(b);
        }

        return b.equals(a);
    }

    /**
     * Compares two strings for equality, considering null and empty string to be equal.
     *
     * @param a The first string
     * @param b The second string
     * @return true if both strings are either null or empty string, or if both strings are equal
     */
    public static boolean stringEqualsEquatingNullWithEmptyString(@Nullable final String a, @Nullable final String b) {
        if ((a == null || a.isEmpty()) && (b == null || b.isEmpty())) {
            return true;
        }

        if (a != null) {
            return a.equals(b);
        }

        return b.equals(a);
    }

    /**
     * Compares two strings for equality, considering null and empty string to be equal. Different line breaks (\r\n or \n)
     * are also ignored.
     *
     * @param a The first string
     * @param b The second string
     * @return true if both strings are either null or empty string, or if both strings are equal
     */
    public static boolean stringEqualsEquatingNullWithEmptyStringAndIgnoreLineBreaks(@Nullable final String a, @Nullable final String b) {
        if ((a == null || a.isEmpty()) && (b == null || b.isEmpty())) {
            return true;
        }

        if (a != null && b != null) {
            final String fixedA = a.replaceAll(Constants.CARRIAGE_RETURN_AND_LINE_BREAK, Constants.LINE_BREAK);
            final String fixedB = b.replaceAll(Constants.CARRIAGE_RETURN_AND_LINE_BREAK, Constants.LINE_BREAK);

            return fixedA.equals(fixedB);
        }

        return false;
    }

    /**
     * Removes a history token and its suffix semicolon from a token string. For example,"TagsFilteredResultsAndTagView;query;"
     * would become "query;".
     *
     * @param token        The token string to remove the history token from
     * @param historyToken The history token to remove
     * @return the token with the history token and semi-colon suffix removed
     */
    @NotNull
    public static String removeHistoryToken(@NotNull final String token, @NotNull final String historyToken) {
        return token.replace(historyToken + ";", "");
    }

    /**
     * Clears the container and adds the display's top-level panel to the container, an often-repeated combination.
     *
     * @param container The parent container to clear and then add the display to
     * @param display   The display to be added to the parent container
     */
    public static void clearContainerAndAddTopLevelPanel(@NotNull final HasWidgets container, @NotNull final BaseTemplateViewInterface display) {
        container.clear();
        container.add(display.getTopLevelPanel());
    }
}
