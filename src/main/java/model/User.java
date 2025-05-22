package model;

import exceptions.AlreadyOrganizingAnotherEventException;
import exceptions.AlreadyRegisteredToHackathonException;
import exceptions.OrganizerSelfRegistrationException;

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

    public Hackathon createHackathon(String title, String location, LocalDate startDate, LocalDate endDate) throws Exception {
        return new Hackathon(title, location, startDate, endDate, this);
    }

    /* Solo uno user che non si è mai registrato ad al più di un Hackathon oppure che non 
    lo ha mai organizzato oppure mai partecipato nelle vesti di giudice 
    può registrarsi ad un hackathon. Una volta iscritto diventa automaticamente un participant. */
    public void registerToHackathon(Hackathon hackathon)
            throws OrganizerSelfRegistrationException, AlreadyRegisteredToHackathonException, AlreadyOrganizingAnotherEventException {
        if (this.role instanceof OrganizerRole) {
            if (this.registeredHackathon.equals(hackathon)) {
                throw new OrganizerSelfRegistrationException();
            }
            throw new AlreadyOrganizingAnotherEventException();
        }

        try {
            hackathon.addParticipant(this);
        } catch (AlreadyRegisteredToHackathonException ex) {
            throw new AlreadyRegisteredToHackathonException();
        }
    }
}