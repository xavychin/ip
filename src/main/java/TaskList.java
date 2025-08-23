import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private FileHandler fileHandler = new FileHandler();
    ArrayList<Task> listItems;

    public TaskList(){
        try {
            fileHandler.checkFileExists();
            this.listItems = fileHandler.retrieveTasksFromFile();
        } catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        }
    }

    public void addTask(Task task) throws IOException {
        fileHandler.appendToFile(task.appendToFile());
        listItems.add(task);
    }

    public Integer getSize() {
        return this.listItems.size();
    }

    public Task getItemAtIndex (int index){
        return this.listItems.get(index);
    }

    public void deleteItemAtIndex (int index) throws IOException {
        this.listItems.remove(index);
        fileHandler.deleteFromFile(this.listItems);
    }

    public void markAsDone() throws IOException {
        fileHandler.deleteFromFile(this.listItems);
    }

    public void unmark() throws IOException {
        fileHandler.deleteFromFile(this.listItems);
    }
}
