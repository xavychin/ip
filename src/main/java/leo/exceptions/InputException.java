package leo.exceptions;

import java.io.IOException;

/**
 * The InputException class provides a custom exceptions for inputs into the program.
 */
public class InputException extends IOException {
    private static final String FAILED_TO_LOAD_FILE_EXCEPTION = "Failed to load file.";
    private static final String INVALID_FILE_PATH_EXCEPTION = "Invalid file path given.";
    private static final String FILE_CREATION_EXCEPTION = "File not found and failed to create.";

    public InputException(String type) {
        super(typeOfError(type));
    }

    /**
     * Chooses the correct error message based on the type of task.
     *
     * @param taskType The type of task.
     * @return String containing the error message.
     */
    private static String typeOfError(String taskType) {
        String returnString = "";
        switch (taskType.trim()) {
        case "load":
            returnString = FAILED_TO_LOAD_FILE_EXCEPTION;
            break;
        case "path":
            returnString = INVALID_FILE_PATH_EXCEPTION;
            break;
        case "create":
            returnString = FILE_CREATION_EXCEPTION;
            break;
        default:
            break;
        }

        return returnString;
    }
}
