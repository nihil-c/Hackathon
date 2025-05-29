package model;

import exceptions.*;

import java.time.LocalDate;

public class OrganizerRole implements Role {
    // Metodi pubblici
    public Hackathon createHackathon(String title, String location, LocalDate startDate, LocalDate endDate, int maxParticipantNumber, int maxTeamSize, User user)
            throws BlankFieldException, InvalidTimeWindowException, InvalidIntegerParameterException {
        Hackathon newHackathon = new Hackathon(title, location, startDate, endDate, maxParticipantNumber, maxTeamSize, user);
        user.setHackathon(newHackathon);
        return newHackathon;
    }

    @Override
    public String getRoleName() {
        return "Organizer";
    }
}
