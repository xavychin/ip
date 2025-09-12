package leo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import leo.exceptions.DateTimeFormatException;
import leo.util.DateTimeParser;

public class DateTimeParserTest {
    @Test
    public void dateTimeParser_correctStringFormatFromUser_success() {
        assertEquals("Dec 12 2025, 1200",
                DateTimeParser.formatDateTimeFromInput("12/12/2025 1200"));
    }

    @Test
    public void dateTimeParser_correctStringFormatFromFile_success() {
        assertEquals("12/12/2025 1200",
                DateTimeParser.formatDateTimeFromFile("Dec 12 2025, 1200"));
    }

    @Test
    public void dateTimeParser_wrongDateFormatFromUser_exceptionThrown() {
        String error = "Incorrect date or time format for /from or /to..."
                + "\n\tIt should be <dd/MM/yyyy HHmm>";

        //Solution adapted from https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
        Exception exception = assertThrows(DateTimeFormatException.class, () -> {
            DateTimeParser.formatDateTimeFromInput("12-1-2025 1200");

        });
        assertEquals(error, exception.getMessage());
    }

    @Test
    public void dateTimeParser_wrongDateFormatFromFile_exceptionThrown() {
        String error = "Incorrect date or time format stored in the file...";

        //Solution adapted from https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
        Exception exception = assertThrows(DateTimeFormatException.class, () -> {
            DateTimeParser.formatDateTimeFromFile("DEC 12 2025, 1200");
        });
        assertEquals(error, exception.getMessage());
    }

    @Test
    public void dateTimeParser_misingCommaInDateFormatFromFile_exceptionThrown() {
        String error = "Incorrect date or time format stored in the file...";

        //Solution adapted from https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
        Exception exception = assertThrows(DateTimeFormatException.class, () -> {
            DateTimeParser.formatDateTimeFromFile("Dec 12 2025 1200");
        });
        assertEquals(error, exception.getMessage());
    }

    //Error test wrong time format for user input to task object field
    @Test
    public void dateTimeParser_wrongTimeFormatFromUser_exceptionThrown() {
        String error = "Incorrect date or time format for /from or /to..."
                + "\n\tIt should be <dd/MM/yyyy HHmm>";

        //Solution adapted from https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
        Exception exception = assertThrows(DateTimeFormatException.class, () -> {
            DateTimeParser.formatDateTimeFromInput("12-1-2025 12:00");
        });
        assertEquals(error, exception.getMessage());
    }

    @Test
    public void dateTimeParser_wrongTimeFormatFromFile_exceptionThrown() {
        String error = "Incorrect date or time format stored in the file...";

        //Solution adapted from https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
        Exception exception = assertThrows(DateTimeFormatException.class, () -> {
            DateTimeParser.formatDateTimeFromFile("Dec 12 2025, 12");
        });
        assertEquals(error, exception.getMessage());
    }
}
