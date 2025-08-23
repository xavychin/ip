import java.io.IOException;

public class Functions {
    Messages message = new Messages();
    TaskList listItems = new TaskList();

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void SearchFunctions(String funcName) throws ZeroLengthException, IndexOutOfBoundsException, IOException{
        String[] funcNameList = funcName.split(" ");
        switch(funcNameList[0]){
            case "list":
                this.list();
                break;
            case "bye":
                message.Goodbye();
                break;
            case "mark":
                try{
                    this.markAsDone(Integer.parseInt(funcNameList[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to mark.");
                }
                break;
            case "unmark":
                try{
                    this.unmark(Integer.parseInt(funcNameList[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to unmark.");
                }
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
            case "delete":
                try{
                    this.deleteTask(Integer.parseInt(funcNameList[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to delete.");
                }
                break;
            default:
                System.out.println("\tI don't know how to do this...");
                break;
        }
    }

    //Solution adapted from https://www.perplexity.ai/search/how-to-create-a-custom-excepti-Y_RyDVATSjKGxzSDx1HUeg
    public void list() throws ZeroLengthException {
        int listItemsLength = listItems.getSize();
        if(listItemsLength == 0){
            throw new ZeroLengthException("The list is empty.");
        }
        else{
            System.out.println("Here are the tasks in your list:");
            for(int i=0; i<listItemsLength; i++){
                System.out.println("\t" + (i+1) + ". " + listItems.getItemAtIndex(i).toString());
            }
        }
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void markAsDone(int num) throws IndexOutOfBoundsException, IOException{
        try{
            Task task = listItems.getItemAtIndex(num-1);
            task.markAsDone();
            listItems.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + task.toString());
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Task to mark is out of the list length.");
        }
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void unmark(int num) throws IndexOutOfBoundsException, IOException{
        try{
            Task task = listItems.getItemAtIndex(num-1);
            task.unmark();
            listItems.unmark();
            System.out.println("Ok! I've marked this task as not done yet:");
            System.out.println("\t" + task.toString());
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Task to unmark is out of the list length.");
        }
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void todo(String funcName) throws ArrayIndexOutOfBoundsException, IOException{
        String[] funcNameList = funcName.split("todo");
        if(funcNameList.length < 2){
            throw new ArrayIndexOutOfBoundsException("The description of the task is missing!" +
                    "\n\tMake sure it is in this format:" +
                    "\n\t\ttodo <task description>");
        }
        String funcDesc = funcNameList[1].trim();
        ToDo ToDoTask = new ToDo(funcDesc);
        listItems.addTask(ToDoTask);
        message.addTask();
        System.out.println("\t" + ToDoTask.toString());
        message.TaskCount(listItems.getSize());
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void deadline(String funcName) throws ArrayIndexOutOfBoundsException, IOException{
        String[] funcNameList = funcName.split("deadline | /by");
        if(funcNameList.length < 3){
            throw new ArrayIndexOutOfBoundsException("The description or deadline of the task is missing!" +
                    "\n\tMake sure it is in this format:" +
                    "\n\t\tdeadline <task description> /by <deadline>");
        }
        String funcDesc = funcNameList[1].trim();
        String deadline = funcNameList[2].trim();
        Deadline DeadlineTask = new Deadline(funcDesc, deadline);
        listItems.addTask(DeadlineTask);
        message.addTask();
        System.out.println("\t" + DeadlineTask.toString());
        message.TaskCount(listItems.getSize());
    }

    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void event(String funcName) throws ArrayIndexOutOfBoundsException, IOException{
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
        listItems.addTask(EventTask);
        message.addTask();
        System.out.println("\t" + EventTask.toString());
        message.TaskCount(listItems.getSize());
    }

    public void deleteTask(int indexToDel) throws IndexOutOfBoundsException, IOException{
        try{
            Task taskToDel = listItems.getItemAtIndex(indexToDel-1);
            listItems.deleteItemAtIndex(indexToDel-1);
            System.out.println("Understood, I've removed the task:");
            System.out.println("\t" + taskToDel.toString());
            message.TaskCount(listItems.getSize());
        }
        catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Task to delete is out of the list length.");
        }
    }
}
