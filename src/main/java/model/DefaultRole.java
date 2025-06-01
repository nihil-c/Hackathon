package model;

/**
 * Rappresenta il ruolo di default di un utente.
 * Un utente con questo ruolo non ha privilegi speciali.
 */
public class DefaultRole implements Role {
    /**
     * Restituisce il nome del ruolo.
     * @return "User"
     */
    @Override
    public String getRoleName() {
        return "User";
    }
}
