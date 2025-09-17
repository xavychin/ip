package leo.exceptions;

/**
 * The DateTimeFormatException class provides a custom exceptions
 * for cases where the date or time format to be parsed is incorrect.
 */
public class DateTimeParserException extends RuntimeException {
    private static final String DATE_FORMAT_FROM_INPUT =
            "Incorrect date or time format for /from or /to..."
                    + "\n\tIt should be <dd/MM/yyyy HHmm>";
    private static final String DATE_FORMAT_FROM_FILE =
            "I don't recognise the date or time format stored in the file...";
    private static final String DEADLINE_BEFORE_CURRENT_DATETIME =
            "You cannot give a deadline that is before today or right now...";
    private static final String START_DATE_BEFORE_CURRENT_DATETIME =
            "You cannot give a start date or time that is before today or right now...";
    private static final String START_DATETIME_BEFORE_END_DATETIME =
            "Your start date or time cannot be after your end date or time...";
    private static final String INVALID_DATE_TIME =
            "The date or time you gave only exists in an imaginary world...";

    /**
     * Instantiates a DateTimeFormatException with the location that provided the date and time.
     *
     * @param type The method which the date and time data is obtained.
     */
    public DateTimeParserException(String type) {
        super(typeOfError(type));
    }

    /**
     * Chooses the correct error message based on the location of the date and time data is obtained from.
     * input is from the user.
     * file is from the storage file.
     *
     * @param type The method which the date and time data is obtained.
     * @return String containing the error message.
     */
    private static String typeOfError(String type) {
        String returnString = "";

        switch (type.trim()) {
        case "file":
            returnString = DATE_FORMAT_FROM_FILE;
            break;
        case "input":
            returnString = DATE_FORMAT_FROM_INPUT;
            break;
        case "before":
            returnString = DEADLINE_BEFORE_CURRENT_DATETIME;
            break;
        case "start":
            returnString = START_DATE_BEFORE_CURRENT_DATETIME;
            break;
        case "startEnd":
            returnString = START_DATETIME_BEFORE_END_DATETIME;
            break;
        case "invalid":
            returnString = INVALID_DATE_TIME;
            break;
        default:
            break;
        }

        return returnString;
    }
}
