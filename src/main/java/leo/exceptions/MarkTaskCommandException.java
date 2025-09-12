package leo.exceptions;

/**
 * The MarkTaskCommandException class provides a custom exceptions for the mark task command.
 */
public class MarkTaskCommandException extends IndexOutOfBoundsException {
    public MarkTaskCommandException(String task) {
        super("Task to " + task + " is out of the list length.");
    }
}
