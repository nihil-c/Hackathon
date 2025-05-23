package exceptions;

public class InvalidOrganizerException extends Exception {
    public InvalidOrganizerException() {
        super("User is not an organizer.");
    }
}