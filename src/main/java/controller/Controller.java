package controller;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private User currentUser;
    private ArrayList<User> users;
    private ArrayList<Hackathon> hackathons;

    public Controller() {
        this.users = new ArrayList<>();
        this.hackathons = new ArrayList<>();
    }

    public void registerUser(String username, String email, String password) throws Exception {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new Exception("Missing user information.");
        }
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                throw new Exception("Username already taken.");
            }
        }
        users.add(new User(username, email, password));
    }

    public void loginUser(String username, String password) throws Exception {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(password)) {
                    this.currentUser = user;
                    return;
                } else {
                    throw new Exception("Incorrect password.");
                }
            }
        }
        throw new Exception("User not found.");
    }

    public void logoutUser() {
        this.currentUser = null;
    }

    public void assignRoleToCurrentUser(String role) {
        switch (role.toLowerCase()) {
            case "participant":
                currentUser.setRole(new ParticipantRole());
                break;
            case "organizer":
                currentUser.setRole(new OrganizerRole());
                break;
            default:
                throw new IllegalArgumentException("Invalid role.");
        }
    }

    public void createHackathon(String title, String location, LocalDate startDate, LocalDate endDate) throws Exception {
        Hackathon h = new Hackathon(title, location, startDate, endDate, currentUser);
        hackathons.add(h);
    }

    public User getCurrentUser() {
        return currentUser;
    }
    

    public ArrayList<Hackathon> getHackathons() {
        return hackathons;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
