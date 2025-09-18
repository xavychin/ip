package leo.util;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import leo.exceptions.DateTimeParserException;

/**
 * The DateTimeParser class formats the date and time between user input format and file storage format,
 * and provides utility methods for date-time related checks.
 */
public class DateTimeParser {
    //Solution adapted from
    // https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
    // https://www.perplexity.ai/search/if-i-thrw-an-exception-des-the-7ZfBBYEkQsaTba5w0wkjgA#13
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter FILE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Returns a string containing the date and time formatted to an easier to read format.
     *
     * @param dateTimeToFormat The date and time to be formatted.
     * @return Readable date and time text.
     * @throws DateTimeParserException If incorrect date and time format was given.
     */

    public static String formatDateTimeFromInput(String dateTimeToFormat) throws DateTimeParserException {
        assert dateTimeToFormat != null && !dateTimeToFormat.isEmpty()
                : "Input dateTimeToFormat must not be null or empty";

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, INPUT_FORMAT);
            return dateTime.format(FILE_FORMAT);
        } catch (DateTimeException e) {
            throw new DateTimeParserException("input");
        }
    }

    /**
     * Returns a string containing the date and time formatted for file storage.
     *
     * @param dateTimeToFormat The date and time to be formatted.
     * @return Readable date and time text.
     * @throws DateTimeParserException If incorrect date and time format was given.
     */
    public static String formatDateTimeFromFile(String dateTimeToFormat) throws DateTimeParserException {
        assert dateTimeToFormat != null && !dateTimeToFormat.isEmpty()
                : "Input dateTimeToFormat must not be null or empty";

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, FILE_FORMAT);
            return dateTime.format(INPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new DateTimeParserException("file");
        }
    }

    /**
     * Gets the current date and time.
     *
     * @return DateTime object of the current date and time
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Converts the date and time from string to a DateTime object.
     *
     * @param stringDateTime String containing the date and time.
     * @return DateTime object of the input string.
     */
    public static LocalDateTime stringToDateTime(String stringDateTime) {
        try {
            return LocalDateTime.parse(stringDateTime, FILE_FORMAT);
        } catch (DateTimeException e) {
            throw new DateTimeParserException("file");
        }
    }

    /**
     * Checks if the deadline given is before the current date and time.
     *
     * @param deadline DateTime to be checked.
     * @return True if deadline is before current date and time, else false.
     */
    public static boolean deadlineBeforeCurrentDateTime(String deadline) {
        try {
            LocalDateTime currDateTime = DateTimeParser.getCurrentDateTime();
            LocalDateTime dateTime = LocalDateTime.parse(deadline, INPUT_FORMAT);
            return dateTime.isBefore(currDateTime) || dateTime.isEqual(currDateTime);
        } catch (DateTimeException e) {
            throw new DateTimeParserException("invalid");
        }
    }

    /**
     * Checks if the end date and time is before the start date and time.
     *
     * @param startDate The start date and time.
     * @param endDate The end date and time.
     * @return True if end date and time is before the start date, else false.
     */
    public static boolean endDateBeforeStartDate(String startDate, String endDate) {
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(startDate, INPUT_FORMAT);
            LocalDateTime endDateTime = LocalDateTime.parse(endDate, INPUT_FORMAT);
            return !endDateTime.isAfter(startDateTime);
        } catch (DateTimeException e) {
            throw new DateTimeParserException("invalid");
        }
    }
}
