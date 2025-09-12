package leo.functions.commands;

import java.io.IOException;

import leo.display.Messages;
import leo.exceptions.AddTaskException;
import leo.exceptions.DateTimeFormatException;
import leo.functions.task.Deadline;
import leo.functions.task.Event;
import leo.functions.task.TaskList;
import leo.functions.task.ToDo;

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
     * @throws IOException If the file storing data cannot be found.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static String todo(String userInput, TaskList listItems)
            throws AddTaskException, IOException {
        String[] userInputList = userInput.split("todo");
        if (userInputList.length < 2) {
            throw new AddTaskException("todo");
        }
        String taskDesc = userInputList[1].trim();
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
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeFormatException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static String deadline(String userInput, TaskList listItems)
            throws AddTaskException, IOException, DateTimeFormatException {
        String[] userInputList = userInput.split("deadline | /by");
        if (userInputList.length < 3) {
            throw new AddTaskException("deadline");
        }
        String taskDesc = userInputList[1].trim();
        String deadline = userInputList[2].trim();
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
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeFormatException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static String event(String userInput, TaskList listItems)
            throws AddTaskException, IOException, DateTimeFormatException {
        String[] userInputList = userInput.split("event | /from | /to");
        if (userInputList.length < 4) {
            throw new AddTaskException("event");
        }
        String taskDesc = userInputList[1].trim();
        String startDate = userInputList[2].trim();
        String endDate = userInputList[3].trim();
        Event eventTask = new Event(taskDesc, startDate, endDate);

        listItems.addTask(eventTask);
        return String.format(Messages.addTask()
                + "\n\t"
                + eventTask
                + "\n\t"
                + Messages.taskCount(listItems.getSize()));
    }
}
