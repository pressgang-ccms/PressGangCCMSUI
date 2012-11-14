package org.jboss.pressgang.ccms.ui.client.local.utilities;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

/**
 * GWT has some limitations, like not being able to bind an Editor to an array
 * (http://code.google.com/p/google-web-toolkit/issues/detail?id=6600) and not supporting conversion from a byte array to a
 * String.
 * 
 * This class provides some GWT friendly methods to work around these issues, especially for the RESTLanguageImageV1 class.
 * 
 * @author Matthew Casperson
 * 
 */
final public class GWTUtilities {
    private static final int BITS_PER_BYTE = 8;

    private GWTUtilities() {
    }
    
    public static boolean IsStringNullOrEmpty(final String input)
    {
        if (input == null) return true;
        if (input.trim().isEmpty()) return true;
        return false;
        
    }
    
    /**
     * Takes a string and returns only integers separated by single commas. Useful for cleaning up a string
     * used to search for a list of ids.
     * @param input The string to be cleaned
     * @return The cleaned string
     */
    public static String fixUpIdSearchString(final String input)
    {
        final RegExp regex = RegExp.compile("[^0-9,]", "g");
        
        String retValue = regex.replace(input, "");
        
        /* Cannot have sequential commas */
        while (retValue.contains(",,"))
            retValue = retValue.replaceAll(",,", ",");
        
        /* Cannot start with a comma */
        while (retValue.endsWith(","))
            retValue = retValue.substring(0,retValue.length() - 1);
        
        /* Cannot end with a comma */
        while (retValue.startsWith(","))
            retValue = retValue.substring(1, retValue.length());
        
        return retValue;
    }

    public static byte[] getBytesUTF8(final String string) {
        return getBytes(string, 1);
    }

    public static byte[] getBytes(final String string, final int bytesPerChar) {
        if (string == null) {
            throw new IllegalArgumentException("string cannot be null");
        }
        if (bytesPerChar < 1) {
            throw new IllegalArgumentException("bytesPerChar must be greater than 1");
        }

        char[] chars = string.toCharArray();
        byte[] toReturn = new byte[chars.length * bytesPerChar];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < bytesPerChar; j++) {
                toReturn[i * bytesPerChar + j] = (byte) (chars[i] >>> (BITS_PER_BYTE * (bytesPerChar - 1 - j)));
            }
        }
        return toReturn;
    }

    public static String getStringUTF8(byte[] bytes) {
        return getString(bytes, 1);
    }

    public static String getString(byte[] bytes, int bytesPerChar) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes cannot be null");
        }
        if (bytesPerChar < 1) {
            throw new IllegalArgumentException("bytesPerChar must be greater than 1");
        }

        final int length = bytes.length / bytesPerChar;
        final StringBuilder retValue = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char thisChar = 0;

            for (int j = 0; j < bytesPerChar; j++) {
                final int shift = (bytesPerChar - 1 - j) * BITS_PER_BYTE;
                thisChar |= (0x000000FF << shift) & (bytes[i * bytesPerChar + j] << shift);
            }

            retValue.append(thisChar);
        }

        return retValue.toString();
    }

    /**
     * Replacement for String.toByteArray().
     * 
     * @param string The string to convert
     * @param bytesPerChar The number of bytes per character
     * @return the same as the standard Java String.toByteArray() method
     */
    public static byte[] getByteArray(final String string, final int bytesPerChar) {
        char[] chars = string.toCharArray();
        byte[] toReturn = new byte[chars.length * bytesPerChar];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < bytesPerChar; j++) {
                toReturn[i * bytesPerChar + j] = (byte) (chars[i] >>> (BITS_PER_BYTE * (bytesPerChar - 1 - j)));
            }
        }
        return toReturn;
    }
    
    /**
     * Compares two strings
     * @param a The first string
     * @param b The second string
     * @return true if both strings are null, or if both strings are equal
     */
    public static boolean compareStrings(final String a, final String b)
    {
        if (a == null && b == null)
            return true;
        
        if (a != null)
            return a.equals(b);
        
        return b.equals(a);
    }

    /**
     * Compares two strings for equality, considering null and empty string to be equal
     * @param a The first string
     * @param b The second string
     * @return true if both strings are either null or empty string, or if both strings are equal
     */
    public static boolean stringEqualsEquatingNullWithEmptyString(final String a, final String b)
    {
        if ((a == null || a.isEmpty()) && (b == null || b.isEmpty()))
            return true;

        if (a != null)
            return a.equals(b);

        return b.equals(a);
    }

    /**
     * Removes a history token and its postfix semicolon from a token string.
     * For example,"TagsFilteredResultsAndTagView;query;" would become "query;".
     *
     * @param token The token string to remove the history token from
     * @param historyToken The history token to remove
     * @return
     */
    public static String removeHistoryToken(String token, String historyToken) {
        return token.replace(historyToken + ";", "");
    }

    /**
     * Clears the container and adds the display's top-level panel to the container, an often-repeated combination.
     *
     * @param container
     * @param display
     */
    public static void clearContainerAndAddTopLevelPanel(HasWidgets container, BaseTemplateViewInterface display) {
        container.clear();
        container.add(display.getTopLevelPanel());
    }
}
