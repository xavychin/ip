import java.util.ArrayList;

public class Functions {
    Messages message = new Messages();
    ArrayList<Task> listItems = new ArrayList<>();

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void SearchFunctions(String funcName) throws ArrayIndexOutOfBoundsException, ZeroLengthException, IndexOutOfBoundsException{
        String[] funcNameList = funcName.split(" ");
        switch(funcNameList[0]){
            case "list":
                this.list();
                break;
            case "bye":
                message.Goodbye();
                break;
            case "mark":
                this.markAsDone(Integer.parseInt(funcNameList[1]));
                break;
            case "unmark":
                this.unmark(Integer.parseInt(funcNameList[1]));
                break;
            case "todo":
                this.todo(funcName);
                break;
            case "deadline":
                this.deadline(funcName);
                break;
            case "event":
                this.event(funcName);
                break;
            default:
                System.out.println("\tI don't know how to do this...");
                break;
        }
    }

    //Solution adapted from https://www.perplexity.ai/search/how-to-create-a-custom-excepti-Y_RyDVATSjKGxzSDx1HUeg
    public void list() throws ZeroLengthException{
        int listItemsLength = listItems.size();
        if(listItemsLength == 0){
            throw new ZeroLengthException("The list is empty.");
        }
        else{
            System.out.println("Here are the tasks in your list:");
            for(int i=0; i<listItemsLength; i++){
                System.out.println("\t" + (i+1) + ". " + listItems.get(i).toString());
            }
        }
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void markAsDone(int num) throws IndexOutOfBoundsException{
        Task task = listItems.get(num-1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void unmark(int num){
        Task task = listItems.get(num-1);
        task.unmark();
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void todo(String funcName) throws ArrayIndexOutOfBoundsException{
        String[] funcNameList = funcName.split("todo");
        if(funcNameList.length < 2){
            throw new ArrayIndexOutOfBoundsException("The description of the task is missing!" +
                    "\n\tMake sure it is in this format:" +
                    "\n\t\ttodo <task description>");
        }
        String funcDesc = funcNameList[1].trim();
        ToDo ToDoTask = new ToDo(funcDesc);
        listItems.add(ToDoTask);
        message.addTask();
        System.out.println("\t" + ToDoTask.toString());
        message.TaskCount(listItems.size());
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void deadline(String funcName) throws ArrayIndexOutOfBoundsException{
        String[] funcNameList = funcName.split("deadline | /by");
        if(funcNameList.length < 3){
            throw new ArrayIndexOutOfBoundsException("The description or deadline of the task is missing!" +
                    "\n\tMake sure it is in this format:" +
                    "\n\t\tdeadline <task description> /by <deadline>");
        }
        String funcDesc = funcNameList[1].trim();
        String deadline = funcNameList[2].trim();
        Deadline DeadlineTask = new Deadline(funcDesc, deadline);
        listItems.add(DeadlineTask);
        message.addTask();
        System.out.println("\t" + DeadlineTask.toString());
        message.TaskCount(listItems.size());
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void event(String funcName) throws ArrayIndexOutOfBoundsException{
        String[] funcNameList = funcName.split("event | /from | /to");
        if(funcNameList.length < 4){
            throw new ArrayIndexOutOfBoundsException("The description or timing of the task is missing!" +
                    "\n\tMake sure it is in this format:" +
                    "\n\t\tevent <task description> /from <start timing> /to <end timing>");
        }
        String funcDesc = funcNameList[1].trim();
        String startDate = funcNameList[2].trim();
        String endDate = funcNameList[3].trim();
        Event EventTask = new Event(funcDesc, startDate, endDate);
        listItems.add(EventTask);
        message.addTask();
        System.out.println("\t" + EventTask.toString());
        message.TaskCount(listItems.size());
    }
}
