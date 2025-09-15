package leo.exceptions;

/**
 * The AddTaskException class provides a custom exceptions for the add command.
 */
public class AddTaskException extends IndexOutOfBoundsException {
    private static final String TODO_TASK_ARRAY_EXCEPTION =
            "The description of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\ttodo <task description>";
    private static final String DEADLINE_TASK_ARRAY_EXCEPTION =
            "The description or deadline of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tdeadline <task description> /by <dd/MM/yyyy HHmm>";
    private static final String EVENT_TASK_ARRAY_EXCEPTION =
            "The description or timing of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tevent <task description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>";
    private static final String DUPLICATE_TASK =
            "The task that you are adding is already in the list...";

    public AddTaskException(String taskType) {
        super(typeOfTask(taskType));
    }

    /**
     * Chooses the correct error message based on the type of task.
     *
     * @param taskType The type of task.
     * @return String containing the error message.
     */
    private static String typeOfTask(String taskType) {
        String returnString = "";
        switch (taskType.trim()) {
        case "todo":
            returnString = TODO_TASK_ARRAY_EXCEPTION;
            break;
        case "deadline":
            returnString = DEADLINE_TASK_ARRAY_EXCEPTION;
            break;
        case "event":
            returnString = EVENT_TASK_ARRAY_EXCEPTION;
            break;
        case "duplicate":
            returnString = DUPLICATE_TASK;
            break;
        default:
            break;
        }

        return returnString;
    }
}
