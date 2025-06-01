package model;

/**
 * Interfaccia che rappresenta un ruolo utente nel sistema.
 * Ogni ruolo deve fornire il proprio nome.
 */
public interface Role {
    /**
     * Restituisce il nome del ruolo.
     * @return nome del ruolo
     */
    String getRoleName();
}
