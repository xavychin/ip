package leo.exceptions;

/**
 * The FindCommandException class provides a custom exceptions for the find command.
 */
public class FindCommandException extends IndexOutOfBoundsException {
    private static final String FIND_COMMAND_FORMAT_EXCEPTION =
            "The keyword to search for is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tfind <keyword>";

    public FindCommandException() {
        super(FIND_COMMAND_FORMAT_EXCEPTION);
    }
}
