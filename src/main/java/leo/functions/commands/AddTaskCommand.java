package leo.functions.commands;

import leo.display.Messages;
import leo.exceptions.AddTaskException;
import leo.exceptions.DateTimeParserException;
import leo.exceptions.InputException;
import leo.functions.task.Deadline;
import leo.functions.task.Event;
import leo.functions.task.TaskList;
import leo.functions.task.ToDo;
import leo.util.DateTimeParser;

/**
 * Represents the function that is called when the user wants to add a task.
 */
public class AddTaskCommand implements Command {
    /**
     * Adds a ToDo task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @return Formatted string of the output.
     * @throws AddTaskException If the user input is in the wrong format.
     * @throws InputException If the file storing data cannot be found.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static String todo(String userInput, TaskList listItems)
            throws AddTaskException, InputException {
        assert userInput != null && !userInput.isEmpty() : "User input must not be null or empty";
        assert listItems != null : "TaskList must not be null";

        String[] userInputList = userInput.split("todo");
        String taskDesc = normalizeInputPart(userInputList, 1, "todo", 2);

        if (listItems.checkDuplicate("T", taskDesc)) {
            throw new AddTaskException("duplicate");
        }

        ToDo toDoTask = new ToDo(taskDesc);

        listItems.addTask(toDoTask);
        return String.format(Messages.addTask()
                + "\n\t"
                + toDoTask
                + "\n\t"
                + Messages.taskCount(listItems.getSize()));
    }

    /**
     * Adds a Deadline task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @return Formatted string of the output.
     * @throws AddTaskException If the user input is in the wrong format.
     * @throws InputException If the file storing data cannot be found.
     * @throws DateTimeParserException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static String deadline(String userInput, TaskList listItems)
            throws AddTaskException, InputException, DateTimeParserException {
        assert userInput != null && !userInput.isEmpty() : "User input must not be null or empty";
        assert listItems != null : "TaskList must not be null";

        String[] userInputList = userInput.split("deadline | /by");
        String taskDesc = normalizeInputPart(userInputList, 1, "deadline", 3);
        String deadline = normalizeInputPart(userInputList, 2, "deadline", 3);

        if (DateTimeParser.deadlineBeforeCurrentDateTime(deadline)) {
            throw new DateTimeParserException("before");
        }

        if (listItems.checkDuplicate("D", taskDesc, DateTimeParser.formatDateTimeFromInput(deadline))) {
            throw new AddTaskException("duplicate");
        }

        Deadline deadlineTask = new Deadline(taskDesc, deadline);

        listItems.addTask(deadlineTask);
        return String.format(Messages.addTask()
                + "\n\t"
                + deadlineTask
                + "\n\t"
                + Messages.taskCount(listItems.getSize()));
    }

    /**
     * Adds an Event task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @return Formatted string of the output.
     * @throws AddTaskException If the user input is in the wrong format.
     * @throws InputException If the file storing data cannot be found.
     * @throws DateTimeParserException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static String event(String userInput, TaskList listItems)
            throws AddTaskException, InputException, DateTimeParserException {
        assert userInput != null && !userInput.isEmpty() : "User input must not be null or empty";
        assert listItems != null : "TaskList must not be null";

        String[] userInputList = userInput.split("event | /from | /to");
        String taskDesc = normalizeInputPart(userInputList, 1, "event", 4);
        String startDate = normalizeInputPart(userInputList, 2, "event", 4);
        String endDate = normalizeInputPart(userInputList, 3, "event", 4);

        if (DateTimeParser.deadlineBeforeCurrentDateTime(startDate)) {
            throw new DateTimeParserException("start");
        } else if (DateTimeParser.endDateBeforeStartDate(startDate, endDate)) {
            throw new DateTimeParserException("startEnd");
        }

        if (listItems.checkDuplicate("E",
                taskDesc,
                DateTimeParser.formatDateTimeFromInput(startDate),
                DateTimeParser.formatDateTimeFromInput(endDate))) {
            throw new AddTaskException("duplicate");
        }

        Event eventTask = new Event(taskDesc, startDate, endDate);

        listItems.addTask(eventTask);
        return String.format(Messages.addTask()
                + "\n\t"
                + eventTask
                + "\n\t"
                + Messages.taskCount(listItems.getSize()));
    }

    /**
     * Normalizes the user input and takes returns the details of the specified part of the task.
     *
     * @param userInputList List containing details of the task based on the user input.
     * @param index Index of detail to extract from the list.
     * @param typeOfTask The type of the task.
     * @param expectedLength The expected length of the list.
     * @return The formatted string of the specified detail of the task from the user input.
     * @throws AddTaskException If the user input is in the wrong format.
     */
    //Solution taken from https://www.perplexity.ai/search/how-to-modify-string-such-that-UGzl21BmRMOih7yw9UNaYQ#1
    private static String normalizeInputPart(String[] userInputList, int index, String typeOfTask, int expectedLength)
            throws AddTaskException {
        if (userInputList.length < expectedLength) {
            throw new AddTaskException(typeOfTask);
        }
        //Solution adapted from https://www.perplexity.ai/search/delete-leading-whitespace-java-ffS3wQ_dRtO.DioPVlTSyg#1
        return userInputList[index].replaceAll("\\s+", " ").trim();
    }
}
