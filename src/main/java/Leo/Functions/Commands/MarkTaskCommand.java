package Leo.Functions.Commands;

import Leo.Functions.Task.Task;
import Leo.Functions.Task.TaskList;

import java.io.IOException;

public class MarkTaskCommand implements Command {
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
