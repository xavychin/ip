package Leo.Functions.Commands;

import Leo.Functions.Task.Task;
import Leo.Functions.Task.TaskList;

import java.io.IOException;

/**
 * Represents the function that is called when the user wants to mark/unmark a task.
 */
public class MarkTaskCommand implements Command {
    /**
     * Marks a task as done
     *
     * @param num Index of task to be marked.
     * @param listItems List of tasks.
     * @throws IndexOutOfBoundsException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static void markAsDone(int num, TaskList listItems) throws IndexOutOfBoundsException, IOException {
        try{
            Task task = listItems.getItemAtIndex(num-1);
            task.markAsDone();
            listItems.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + task);
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Leo.Functions.Task.Task to mark is out of the list length.");
        }
    }

    /**
     * Marks task as not done
     *
     * @param num Index of task to be marked.
     * @param listItems List of tasks.
     * @throws IndexOutOfBoundsException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static void unmark(int num, TaskList listItems) throws IndexOutOfBoundsException, IOException{
        try{
            Task task = listItems.getItemAtIndex(num-1);
            task.unmark();
            listItems.unmark();
            System.out.println("Ok! I've marked this task as not done yet:");
            System.out.println("\t" + task);
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Leo.Functions.Task.Task to unmark is out of the list length.");
        }
    }
}
