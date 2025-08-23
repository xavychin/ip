import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    Messages message = new Messages();
    private final String FILEPATH = ".data/Leo.txt";

    public void checkFileExists() throws IOException{
        File file = new File(this.FILEPATH);
        file.createNewFile();
    }

    public void appendToFile(String textToAdd) throws IOException{
        FileWriter writer = new FileWriter(this.FILEPATH, true);
        File file = new File(this.FILEPATH);

        if(file.length() > 0){
            //Solution adapted from https://www.perplexity.ai/search/add-new-line-infront-of-text-i-zPpF4r0fTCSRDpGFnPRUxg
            writer.write(System.lineSeparator() + textToAdd);
        } else {
            writer.write(textToAdd);
        }

        writer.close();
    }

    //Solution adapted from https://www.perplexity.ai/search/delete-text-from-file-in-java-8_mCJnSyQZmnkscaHNiuUw
    public void deleteFromFile(ArrayList<Task> listItems) throws IOException {
        FileWriter writer = new FileWriter(this.FILEPATH, false);

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
        File file = new File(this.FILEPATH);
        Scanner scanner = new Scanner(file);
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

            switch(taskDescriptionList[0].trim()){
                case "ToDo":
                    task = new ToDo(taskDescription);
                    break;
                case "Event":
                    String[] eventDates = taskDescriptionList[3].split("-");
                    task = new Event(taskDescription, eventDates[0].trim(), eventDates[1].trim());
                    break;
                case "Deadline":
                    task = new Deadline(taskDescription, taskDescriptionList[3].trim());
                    break;
            }

            if(task != null && taskDescriptionList[1].trim().equals("X")) {
                task.markAsDone();
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            message.MessageBreak();
            System.out.println("Failed to load the task (" + lineFromFile + ") due to invalid format.");
            message.MessageBreak();
        }

        return task;
    }
}
