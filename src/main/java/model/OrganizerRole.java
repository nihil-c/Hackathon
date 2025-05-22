package model;

import exceptions.InvalidRoleException;

public class OrganizerRole implements Role {
    @Override
    public String getRoleName() {
        return "Organizer";
    }
}
