package model;

import exceptions.AlreadyPartOfATeamException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {
    String teamName;
    String accessCode;
    int score;

    List<User> members;
    List<Upload> uploads;

    public Team(String teamName) {
        this.teamName = teamName;
        this.accessCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.score = -1;
        this.members = new ArrayList<>();
        this.uploads = new ArrayList<>();
    }

    // Metodi pubblici
    public void addMember(User user) throws AlreadyPartOfATeamException {
        if (!members.contains(user)) {
            members.add(user);
        } else {
            throw new AlreadyPartOfATeamException();
        }
    }

    public void addUpload(Upload upload) {
        uploads.add(upload);
    }

    // Getter & Setter
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Upload> getUploads() {
        return uploads;
    }

    public void setUploads(List<Upload> uploads) {
        this.uploads = uploads;
    }
}
