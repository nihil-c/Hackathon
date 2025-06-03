package model;

import exceptions.AlreadyPartOfATeamException;

/**
 * Rappresenta il ruolo di partecipante di un utente.
 * Un partecipante può creare un team e farne parte.
 */
public class ParticipantRole implements Role {
    private Team team;

    /**
     * Costruttore di default. Il partecipante non ha ancora un team.
     */
    public ParticipantRole() {
        team = null;
    }

    /**
     * Restituisce il nome del ruolo.
     * @return "Participant"
     */
    @Override
    public String getRoleName() {
        return "Participant";
    }

    /**
     * Crea un nuovo team e aggiunge l'utente come membro.
     * @param user utente che crea il team
     * @param hackathon hackathon di riferimento
     * @param teamName nome del team
     * @param accessCode codice di accesso
     * @throws AlreadyPartOfATeamException se l'utente è già in un team
     */
    public void createTeam(User user, Hackathon hackathon, String teamName, String accessCode) throws AlreadyPartOfATeamException {
        Team newTeam = new Team(hackathon, teamName, accessCode);
        this.team = newTeam;
        newTeam.addMember(user);
        hackathon.addTeam(newTeam);
    }

    /**
     * Permette a un partecipante di effettuare un upload per il proprio team.
     * @param user l'utente che effettua l'upload
     * @param title titolo dell'upload
     * @param url url del progetto caricato
     */
    public void upload(User user, String title, String url) {
        if (team != null && team.getMembers().contains(user)) {
            Upload upload = new Upload(title, url, user);
            upload.setFeedback(null); // opzionale, feedback vuoto
            team.addUpload(upload);
        }
    }

    /**
     * Restituisce il team associato al partecipante.
     * @return team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Imposta il team associato al partecipante.
     * @param team nuovo team
     */
    public void setTeam(Team team) {
        this.team = team;
    }
}
