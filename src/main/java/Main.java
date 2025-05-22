import controller.*;
import exceptions.*;
import gui.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
            throws Exception {
        Controller controller = new Controller();
        controller.registerUser("asd", "asd", "asd");
        SwingUtilities.invokeLater(() -> new AuthFrame(controller).setVisible(true));
    }
}