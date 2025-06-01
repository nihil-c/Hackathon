package model;

import exceptions.*;

import java.time.LocalDate;

/**
 * Rappresenta il ruolo di organizzatore di un utente.
 * Un organizzatore può creare hackathon.
 */
public class OrganizerRole implements Role {
    /**
     * Crea un nuovo hackathon e lo assegna all'utente organizzatore.
     * @param title titolo dell'hackathon
     * @param location luogo dell'hackathon
     * @param startDate data di inizio
     * @param endDate data di fine
     * @param maxParticipantNumber numero massimo partecipanti
     * @param maxTeamSize dimensione massima team
     * @param user utente organizzatore
     * @return nuovo oggetto Hackathon
     * @throws BlankFieldException se un campo è vuoto
     * @throws InvalidTimeWindowException se le date non sono valide
     * @throws InvalidIntegerParameterException se i limiti sono non validi
     */
    public Hackathon createHackathon(String title, String location, LocalDate startDate, LocalDate endDate, int maxParticipantNumber, int maxTeamSize, User user)
            throws BlankFieldException, InvalidTimeWindowException, InvalidIntegerParameterException {
        Hackathon newHackathon = new Hackathon(title, location, startDate, endDate, maxParticipantNumber, maxTeamSize, user);
        user.setHackathon(newHackathon);
        return newHackathon;
    }

    /**
     * Restituisce il nome del ruolo.
     * @return "Organizer"
     */
    @Override
    public String getRoleName() {
        return "Organizer";
    }
}
