package Exceptions;

public class DefineException extends CommandException {
    public DefineException() {
        super();
    }

    public DefineException(String message) {
        super(message);
    }

    public DefineException(String message, Exception cause) {
        super(message, cause);
    }
}
