package leo.functions.task;

import java.time.LocalDateTime;

import leo.util.DateTimeParser;

/**
 * Represents a Deadline task.
 */
public class Deadline extends DateTask {
    private String deadline;

    /**
     * Instantiates a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description, deadline);
        assert description != null && !description.isEmpty() : "Description must not be null or empty";
        assert deadline != null && !deadline.isEmpty() : "Deadline must not be null or empty";

        this.deadline = DateTimeParser.formatDateTimeFromInput(deadline);
    }

    /**
     * Get the deadline of the task.
     *
     * @return DateTime object of the task's deadline.
     */
    public LocalDateTime getDateTime() {
        return DateTimeParser.stringToDateTime(this.deadline);
    }

    /**
     * Returns the formatted string of the task to be written to a file.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String appendToFile() {
        assert description != null && !description.isEmpty() : "Description must not be null or empty";
        assert deadline != null && !deadline.isEmpty() : "Deadline must not be null or empty";

        return String.format(
                "Deadline | "
                + this.getStatusIcon()
                + " | "
                + this.description
                + " | "
                + this.deadline
        );
    }

    /**
     * Returns a formatted string of the task to be written to the UI.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String toString() {
        assert description != null && !description.isEmpty() : "Description must not be null or empty";
        assert deadline != null && !deadline.isEmpty() : "Deadline must not be null or empty";

        return String.format(
                "[D]["
                + this.getStatusIcon()
                + "] "
                + this.description
                + " (by: "
                + this.deadline
                + ")"
        );
    }
}
