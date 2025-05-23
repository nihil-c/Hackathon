package exceptions;

public class MissingHackathonDataException extends Exception {
    public MissingHackathonDataException() {
        super("Missing required hackathon data.");
    }
}
