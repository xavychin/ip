package leo.exceptions;

import java.io.IOException;

/**
 * The InputException class provides a custom exceptions for inputs into the program.
 */
public class InputException extends IOException {
    private static final String FAILED_TO_LOAD_FILE_EXCEPTION = "I can't remember your past tasks...";
    private static final String INVALID_FILE_PATH_EXCEPTION = "I can't find your past tasks...";
    private static final String FILE_CREATION_EXCEPTION = "I can't find your past tasks or store your current tasks...";

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
