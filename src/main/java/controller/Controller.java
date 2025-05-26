package controller;

import exceptions.AlreadyRegisteredUserException;
import exceptions.BlankFieldException;
import exceptions.UserNotFoundException;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    // Attributi
    User currentUser;

    List<User> users;

    // Costruttore
    public Controller() {
        users = new ArrayList<>();
    }

    // Metodi pubblici
    public void registerUser(String username, String email, String password) throws Exception {
        User newUser = new User(username, email, password);

        if (users.contains(newUser)) throw new AlreadyRegisteredUserException();

        users.add(newUser);
    }

    public void loginUser(String username, String password)
            throws BlankFieldException, UserNotFoundException {
        if (username == null || password == null) throw new NullPointerException();
        if (username.isBlank() || password.isBlank()) throw new BlankFieldException();

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                return;
            }
        }

        throw new UserNotFoundException();
    }
}
