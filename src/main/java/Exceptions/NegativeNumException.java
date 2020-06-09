package Exceptions;

public class NegativeNumException extends CommandException{
    public NegativeNumException() {
        super();
    }

    public NegativeNumException(String message) {
        super(message);
    }

    public NegativeNumException(String message, Exception cause) {
        super(message, cause);
    }
}
