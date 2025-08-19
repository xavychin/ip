import java.util.Scanner;

public class Leo {
    public static void main(String[] args) {
        Messages message = new Messages();
        message.Greetings();

        //Solution below adapted from https://www.perplexity.ai/search/how-to-obtain-user-input-in-cl-KvQBaeNySBqYFdBIYZJrjw
        Scanner sc = new Scanner(System.in);
        Functions func = new Functions();
        //Solution of using equalsIgnoreCase() suggested by IntelliJ code completion
        String CallFunction = "";
        while(!CallFunction.equalsIgnoreCase("bye")){
            CallFunction = sc.nextLine();
            func.SearchFunctions(CallFunction);
            message.MessageBreak();
        }
    }
}