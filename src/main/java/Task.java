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

    public String getDescription(){
        return this.description;
    }

    public String appendToFile(){
        return "";
    }

    @Override
    public String toString(){
        return String.format("[" + this.getStatusIcon() + "] " + this.description);
    }
}
