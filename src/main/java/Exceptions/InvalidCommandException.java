package Exceptions;

public class InvalidCommandException extends CommandException {
    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String message, Exception cause) {
        super(message, cause);
    }

}
