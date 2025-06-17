import controller.*;
import gui.*;
import model.Hackathon;
import model.JudgeRole;
import model.OrganizerRole;
import model.User;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        controller.registerUser("asd", "asd@example.com", "asd");
        controller.registerUser("qwe", "qwe@example.com", "qwe");
        //User asdUser = controller.getUserByUsername("asd");
        //Hackathon hackathon = new Hackathon("Hackathon", "Italia", LocalDate.now(), LocalDate.now().plusDays(5), 100, 5, new User("cacca", "cacca", "cacca"));
        //asdUser.setHackathon(hackathon);
        //asdUser.setRole(new JudgeRole());
        SwingUtilities.invokeLater(() -> new AuthFrame(controller).setVisible(true));
    }
}