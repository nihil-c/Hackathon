import controller.*;
import gui.*;
import model.JudgeRole;
import model.OrganizerRole;
import model.ParticipantRole;
import model.User;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
            throws Exception {
        Controller controller = new Controller();
        controller.registerUser("asd", "asd", "asd");
        // User user = controller.getUserByUsername("asd");
        // user.setRole(new JudgeRole());
        SwingUtilities.invokeLater(() -> new AuthFrame(controller).setVisible(true));
    }
}