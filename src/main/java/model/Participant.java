package model;

public class Participant extends User {
    private Hackathon hackathon;

    public Participant(String username, String email, String password) {
        super(username, email, password);
        this.hackathon = null;
    }

    @Override
    public String getRole() {
        return "PARTICIPANT";
    }

    public Hackathon getHackathon() {
        return hackathon;
    }
}
