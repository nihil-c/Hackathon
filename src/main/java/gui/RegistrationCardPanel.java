package gui;

import controller.Controller;
import exceptions.EmailAlreadyTakenException;
import exceptions.UsernameAlreadyTakenException;
import exceptions.BlankFieldException;
import exceptions.PasswordsDoNotMatchException;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrationCardPanel {
    // Attributi
    private JPanel rootPanel;
    private JPasswordField confirmPasswordField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPanel rBackPanel;
    private JPanel rConfirmPanel;
    private JLabel backLabel;
    private JLabel confirmLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel errorLabel;
    private JLabel registerYourAccountLabel;

    private final Controller controller;
    private final JPanel cardPanel;

    // Costruttore
    public RegistrationCardPanel(JPanel cardPanel, Controller controller) {
        this.cardPanel = cardPanel;
        this.controller = controller;

        customizeComponents();
    }

    private void customizeComponents() {
        registerYourAccountLabel.setForeground(UIColors.NIGHT_BLUE);
        usernameLabel.setForeground(Color.GRAY);
        emailLabel.setForeground(Color.GRAY);
        passwordLabel.setForeground(Color.GRAY);
        confirmPasswordLabel.setForeground(Color.GRAY);

        errorLabel.setVisible(false);
        errorLabel.setForeground(UIColors.CARMINE_RED);

        rBackPanel.setBackground(Color.WHITE);
        ((RoundedPanel) rBackPanel).setBorderColor(UIColors.NIGHT_BLUE);
        backLabel.setForeground(UIColors.NIGHT_BLUE);

        rConfirmPanel.setBackground(UIColors.NIGHT_BLUE);
        confirmLabel.setForeground(Color.WHITE);
    }

    private void createUIComponents() {
        rBackPanel = new RoundedPanel();
        rConfirmPanel = new RoundedPanel();

        setupRBackPanelListener();
        setupRConfirmPanelListener();
    }

    private void setupRBackPanelListener() {
        rBackPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rBackPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "login");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((RoundedPanel) rBackPanel).setBorderColor(UIColors.CARMINE_RED);
                backLabel.setForeground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((RoundedPanel) rBackPanel).setBorderColor(UIColors.NIGHT_BLUE);
                backLabel.setForeground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void setupRConfirmPanelListener() {
        rConfirmPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rConfirmPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleRegistration();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rConfirmPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rConfirmPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private  void handleRegistration() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        try {
            checkPasswords(password, confirmPassword);
            controller.registerUser(username, email, password);

            JOptionPane.showMessageDialog(
                    null,
                    "Your account has been successfully registered!",
                    "Registration Completed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            usernameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");

            CardLayout layout = (CardLayout) cardPanel.getLayout();
            layout.show(cardPanel, "login");
        } catch (PasswordsDoNotMatchException ex) {
            errorLabel.setVisible(true);
        } catch (BlankFieldException | UsernameAlreadyTakenException | EmailAlreadyTakenException ex) {
            showErrorDialog(ex.getMessage());
        }
    }

    private void checkPasswords(String password, String confirmPassword) throws PasswordsDoNotMatchException {
        if (!password.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        } else {
            errorLabel.setVisible(false);
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Login Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}