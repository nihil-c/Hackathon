package model;

import java.time.LocalDate;

public class Upload {
    private String title;
    private String url;
    private LocalDate uploadDate;
    private User uploadedBy;
    private String feedback;

    public Upload(String title, String url, LocalDate uploadDate, User uploadedBy) {
        this.title = title;
        this.url = url;
        this.uploadDate = uploadDate;
        this.uploadedBy = uploadedBy;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
