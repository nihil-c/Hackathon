package exceptions;

/**
 * Eccezione lanciata quando il ruolo assegnato a un utente non è valido.
 * <p>
 * Può verificarsi durante la registrazione o l'assegnazione dei permessi.
 * </p>
 */
public class InvalidRoleException extends Exception {
    public InvalidRoleException() {
        super("Invalid role.");
    }

    public InvalidRoleException(String message) {
        super(message);
    }
}
