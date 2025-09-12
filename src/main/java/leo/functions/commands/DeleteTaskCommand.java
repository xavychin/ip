package leo.functions.commands;

import java.io.IOException;

import leo.display.Messages;
import leo.exceptions.DeleteTaskException;
import leo.functions.task.Task;
import leo.functions.task.TaskList;

/**
 * Represents the function that is called when the user wants to delete a task.
 */
public class DeleteTaskCommand implements Command {
    /**
     * Deletes a task from the list.
     *
     * @param indexToDel Index of task to be deleted.
     * @param listItems List of tasks.
     * @return Formatted string of output.
     * @throws DeleteTaskException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     */
    public static String deleteTask(int indexToDel, TaskList listItems)
            throws DeleteTaskException, IOException {
        assert indexToDel > 0 : "Index to delete must be greater than zero";
        assert listItems != null : "TaskList must not be null";

        try {
            Task taskToDel = listItems.getItemAtIndex(indexToDel - 1);
            listItems.deleteItemAtIndex(indexToDel - 1);

            return String.format("Understood, I've removed the task:"
                    + "\n\t"
                    + taskToDel.toString()
                    + "\n\t"
                    + Messages.taskCount(listItems.getSize()));
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteTaskException("index");
        }
    }
}
