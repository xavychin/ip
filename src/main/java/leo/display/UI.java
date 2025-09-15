package leo.display;

import java.util.Scanner;

import leo.exceptions.AddTaskException;
import leo.exceptions.DateTimeParserException;
import leo.exceptions.DeleteTaskException;
import leo.exceptions.FindCommandException;
import leo.exceptions.InputException;
import leo.exceptions.MarkTaskCommandException;
import leo.exceptions.ZeroLengthException;
import leo.functions.Functions;

/**
 * The UI class manages the interaction between the user and the chatbot,
 * outputting messages to the user interface and receiving input from the user.
 */
public class UI {
    //Solution below adapted from https://www.perplexity.ai/search/how-to-obtain-user-input-in-cl-KvQBaeNySBqYFdBIYZJrjw
    private Scanner scanner = new Scanner(System.in);
    private Functions func;

    /**
     * Takes in user input from the command line.
     *
     * @param functions An object that contains the possible functions of the chatbot.
     */
    public void getUserInput(Functions functions) {
        assert functions != null : "Functions object must not be null";
        this.func = functions;

        //Solution of using equalsIgnoreCase() suggested by IntelliJ code completion
        String callFunction = "";
        while (!callFunction.equalsIgnoreCase("bye")) {
            callFunction = scanner.nextLine();
            //Solution adapted from
            // https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
            try {
                func.searchFunctions(callFunction);
            } catch (ZeroLengthException
                     | FindCommandException
                     | MarkTaskCommandException
                     | DeleteTaskException
                     | AddTaskException
                     | InputException
                     | DateTimeParserException
                    e) {
                System.out.println(
                        "\t"
                        + e.getMessage()
                );
            }
            Messages.messageBreak();
        }

        scanner.close();
    }

    /**
     * Gets the response from the chatbot based on the user input.
     *
     * @param functions An object that contains the possible functions of the chatbot.
     * @param userInput Text containing the user input.
     * @return Output string of the selected function.
     */
    public String getLeoResponse(Functions functions, String userInput) {
        assert functions != null : "Functions object must not be null";
        assert userInput != null : "User input must not be null";

        this.func = functions;
        String returnString = "";

        try {
            returnString = func.searchFunctions(userInput);
        } catch (ZeroLengthException
                 | FindCommandException
                 | MarkTaskCommandException
                 | DeleteTaskException
                 | AddTaskException
                 | InputException
                 | DateTimeParserException
                e) {
            return e.getMessage();
        }

        return returnString;
    }
}
