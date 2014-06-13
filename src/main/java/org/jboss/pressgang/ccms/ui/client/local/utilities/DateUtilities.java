package org.jboss.pressgang.ccms.ui.client.local.utilities;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.regexp.shared.RegExp;

public class DateUtilities {
    private static final RegExp THURSDAY_DATE_RE = RegExp.compile("Thurs?(?!s?day)", "i");
    private static final RegExp TUESDAY_DATE_RE = RegExp.compile("Tues(?!day)", "i");

    public static Date parseDateStrictly(final String date, final String[] formats) {
        for (final String format : formats) {
            try {
                final DateTimeFormat dateFormat = DateTimeFormat.getFormat(format);

                final Date parsedDate = dateFormat.parseStrict(date);
                if (parsedDate != null) {
                    return parsedDate;
                }
            } catch (IllegalArgumentException e) {
                // Do nothing, as we should just move onto the next date
            }
        }

        throw new IllegalArgumentException("Unable to parse the date: " + date);
    }

    public static Date parseDate(final String date, final String[] formats) {
        for (final String format : formats) {
            try {
                final DateTimeFormat dateFormat = DateTimeFormat.getFormat(format);

                final Date parsedDate = dateFormat.parse(date);
                if (parsedDate != null) {
                    return parsedDate;
                }
            } catch (IllegalArgumentException e) {
                // Do nothing, as we should just move onto the next date
            }
        }

        throw new IllegalArgumentException("Unable to parse the date: " + date);
    }

    /**
     * Basic method to clean a date string to fix any partial day names. It currently cleans "Thur", "Thurs" and "Tues".
     *
     * @param dateString
     * @return
     */
    public static String cleanDate(final String dateString) {
        if (dateString == null) {
            return dateString;
        }

        String retValue = dateString;
        retValue = THURSDAY_DATE_RE.replace(retValue, "Thu");
        retValue = TUESDAY_DATE_RE.replace(retValue, "Tue");

        return retValue;
    }
}
