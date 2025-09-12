package leo.functions.task;

import java.time.LocalDateTime;

import leo.util.DateTimeParser;

/**
 * The DateTask superclass provides common functionality for different types of tasks that contain date and time.
 */
public class DateTask extends Task {
    private String date;
    /**
     * Instantiates a task that contains a date.
     *
     * @param description The description of the task.
     */
    public DateTask(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Get the date and time of the task.
     *
     * @return DateTime object of the task's deadline.
     */
    public LocalDateTime getDateTime() {
        return DateTimeParser.stringToDateTime(this.date);
    };
}
