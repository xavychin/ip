package Leo.Functions.Task;

import Leo.Display.Messages;
import Leo.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private FileHandler fileHandler;
    private ArrayList<Task> listItems;

    public TaskList(FileHandler fileHandler) throws FileNotFoundException, ArrayIndexOutOfBoundsException{
        this.fileHandler = fileHandler;
        this.listItems = fileHandler.retrieveTasksFromFile();
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
