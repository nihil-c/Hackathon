package model;

/**
 * Rappresenta il ruolo di giudice di un utente.
 * Un giudice può valutare i progetti presentati durante un hackathon.
 */
public class JudgeRole implements Role {
    /**
     * Restituisce il nome del ruolo.
     * @return "Judge"
     */
    @Override
    public String getRoleName() {
        return "Judge";
    }
}
