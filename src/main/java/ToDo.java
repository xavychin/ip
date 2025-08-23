public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public String appendToFile() {
        return String.format("ToDo | " + this.getStatusIcon() + " | " + this.description);
    }

    @Override
    public String toString(){
        return String.format("[T][" + this.getStatusIcon() + "] " + this.description);
    }
}
