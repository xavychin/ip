package leo.functions.commands;

import java.io.IOException;

import leo.exceptions.MarkTaskCommandException;
import leo.functions.task.Task;
import leo.functions.task.TaskList;

/**
 * Represents the function that is called when the user wants to mark/unmark a task.
 */
public class MarkTaskCommand implements Command {
    /**
     * Marks a task as done.
     *
     * @param index Index of task to be marked.
     * @param listItems List of tasks.
     * @return Formatted String of the output.
     * @throws MarkTaskCommandException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static String markTask(int index, TaskList listItems)
            throws MarkTaskCommandException, IOException {
        assert index > 0 : "Index must be greater than zero";
        assert listItems != null : "TaskList must not be null";

        try {
            Task task = listItems.getItemAtIndex(index - 1);
            task.markTask();
            listItems.markTask();

            return String.format("Nice! I've marked this task as done:"
                    + "\n\t"
                    + task);
        } catch (IndexOutOfBoundsException e) {
            throw new MarkTaskCommandException("index", "mark");
        }
    }

    /**
     * Marks task as not done
     *
     * @param index Index of task to be marked.
     * @param listItems List of tasks.
     * @return Formatted String of the output.
     * @throws MarkTaskCommandException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static String unmarkTask(int index, TaskList listItems)
            throws MarkTaskCommandException, IOException {
        assert index > 0 : "Index must be greater than zero";
        assert listItems != null : "TaskList must not be null";

        try {
            Task task = listItems.getItemAtIndex(index - 1);
            task.unmarkTask();
            listItems.unmarkTask();

            return String.format("Ok! I've marked this task as not done yet:"
                    + "\n\t"
                    + task);
        } catch (IndexOutOfBoundsException e) {
            throw new MarkTaskCommandException("index", "unmark");
        }
    }
}
