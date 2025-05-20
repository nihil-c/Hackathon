package model;

import java.time.LocalDate;

public class Hackathon {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Organizer organizer;

    public Hackathon(String title, LocalDate startDate, LocalDate endDate, Organizer organizer) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizer = organizer;
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
}
