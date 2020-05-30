package Exceptions;

public class StackEmptyException extends CommandException {
    public StackEmptyException() {
        super();
    }

    public StackEmptyException(String message) {
        super(message);
    }

    public StackEmptyException(String message, Exception cause) {
        super(message, cause);
    }
}
