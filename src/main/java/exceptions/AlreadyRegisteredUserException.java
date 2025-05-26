package exceptions;

public class AlreadyRegisteredUserException extends Exception {
    public AlreadyRegisteredUserException() {
        super("User already registered.");
    }
}
