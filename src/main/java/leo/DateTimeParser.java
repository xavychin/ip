package leo;

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
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");

    /**
     * Return a string containing the date and time formatted to an easier to read format.
     *
     * @param dateTimeToFormat The date and time to be formatted.
     * @return Readable date and time text.
     * @throws DateTimeFormatException If incorrect date and time format was given.
     */
    public String formatDateTimeFromInput(String dateTimeToFormat) throws DateTimeFormatException {
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
    public String formatDateTimeFromFile(String dateTimeToFormat) throws DateTimeFormatException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, fileFormat);
            return dateTime.format(inputFormat);
        } catch (DateTimeException e) {
            throw new DateTimeFormatException("file");
        }
    }
}
