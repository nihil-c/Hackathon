package model;

import java.time.LocalDate;

public class Upload {
    private String title;
    private String url;
    private LocalDate creationDate;
    private String feedback;

    public Upload(String title, String url) {
        this.title = title;
        this.url = url;
        this.creationDate = LocalDate.now();
        this.feedback = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
