package exceptions;

/**
 * Eccezione lanciata quando uno o più campi obbligatori risultano vuoti.
 * <p>
 * Utile in fase di validazione dei form per garantire l'inserimento completo dei dati.
 * </p>
 */
public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("All fields must be filled.");
    }

    public EmptyFieldException(String message) {
        super(message);
    }
}
