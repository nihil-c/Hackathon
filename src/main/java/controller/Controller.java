package controller;

import exceptions.EmailAlreadyInUseException;
import exceptions.EmptyFieldException;
import exceptions.UsernameAlreadyTakenException;
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

    public void registerUser(String username, String email, String password)
            throws EmptyFieldException, EmailAlreadyInUseException, UsernameAlreadyTakenException {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new EmptyFieldException();
        }
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                throw new UsernameAlreadyTakenException();
            }

            if (u.getEmail().equalsIgnoreCase(email)) {
                throw new EmailAlreadyInUseException();
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

    public void addHackathonToList(Hackathon hackathon) {
        hackathons.add(hackathon);
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
