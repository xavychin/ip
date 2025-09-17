package leo.functions.commands;

import leo.exceptions.FindCommandException;
import leo.functions.task.TaskList;

/**
 * Represents the function that is called when the user prompts
 * the chatbot to search the related tasks based on a keyword.
 */
public class FindCommand {
    /**
     * Searches for the tasks that contain the specified keyword.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @return String containing the output.
     * @throws FindCommandException If the user input is in the wrong format.
     */

    public static String find(String userInput, TaskList listItems) throws FindCommandException {
        assert userInput != null && !userInput.isEmpty() : "User input must not be null or empty";
        assert listItems != null : "TaskList must not be null";

        StringBuilder returnString = new StringBuilder();
        String[] userInputList = userInput.split("find");

        if (userInputList.length < 2) {
            throw new FindCommandException();
        }

        String keyword = userInputList[1].trim();
        if (keyword.isEmpty()) {
            throw new FindCommandException();
        }

        int outputListIndex = 1;
        for (int i = 0; i < listItems.getSize(); i++) {
            String taskInfo = listItems.getItemAtIndex(i).toString();
            if (taskInfo.contains(keyword)) {
                if (outputListIndex == 1) {
                    returnString.append("Here's the tasks with the word '").append(keyword).append("':");
                }
                returnString.append("\n\t").append(outputListIndex).append(". ").append(taskInfo);
                outputListIndex++;
            }
        }

        if (outputListIndex == 1) {
            returnString.append("I can't find any related task in the list.");
        }

        return returnString.toString();
    }
}
