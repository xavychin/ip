package leo.functions;

import java.io.IOException;
import java.time.DateTimeException;

import leo.display.Messages;
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
        this.listItems = listItems;
    }

    /**
     * The method calls the function based on the user input.
     *
     * @param userInput User input.
     * @throws ZeroLengthException If the list is empty.
     * @throws IndexOutOfBoundsException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeException If the date or time is given in the wrong format.
     * @throws NumberFormatException If the input format is incorrect
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void searchFunctions(String userInput)
            throws NumberFormatException,
            ZeroLengthException,
            IndexOutOfBoundsException,
            IOException,
            DateTimeException {
        String[] userInputList = userInput.split(" ");
        switch(userInputList[0].trim()) {
        case "list":
            ListTaskCommand.list(listItems);
            break;
        case "bye":
            Messages.goodbye();
            break;
        case "mark":
            try {
                MarkTaskCommand.markTask(Integer.parseInt(userInputList[1].trim()), listItems);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Missing list number of task to mark.");
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        "Incorrect format provided."
                        + "\n\tMake sure it is in this format:"
                        + "\n\t\tunmark <task index>"
                );
            }
            break;
        case "unmark":
            try {
                MarkTaskCommand.unmarkTask(Integer.parseInt(userInputList[1].trim()), listItems);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Missing list number of task to unmark.");
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        "Incorrect format provided."
                        + "\n\tMake sure it is in this format:"
                        + "\n\t\tunmark <task index>"
                );
            }
            break;
        case "todo":
            AddTaskCommand.todo(userInput, listItems);
            break;
        case "deadline":
            AddTaskCommand.deadline(userInput, listItems);
            break;
        case "event":
            AddTaskCommand.event(userInput, listItems);
            break;
        case "delete":
            try {
                DeleteTaskCommand.deleteTask(Integer.parseInt(userInputList[1]), listItems);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Missing list number of task to delete.");
            }
            break;
        case "find":
            FindCommand.findCommand(userInput, listItems);
            break;
        default:
            System.out.println("\tI don't know how to do this...");
            break;
        }
    }

    /**
     * The method calls the function based on the user input.
     *
     * @param userInput User input.
     * @return String containing the output.
     * @throws ZeroLengthException If the list is empty.
     * @throws IndexOutOfBoundsException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeException If the date or time is given in the wrong format.
     * @throws NumberFormatException If the input format is incorrect
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public String searchFunctionsReturnOutput(String userInput)
            throws NumberFormatException,
            ZeroLengthException,
            IndexOutOfBoundsException,
            IOException,
            DateTimeException {
        String[] userInputList = userInput.split(" ");
        String returnString = "";

        switch(userInputList[0].trim()) {
        case "list":
            returnString = ListTaskCommand.listReturnOutput(listItems);
            break;
        case "bye":
            returnString = Messages.goodbyeReturnOutput();
            break;
        case "mark":
            try {
                returnString =
                        MarkTaskCommand.markTaskReturnOutput(Integer.parseInt(userInputList[1].trim()), listItems);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Missing list number of task to mark.");
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        "Incorrect format provided."
                                + "\n\tMake sure it is in this format:"
                                + "\n\t\tunmark <task index>"
                );
            }
            break;
        case "unmark":
            try {
                returnString =
                        MarkTaskCommand.unmarkTaskReturnOutput(Integer.parseInt(userInputList[1].trim()), listItems);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Missing list number of task to unmark.");
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        "Incorrect format provided."
                                + "\n\tMake sure it is in this format:"
                                + "\n\t\tunmark <task index>"
                );
            }
            break;
        case "todo":
            returnString = AddTaskCommand.todoReturnOutput(userInput, listItems);
            break;
        case "deadline":
            returnString = AddTaskCommand.deadlineReturnOutput(userInput, listItems);
            break;
        case "event":
            returnString = AddTaskCommand.eventReturnOutput(userInput, listItems);
            break;
        case "delete":
            try {
                returnString = DeleteTaskCommand.deleteTaskReturnOutput(Integer.parseInt(userInputList[1]), listItems);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Missing list number of task to delete.");
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        "Incorrect format provided."
                                + "\n\tMake sure it is in this format:"
                                + "\n\t\tdelete <task index>"
                );
            }
            break;
        case "find":
            returnString = FindCommand.findCommandReturnOutput(userInput, listItems);
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
