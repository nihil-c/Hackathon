package exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Incorrect password.");
    }
}