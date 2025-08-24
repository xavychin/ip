package Functions.Task;

public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate){
        super(description);

        DateTimeParser dateTimeParser = new DateTimeParser();
        this.startDate = dateTimeParser.dateTimeFormatter(startDate);
        this.endDate = dateTimeParser.dateTimeFormatter(endDate);
    }

    @Override
    public String appendToFile() {
        return String.format("Functions.Task.Event | "
                + this.getStatusIcon()
                + " | "
                + this.description
                + " | "
                + this.startDate
                + "-"
                + this.endDate);
    }

    @Override
    public String toString() {
        return String.format("[E]["
                + this.getStatusIcon()
                + "] "
                + this.description
                + " (from: "
                + this.startDate
                + " to: "
                + this.endDate
                + ")");
    }
}
