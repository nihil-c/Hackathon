package model;

import exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hackathon {
    // Attributi
    private boolean isOpen;
    private String title;
    private String location;
    private String problemStatement;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationDeadline;
    private LocalDate creationDate;
    private int maxParticipants;
    private int maxTeamSize;
    User organizer;

    List<User> participants;
    List<Team> teams;
    List<Team> ranking;

    // Costruttore
    public Hackathon(String title, String location, LocalDate startDate, LocalDate endDate, int maxParticipants, int maxTeamSize, User organizer)
            throws BlankFieldException, InvalidTimeWindowException, InvalidIntegerParameterException {
        // Eccezioni
        if (title.isBlank() || location.isBlank() || startDate.toString().isBlank() || endDate.toString().isBlank()) throw new BlankFieldException();
        if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(startDate)) throw new InvalidTimeWindowException();
        if (maxParticipants <= 0 || maxTeamSize <= 0) throw new InvalidIntegerParameterException();

        // Assegnazioni
        this.title = title;
        this.location = location;
        this.problemStatement = null;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = startDate.minusDays(2);
        this.maxParticipants = maxParticipants;
        this.maxTeamSize = maxTeamSize;
        this.organizer = organizer;
        this.isOpen = LocalDate.now().isBefore(registrationDeadline) || LocalDate.now().isAfter(endDate);

        this.participants = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.ranking = new ArrayList<>();
    }

    public void addParticipant(User user) throws UserIsAParticipantException, MaxLimitReachedException {
        if (participants.contains(user)) throw new UserIsAParticipantException();
        if (participants.size() == maxParticipants) throw new MaxLimitReachedException();

        participants.add(user);
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    // Getter & Setter
    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProblemStatement() {
        return problemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(LocalDate registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    public void setMaxTeamSize(int maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getRanking() {
        return ranking;
    }

    public void setRanking(List<Team> ranking) {
        this.ranking = ranking;
    }
}
