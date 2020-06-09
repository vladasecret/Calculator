package Exceptions;

public class BadParamException extends CommandException {
    public BadParamException() {
        super();
    }

    public BadParamException(String message) {
        super(message);
    }

    public BadParamException(String message, Exception cause) {
        super(message, cause);
    }
}
