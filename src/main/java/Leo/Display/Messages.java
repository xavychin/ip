package Leo.Display;

/**
 * The Messages class contains generic messages to be used across the application.
 */
public class Messages {
    private static String name = "Leo";
    private static String logo = " __\n"
            + "|  |     _____   _____\n"
            + "|  |    /  __ \\ /  _  \\\n"
            + "|  |___ |  ___/ | |_| |\n"
            + "|______|\\_____| \\_____/\n";

    /**
     * Outputs the initial message to prompt for user input.
     */
    public static void Greetings(){
        System.out.println("Hello! I'm");
        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Outputs the final message to the user before the application closes.
     */
    public static void Goodbye(){
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Outputs a line to improve readability between messages.
     */
    public static void MessageBreak(){
        StringBuilder MsgBreak = new StringBuilder();
        for(int i=0; i<72; i++){
            MsgBreak.append("-");
        }
        System.out.println(MsgBreak);
    }

    /**
     * Outputs the current number of tasks stored in the list.
     *
     * @param count Number of tasks stored in the list.
     */
    public static void TaskCount(int count){
        System.out.println("Now you have " + count + " task(s) in the list.");
    }

    /**
     * Outputs a confirmation after a task was successfully
     * added to the list.
     */
    public static void addTask(){
        System.out.println("Got it. I've added this task:");
    }
}
