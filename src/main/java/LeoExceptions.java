//Solution adapted from https://www.perplexity.ai/search/how-to-create-a-custom-excepti-Y_RyDVATSjKGxzSDx1HUeg
public class LeoExceptions extends Exception{
    public LeoExceptions(){
        super();
    }

    public LeoExceptions(String errorMessage){
        super(errorMessage);
    }
}
