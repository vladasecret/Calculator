package Exceptions;

public class CommandException extends Exception {
    public CommandException() {
        super();
    }
    public CommandException(String message) {

        super(message);
    }
    public CommandException(String message, Exception cause) {

        super(message, cause);
    }
}
