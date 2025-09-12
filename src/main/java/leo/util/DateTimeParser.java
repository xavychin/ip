package leo.util;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import leo.exceptions.DateTimeFormatException;

/**
 * The DateTimeParser class formats the date and time.
 */
public class DateTimeParser {
    //Solution adapted from
    // https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
    private static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");

    /**
     * Return a string containing the date and time formatted to an easier to read format.
     *
     * @param dateTimeToFormat The date and time to be formatted.
     * @return Readable date and time text.
     * @throws DateTimeFormatException If incorrect date and time format was given.
     */

    public static String formatDateTimeFromInput(String dateTimeToFormat) throws DateTimeFormatException {
        assert dateTimeToFormat != null && !dateTimeToFormat.isEmpty()
                : "Input dateTimeToFormat must not be null or empty";

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, inputFormat);
            return dateTime.format(fileFormat);
        } catch (DateTimeException e) {
            throw new DateTimeFormatException("input");
        }
    }

    /**
     * Return a string containing the date and time formatted for file storage.
     *
     * @param dateTimeToFormat The date and time to be formatted.
     * @return Readable date and time text.
     * @throws DateTimeFormatException If incorrect date and time format was given.
     */
    public static String formatDateTimeFromFile(String dateTimeToFormat) throws DateTimeFormatException {
        assert dateTimeToFormat != null && !dateTimeToFormat.isEmpty()
                : "Input dateTimeToFormat must not be null or empty";

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, fileFormat);
            return dateTime.format(inputFormat);
        } catch (DateTimeException e) {
            throw new DateTimeFormatException("file");
        }
    }

    /**
     * Get the current date and time.
     *
     * @return DateTime object of the current date and time
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Convert the date and time from string to a DateTime object.
     *
     * @param stringDateTime String containing the date and time.
     * @return DateTime object of the input string.
     */
    public static LocalDateTime stringToDateTime(String stringDateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
            return LocalDateTime.parse(stringDateTime, formatter);
        } catch (DateTimeException e) {
            throw new DateTimeException(
                    "Incorrect date or time format in storage file."
            );
        }
    }
}
