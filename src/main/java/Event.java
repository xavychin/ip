public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate){
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString(){
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
