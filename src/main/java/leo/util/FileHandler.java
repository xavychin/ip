package leo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import leo.exceptions.FileHandlerException;
import leo.exceptions.InputException;
import leo.functions.task.Deadline;
import leo.functions.task.Event;
import leo.functions.task.Task;
import leo.functions.task.ToDo;

/**
 * The FileHandler class provides utility methods to manage file operations
 * such as:
 * - loading data from files
 * - writing data to files
 * - deleting data from files
 * - retrieving data from files
 */
public class FileHandler {
    private static final String TODO_TASK_NAME = "ToDo";
    private static final String DEADLINE_TASK_NAME = "Deadline";
    private static final String EVENT_TASK_NAME = "Event";
    private static final String COMPLETED_SYMBOL = "X";
    private File file;

    /**
     * Instantiates the FileHandler object.
     *
     * @param filePath The file path used for carrying out file operations.
     */
    public FileHandler(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path must not be null or empty";
        this.file = new File(filePath);
    }

    /**
     * Loads the file used to carry out file operations.
     *
     * @return The FileHandler object.
     * @throws InputException If file fails to create due to input errors.
     */
    public FileHandler loadFile() throws InputException {
        //Solution adapted from https://www.perplexity.ai/search/jar-file-not-working-BkZmiAErSv6Q80JHsA1TMw#16
        File parentDirectory = file.getParentFile();
        boolean isCreatedDirectory = false;
        boolean isCreatedFile = false;
        if (parentDirectory != null && !parentDirectory.exists()) {
            isCreatedDirectory = parentDirectory.mkdirs();
        } else {
            isCreatedDirectory = true;
        }
        if (!this.file.exists()) {
            try {
                isCreatedFile = this.file.createNewFile();
            } catch (IOException e) {
                throw new InputException("create");
            }

        } else {
            isCreatedFile = true;
        }

        assert isCreatedDirectory : "Directory should be created";
        assert isCreatedFile : "File should be created";

        return this;
    }

    /**
     * Writes data to the file.
     *
     * @param textToAdd Text to be written to the file.
     * @throws InputException If file is in the wrong format or cannot be accessed.
     */
    public void appendToFile(String textToAdd) throws InputException {
        assert textToAdd != null : "Text to add must not be null";

        try (FileWriter writer = new FileWriter(this.file, true)) {
            if (this.file.length() > 0) {
                //Solution adapted from
                // https://www.perplexity.ai/search/add-new-line-infront-of-text-i-zPpF4r0fTCSRDpGFnPRUxg
                writer.write(System.lineSeparator() + textToAdd);
            } else {
                writer.write(textToAdd);
            }
        } catch (IOException e) {
            throw new InputException("path");
        }
    }

    /**
     * Overwrites data in the file with an updated list of tasks.
     *
     * @param listItems Remaining list of tasks after update.
     * @throws InputException If file is in the wrong format or cannot be accessed.
     */
    //Solution adapted from https://www.perplexity.ai/search/delete-text-from-file-in-java-8_mCJnSyQZmnkscaHNiuUw
    public void overwriteFile(ArrayList<Task> listItems) throws InputException {
        assert listItems != null : "List of tasks must not be null";

        try {
            FileWriter writer = new FileWriter(this.file, false);

            for (int i = 0; i < listItems.size(); i++) {
                if (i == 0) {
                    writer.write(listItems.get(i).appendToFile());
                } else {
                    writer.write(System.lineSeparator() + listItems.get(i).appendToFile());
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new InputException("path");
        }
    }

    /**
     * Retrieves and returns the list of tasks from the file.
     *
     * @return List of tasks.
     * @throws InputException If file is in the wrong format or cannot be accessed.
     * @throws FileHandlerException If task details are in the incorrect format.
     */
    //Solution adapted from https://www.perplexity.ai/search/retrieves-and-returns-the-list-abAmOhH_QFC599ZDVGzXmA
    public ArrayList<Task> retrieveTasksFromFile() throws InputException {
        ArrayList<Task> listItems = new ArrayList<>();
        assert this.file != null : "File must not be null";
        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (!nextLine.isEmpty()) {
                    try {
                        Task task = getTaskFromFile(nextLine);
                        if (task != null) {
                            listItems.add(task);
                        }
                    } catch (FileHandlerException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new InputException("path");
        }

        return listItems;
    }

    /**
     * Returns a task from the file.
     *
     * @param taskFromFile Text information of task stored in the file.
     * @return Task object.
     * @throws FileHandlerException If task details are in the incorrect format.
     */
    private Task getTaskFromFile(String taskFromFile) throws FileHandlerException {
        assert taskFromFile != null && !taskFromFile.isEmpty() : "Task from file must not be null or empty";

        Task task = null;
        try {
            //Solution adapted from https://www.perplexity.ai/search/split-string-by-in-java-U7_N33gYS4651R96jeoK8Q
            String[] taskDescriptionList = taskFromFile.split("\\|");
            String taskDescription = taskDescriptionList[2].trim();

            switch(taskDescriptionList[0].trim()) {
            case TODO_TASK_NAME:
                task = new ToDo(taskDescription);
                break;
            case EVENT_TASK_NAME:
                String[] eventDates = taskDescriptionList[3].split("-");
                String startDateTime = DateTimeParser.formatDateTimeFromFile(eventDates[0].trim());
                String endDateTime = DateTimeParser.formatDateTimeFromFile(eventDates[1].trim());
                task = new Event(taskDescription, startDateTime, endDateTime);
                break;
            case DEADLINE_TASK_NAME:
                //Solution adapted from
                // https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
                String dateTime = DateTimeParser.formatDateTimeFromFile(taskDescriptionList[3].trim());
                task = new Deadline(taskDescription, dateTime);
                break;
            default:
                break;
            }

            if (task != null && taskDescriptionList[1].trim().equals(COMPLETED_SYMBOL)) {
                task.markTask();
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FileHandlerException(taskFromFile);
        }

        return task;
    }
}
