package exceptions;

/**
 * Eccezione lanciata quando la password inserita è errata.
 * <p>
 * Tipicamente usata nei processi di login o verifica dell'identità.
 * </p>
 */
public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Incorrect password.");
    }
}
