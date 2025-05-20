package controller;

import exceptions.*;
import model.*;

import java.lang.reflect.Constructor;
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

    // Getter
    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Hackathon> getHackathons() {
        return hackathons;
    }

    public void registerUser(String username, String email, String password)
            throws EmptyFieldException, UsernameAlreadyTakenException, EmailAlreadyInUseException {
        if (username == null || username.isBlank() || email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new EmptyFieldException();
        }

        for (User user : users) {
            if (username.equalsIgnoreCase(user.getUsername())) {
                throw new UsernameAlreadyTakenException();
            }

            if (email.equalsIgnoreCase(user.getEmail())) {
                throw new EmailAlreadyInUseException();
            }
        }

        User newUser = new User(username, email, password);
        users.add(newUser);
    }

    public void loginUser(String username, String password)
            throws EmptyFieldException, UserNotFoundException, IncorrectPasswordException {

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new EmptyFieldException();
        }

        for (User user : users) {
            if (username.equalsIgnoreCase(user.getUsername())) {
                if (password.equals(user.getPassword())) {
                    this.currentUser = user;
                    return;
                } else {
                    throw new IncorrectPasswordException();
                }
            }
        }

        throw new UserNotFoundException();
    }

    public void logoutUser() {
        currentUser = null;
    }

    public User changeUserRole(String role) {
        User newUser = null;

        switch (role.toLowerCase()) {
            case "organizer":
                newUser = new Organizer(currentUser.getUsername(), currentUser.getEmail(), currentUser.getPassword());
                break;
            case "participant":
                newUser = new Participant(currentUser.getUsername(), currentUser.getEmail(), currentUser.getPassword());
                break;
            case "judge":
                // newUser = new Judge(currentUser.getUsername(), currentUser.getEmail());
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }

        int index = users.indexOf(currentUser);
        if (index != -1) {
            users.set(index, newUser);
            currentUser = newUser;
            return newUser;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void addHackathon(Hackathon hackathon) {
        hackathons.add(hackathon);
    }

    public void registerParticipantToHackathon(Participant participant, Hackathon hackathon) throws Exception {
        hackathon.addParticipant(participant);
    }

}
