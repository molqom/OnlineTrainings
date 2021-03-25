package by.epam.web.exception;

public class IllegalCommandException extends Exception{
    public IllegalCommandException() {
        super();
    }

    public IllegalCommandException(String message) {
        super(message);
    }

    public IllegalCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
