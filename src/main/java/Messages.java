public class Messages {
    private String name = "Leo";
    private String logo = " __\n"
            + "|  |     _____   _____\n"
            + "|  |    /  __ \\ /  _  \\\n"
            + "|  |___ |  ___/ | |_| |\n"
            + "|______|\\_____| \\_____/\n";

    public Messages(){
    }

    public void Greetings(){
        this.MessageBreak();
        System.out.println("Hello! I'm ");
        System.out.println(this.logo);
        System.out.println("What can I do for you?");
    }

    public void Goodbye(){
        this.MessageBreak();
        System.out.println("Bye. Hope to see you again soon!");
        this.MessageBreak();
    }

    public void MessageBreak(){
        StringBuilder MsgBreak = new StringBuilder();
        for(int i=0; i<72; i++){
            MsgBreak.append("-");
        }
        System.out.println(MsgBreak.toString());
    }
}
