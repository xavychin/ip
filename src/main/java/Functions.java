import java.util.ArrayList;

public class Functions {
    Messages message = new Messages();
    ArrayList<Task> listItems = new ArrayList<>();

    public void SearchFunctions(String funcName){
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
                this.todo(funcNameList);
                break;
            case "deadline":
                this.deadline(funcName);
                break;
            case "event":
                this.event(funcName);
                break;
            default:
                //System.out.println("\tI don't know how to do this...");
                break;
        }
    }

    public void list(){
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i<listItems.size(); i++){
            System.out.println("\t" + (i+1) + ". " + listItems.get(i).toString());
        }
    }

    public void markAsDone(int num){
        Task task = listItems.get(num-1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    public void unmark(int num){
        Task task = listItems.get(num-1);
        task.unmark();
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    public void todo(String[] funcNameList){
        StringBuilder funcDesc = new StringBuilder();
        for(int i=1; i<funcNameList.length; i++){
            funcDesc.append(funcNameList[i]).append(" ");
        }
        ToDo ToDoTask = new ToDo(funcDesc.toString());
        listItems.add(ToDoTask);
        message.addTask();
        System.out.println("\t" + ToDoTask.toString());
        message.TaskCount(listItems.size());
    }

    public void deadline(String funcName){
        String[] funcNameList = funcName.split("deadline | /by");
        String funcDesc = funcNameList[1].trim();
        String deadline = funcNameList[2].trim();
        Deadline DeadlineTask = new Deadline(funcDesc, deadline);
        listItems.add(DeadlineTask);
        message.addTask();
        System.out.println("\t" + DeadlineTask.toString());
        message.TaskCount(listItems.size());
    }

    public void event(String funcName){
        String[] funcNameList = funcName.split("event | /from | /to");
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
