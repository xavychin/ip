package leo.functions;

import leo.display.Messages;
import leo.exceptions.AddTaskException;
import leo.exceptions.DateTimeFormatException;
import leo.exceptions.DeleteTaskException;
import leo.exceptions.FindCommandException;
import leo.exceptions.InputException;
import leo.exceptions.MarkTaskCommandException;
import leo.exceptions.ZeroLengthException;
import leo.functions.commands.AddTaskCommand;
import leo.functions.commands.DeleteTaskCommand;
import leo.functions.commands.FindCommand;
import leo.functions.commands.ListTaskCommand;
import leo.functions.commands.MarkTaskCommand;
import leo.functions.commands.ReminderCommand;
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
        assert listItems != null : "TaskList must not be null";
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
     * @throws InputException If the file storing data cannot be found.
     * @throws DateTimeFormatException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public String searchFunctions(String userInput)
            throws
            ZeroLengthException,
            InputException,
            DateTimeFormatException,
            FindCommandException,
            MarkTaskCommandException,
            DeleteTaskException,
            AddTaskException {
        assert userInput != null && !userInput.isEmpty() : "User input must not be null or empty";

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
            returnString = FindCommand.find(userInput, listItems);
            break;
        case "remind":
            returnString = ReminderCommand.remindCommand(userInput, listItems);
            break;
        default:
            returnString = "I don't know how to do this...";
            break;
        }

        return returnString;
    }
}
