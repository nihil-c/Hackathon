package model;

public class ParticipantRole implements Role {
    private Team team;

    @Override
    public String getRoleName() {
        return "Participant";
    }
}