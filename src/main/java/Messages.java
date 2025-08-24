public class Messages {
    private static String name = "Leo";
    private static String logo = " __\n"
            + "|  |     _____   _____\n"
            + "|  |    /  __ \\ /  _  \\\n"
            + "|  |___ |  ___/ | |_| |\n"
            + "|______|\\_____| \\_____/\n";

    public static void Greetings(){
        System.out.println("Hello! I'm");
        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    public static void Goodbye(){
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void MessageBreak(){
        StringBuilder MsgBreak = new StringBuilder();
        for(int i=0; i<72; i++){
            MsgBreak.append("-");
        }
        System.out.println(MsgBreak);
    }

    public static void TaskCount(int count){
        System.out.println("Now you have " + count + " task(s) in the list.");
    }

    public static void addTask(){
        System.out.println("Got it. I've added this task:");
    }
}
