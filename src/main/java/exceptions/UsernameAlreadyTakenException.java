package exceptions;

/**
 * Eccezione lanciata quando il nome utente scelto è già utilizzato da un altro utente.
 * <p>
 * Serve a garantire l'unicità degli username nel sistema.
 * </p>
 */
public class UsernameAlreadyTakenException extends Exception {
    public UsernameAlreadyTakenException() {
        super("Username is already taken.");
    }

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
