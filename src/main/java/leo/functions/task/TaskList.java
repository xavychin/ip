package leo.functions.task;

import java.util.ArrayList;
import java.util.Arrays;

import leo.exceptions.FileHandlerException;
import leo.exceptions.InputException;
import leo.util.FileHandler;

/**
 * The TaskList class provides utility methods to manage list management operations
 * such as:
 * - adding task to files
 * - getting the number of tasks in the file
 * - getting the task at a specified index in the list
 * - deleting a task in the list
 * - marking a task in the list as completed
 * - marking a task in the list as not completed
 */
public class TaskList {
    private FileHandler fileHandler;
    private ArrayList<Task> listItems;

    /**
     * Instantiates a TaskList.
     *
     * @param fileHandler FileHandler to facilitate file operations.
     * @throws InputException If file is in the wrong format or cannot be accessed.
     * @throws ArrayIndexOutOfBoundsException If task details are in the incorrect format.
     */
    public TaskList(FileHandler fileHandler) throws InputException, FileHandlerException {
        this.fileHandler = fileHandler;
        assert fileHandler != null : "Objet to handle files is empty";
        this.listItems = fileHandler.retrieveTasksFromFile();
        assert listItems != null : "List of tasks should not be null after retrieval";
    }

    /**
     * Adds a task to the list and the file.
     *
     * @param task Task to be added to the list and the file.
     * @throws InputException If file is in the wrong format or cannot be accessed.
     */
    public void addTask(Task task) throws InputException {
        assert task != null : "Task to add must not be null";

        fileHandler.appendToFile(task.appendToFile());
        listItems.add(task);
    }

    /**
     * Get the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public Integer getSize() {
        return this.listItems.size();
    }

    /**
     * Get the task at the specified index.
     *
     * @param index Index of the specified task.
     * @return The task at the specified index.
     */
    public Task getItemAtIndex(int index) {
        assert index >= 0 && index < listItems.size() : "Index is out of bounds";

        return this.listItems.get(index);
    }

    /**
     * Get the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.listItems;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Index of the specified task.
     * @throws InputException If file is in the wrong format or cannot be accessed.
     */
    public void deleteItemAtIndex(int index) throws InputException {
        assert index >= 0 && index < listItems.size() : "Index is out of bounds";

        this.listItems.remove(index);
        fileHandler.overwriteFile(this.listItems);
    }

    /**
     * Marks the task as completed.
     *
     * @throws InputException If file is in the wrong format or cannot be accessed.
     */
    public void markTask() throws InputException {
        assert listItems != null : "List of tasks should not be null";
        fileHandler.overwriteFile(this.listItems);
    }

    /**
     * Marks the task as not completed.
     *
     * @throws InputException If file is in the wrong format or cannot be accessed.
     */
    public void unmarkTask() throws InputException {
        assert listItems != null : "List of tasks should not be null";
        fileHandler.overwriteFile(this.listItems);
    }

    /**
     * Check if the list of tasks already contains the task being added.
     *
     * @param args Details of the task being added.
     * @return True if the task is already in the list, else false.
     */
    //Solution adapted from https://www.perplexity.ai/search/public-boolean-checkduplicate-3Iy5_eQJTyO9Zdvedfhr9g#0
    public boolean checkDuplicate(String... args) {
        for (Task task : listItems) {
            boolean hasDuplicate = Arrays.stream(args).allMatch(task.toString()::contains);
            if (hasDuplicate) {
                return true;
            }
        }

        return false;
    }
}
