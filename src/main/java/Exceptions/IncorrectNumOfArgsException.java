package Exceptions;

public class IncorrectNumOfArgsException extends CommandException {
    public IncorrectNumOfArgsException() {
        super();
    }

    public IncorrectNumOfArgsException(String message) {
        super(message);
    }

    public IncorrectNumOfArgsException(String message, Exception cause) {
        super(message, cause);
    }
}
