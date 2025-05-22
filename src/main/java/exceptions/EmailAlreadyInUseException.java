package exceptions;

/**
 * Eccezione lanciata quando un indirizzo email è già associato a un altro account.
 * <p>
 * Utile in fase di registrazione o aggiornamento profilo per evitare duplicazioni
 * di email già utilizzate da altri utenti nel sistema.
 * </p>
 */
public class EmailAlreadyInUseException extends Exception {

    /**
     * Inizializza l'eccezione con un messaggio di default:
     * "Email is already in use."
     * </p>
     */
    public EmailAlreadyInUseException() {
        super("Email is already in use.");
    }

    /**
     * Costruttore che accetta un messaggio personalizzato.
     *
     * @param message Il messaggio dettagliato dell'eccezione.
     */
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
