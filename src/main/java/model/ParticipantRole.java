package model;

public class ParticipantRole implements Role {
    private Team team;

    @Override
    public String getRoleName() {
        return "Participant";
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}