package exceptions;

public class AlreadyOrganizingException extends RuntimeException {
    public AlreadyOrganizingException() {
        super("User is already organizing another hackathon.");
    }
}
