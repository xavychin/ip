import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate) throws DateTimeException{
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);

            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
            this.startDate = startDateTime.format(newFormat);
            this.endDate = endDateTime.format(newFormat);
        } catch (DateTimeException e) {
            throw new DateTimeException("Incorrect date ot time format for /from or /to..." +
                    "\n\tIt should be <dd/MM/yyyy HHmm>");
        }
    }

    @Override
    public String appendToFile() {
        return String.format("Event | " + this.getStatusIcon() + " | " + this.description + " | " + this.startDate + "-" + this.endDate);
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
