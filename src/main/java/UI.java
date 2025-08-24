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
        String CallFunction = "";
        while(!CallFunction.equalsIgnoreCase("bye")){
            CallFunction = scanner.nextLine();
            //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
            try{
                func.SearchFunctions(CallFunction);
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
