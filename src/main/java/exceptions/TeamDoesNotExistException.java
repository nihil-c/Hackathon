package exceptions;

public class TeamDoesNotExistException extends Exception {
    public TeamDoesNotExistException() {
        super("The team does not exist.");
    }
}
