import java.io.IOException;
import java.time.DateTimeException;

public class Functions {
    TaskList listItems;

    public Functions(TaskList listItems) {
        this.listItems = listItems;
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void SearchFunctions(String funcName) throws ZeroLengthException, IndexOutOfBoundsException, IOException, DateTimeException{
        String[] funcNameList = funcName.split(" ");
        switch(funcNameList[0]){
            case "list":
                ListTaskCommand.list(listItems);
                break;
            case "bye":
                Messages.Goodbye();
                break;
            case "mark":
                try{
                    MarkTaskCommand.markAsDone(Integer.parseInt(funcNameList[1]), listItems);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to mark.");
                }
                break;
            case "unmark":
                try{
                    MarkTaskCommand.unmark(Integer.parseInt(funcNameList[1]), listItems);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to unmark.");
                }
                break;
            case "todo":
                AddTaskCommand.todo(funcName, listItems);
                break;
            case "deadline":
                AddTaskCommand.deadline(funcName, listItems);
                break;
            case "event":
                AddTaskCommand.event(funcName, listItems);
                break;
            case "delete":
                try{
                    DeleteTaskCommand.deleteTask(Integer.parseInt(funcNameList[1]), listItems);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to delete.");
                }
                break;
            default:
                System.out.println("\tI don't know how to do this...");
                break;
        }
    }
}
