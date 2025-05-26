import controller.*;
import gui.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
            throws Exception {
        Controller controller = new Controller();
        //controller.registerUser("asd", "asd", "asd");
        // User user = controller.getUserByUsername("asd");
        // user.setRole(new JudgeRole());
        SwingUtilities.invokeLater(() -> new AuthFrame(controller).setVisible(true));
    }
}