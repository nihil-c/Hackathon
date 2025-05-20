package exceptions;

public class EmailAlreadyInUseException extends Exception {
    public EmailAlreadyInUseException() {
        super("Email is already in use.");
    }

    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
