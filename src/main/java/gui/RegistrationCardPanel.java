package gui;

import controller.Controller;

import javax.swing.*;

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
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}