package Leo.Functions.Commands;

import Leo.Functions.Task.TaskList;
import Leo.ZeroLengthException;

/**
 * Represents the function that is called when the user wants to list all the tasks.
 */
public class ListTaskCommand implements Command {
    /**
     * Lists all the tasks stored in the list.
     *
     * @param listItems List of tasks.
     * @throws ZeroLengthException If the list is empty.
     */
    //Solution adapted from https://www.perplexity.ai/search/how-to-create-a-custom-excepti-Y_RyDVATSjKGxzSDx1HUeg
    public static void list(TaskList listItems) throws ZeroLengthException {
        int listItemsLength = listItems.getSize();
        if(listItemsLength == 0){
            throw new ZeroLengthException("The list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i=0; i<listItemsLength; i++){
                System.out.println("\t" + (i+1) + ". " + listItems.getItemAtIndex(i).toString());
            }
        }
    }
}
