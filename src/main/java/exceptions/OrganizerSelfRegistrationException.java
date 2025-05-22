package exceptions;

public class OrganizerSelfRegistrationException extends Exception {
    public OrganizerSelfRegistrationException() {
        super("Organizer tries to register to own event.");
    }

    public OrganizerSelfRegistrationException(String message) {
        super(message);
    }
}
