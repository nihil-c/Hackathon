package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hackathon {
    private String title;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationDeadline;
    private User organizer;
    private ArrayList<User> participants;

    public Hackathon(String title, String location, LocalDate startDate, LocalDate endDate, User organizer) throws Exception {
        if (title.isEmpty() || location.isEmpty() || startDate == null || endDate == null || organizer == null) {
            throw new Exception("Missing required hackathon data.");
        }
        if (!(organizer.getRoleInstance() instanceof OrganizerRole)) {
            throw new Exception("User is not an organizer.");
        }
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = startDate.minusDays(2);
        this.organizer = organizer;
        this.participants = new ArrayList<>();
    }

    public void addParticipant(User user) throws Exception {
        if (!(user.getRoleInstance() instanceof ParticipantRole)) {
            throw new Exception("User is not a participant.");
        }
        if (participants.contains(user)) {
            throw new Exception("Already registered.");
        }
        participants.add(user);
        ((ParticipantRole) user.getRoleInstance()).setHackathon(this);
    }

    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public LocalDate getRegistrationDeadline() { return registrationDeadline; }
    public User getOrganizer() { return organizer; }
    public ArrayList<User> getParticipants() { return participants; }
}