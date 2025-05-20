package model;

public class Organizer extends User { public Organizer(String username, String email, String password) {
        super(username, email, password);
    }

    @Override
    public String getRole() {
        return "ORGANIZER";
    }
}
