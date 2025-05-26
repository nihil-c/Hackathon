package model;

import exceptions.BlankFieldException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hackathon {
    // Attributi
    private boolean isOpen;
    private String title;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationDeadline;
    private LocalDate creationDate;
    private int maxParticipantNumber;
    private int maxTeamSize;
    User organizer;

    List<User> participants;
    List<Team> teams;

    // Costruttore
    public Hackathon(String title, String location, LocalDate startDate, LocalDate endDate, int maxParticipantNumber, int maxTeamSize, User organizer) throws Exception {
        // Eccezioni
        if (title == null || location == null || organizer == null || startDate == null || endDate == null) throw new Exception();
        if (title.isBlank() || location.isBlank() || startDate.toString().isBlank() || endDate.toString().isBlank()) throw new BlankFieldException();
        if (endDate.isBefore(startDate)) throw new Exception();
        if (maxParticipantNumber <= 0 || maxTeamSize <= 0) throw new Exception();

        // Assegnazioni
        this.isOpen = false;
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = startDate.minusDays(2);
        this.maxParticipantNumber = maxParticipantNumber;
        this.maxTeamSize = maxTeamSize;
        this.organizer = organizer;

        this.participants = new ArrayList<>();
        this.teams = new ArrayList<>();
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

    public int getMaxParticipantNumber() {
        return maxParticipantNumber;
    }

    public void setMaxParticipantNumber(int maxParticipantNumber) {
        this.maxParticipantNumber = maxParticipantNumber;
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
}
