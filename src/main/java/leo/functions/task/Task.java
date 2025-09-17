package leo.functions.task;

/**
 * The Task superclass provides common functionality for different types of tasks.
 */
public class Task {
    protected String description;
    private boolean isDone;

    /**
     * Instantiates a task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.isEmpty() : "Task description must not be null or empty";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return The completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the formatted string of the task to be written to a file.
     *
     * @return A formatted string of the task.
     */
    public String appendToFile() {
        assert this.description != null && !this.description.isEmpty() : "Task description must not be null or empty";

        return String.format(
                "Task | "
                + this.getStatusIcon()
                + " | "
                + this.description
        );
    }

    /**
     * Returns a formatted string of the task to be written to the UI.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String toString() {
        assert this.description != null && !this.description.isEmpty() : "Task description must not be null or empty";

        return String.format(
                "["
                + this.getStatusIcon()
                + "] " + this.description
        );
    }
}
