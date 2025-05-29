package gui;

import controller.*;

import javax.swing.*;
import java.awt.*;

import static utils.UIColors.*;

public class AuthFrame extends JFrame {
    private JPanel rootPanel;
    private JPanel hackathonIoPanel;
    private JLabel hackathonIoLabel;
    private JPanel sidePanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private final Controller controller;

    public AuthFrame(Controller controller) {
        this.controller = controller;

        setTitle("Hackathon.IO - Access");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(rootPanel);

        customizeComponents();
        setupCardPanel();
    }

    private void customizeComponents() {
        sidePanel.setBackground(NIGHT_BLUE);
        hackathonIoPanel.setBackground(NIGHT_BLUE);
        hackathonIoLabel.setForeground(Color.WHITE);
    }

    public void setupCardPanel() {
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        LoginCardPanel loginPanel = new LoginCardPanel(cardPanel, controller);
        cardPanel.add(loginPanel.getRootPanel(), "login");

        RegistrationCardPanel registrationPanel = new RegistrationCardPanel(cardPanel, controller);
        cardPanel.add(registrationPanel.getRootPanel(), "register");

        cardLayout.show(cardPanel, "login");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}