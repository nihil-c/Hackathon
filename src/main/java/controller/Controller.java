package controller;

import exceptions.*;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    // Attributi
    User currentUser;
    List<User> users;
    List<Hackathon> hackathons;

    // Costruttore
    public Controller() {
        users = new ArrayList<>();
        hackathons = new ArrayList<>();
    }

    // Metodi pubblici
    public void registerUser(String username, String email, String password)
            throws BlankFieldException, UsernameAlreadyTakenException, EmailAlreadyTakenException {
        if (username.isBlank() || email.isBlank() || password.isBlank()) throw new BlankFieldException();

        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                throw new UsernameAlreadyTakenException();
            }
            if (u.getEmail().equalsIgnoreCase(email)) {
                throw new EmailAlreadyTakenException();
            }
        }

        User newUser = new User(username, email, password);
        users.add(newUser);
    }

    public void loginUser(String username, String password)
            throws BlankFieldException, UserNotFoundException {
        if (username == null || password == null) throw new NullPointerException();
        if (username.isBlank() || password.isBlank()) throw new BlankFieldException();

        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equalsIgnoreCase(password)) {
                currentUser = u;
                return;
            }
        }

        throw new UserNotFoundException();
    }

    public void addHackathon(Hackathon hackathon) {
        hackathons.add(hackathon);
    }

    public void registerUserToHackathon(Hackathon hackathon, User user)
            throws UserIsAParticipantException, UserIsAnOrganizerException, UserIsAJudgeException, CannotRegisterToEventException, MaxLimitReachedException {
        if (user.getRole() instanceof OrganizerRole) throw new UserIsAnOrganizerException();
        if (user.getRole() instanceof JudgeRole) throw new UserIsAJudgeException();
        if (!LocalDate.now().isBefore(hackathon.getRegistrationDeadline())) throw new CannotRegisterToEventException();

        hackathon.addParticipant(currentUser);
        user.setRole(new ParticipantRole());
        user.setHackathon(hackathon);
    }

    // Getter & Setter
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }

        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Hackathon> getHackathons() {
        return hackathons;
    }

    public void setHackathons(List<Hackathon> hackathons) {
        this.hackathons = hackathons;
    }
}
