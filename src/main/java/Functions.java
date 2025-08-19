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
            default:
                //System.out.println("\tI don't know how to do this...");
                System.out.println("\tadded: " + funcName);
                listItems.add(new Task(funcName));
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
}
