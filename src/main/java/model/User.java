package model;

import exceptions.*;

import java.time.LocalDate;

public class User {
    private String username;
    private String email;
    private String password;
    private Hackathon registeredHackathon;
    private Role role;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registeredHackathon = null;
        this.role = null;
    }

    public String getUsername() { 
        return username; 
    }
    
    public String getEmail() { 
        return email; 
    }
    
    public String getPassword() { 
        return password; 
    }
    
    public Hackathon getRegisteredHackathon() {
        return registeredHackathon;
    }
    
    public String getRole() { 
        return role == null ? "User" : role.getRoleName(); 
    }
    
    public Role getRoleInstance() { 
        return role; 
    }

    public void setUsername(String username) { 
        this.username = username; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public void setPassword(String password) { 
        this.password = password; 
    }
    
    public void setRegisteredHackathon(Hackathon hackathon) {
        this.registeredHackathon = hackathon;
    }
    
    public void setRole(Role role) { 
        this.role = role; 
    }

    public Hackathon createHackathon(String title, String location, LocalDate startDate, LocalDate endDate)
            throws MissingHackathonDataException, InvalidOrganizerException {

        this.role = new OrganizerRole(this);
        Hackathon hackathon = new Hackathon(title, location, startDate, endDate, this);
        this.registeredHackathon = hackathon;
        return hackathon;
    }


    public void registerToHackathon(Hackathon hackathon)
            throws OrganizerSelfRegistrationException, AlreadyRegisteredToHackathonException, AlreadyOrganizingAnotherEventException {

        if (this.role instanceof OrganizerRole) {
            if (this.registeredHackathon != null && this.registeredHackathon.equals(hackathon)) {
                throw new OrganizerSelfRegistrationException();
            }
            throw new AlreadyOrganizingAnotherEventException();
        }

        hackathon.addParticipant(this);

        this.registeredHackathon = hackathon;
        this.role = new ParticipantRole(this);
    }
}