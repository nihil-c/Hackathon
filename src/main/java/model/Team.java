package model;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private int teamSize;
    private String accessCode;
    private ArrayList<User> members;
    private int score;

    public Team(String teamName) {
        this.teamName = teamName;
        this.accessCode = "43%1oP()87";
        this.score = -1;
        this.members = new ArrayList<>();
    }

    public void addMember(User participant) {
        members.add(participant);
    }

    public void setTeamSize(int size) {
        this.teamSize = size;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getAccessCode() {
        return accessCode;
    }
}
