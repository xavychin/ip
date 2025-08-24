package Functions.Task;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline){
        super(description);

        DateTimeParser dateTimeParser = new DateTimeParser();
        this.deadline = dateTimeParser.dateTimeFormatter(deadline);
    }

    @Override
    public String appendToFile() {
        return String.format("Functions.Task.Deadline | "
                + this.getStatusIcon()
                + " | "
                + this.description
                + " | "
                + this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]["
                + this.getStatusIcon()
                + "] "
                + this.description
                + " (by: "
                + this.deadline
                + ")");
    }
}
