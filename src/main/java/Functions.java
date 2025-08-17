public class Functions {
    Messages message = new Messages();
    public void SearchFunctions(String funcName){
        switch(funcName){
            case "list":
                this.list();
                break;
            case "blah":
                this.blah();
                break;
            case "bye":
                message.Goodbye();
                break;
            default:
                System.out.println("\tI don't know how to do this...");
                break;
        }
    }

    public void list(){
        System.out.println("\tlist");
    }

    public void blah(){
        System.out.println("\tblah");
    }
}
