package Leo.Functions.Commands;

import Leo.Display.Messages;
import Leo.Functions.Task.Deadline;
import Leo.Functions.Task.Event;
import Leo.Functions.Task.TaskList;
import Leo.Functions.Task.ToDo;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * Represents the function that is called when the user wants to add a task.
 */
public class AddTaskCommand implements Command {
    /**
     * Adds a ToDo task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @throws ArrayIndexOutOfBoundsException If the user input is in the wrong format.
     * @throws IOException If the file storing data cannot be found.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static void todo(String userInput, TaskList listItems) throws ArrayIndexOutOfBoundsException, IOException {
        String[] userInputList = userInput.split("todo");
        if(userInputList.length < 2) {
            throw new ArrayIndexOutOfBoundsException("The description of the task is missing!" +
                    "\n\tMake sure it is in this format:" +
                    "\n\t\ttodo <task description>");
        }
        String funcDesc = userInputList[1].trim();
        ToDo ToDoTask = new ToDo(funcDesc);

        listItems.addTask(ToDoTask);
        Messages.addTask();

        System.out.println("\t" + ToDoTask);
        Messages.TaskCount(listItems.getSize());
    }

    /**
     * Adds a Deadline task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @throws ArrayIndexOutOfBoundsException If the user input is in the wrong format.
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static void deadline(String userInput, TaskList listItems) throws ArrayIndexOutOfBoundsException, IOException, DateTimeException {
        String[] userInputList = userInput.split("deadline | /by");
        if(userInputList.length < 3) {
            throw new ArrayIndexOutOfBoundsException(
                    "The description or deadline of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tdeadline <task description> /by <dd/MM/yyyy HHmm>"
            );
        }
        String funcDesc = userInputList[1].trim();
        String deadline = userInputList[2].trim();
        Deadline DeadlineTask = new Deadline(funcDesc, deadline);

        listItems.addTask(DeadlineTask);
        Messages.addTask();

        System.out.println("\t" + DeadlineTask);
        Messages.TaskCount(listItems.getSize());
    }

    /**
     * Adds an Event task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @throws ArrayIndexOutOfBoundsException If the user input is in the wrong format.
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static void event(String userInput, TaskList listItems) throws ArrayIndexOutOfBoundsException, IOException, DateTimeException {
        String[] userInputList = userInput.split("event | /from | /to");
        if(userInputList.length < 4) {
            throw new ArrayIndexOutOfBoundsException(
                    "The description or timing of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tevent <task description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>"
            );
        }
        String funcDesc = userInputList[1].trim();
        String startDate = userInputList[2].trim();
        String endDate = userInputList[3].trim();
        Event EventTask = new Event(funcDesc, startDate, endDate);

        listItems.addTask(EventTask);
        Messages.addTask();

        System.out.println("\t" + EventTask);
        Messages.TaskCount(listItems.getSize());
    }
}
