package model;

public class OrganizerRole implements Role {
    private User user;

    public OrganizerRole(User organizer) {
        this.user = organizer;
    }

    @Override
    public String getRoleName() {
        return "Organizer";
    }
}
