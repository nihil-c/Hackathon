package model;

public class ParticipantRole implements Role {
    private Hackathon hackathon;

    public ParticipantRole() {
        this.hackathon = null;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    @Override
    public String getRoleName() {
        return "PARTICIPANT";
    }
}
