package Leo.Display;

import Leo.Functions.Functions;
import Leo.ZeroLengthException;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;

public class UI {
    //Solution below adapted from https://www.perplexity.ai/search/how-to-obtain-user-input-in-cl-KvQBaeNySBqYFdBIYZJrjw
    Scanner scanner = new Scanner(System.in);
    Functions func;

    public void getUserInput(Functions functions) {
        this.func = functions;
        Messages.Greetings();

        //Solution of using equalsIgnoreCase() suggested by IntelliJ code completion
        String callFunction = "";
        while(!callFunction.equalsIgnoreCase("bye")){
            callFunction = scanner.nextLine();
            //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
            try{
                func.SearchFunctions(callFunction);
            }
            catch (ZeroLengthException |
                   IndexOutOfBoundsException |
                   IOException |
                   DateTimeException e){
                System.out.println("\t"
                        + e.getMessage());
            }
            Messages.MessageBreak();
        }

        scanner.close();
    }
}
