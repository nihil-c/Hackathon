package model;

import java.time.LocalDate;

/**
 * Rappresenta un upload (consegna) effettuato da un team durante un hackathon.
 * Ogni upload contiene titolo, url, data di creazione e feedback.
 */
public class Upload {
    private String title;
    private String url;
    private LocalDate creationDate;
    private String feedback;

    /**
     * Costruisce un nuovo upload con titolo e url.
     * La data di creazione viene impostata automaticamente a oggi.
     * @param title titolo dell'upload
     * @param url url del file caricato
     */
    public Upload(String title, String url) {
        this.title = title;
        this.url = url;
        this.creationDate = LocalDate.now();
        this.feedback = null;
    }

    /**
     * Restituisce il titolo dell'upload.
     * @return titolo
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il titolo dell'upload.
     * @param title nuovo titolo
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Restituisce l'URL dell'upload.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Imposta l'URL dell'upload.
     * @param url nuovo url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Restituisce la data di creazione dell'upload.
     * @return data di creazione
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Imposta la data di creazione dell'upload.
     * @param creationDate nuova data di creazione
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Restituisce il feedback associato all'upload.
     * @return feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Imposta il feedback associato all'upload.
     * @param feedback nuovo feedback
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
