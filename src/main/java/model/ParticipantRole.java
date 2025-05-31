package model;

import exceptions.AlreadyPartOfATeamException;

public class ParticipantRole implements Role {
    private Team team;

    public ParticipantRole() {
        team = null;
    }

    @Override
    public String getRoleName() {
        return "Participant";
    }

    public void createTeam(User user, Hackathon hackathon, String teamName, String accessCode) throws AlreadyPartOfATeamException {
        Team newTeam = new Team(hackathon, teamName, accessCode);
        this.team = newTeam;
        newTeam.addMember(user);
        hackathon.addTeam(newTeam);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}