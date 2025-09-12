package leo.functions.task;

import java.time.LocalDateTime;

import leo.util.DateTimeParser;

/**
 * Represents an Event task.
 */
public class Event extends DateTask {
    private String startDate;
    private String endDate;

    /**
     * Instantiates an Event task.
     *
     * @param description The description of the deadline task.
     * @param startDate The start date and time of the task.
     * @param endDate The end date and time of the task.
     */
    public Event(String description, String startDate, String endDate) {
        super(description, startDate);

        assert description != null && !description.isEmpty() : "Description must not be null or empty";
        assert startDate != null && !startDate.isEmpty() : "Start date must not be null or empty";
        assert endDate != null && !endDate.isEmpty() : "End date must not be null or empty";

        this.startDate = DateTimeParser.formatDateTimeFromInput(startDate);
        this.endDate = DateTimeParser.formatDateTimeFromInput(endDate);
    }

    /**
     * Get the start date of the task.
     *
     * @return DateTime object of the task's start date.
     */
    public LocalDateTime getDateTime() {
        return DateTimeParser.stringToDateTime(this.startDate);
    }

    /**
     * Returns the formatted string of the task to be written to a file.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String appendToFile() {
        assert description != null && !description.isEmpty() : "Description must not be null or empty";
        assert startDate != null && !startDate.isEmpty() : "Start date must not be null or empty";
        assert endDate != null && !endDate.isEmpty() : "End date must not be null or empty";

        return String.format(
                "Event | "
                + this.getStatusIcon()
                + " | "
                + this.description
                + " | "
                + this.startDate
                + "-"
                + this.endDate
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
        assert startDate != null && !startDate.isEmpty() : "Start date must not be null or empty";
        assert endDate != null && !endDate.isEmpty() : "End date must not be null or empty";

        return String.format(
                "[E]["
                + this.getStatusIcon()
                + "] "
                + this.description
                + " (from: "
                + this.startDate
                + " to: "
                + this.endDate
                + ")"
        );
    }
}
