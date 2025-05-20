package exceptions;

public class AlreadyRegisteredToHackathonException extends Exception {
    public AlreadyRegisteredToHackathonException() {
        super("Participant is already registered in another hackathon.");
    }

    public AlreadyRegisteredToHackathonException(String message) {
        super(message);
    }
}
