package gui;

import controller.*;
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
    private JPanel roundedRegisterPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JLabel registerLabel;
    private JLabel dontHaveAnAccountLabel;
    private JLabel infoLabel;
    private JLabel welcomeLabel;

    private final Controller controller;

    public LoginCardPanel(final JPanel cardPanel, final Controller controller) {
        this.cardPanel = cardPanel;
        this.controller = controller;

        setupStyle();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void setupStyle() {
        welcomeLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
        usernameLabel.setForeground(Color.GRAY);
        passwordLabel.setForeground(Color.GRAY);
        dontHaveAnAccountLabel.setForeground(Color.GRAY);
        loginLabel.setForeground(Color.WHITE);
        registerLabel.setForeground(Color.WHITE);
    }

    private void createUIComponents() {
        roundedLoginPanel = new RoundedPanel();
        roundedRegisterPanel = new RoundedPanel();

        setupLoginPanel();
        setupRegisterPanel();
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
            public void mousePressed(MouseEvent e) {
                handleLogin();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedLoginPanel.setCursor(Cursor.getDefaultCursor());
                roundedLoginPanel.setBackground(UIColors.CARMINE_RED);
            }
        });
    }

    private void handleLogin() {
        final String username = usernameField.getText();
        final String password = new String(passwordField.getPassword());

        try {
            controller.loginUser(username, password);

            // Open main frame and close auth frame
            SwingUtilities.invokeLater(() -> {
                new MainFrame(controller).setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                if (frame != null) frame.dispose();
            });

        } catch (EmptyFieldException e) {
            showErrorDialog("Fields must not be empty.");
        } catch (UserNotFoundException e) {
            showErrorDialog("User not found.");
        } catch (IncorrectPasswordException e) {
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

    private void setupRegisterPanel() {
        roundedRegisterPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedRegisterPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedRegisterPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedRegisterPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "register");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedRegisterPanel.setCursor(Cursor.getDefaultCursor());
                roundedRegisterPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }
}
