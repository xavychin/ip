import java.util.ArrayList;

public class Functions {
    Messages message = new Messages();
    ArrayList<String> listItems = new ArrayList<>();

    public void SearchFunctions(String funcName){
        switch(funcName){
            case "list":
                this.list();
                break;
            case "blah":
                this.blah();
                break;
            case "bye":
                message.Goodbye();
                break;
            default:
                //System.out.println("\tI don't know how to do this...");
                System.out.println("\tadded: " + funcName);
                listItems.add(funcName);
                break;
        }
    }

    public void list(){
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i<listItems.size(); i++){
            System.out.println("\t" + (i+1) + ". " + listItems.get(i));
        }
    }

    public void blah(){
        System.out.println("\tblah");
    }
}
