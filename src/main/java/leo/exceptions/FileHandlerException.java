package leo.exceptions;

/**
 * The FileHandlerException class provides a custom exceptions for file handling operations.
 */
public class FileHandlerException extends RuntimeException {
    /**
     * Instantiates a FileHandlerException with the task that failed to be retrieved
     * from the storage file due to incorrect format.
     *
     * @param task Task retrieved from the file
     */
    public FileHandlerException(String task) {
        super("I don't recognise "
                + task
                + " stored in the file...");
    }
}
