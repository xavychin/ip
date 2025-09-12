package leo.exceptions;

/**
 * The DeleteTaskException class provides a custom exceptions for the delete command.
 */
public class DeleteTaskException extends IndexOutOfBoundsException {
    private static final String DELETE_COMMAND_EXCEPTION =
            "Task to delete is out of the list length.";

    public DeleteTaskException() {
        super(DELETE_COMMAND_EXCEPTION);
    }
}
