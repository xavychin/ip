package Leo;

import Leo.Functions.Task.Deadline;
import Leo.Functions.Task.Event;
import Leo.Functions.Task.Task;
import Leo.Functions.Task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
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
        if (!this.file.exists()) {
            this.file.createNewFile();
        }

        return this;
    }

    public void appendToFile(String textToAdd) throws IOException{
        try{
            FileWriter writer = new FileWriter(this.file, true);

            if(this.file.length() > 0){
                //Solution adapted from https://www.perplexity.ai/search/add-new-line-infront-of-text-i-zPpF4r0fTCSRDpGFnPRUxg
                writer.write(System.lineSeparator() + textToAdd);
            } else {
                writer.write(textToAdd);
            }

            writer.close();
        } catch (IOException e) {
            throw new IOException("Invalid file path given");
        }
    }

    //Solution adapted from https://www.perplexity.ai/search/delete-text-from-file-in-java-8_mCJnSyQZmnkscaHNiuUw
    public void deleteFromFile(ArrayList<Task> listItems) throws IOException {
        try {
            FileWriter writer = new FileWriter(this.file, false);

            for (int i=0; i<listItems.size(); i++) {
                if (i == 0) {
                    writer.write(listItems.get(i).appendToFile());
                } else {
                    writer.write(System.lineSeparator() + listItems.get(i).appendToFile());
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Invalid file path given");
        }
    }

    public ArrayList<Task> retrieveTasksFromFile() throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        ArrayList<Task> listItems = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);

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
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Invalid file path given");
        }

        return listItems;
    }

    private Task getTaskFromFile(String lineFromFile) throws ArrayIndexOutOfBoundsException {
        Task task = null;
        try {
            //Solution adapted from https://www.perplexity.ai/search/split-string-by-in-java-U7_N33gYS4651R96jeoK8Q
            String[] taskDescriptionList = lineFromFile.split("\\|");
            String taskDescription = taskDescriptionList[2].trim();

            DateTimeParser dateTimeParser = new DateTimeParser();

            switch(taskDescriptionList[0].trim()){
                case "Leo.Functions.Task.ToDo":
                    task = new ToDo(taskDescription);
                    break;
                case "Leo.Functions.Task.Event":
                    String[] eventDates = taskDescriptionList[3].split("-");
                    String startDateTime = dateTimeParser.formatDateTimeFromFile(eventDates[0].trim());
                    String endDateTime = dateTimeParser.formatDateTimeFromFile(eventDates[1].trim());
                    task = new Event(taskDescription, startDateTime, endDateTime);
                    break;
                case "Leo.Functions.Task.Deadline":
                    //Solution adapted from https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
                    String dateTime = dateTimeParser.formatDateTimeFromFile(taskDescriptionList[3].trim());
                    task = new Deadline(taskDescription, dateTime);
                    break;
            }

            if(task != null && taskDescriptionList[1].trim().equals("X")) {
                task.markAsDone();
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Failed to load the task ("
                    + lineFromFile
                    + ") due to invalid format.");
        }

        return task;
    }
}
