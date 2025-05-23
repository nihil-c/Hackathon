package exceptions;

public class AlreadyHaveATeamException extends Exception {
    public AlreadyHaveATeamException() {
        super("Participant already has a team.");
    }
}
