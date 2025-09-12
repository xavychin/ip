package leo.functions.search;

import leo.functions.task.TaskList;

/**
 * Represents the function that is called when the user prompts
 * the chatbot to search the related tasks based on a keyword.
 */
public class Find {
    /**
     * Searches for the tasks that contain the specified keyword.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @throws ArrayIndexOutOfBoundsException If the user input is in the wrong format.
     */
    public static void find(String userInput, TaskList listItems) throws ArrayIndexOutOfBoundsException {
        assert userInput != null && !userInput.isEmpty() : "User input must not be null or empty";
        assert listItems != null : "TaskList must not be null";

        String[] userInputList = userInput.split("find");
        if (userInputList.length < 2) {
            throw new ArrayIndexOutOfBoundsException(
                    "The keyword to search for is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tfind <keyword>"
            );
        }

        String keyword = userInputList[1].trim();
        if (keyword.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(
                    "The keyword to search for is missing!"
                            + "\n\tMake sure it is in this format:"
                            + "\n\t\tfind <keyword>"
            );
        }

        int outputListIndex = 1;
        for (int i = 0; i < listItems.getSize(); i++) {
            String taskInfo = listItems.getItemAtIndex(i).toString();
            if (taskInfo.contains(keyword)) {
                System.out.println(
                        "\t"
                        + outputListIndex
                        + ". "
                        + taskInfo
                );
                outputListIndex++;
            }
        }

        if (outputListIndex == 1) {
            System.out.println("\tNo related task in the list.");
        }
    }

    /**
     * Searches for the tasks that contain the specified keyword.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @return String containing the output.
     * @throws ArrayIndexOutOfBoundsException If the user input is in the wrong format.
     */
    public static String findReturnOutput(String userInput, TaskList listItems) throws ArrayIndexOutOfBoundsException {
        assert userInput != null && !userInput.isEmpty() : "User input must not be null or empty";
        assert listItems != null : "TaskList must not be null";

        StringBuilder returnString = new StringBuilder();
        String[] userInputList = userInput.split("find");

        if (userInputList.length < 2) {
            throw new ArrayIndexOutOfBoundsException(
                    "The keyword to search for is missing!"
                            + "\n\tMake sure it is in this format:"
                            + "\n\t\tfind <keyword>"
            );
        }

        String keyword = userInputList[1].trim();
        if (keyword.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(
                    "The keyword to search for is missing!"
                            + "\n\tMake sure it is in this format:"
                            + "\n\t\tfind <keyword>"
            );
        }

        int outputListIndex = 1;
        for (int i = 0; i < listItems.getSize(); i++) {
            String taskInfo = listItems.getItemAtIndex(i).toString();
            if (taskInfo.contains(keyword)) {
                if (outputListIndex == 1) {
                    returnString.append("Here's the tasks with the word '" + keyword + "':");
                }
                returnString.append("\n\t"
                        + outputListIndex
                        + ". "
                        + taskInfo);
                outputListIndex++;
            }
        }

        if (outputListIndex == 1) {
            returnString.append("\tNo related task in the list.");
        }

        return returnString.toString();
    }
}
