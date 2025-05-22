package exceptions;

public class AlreadyOrganizingAnotherEventException extends Exception {
    public AlreadyOrganizingAnotherEventException() {
        super("Organizer is trying to register to another event.");
    }

    public AlreadyOrganizingAnotherEventException(String message) {
        super(message);
    }
}
