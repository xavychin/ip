package leo.exceptions;

/**
 * The MarkTaskCommandException class provides a custom exceptions for the mark task command.
 */
public class MarkTaskCommandException extends RuntimeException {
    public MarkTaskCommandException(String type, String task) {
        super(typeOfError(type, task));
    }

    /**
     * Chooses the correct error message based on the type of error.
     *
     * @param type The type of error.
     * @param task The task that was called.
     * @return String containing the error message.
     */
    private static String typeOfError(String type, String task) {
        String returnString = "";
        if (type.equalsIgnoreCase("index")) {
            returnString = "Task to " + task + " is out of the list length.";
        } else if (type.equalsIgnoreCase("number")) {
            returnString = "Incorrect format provided."
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\t"
                    + task
                    + "<task index>";
        }
        return returnString;
    }
}
