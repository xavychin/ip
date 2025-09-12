package leo;

import java.io.IOException;

import leo.display.MainWindow;
import leo.display.Messages;
import leo.display.UI;
import leo.functions.Functions;
import leo.functions.commands.ReminderCommand;
import leo.functions.task.TaskList;
import leo.util.FileHandler;

/**
 * Represents the chatbot application Leo.
 */
public class Leo {
    private FileHandler fileHandler;
    private UI ui;
    private TaskList tasks;
    private Functions functions;
    private MainWindow controller;

    /**
     * Instantiates the chatbot Leo.
     *
     * @param filePath The file path where the data will be stored.
     * @throws IOException If file path is incorrect and a new file cannot be created
     *      due to input errors.
     */
    public Leo(String filePath, MainWindow controller) throws IOException {
        assert filePath != null && !filePath.isEmpty() : "File path must not be null or empty";
        this.ui = new UI();
        this.controller = controller;
        this.fileHandler = new FileHandler(filePath);

        assert this.fileHandler != null : "FileHandler must be initialized";

        try {
            this.tasks = new TaskList(fileHandler.loadFile());
            assert this.tasks != null : "TaskList must be initialized";

            controller.leoMessage(Messages.greetings());
            controller.leoMessage(ReminderCommand.remindCommand("", this.tasks));

        } catch (IOException e) {
            throw new IOException("File not found and failed to create.");
        } catch (ArrayIndexOutOfBoundsException e) {
            Messages.messageBreak();
            System.out.println(e.getMessage());
            Messages.messageBreak();
        }
    }

    /**
     * Starts Leo to prompt user for input.
     */
    public void run() {
        assert this.tasks != null : "TaskList must be initialized before running";

        this.functions = new Functions(this.tasks);
        assert this.functions != null : "Functions must be initialized";

        this.ui.getUserInput(this.functions);
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param userInput Text containing the user input into the application.
     * @return String containing the response from the chatbot.
     */
    public String getResponse(String userInput) {
        assert userInput != null : "User input must not be null";

        if (userInput.isEmpty()) {
            return "Please enter a command for me to carry out.";
        }
        this.functions = new Functions(this.tasks);
        return this.ui.getLeoResponse(this.functions, userInput);
    }

    /**
     * The main entry point for the chatbot application.
     *
     * @param args Command line input
     * @throws IOException If file fails to load
     */
    public static void main(String[] args) throws IOException {
        new Leo("data/Leo.txt", null).run();
    }
}
