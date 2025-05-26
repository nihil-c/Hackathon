package model;

import exceptions.AlreadyOrganizingException;

import java.time.LocalDate;

public class Organizer implements Role {
    // Metodi pubblici
    public Hackathon createHackathon(String title, String location, LocalDate startDate, LocalDate endDate, int maxParticipantNumber, int maxTeamSize, User organizer) throws Exception {
        // Eccezioni
        if (organizer.getHackathon() != null) throw new AlreadyOrganizingException();

        // Assegnazioni
        Hackathon newHackathon = new Hackathon(title, location, startDate, endDate, maxParticipantNumber, maxTeamSize, organizer);
        organizer.setHackathon(newHackathon);
        return newHackathon;
    }
}
