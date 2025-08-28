package Leo;

import Leo.Display.Messages;
import Leo.Display.UI;
import Leo.Functions.Functions;
import Leo.Functions.Task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Leo {
    private FileHandler fileHandler;
    private UI ui;
    private TaskList tasks;
    private Functions functions;

    public Leo(String filePath) throws IOException {
        this.ui = new UI();
        this.fileHandler = new FileHandler(filePath);
        try {
            this.tasks = new TaskList(fileHandler.loadFile());
        } catch (IOException e) {
            throw new IOException("File not found and failed to create.");
        } catch (ArrayIndexOutOfBoundsException e) {
            Messages.MessageBreak();
            System.out.println(e.getMessage());
            Messages.MessageBreak();
        }
    }

    public void run() {
        this.functions = new Functions(this.tasks);
        this.ui.getUserInput(this.functions);
    }

    public static void main(String[] args) throws IOException {
        new Leo(".data/Leo.txt").run();
    }
}