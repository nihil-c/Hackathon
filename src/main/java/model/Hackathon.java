package model;

import exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un hackathon, evento con partecipanti, team e una classifica.
 * Contiene informazioni su titolo, luogo, date, limiti e partecipanti.
 */
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

    /**
     * Costruisce un nuovo hackathon con i parametri principali.
     * @param title titolo
     * @param location luogo
     * @param startDate data di inizio
     * @param endDate data di fine
     * @param maxParticipants numero massimo partecipanti
     * @param maxTeamSize dimensione massima team
     * @param organizer utente organizzatore
     * @throws BlankFieldException se un campo è vuoto
     * @throws InvalidTimeWindowException se le date non sono valide
     * @throws InvalidIntegerParameterException se i limiti sono non validi
     */
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

    /**
     * Aggiunge un partecipante all'hackathon.
     * @param user utente da aggiungere
     * @throws UserIsAParticipantException se l'utente è già iscritto
     * @throws MaxLimitReachedException se è stato raggiunto il limite massimo
     */
    public void addParticipant(User user) throws UserIsAParticipantException, MaxLimitReachedException {
        if (participants.contains(user)) throw new UserIsAParticipantException();
        if (participants.size() == maxParticipants) throw new MaxLimitReachedException();

        participants.add(user);
    }

    /**
     * Aggiunge un team all'hackathon.
     * @param team team da aggiungere
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /**
     * Indica se l'hackathon è aperto alle iscrizioni.
     * @return true se aperto
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Imposta se l'hackathon è aperto alle iscrizioni.
     * @param open true se aperto
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }

    /**
     * Restituisce il titolo dell'hackathon.
     * @return titolo
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il titolo dell'hackathon.
     * @param title nuovo titolo
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Restituisce il luogo dell'hackathon.
     * @return luogo
     */
    public String getLocation() {
        return location;
    }

    /**
     * Imposta il luogo dell'hackathon.
     * @param location nuovo luogo
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Restituisce la descrizione del problema.
     * @return problem statement
     */
    public String getProblemStatement() {
        return problemStatement;
    }

    /**
     * Imposta la descrizione del problema.
     * @param problemStatement nuova descrizione
     */
    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    /**
     * Restituisce la data di inizio.
     * @return data di inizio
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Imposta la data di inizio.
     * @param startDate nuova data
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Restituisce la data di fine.
     * @return data di fine
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Imposta la data di fine.
     * @param endDate nuova data
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Restituisce la scadenza iscrizioni.
     * @return scadenza iscrizioni
     */
    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    /**
     * Imposta la scadenza iscrizioni.
     * @param registrationDeadline nuova scadenza
     */
    public void setRegistrationDeadline(LocalDate registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    /**
     * Restituisce la data di creazione.
     * @return data di creazione
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Imposta la data di creazione.
     * @param creationDate nuova data
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Restituisce il numero massimo di partecipanti.
     * @return max partecipanti
     */
    public int getMaxParticipants() {
        return maxParticipants;
    }

    /**
     * Imposta il numero massimo di partecipanti.
     * @param maxParticipants nuovo limite
     */
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    /**
     * Restituisce la dimensione massima dei team.
     * @return max team size
     */
    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    /**
     * Imposta la dimensione massima dei team.
     * @param maxTeamSize nuovo limite
     */
    public void setMaxTeamSize(int maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }

    /**
     * Restituisce l'organizzatore dell'hackathon.
     * @return organizzatore
     */
    public User getOrganizer() {
        return organizer;
    }

    /**
     * Imposta l'organizzatore dell'hackathon.
     * @param organizer nuovo organizzatore
     */
    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    /**
     * Restituisce la lista dei partecipanti.
     * @return lista partecipanti
     */
    public List<User> getParticipants() {
        return participants;
    }

    /**
     * Imposta la lista dei partecipanti.
     * @param participants nuova lista
     */
    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    /**
     * Restituisce la lista dei team.
     * @return lista team
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * Imposta la lista dei team.
     * @param teams nuova lista
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * Restituisce la classifica dei team.
     * @return classifica
     */
    public List<Team> getRanking() {
        return ranking;
    }

    /**
     * Imposta la classifica dei team.
     * @param ranking nuova classifica
     */
    public void setRanking(List<Team> ranking) {
        this.ranking = ranking;
    }
}
