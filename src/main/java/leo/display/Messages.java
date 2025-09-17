package leo.display;

import leo.exceptions.InputException;

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
     * Outputs the initial message to the GUI to prompt for user input.
     *
     * @return The initial message to prompt for user input.
     */
    public static String greetings() throws InputException {
        String greeting = "Hello! I'm "
                + name
                + "\n"
                + "How can I help you?";
        System.out.println(greeting);
        return greeting;
    }

    /**
     * Outputs the final message to the user before the application closes.
     *
     * @return The final message.
     */
    public static String goodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
        return goodbyeMessage;
    }

    /**
     * Outputs a line to improve readability between messages.
     */
    public static void messageBreak() {
        StringBuilder msgBreak = new StringBuilder();

        for (int i = 0; i < 72; i++) {
            msgBreak.append("-");
        }

        System.out.println(msgBreak);
    }

    /**
     * Outputs the current number of tasks stored in the list.
     *
     * @param count Number of tasks stored in the list.
     * @return String containing count of task in list.
     */
    public static String taskCount(int count) {
        String taskCountMessage = "Now you have "
                + count
                + " task(s) in the list.";
        System.out.println(taskCountMessage);
        return taskCountMessage;
    }

    /**
     * Outputs a confirmation after a task was successfully
     * added to the list.
     *
     * @return A confirmation message.
     */
    public static String addTask() {
        String addTaskMessage = "Got it. I've added this task:";
        System.out.println(addTaskMessage);
        return addTaskMessage;
    }
}
