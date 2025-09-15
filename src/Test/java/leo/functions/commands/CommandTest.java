package leo.functions.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import leo.exceptions.AddTaskException;
import leo.exceptions.ZeroLengthException;
import leo.functions.task.Deadline;
import leo.functions.task.Event;
import leo.functions.task.TaskList;
import leo.functions.task.ToDo;
import leo.util.FileHandler;

public class CommandTest {
    private String testFilePath = ".dataTest/temp.txt";

    @Test
    public void command_addToDoTaskCommand_success() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);
            AddTaskCommand.todo("todo read book", taskList);

            Scanner scanner = new Scanner(tempFile);
            String task = scanner.nextLine();
            scanner.close();

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            assertEquals("ToDo |   | read book", task);
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_addToDoTaskCommandInvalidFormat_exceptionThrown() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            //Solution adapted from
            // https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
            Exception exception = assertThrows(AddTaskException.class, () -> {
                AddTaskCommand.todo("todo", taskList);
            });

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String error = "The description of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\ttodo <task description>";

            assertEquals(error, exception.getMessage());
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_addDeadlineTaskCommand_success() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);
            AddTaskCommand.deadline("deadline return book /by 12/01/2026 2000", taskList);

            Scanner scanner = new Scanner(tempFile);
            String task = scanner.nextLine();
            scanner.close();

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            assertEquals("Deadline |   | return book | Jan 12 2026, 2000", task);
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_addDeadlineTaskCommandInvalidFormat_exceptionThrown() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            //Solution adapted from
            // https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
            Exception exception = assertThrows(AddTaskException.class, () -> {
                AddTaskCommand.deadline("deadline", taskList);
            });

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String error = "The description or deadline of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tdeadline <task description> /by <dd/MM/yyyy HHmm>";

            assertEquals(error, exception.getMessage());
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_addEventTaskCommand_success() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);
            AddTaskCommand.event("event project meeting /from 02/03/2026 1000 /to 02/03/2026 1030", taskList);

            Scanner scanner = new Scanner(tempFile);
            String task = scanner.nextLine();
            scanner.close();

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            assertEquals("Event |   | project meeting | Mar 02 2026, 1000-Mar 02 2026, 1030", task);
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_addEventTaskCommandInvalidFormat_exceptionThrown() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            //Solution adapted from
            // https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
            Exception exception = assertThrows(AddTaskException.class, () -> {
                AddTaskCommand.event("event", taskList);
            });

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String error = "The description or timing of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tevent <task description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>";

            assertEquals(error, exception.getMessage());
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_deleteTaskCommand_success() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            taskList.addTask(new ToDo("return book"));
            taskList.addTask(new Deadline("return book", "12/01/2025 2000"));
            taskList.addTask(new Event("project meeting", "02/03/2024 1000", "02/03/2024 1030"));

            DeleteTaskCommand.deleteTask(3, taskList);

            Scanner scanner = new Scanner(tempFile);

            StringBuilder actual = new StringBuilder();
            while (scanner.hasNext()) {
                actual.append(scanner.nextLine());
            }
            scanner.close();

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            assertEquals(("ToDo |   | return book"
                            + "Deadline |   | return book | Jan 12 2025, 2000"),
                    actual.toString()
            );
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_deleteTaskCommandIndexOutOfBounds_exceptionThrown() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);
            FileWriter writer = new FileWriter(tempFile, false);
            writer.write("ToDo |   | return book"
                    + "Deadline |   | return book | Jan 12 2025, 2000"
                    + "Event |   | project meeting | Mar 02 2024, 1000-Mar 02 2024, 1030"
            );
            writer.close();

            String assertMessage = "";

            try {
                DeleteTaskCommand.deleteTask(4, taskList);
            } catch (AssertionError e) {
                assertMessage = e.getMessage();
            }

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String error = "Index is out of bounds";

            assertEquals(error, assertMessage);
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_listTaskCommand_success() {
        //Solution adapted from
        // https://www.perplexity.ai/search/inherit-static-class-88b6UsAsRy2XFwjK4cnE_g#3
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            taskList.addTask(new ToDo("return book"));
            taskList.addTask(new Deadline("return book", "12/01/2025 2000"));
            taskList.addTask(new Event("project meeting", "02/03/2024 1000", "02/03/2024 1030"));

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String expected = "Here are the tasks in your list:"
                + "\n\t1. [T][ ] return book"
                + "\n\t2. [D][ ] return book (by: Jan 12 2025, 2000)"
                + "\n\t3. [E][ ] project meeting (from: Mar 02 2024, 1000 to: Mar 02 2024, 1030)\n";

            ListTaskCommand.list(taskList);

            //Solution adapted from https://www.perplexity.ai/search/inherit-static-class-88b6UsAsRy2XFwjK4cnE_g#7
            String normalizedExpected = expected.replaceAll("\\r\\n?", "\n");
            String normalizedActual = outputStream.toString().replaceAll("\\r\\n?", "\n");

            assertEquals(
                    normalizedActual,
                    normalizedExpected

            );
        } catch (IOException | ZeroLengthException e) {
            System.out.println("Testing failed");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void command_listTaskCommandZeroLengthList_exceptionThrown() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String error = "The list is empty.";

            //Solution adapted from
            // https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
            Exception exception = assertThrows(ZeroLengthException.class, () -> {
                ListTaskCommand.list(taskList);
            });

            assertEquals(
                    error,
                    exception.getMessage()
            );
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_markTaskCommand_success() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            taskList.addTask(new ToDo("return book"));
            MarkTaskCommand.markTask(1, taskList);

            StringBuilder actual = new StringBuilder();

            Scanner scanner = new Scanner(tempFile);
            while (scanner.hasNext()) {
                actual.append(scanner.nextLine());
            }

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String expected = "ToDo | X | return book";

            assertEquals(expected, actual.toString());
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_markTaskCommandIndexOutOfBounds_exceptionThrown() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            taskList.addTask(new ToDo("return book"));
            String assertMessage = "";

            try {
                MarkTaskCommand.markTask(2, taskList);
            } catch (AssertionError e) {
                assertMessage = e.getMessage();
            }

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String error = "Index is out of bounds";

            assertEquals(error, assertMessage);
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_unmarkTaskTaskCommand_success() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            taskList.addTask(new ToDo("return book"));
            MarkTaskCommand.markTask(1, taskList);
            MarkTaskCommand.unmarkTask(1, taskList);

            StringBuilder actual = new StringBuilder();

            Scanner scanner = new Scanner(tempFile);
            while (scanner.hasNext()) {
                actual.append(scanner.nextLine());
            }

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String expected = "ToDo |   | return book";

            assertEquals(expected, actual.toString());
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void command_unmarkTaskTaskCommandIndexOutOfBounds_exceptionThrown() {
        try {
            File tempFile = new File(testFilePath);
            FileHandler fhTemp = new FileHandler(testFilePath);
            TaskList taskList = new TaskList(fhTemp);

            taskList.addTask(new ToDo("return book"));
            MarkTaskCommand.markTask(1, taskList);

            String assertMessage = "";

            try {
                MarkTaskCommand.unmarkTask(2, taskList);
            } catch (AssertionError e) {
                assertMessage = e.getMessage();
            }

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            String error = "Index is out of bounds";

            assertEquals(error, assertMessage);
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }
}
