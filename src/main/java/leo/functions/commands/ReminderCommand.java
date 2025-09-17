package leo.functions.commands;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import leo.functions.task.DateTask;
import leo.functions.task.TaskList;
import leo.util.DateTimeParser;

/**
 * Represents the function that is called to notify the user about upcoming tasks.
 * Tasks are notified if they have less than or equal to 1 week until its deadline or until the event begins.
 */
public class ReminderCommand {
    /**
     * Reminds the user of tasks that are nearing the deadline or events that are approaching.
     *
     * @param listItems list of tasks.
     * @return String containing the tasks with approaching deadline or upcoming events.
     */
    public static String remindCommand(String userInput, TaskList listItems) {
        StringBuilder sb = new StringBuilder();

        List<DateTask> matched = ReminderCommand.getUpcomingTasks(listItems);

        if (userInput.isEmpty()) { //Message when application starts.
            if (!matched.isEmpty()) {
                sb.append("Just a reminder that you have some upcoming task(s):");
            }
        } else { //Message when command is called by user input.
            if (matched.isEmpty()) {
                sb.append("You have no upcoming tasks.");
            } else {
                sb.append("Your upcoming task(s) are:");
            }
        }

        for (int i = 0; i < matched.size(); i++) {
            sb.append("\n\t")
                    .append(i + 1)
                    .append(". ")
                    .append(matched.get(i).toString());
        }

        return sb.toString();
    }

    /**
     * Gets all tasks that have date that is less than or equal to one week away.
     *
     * @param listItems list of tasks.
     * @return The list of upcoming tasks.
     */
    private static List<DateTask> getUpcomingTasks(TaskList listItems) {
        LocalDateTime currentDateTime = DateTimeParser.getCurrentDateTime();

        return listItems.getTasks()
                .stream()
                .filter(task -> task instanceof DateTask)
                .map(task -> (DateTask) task)
                .filter(task -> task.getDateTime().isAfter(currentDateTime)
                        && task.getDateTime().isBefore(currentDateTime.plusWeeks(1)))
                .sorted(Comparator.comparing(DateTask::getDateTime))
                .toList();
    }
}
