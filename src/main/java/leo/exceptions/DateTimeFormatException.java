package leo.exceptions;

/**
 * The DateTimeFormatException class provides a custom exceptions
 * for cases where the date or time format to be parsed is incorrect.
 */
public class DateTimeFormatException extends RuntimeException {
    private static final String DATE_FORMAT_FROM_INPUT =
            "Incorrect date or time format for /from or /to..."
                    + "\n\tIt should be <dd/MM/yyyy HHmm>";
    private static final String DATE_FORMAT_FROM_FILE =
            "Incorrect date or time format stored in the file...";

    /**
     * Instantiates a DateTimeFormatException with the location of the date and time.
     *
     * @param location The method which the date and time data is obtained.
     */
    public DateTimeFormatException(String location) {
        super(fromFileOrInput(location));
    }

    /**
     * Chooses the correct error message based on the location of the date and time data is obtained from.
     * input is from the user.
     * file is from the storage file.
     *
     * @param location The method which the date and time data is obtained.
     * @return String containing the error message.
     */
    private static String fromFileOrInput(String location) {
        String returnString = "";
        if (location.equalsIgnoreCase("file")) {
            returnString = DATE_FORMAT_FROM_FILE;
        } else if (location.equalsIgnoreCase("input")) {
            returnString = DATE_FORMAT_FROM_INPUT;
        }
        return returnString;
    }
}
