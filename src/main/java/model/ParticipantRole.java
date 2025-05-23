package model;

import exceptions.AlreadyHaveATeamException;
import exceptions.AlreadyRegisteredToHackathonException;
import exceptions.TeamDoesNotExistException;

public class ParticipantRole implements Role {
    private User user;
    private Team team;

    public ParticipantRole(User user) {
        this.user = user;
        this.team = null;
    }

    @Override
    public String getRoleName() {
        return "Participant";
    }

    public void createTeam(Hackathon hackathon, String name) throws AlreadyRegisteredToHackathonException {
        Team newTeam = new Team(name);

        try {
            hackathon.addTeam(team);
        } catch (AlreadyRegisteredToHackathonException ex) {
            throw new AlreadyRegisteredToHackathonException();
        }
    }

    public void joinTeam(Hackathon hackathon, Team team, String accessCode)
            throws TeamDoesNotExistException, AlreadyHaveATeamException {
        if (this.team == null) {
            if (hackathon.getTeams().contains(team)) {
                for (Team t : hackathon.getTeams()) {
                    if (t.getAccessCode().equalsIgnoreCase(accessCode)) {
                        t.addMember(this.user);
                    }
                }
            } else {
                throw new TeamDoesNotExistException();
            }
        } else {
            throw new AlreadyHaveATeamException();
        }
    }
}