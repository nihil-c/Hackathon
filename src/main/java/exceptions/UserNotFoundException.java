package exceptions;

/**
 * Eccezione lanciata quando non viene trovato alcun utente corrispondente ai criteri di ricerca.
 * <p>
 * Utile nelle operazioni di login, recupero password o gestione profili.
 * </p>
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not found.");
    }
}
