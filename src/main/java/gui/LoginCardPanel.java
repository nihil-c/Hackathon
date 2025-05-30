package gui;

import controller.Controller;
import exceptions.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * LoginCardPanel is a GUI panel that handles user login
 * and navigation to the registration panel.
 */
public class LoginCardPanel {
    private JPanel rootPanel;
    private JPanel cardPanel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JPanel roundedLoginPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JLabel dontHaveAnAccountLabel;
    private JLabel infoLabel;
    private JLabel welcomeLabel;

    private final Controller controller;

    public LoginCardPanel(final JPanel cardPanel, final Controller controller) {
        this.cardPanel = cardPanel;
        this.controller = controller;

        setupStyle();
        setupDontHaveAnAccountLabel();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void setupStyle() {
        welcomeLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
        usernameLabel.setForeground(Color.GRAY);
        passwordLabel.setForeground(Color.GRAY);
        loginLabel.setForeground(Color.WHITE);
    }

    private void createUIComponents() {
        roundedLoginPanel = new RoundedPanel();

        setupLoginPanel();
    }

    private void setupLoginPanel() {
        roundedLoginPanel.setBackground(UIColors.CARMINE_RED);
        roundedLoginPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedLoginPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedLoginPanel.setBackground(UIColors.PRESSED_CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedLoginPanel.setCursor(Cursor.getDefaultCursor());
                roundedLoginPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        final String username = usernameField.getText();
        final String password = new String(passwordField.getPassword());

        try {
            controller.loginUser(username, password);

            SwingUtilities.invokeLater(() -> {
                new MainFrame(controller).setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                if (frame != null) frame.dispose();
            });
        } catch(EmptyFieldException ex) {
            showErrorDialog("You must fill all the available fields.");
        } catch (UserNotFoundException ex) {
            showErrorDialog("User not found.");
        } catch (IncorrectPasswordException ex) {
            showErrorDialog("Password is not correct.");
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
                rootPanel,
                message,
                "Login Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void setupDontHaveAnAccountLabel() {
        dontHaveAnAccountLabel.setForeground(UIColors.HYPERLINK_BLUE);
        dontHaveAnAccountLabel.setText("<html><u>Don't have an account? Register!</u></html>");
        dontHaveAnAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        dontHaveAnAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "register");
            }
        });
    }
}