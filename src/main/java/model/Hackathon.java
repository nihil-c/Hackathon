package model;

import exceptions.AlreadyRegisteredToHackathonException;
import exceptions.InvalidOrganizerException;
import exceptions.MissingHackathonDataException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hackathon {
    private String title;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationDeadline;
    private String problemStatement;
    private User organizer;
    private ArrayList<User> participants;
    private ArrayList<User> judges;
    private ArrayList<Team> teams;
    private ArrayList<Team> ranking;

    public Hackathon(String title, String location, LocalDate startDate, LocalDate endDate, User organizer)
            throws MissingHackathonDataException, InvalidOrganizerException {

        if (title == null || title.isEmpty() ||
                location == null || location.isEmpty() ||
                startDate == null || endDate == null || organizer == null) {
            throw new MissingHackathonDataException();
        }

        if (!(organizer.getRoleInstance() instanceof OrganizerRole)) {
            throw new InvalidOrganizerException();
        }

        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = startDate.minusDays(2);
        this.organizer = organizer;

        this.participants = new ArrayList<>();
        this.judges = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.ranking = new ArrayList<>();
    }

    public void addParticipant(User user) throws AlreadyRegisteredToHackathonException {
        if (!participants.contains(user)) {
            participants.add(user);
        } else {
            throw new AlreadyRegisteredToHackathonException();
        }
    }

    public void addTeam(Team team) throws AlreadyRegisteredToHackathonException {
        if (!teams.contains(team)) {
            teams.add(team);
        } else {
            throw new AlreadyRegisteredToHackathonException();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    public User getOrganizer() {
        return organizer;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }
}
