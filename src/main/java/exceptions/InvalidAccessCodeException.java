package exceptions;

public class InvalidAccessCodeException extends Exception {
    public InvalidAccessCodeException() {
        super("Wrong access code.");
    }
}
