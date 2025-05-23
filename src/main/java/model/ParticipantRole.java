package model;

import exceptions.AlreadyHaveATeamException;
import exceptions.AlreadyRegisteredToHackathonException;
import exceptions.InvalidAccessCodeException;
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
        newTeam.addMember(this.user);  // aggiunge il creatore al team
        hackathon.addTeam(newTeam);    // aggiunge il team all'hackathon
        this.team = newTeam;           // memorizza il team in questo ruolo
    }


    public void joinTeam(Hackathon hackathon, Team team, String accessCode)
            throws TeamDoesNotExistException, AlreadyHaveATeamException, InvalidAccessCodeException {

        if (this.team != null) {
            throw new AlreadyHaveATeamException();
        }

        if (!hackathon.getTeams().contains(team)) {
            throw new TeamDoesNotExistException();
        }

        if (!team.getAccessCode().equals(accessCode)) {
            throw new InvalidAccessCodeException();
        }

        team.addMember(this.user); // aggiunge l'utente al team
        this.team = team;          // aggiorna il riferimento interno al team
    }

}