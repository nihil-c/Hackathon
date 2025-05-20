package exceptions;

public class UsernameAlreadyTakenException extends Exception {
    public UsernameAlreadyTakenException() {
        super("Username is already taken.");
    }

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
