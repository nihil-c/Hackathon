package exceptions;

/**
 * Eccezione lanciata quando un partecipante è già registrato a un altro hackathon.
 * <p>
 * Questa eccezione serve per evitare che un partecipante si iscriva contemporaneamente
 * a più hackathon, violando le regole del sistema.
 * </p>
 */
public class AlreadyRegisteredToHackathonException extends Exception {

    /**
     * Inizializza l'eccezione con un messaggio di default:
     * "Participant is already registered in another hackathon."
     * </p>
     */
    public AlreadyRegisteredToHackathonException() {
        super("Participant is already registered in another hackathon.");
    }

    /**
     * Costruttore che accetta un messaggio personalizzato.
     *
     * @param message Il messaggio dettagliato dell'eccezione.
     */
    public AlreadyRegisteredToHackathonException(String message) {
        super(message);
    }
}

