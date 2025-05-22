package gui;

import controller.Controller;
import exceptions.EmailAlreadyInUseException;
import exceptions.EmptyFieldException;
import exceptions.UsernameAlreadyTakenException;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrationCardPanel {
    private final Controller controller;
    private final JPanel cardPanel;

    private JPanel rootPanel;
    private JPasswordField confirmPasswordField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPanel roundedBackPanel;
    private JPanel roundedConfirmPanel;
    private JLabel backLabel;
    private JLabel confirmLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel errorLabel;

    public RegistrationCardPanel(JPanel cardPanel, Controller controller) {
        this.cardPanel = cardPanel;
        this.controller = controller;

        customizeComponents();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void customizeComponents() {
        errorLabel.setForeground(UIColors.CARMINE_RED);
        errorLabel.setVisible(false);

        usernameLabel.setForeground(Color.GRAY);
        emailLabel.setForeground(Color.GRAY);
        passwordLabel.setForeground(Color.GRAY);
        confirmPasswordLabel.setForeground(Color.GRAY);

        backLabel.setForeground(Color.WHITE);
        confirmLabel.setForeground(Color.WHITE);
    }

    private void createUIComponents() {
        roundedBackPanel = new RoundedPanel();
        roundedConfirmPanel = new RoundedPanel();

        setupBackPanel();
        setupConfirmPanel();
    }

    private void setupBackPanel() {
        roundedBackPanel.setBackground(UIColors.CARMINE_RED);
        roundedBackPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedBackPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "login");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedBackPanel.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private void setupConfirmPanel() {
        roundedConfirmPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedConfirmPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedConfirmPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedConfirmPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedConfirmPanel.setCursor(Cursor.getDefaultCursor());
                roundedConfirmPanel.setBackground(UIColors.NIGHT_BLUE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                handleRegistration();
            }
        });
    }

    private void handleRegistration() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (!password.equals(confirmPassword)) {
            errorLabel.setVisible(true);
            return;
        }

        errorLabel.setVisible(false);

        try {
            controller.registerUser(username, email, password);

            JOptionPane.showMessageDialog(
                    rootPanel,
                    "Your account has been successfully registered!",
                    "Registration Completed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            CardLayout layout = (CardLayout) cardPanel.getLayout();
            layout.show(cardPanel, "login");

        } catch (EmptyFieldException ex) {
            showErrorDialog("Fields must not be empty.");
        } catch (UsernameAlreadyTakenException ex) {
            showErrorDialog("This username is already taken.");
        } catch (EmailAlreadyInUseException ex) {
            showErrorDialog("This email is already in use.");
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
                rootPanel,
                message,
                "Registration Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}