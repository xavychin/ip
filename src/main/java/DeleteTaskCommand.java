import java.io.IOException;

public class DeleteTaskCommand implements Command{
    public static void deleteTask(int indexToDel, TaskList listItems) throws IndexOutOfBoundsException, IOException {
        try{
            Task taskToDel = listItems.getItemAtIndex(indexToDel-1);
            listItems.deleteItemAtIndex(indexToDel-1);
            System.out.println("Understood, I've removed the task:");
            System.out.println("\t" + taskToDel.toString());
            Messages.TaskCount(listItems.getSize());
        }
        catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Task to delete is out of the list length.");
        }
    }
}
