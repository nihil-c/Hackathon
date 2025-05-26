package gui;

import controller.Controller;

import javax.swing.*;

public class DashboardCardPanel {
    private JPanel rootPanel;
    private JLabel dashboardLabel;
    private JLabel welcomeLabel;
    private JPanel roundedAddPanel;
    private JLabel addLabel;
    private JScrollPane scrollPanel;
    private JPanel eventListPanel;
    private JLabel infoLabel;
    private JLabel openEventsLabel;

    private final Controller controller;

    public DashboardCardPanel(Controller controller) {
        this.controller = controller;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}