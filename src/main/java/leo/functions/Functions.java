package leo.functions;

import java.io.IOException;

import leo.display.Messages;
import leo.exceptions.AddTaskException;
import leo.exceptions.DateTimeFormatException;
import leo.exceptions.DeleteTaskException;
import leo.exceptions.FindCommandException;
import leo.exceptions.MarkTaskCommandException;
import leo.exceptions.ZeroLengthException;
import leo.functions.commands.AddTaskCommand;
import leo.functions.commands.DeleteTaskCommand;
import leo.functions.commands.ListTaskCommand;
import leo.functions.commands.MarkTaskCommand;
import leo.functions.search.Find;
import leo.functions.task.TaskList;

/**
 * The Functions class calls the list operation based on the user input given.
 */
public class Functions {
    private TaskList listItems;

    /**
     * Instantiates the Functions object.
     *
     * @param listItems List of tasks.
     */
    public Functions(TaskList listItems) {
        this.listItems = listItems;
    }

    /**
     * The method calls the function based on the user input.
     *
     * @param userInput User input.
     * @return String containing the output.
     * @throws ZeroLengthException If the list is empty.
     * @throws FindCommandException If index given is more than the list length.
     * @throws MarkTaskCommandException If index given is more than the list length or the input format is incorrect.
     * @throws DeleteTaskException If index given is more than the list length or the input format is incorrect.
     * @throws AddTaskException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeFormatException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public String searchFunctions(String userInput)
            throws
            ZeroLengthException,
            IOException,
            DateTimeFormatException,
            FindCommandException,
            MarkTaskCommandException,
            DeleteTaskException,
            AddTaskException {
        String[] userInputList = userInput.split(" ");
        String returnString = "";

        switch(userInputList[0].trim()) {
        case "list":
            returnString = ListTaskCommand.list(listItems);
            break;
        case "bye":
            returnString = Messages.goodbye();
            break;
        case "mark":
            try {
                returnString =
                        MarkTaskCommand.markTask(Integer.parseInt(userInputList[1].trim()), listItems);
            } catch (NumberFormatException e) {
                throw new MarkTaskCommandException("number", "mark");
            }
            break;
        case "unmark":
            try {
                returnString =
                        MarkTaskCommand.unmarkTask(Integer.parseInt(userInputList[1].trim()), listItems);
            } catch (NumberFormatException e) {
                throw new MarkTaskCommandException("number", "unmark");
            }
            break;
        case "todo":
            returnString = AddTaskCommand.todo(userInput, listItems);
            break;
        case "deadline":
            returnString = AddTaskCommand.deadline(userInput, listItems);
            break;
        case "event":
            returnString = AddTaskCommand.event(userInput, listItems);
            break;
        case "delete":
            try {
                returnString = DeleteTaskCommand.deleteTask(Integer.parseInt(userInputList[1]), listItems);
            } catch (NumberFormatException e) {
                throw new DeleteTaskException("number");
            }
            break;
        case "find":
            returnString = Find.find(userInput, listItems);
            break;
        default:
            returnString = "I don't know how to do this...";
            break;
        }

        return returnString;
    }
}
