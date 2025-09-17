package leo.exceptions;

/**
 * The DeleteTaskException class provides a custom exceptions for the delete command.
 */
public class DeleteTaskException extends IndexOutOfBoundsException {
    private static final String DELETE_COMMAND_EXCEPTION =
            "Task number that you want to delete is not in the list...";

    public DeleteTaskException(String type) {
        super(typeOfError(type));
    }

    /**
     * Chooses the correct error message based on the type of error.
     *
     * @param type The type of error.
     * @return String containing the error message.
     */
    private static String typeOfError(String type) {
        String returnString = "";
        if (type.equalsIgnoreCase("index")) {
            returnString = DELETE_COMMAND_EXCEPTION;
        } else if (type.equalsIgnoreCase("number")) {
            returnString = "Incorrect format provided."
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tdelete <task index>";
        }
        return returnString;
    }
}
