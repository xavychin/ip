import java.io.IOException;

public class Leo {
    private FileHandler fileHandler;
    private UI ui;
    private TaskList tasks;
    private Functions functions;

    public Leo(String filePath) {
        this.ui = new UI();
        this.fileHandler = new FileHandler(filePath);
        try {
            this.tasks = new TaskList(fileHandler.loadFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        this.functions = new Functions(this.tasks);
        this.ui.getUserInput(this.functions);
    }

    public static void main(String[] args) {
        new Leo("../.data/Leo.txt").run();
    }
}