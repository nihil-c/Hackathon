package gui;

import controller.Controller;
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
        backLabel.setForeground(Color.WHITE);

        rConfirmPanel.setBackground(UIColors.NIGHT_BLUE);
        confirmLabel.setForeground(Color.WHITE);
    }

    private void createUIComponents() {
        rBackPanel = new RoundedPanel();
        rConfirmPanel = new RoundedPanel();

        setupRBackPanelListener();
        //setupRConfirmPanelListener();
    }

    private void setupRBackPanelListener() {
        rBackPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rBackPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rBackPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rBackPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}