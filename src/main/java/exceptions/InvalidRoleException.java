package exceptions;

public class InvalidRoleException extends Exception {
    public InvalidRoleException() {
        super("Invalid role.");
    }

    public InvalidRoleException(String message) {
        super(message);
    }
}
