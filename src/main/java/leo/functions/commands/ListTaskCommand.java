package leo.functions.commands;

import leo.exceptions.ZeroLengthException;
import leo.functions.task.TaskList;

/**
 * Represents the function that is called when the user wants to list all the tasks.
 */
public class ListTaskCommand implements Command {
    /**
     * Lists all the tasks stored in the list.
     *
     * @param listItems List of tasks.
     * @return Formatted String of all the tasks stored in the list.
     * @throws ZeroLengthException If the list is empty.
     */
    //Solution adapted from https://www.perplexity.ai/search/how-to-create-a-custom-excepti-Y_RyDVATSjKGxzSDx1HUeg
    public static String list(TaskList listItems) throws ZeroLengthException {
        StringBuilder sb = new StringBuilder();
        int listItemsLength = listItems.getSize();

        if (listItemsLength == 0) {
            throw new ZeroLengthException();
        } else {
            sb.append("Here are the tasks in your list:");

            for (int i = 0; i < listItemsLength; i++) {
                sb.append("\n\t").append(i + 1).append(". ").append(listItems.getItemAtIndex(i).toString());
            }
        }
        System.out.println(sb);

        return sb.toString();
    }
}
