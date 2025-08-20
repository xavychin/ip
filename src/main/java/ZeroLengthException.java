//Solution adapted from https://www.perplexity.ai/search/how-to-create-a-custom-excepti-Y_RyDVATSjKGxzSDx1HUeg
public class ZeroLengthException extends Exception{
    public ZeroLengthException(){
        super();
    }

    public ZeroLengthException(String errorMessage){
        super(errorMessage);
    }
}
