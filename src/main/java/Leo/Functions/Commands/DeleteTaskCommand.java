package Leo.Functions.Commands;

import Leo.Display.Messages;
import Leo.Functions.Task.Task;
import Leo.Functions.Task.TaskList;

import java.io.IOException;

/**
 * Represents the function that is called when the user wants to delete a task.
 */
public class DeleteTaskCommand implements Command {
    /**
     * Deletes a task from the list.
     *
     * @param indexToDel Index of task to be deleted.
     * @param listItems List of tasks.
     * @throws IndexOutOfBoundsException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     */
    public static void deleteTask(int indexToDel, TaskList listItems) throws IndexOutOfBoundsException, IOException {
        try{
            Task taskToDel = listItems.getItemAtIndex(indexToDel-1);
            listItems.deleteItemAtIndex(indexToDel-1);
            System.out.println("Understood, I've removed the task:");
            System.out.println("\t" + taskToDel.toString());
            Messages.TaskCount(listItems.getSize());
        }
        catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Leo.Functions.Task.Task to delete is out of the list length.");
        }
    }
}
