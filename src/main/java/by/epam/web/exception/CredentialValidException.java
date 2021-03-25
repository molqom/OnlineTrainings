package by.epam.web.exception;

public class CredentialValidException extends Exception {
    public CredentialValidException() {
        super();
    }

    public CredentialValidException(String message) {
        super(message);
    }

    public CredentialValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CredentialValidException(Throwable cause) {
        super(cause);
    }
}
