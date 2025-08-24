import Display.Messages;
import Functions.Task.Deadline;
import Functions.Task.Event;
import Functions.Task.Task;
import Functions.Task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private File file;

    public FileHandler(String filePath) {
        this.file = new File(filePath);
    }

    public FileHandler loadFile() throws IOException {
        if (this.file.exists()) {
            if (this.file.createNewFile()) {
                System.out.print("File Created: " + this.file.getName());
            }
        }

        return this;
    }

    public void appendToFile(String textToAdd) throws IOException{
        FileWriter writer = new FileWriter(this.file, true);

        if(this.file.length() > 0){
            //Solution adapted from https://www.perplexity.ai/search/add-new-line-infront-of-text-i-zPpF4r0fTCSRDpGFnPRUxg
            writer.write(System.lineSeparator() + textToAdd);
        } else {
            writer.write(textToAdd);
        }

        writer.close();
    }

    //Solution adapted from https://www.perplexity.ai/search/delete-text-from-file-in-java-8_mCJnSyQZmnkscaHNiuUw
    public void deleteFromFile(ArrayList<Task> listItems) throws IOException {
        FileWriter writer = new FileWriter(this.file, false);

        for (int i=0; i<listItems.size(); i++) {
            if (i == 0) {
                writer.write(listItems.get(i).appendToFile());
            } else {
                writer.write(System.lineSeparator() + listItems.get(i).appendToFile());
            }
        }
        writer.close();
    }

    public ArrayList<Task> retrieveTasksFromFile() throws IOException{
        Scanner scanner = new Scanner(this.file);
        ArrayList<Task> listItems = new ArrayList<>();

        while(scanner.hasNext()){
            String nextLine = scanner.nextLine();
            if (!nextLine.isEmpty()) {
                Task task = getTaskFromFile(nextLine);

                if (task != null) {
                    listItems.add(task);
                }
            }
        }

        scanner.close();
        return listItems;
    }

    private Task getTaskFromFile(String lineFromFile) {
        Task task = null;
        try {
            //Solution adapted from https://www.perplexity.ai/search/split-string-by-in-java-U7_N33gYS4651R96jeoK8Q
            String[] taskDescriptionList = lineFromFile.split("\\|");
            String taskDescription = taskDescriptionList[2].trim();

            DateTimeParser dateTimeParser = new DateTimeParser();

            switch(taskDescriptionList[0].trim()){
                case "Functions.Task.ToDo":
                    task = new ToDo(taskDescription);
                    break;
                case "Functions.Task.Event":
                    String[] eventDates = taskDescriptionList[3].split("-");
                    String startDateTime = dateTimeParser.formatDateTimeFromFile(eventDates[0].trim());
                    String endDateTime = dateTimeParser.formatDateTimeFromFile(eventDates[1].trim());
                    task = new Event(taskDescription, startDateTime, endDateTime);
                    break;
                case "Functions.Task.Deadline":
                    //Solution adapted from https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
                    String dateTime = dateTimeParser.formatDateTimeFromFile(taskDescriptionList[3].trim());
                    task = new Deadline(taskDescription, dateTime);
                    break;
            }

            if(task != null && taskDescriptionList[1].trim().equals("X")) {
                task.markAsDone();
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            Messages.MessageBreak();
            System.out.println("Failed to load the task ("
                    + lineFromFile
                    + ") due to invalid format.");
            Messages.MessageBreak();
        }

        return task;
    }
}
