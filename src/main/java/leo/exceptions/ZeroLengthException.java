package leo.exceptions;

/**
 * The ZeroLengthException class provides a custom exception
 * for cases where the length of an object cannot be zero.
 */
//Solution adapted from https://www.perplexity.ai/search/how-to-create-a-custom-excepti-Y_RyDVATSjKGxzSDx1HUeg
public class ZeroLengthException extends Exception {
    /**
     * Instantiates a ZeroLengthException with a custom error message.
     */
    public ZeroLengthException() {
        super("The list is empty...");
    }
}
