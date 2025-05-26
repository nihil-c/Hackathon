package model;

import exceptions.BlankFieldException;

public class User {
    // Attributi
    private String username;
    private String email;
    private String password;
    private Role role;

    private Hackathon hackathon;

    // Costruttore
    public User(String username, String email, String password) throws Exception {
        // Eccezioni
        if (username == null || email == null || password == null) throw new NullPointerException();
        if (username.isBlank() || email.isBlank() || password.isBlank()) throw new BlankFieldException();

        // Assegnazioni
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = null;
    }

    // Getter & Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }
}