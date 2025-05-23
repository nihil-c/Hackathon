package controller;

import exceptions.*;
import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private User currentUser;
    private HashMap<String, User> users; // L'HashMap permette di creare un collegamento univoco tra Stringa (username) e Oggetto (User)
    private ArrayList<Hackathon> hackathons;

    public Controller() {
        this.users = new HashMap<>();
        this.hackathons = new ArrayList<>();
    }

    public void registerUser(String username, String email, String password)
            throws EmptyFieldException, EmailAlreadyInUseException, UsernameAlreadyTakenException {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new EmptyFieldException();
        }

        if (users.containsKey(username.toLowerCase())) {
            throw new UsernameAlreadyTakenException();
        }

        for (User u : users.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                throw new EmailAlreadyInUseException();
            }
        }

        users.put(username.toLowerCase(), new User(username, email, password));
    }


    public void loginUser(String username, String password)
            throws EmptyFieldException, IncorrectPasswordException, UserNotFoundException {

        if (username.isEmpty() || password.isEmpty()) {
            throw new EmptyFieldException();
        }

        User user = users.get(username.toLowerCase());

        if (user == null) {
            throw new UserNotFoundException();
        }

        if (!user.getPassword().equals(password)) {
            throw new IncorrectPasswordException();
        }

        this.currentUser = user;
    }


    public void logoutUser() {
        this.currentUser = null;
    }

    public void assignRoleToCurrentUser(String role) {
        switch (role.toLowerCase()) {
            case "participant":
                currentUser.setRole(new ParticipantRole(currentUser));
                break;
            case "organizer":
                currentUser.setRole(new OrganizerRole(currentUser));
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

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public ArrayList<Hackathon> getHackathons() {
        return hackathons;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users.values());
    }
}
