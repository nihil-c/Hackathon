package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hackathon {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Organizer organizer;
    private ArrayList<Participant> participants;

    public Hackathon(String title, LocalDate startDate, LocalDate endDate, Organizer organizer) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizer = organizer;
        this.participants = new ArrayList<>();
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void addParticipant(Participant participant) throws Exception {
        if (participants.contains(participant)) {
            throw new Exception();
        }

        participants.add(participant);
    }
}
