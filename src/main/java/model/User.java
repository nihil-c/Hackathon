package model;

import exceptions.BlankFieldException;

/**
 * Rappresenta un utente del sistema.
 * Un utente può avere diversi ruoli e partecipare a un hackathon.
 */
public class User {
    // Attributi
    private String username;
    private String email;
    private String password;
    private Role role;
    private Hackathon hackathon;

    /**
     * Costruisce un nuovo utente con username, email e password.
     * @param username nome utente
     * @param email email dell'utente
     * @param password password dell'utente
     * @throws NullPointerException se uno dei parametri è null
     * @throws BlankFieldException se uno dei parametri è vuoto
     */
    public User(String username, String email, String password)
            throws NullPointerException, BlankFieldException {
        // Eccezioni
        if (username == null || email == null || password == null) throw new NullPointerException();
        if (username.isBlank() || email.isBlank() || password.isBlank()) throw new BlankFieldException();

        // Assegnazioni
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = new DefaultRole();
    }

    /**
     * Restituisce lo username dell'utente.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Imposta lo username dell'utente.
     * @param username nuovo username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Restituisce l'email dell'utente.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email dell'utente.
     * @param email nuova email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password dell'utente.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     * @param password nuova password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce il ruolo dell'utente.
     * @return ruolo
     */
    public Role getRole() {
        return role;
    }

    /**
     * Imposta il ruolo dell'utente.
     * @param role nuovo ruolo
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Restituisce l'hackathon associato all'utente.
     * @return hackathon
     */
    public Hackathon getHackathon() {
        return hackathon;
    }

    /**
     * Imposta l'hackathon associato all'utente.
     * @param hackathon nuovo hackathon
     */
    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    /**
     * Restituisce una rappresentazione testuale dell'utente.
     * @return stringa con lo username preceduto da @
     */
    @Override
    public String toString() {
        return "@" + username;
    }
}

