package Leo;

import Leo.Display.Messages;
import Leo.Display.UI;
import Leo.Functions.Functions;
import Leo.Functions.Task.TaskList;

import java.io.IOException;

/**
 * Represents the chatbot application Leo.
 */
public class Leo {
    private FileHandler fileHandler;
    private UI ui;
    private TaskList tasks;
    private Functions functions;

    /**
     * Instantiates the chatbot Leo.
     *
     * @param filePath The file path where the data will be stored.
     * @throws IOException If file path is incorrect and a new file cannot be created
     * due to input errors.
     */
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

    /**
     * Starts Leo to prompt user for input.
     */
    public void run() {
        this.functions = new Functions(this.tasks);
        this.ui.getUserInput(this.functions);
    }

    /**
     * The main entry point for the chatbot application.
     *
     * @param args Command line input
     * @throws IOException If file fails to load
     */
    public static void main(String[] args) throws IOException {
        new Leo(".data/Leo.txt").run();
    }
}