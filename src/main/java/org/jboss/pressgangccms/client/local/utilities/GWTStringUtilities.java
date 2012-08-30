package org.jboss.pressgangccms.client.local.utilities;

/**
 * GWT has some limitations, like not being able to bind an Editor to an array
 * (http://code.google.com/p/google-web-toolkit/issues/detail?id=6600) and not
 * supporting conversion from a byte array to a String.
 * 
 * This class provides some GWT friendly methods to work around these issues,
 * especially for the RESTLanguageImageV1 class.
 * 
 * @author Matthew Casperson
 * 
 */
final public class GWTStringUtilities
{
	public static byte[] getBytesUTF8(final String string)
	{
		return getBytes(string, 1);
	}
	
	public static byte[] getBytes(final String string, final int bytesPerChar)
	{
		if (string == null) throw new IllegalArgumentException("string cannot be null");
		if (bytesPerChar < 1) throw new IllegalArgumentException("bytesPerChar must be greater than 1");
		
		char[] chars = string.toCharArray();
		byte[] toReturn = new byte[chars.length * bytesPerChar];
		for (int i = 0; i < chars.length; i++)
		{
			for (int j = 0; j < bytesPerChar; j++)
				toReturn[i * bytesPerChar + j] = (byte) (chars[i] >>> (8 * (bytesPerChar - 1 - j)));
		}
		return toReturn;
	}

	public static String getStringUTF8(byte[] bytes)
	{
		return getString(bytes, 1);
	}

	public static String getString(byte[] bytes, int bytesPerChar)
	{
		if (bytes == null) throw new IllegalArgumentException("bytes cannot be null");
		if (bytesPerChar < 1) throw new IllegalArgumentException("bytesPerChar must be greater than 1");
		
		char[] chars = new char[bytes.length / bytesPerChar];
		for (int i = 0; i < chars.length; i++)
		{
			for (int j = 0; j < bytesPerChar; j++)
			{
				int shift = (bytesPerChar - 1 - j) * 8;
				chars[i] |= (0x000000FF << shift) & (((int) bytes[i * bytesPerChar + j]) << shift);
			}
		}
		return new String(chars);
	}
}
