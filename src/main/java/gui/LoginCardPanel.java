package gui;

import controller.Controller;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        customizeComponents();
    }

    public void customizeComponents() {
        welcomeLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
        usernameLabel.setForeground(Color.GRAY);
        passwordField.setForeground(Color.GRAY);
        loginLabel.setForeground(Color.WHITE);
        roundedLoginPanel.setBackground(UIColors.NIGHT_BLUE);
    }

    private void createUIComponents() {
        roundedLoginPanel = new RoundedPanel();

        setupLoginPanelListener();
    }

    public void setupLoginPanelListener() {
        roundedLoginPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                roundedLoginPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedLoginPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedLoginPanel.setCursor(Cursor.getDefaultCursor());
                roundedLoginPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}