package exceptions;

public class AlreadyTakenEmailException extends Exception {
    public AlreadyTakenEmailException() {
        super("Email already taken.");
    }
}
