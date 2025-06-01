package model;

import exceptions.AlreadyPartOfATeamException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Rappresenta un team partecipante a un hackathon.
 * Un team ha un nome, un codice di accesso, un punteggio, membri e upload associati.
 */
public class Team {
    Hackathon hackathon;
    String teamName;
    String accessCode;
    int score;
    List<User> members;
    List<Upload> uploads;

    /**
     * Costruisce un nuovo team con hackathon e nome.
     * Il codice di accesso viene generato automaticamente.
     * @param hackathon hackathon di riferimento
     * @param teamName nome del team
     */
    public Team(Hackathon hackathon, String teamName) {
        this.hackathon = hackathon;
        this.teamName = teamName;
        this.accessCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.score = -1;
        this.members = new ArrayList<>();
        this.uploads = new ArrayList<>();
    }

    /**
     * Costruisce un nuovo team con hackathon, nome e codice di accesso specificato.
     * @param hackathon hackathon di riferimento
     * @param teamName nome del team
     * @param accessCode codice di accesso
     */
    public Team(Hackathon hackathon, String teamName, String accessCode) {
        this.hackathon = hackathon;
        this.teamName = teamName;
        this.accessCode = accessCode;
        this.score = -1;
        this.members = new ArrayList<>();
        this.uploads = new ArrayList<>();
    }

    /**
     * Aggiunge un membro al team.
     * @param user utente da aggiungere
     * @throws AlreadyPartOfATeamException se l'utente è già nel team
     */
    public void addMember(User user) throws AlreadyPartOfATeamException {
        if (!members.contains(user)) {
            members.add(user);
        } else {
            throw new AlreadyPartOfATeamException();
        }
    }

    /**
     * Aggiunge un upload al team.
     * @param upload upload da aggiungere
     */
    public void addUpload(Upload upload) {
        uploads.add(upload);
    }

    /**
     * Restituisce il nome del team.
     * @return nome del team
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Imposta il nome del team.
     * @param teamName nuovo nome
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Restituisce il codice di accesso del team.
     * @return codice di accesso
     */
    public String getAccessCode() {
        return accessCode;
    }

    /**
     * Imposta il codice di accesso del team.
     * @param accessCode nuovo codice
     */
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    /**
     * Restituisce il punteggio del team.
     * @return punteggio
     */
    public int getScore() {
        return score;
    }

    /**
     * Imposta il punteggio del team.
     * @param score nuovo punteggio
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Restituisce la lista dei membri del team.
     * @return lista membri
     */
    public List<User> getMembers() {
        return members;
    }

    /**
     * Imposta la lista dei membri del team.
     * @param members nuova lista membri
     */
    public void setMembers(List<User> members) {
        this.members = members;
    }

    /**
     * Restituisce la lista degli upload del team.
     * @return lista upload
     */
    public List<Upload> getUploads() {
        return uploads;
    }

    /**
     * Imposta la lista degli upload del team.
     * @param uploads nuova lista upload
     */
    public void setUploads(List<Upload> uploads) {
        this.uploads = uploads;
    }

    /**
     * Restituisce una rappresentazione testuale del team.
     * @return nome del team
     */
    @Override
    public String toString() {
        return teamName;
    }
}
