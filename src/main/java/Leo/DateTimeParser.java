package Leo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    public String dateTimeFormatter(String dateTimeToFormat) throws DateTimeException{
        try {
            //Solution adapted from https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, formatter);

            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
            return dateTime.format(newFormat);
        } catch (DateTimeException e) {
            throw new DateTimeException("Incorrect date ot time format for /from or /to..." +
                    "\n\tIt should be <dd/MM/yyyy HHmm>");
        }
    }

    public String formatDateTimeFromFile(String dateTimeToFormat) throws DateTimeException{
        //Solution adapted from https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, formatter);

        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(newFormat);
    }
}
