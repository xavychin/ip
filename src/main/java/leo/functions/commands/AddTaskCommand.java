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
        if (userInputList.length < 2) {
            throw new AddTaskException("todo");
        }
        String taskDesc = userInputList[1].trim();

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
        if (userInputList.length < 3) {
            throw new AddTaskException("deadline");
        }
        String taskDesc = userInputList[1].trim();
        String deadline = userInputList[2].trim();

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
        if (userInputList.length < 4) {
            throw new AddTaskException("event");
        }
        String taskDesc = userInputList[1].trim();
        String startDate = userInputList[2].trim();
        String endDate = userInputList[3].trim();

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
}
