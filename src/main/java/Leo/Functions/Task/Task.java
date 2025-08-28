package Leo.Functions.Task;

public class Task {
    protected String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmark(){
        this.isDone = false;
    }

    public String appendToFile(){
        return String.format("Leo.Functions.Task | "
                + this.getStatusIcon()
                + " | "
                + this.description);
    }

    @Override
    public String toString(){
        return String.format("[" + this.getStatusIcon() + "] " + this.description);
    }
}
