import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String deadline;

    public Deadline(String description, String deadline) throws DateTimeException{
        super(description);

        try{
            //Solution adapted from https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
            this.deadline = dateTime.format(newFormat);
        } catch (DateTimeException e) {
            throw new DateTimeException("Incorrect date or time format for /from or /to..." +
                    "\n\tIt should be <dd/MM/yyyy HHmm>");
        }
    }

    @Override
    public String appendToFile() {
        return String.format("Deadline | " + this.getStatusIcon() + " | " + this.description + " | " + this.deadline);
    }

    @Override
    public String toString(){
        return String.format("[D][" + this.getStatusIcon() + "] "
                + this.description
                + " (by: "
                + this.deadline
                + ")");
    }
}
