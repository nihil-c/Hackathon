package exceptions;

public class AlreadyTakenUsernameException extends Exception {
    public AlreadyTakenUsernameException() {
        super("Username already taken.");
    }
}
