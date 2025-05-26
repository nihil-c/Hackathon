package gui;

import controller.Controller;
import model.User;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private JPanel rootPanel;
    private JPanel sidebarPanel;
    private JPanel cardPanel;
    private JPanel containerPanel;

    private JPanel roundedDashboardPanel;
    private JPanel roundedHackathonPanel;
    private JPanel roundedTeamPanel;
    private JPanel roundedLogoutPanel;
    private JPanel roundedManagePanel;

    private JLabel dashboardLabel;
    private JLabel hackathonLabel;
    private JLabel teamLabel;
    private JLabel logoutLabel;
    private JLabel manageLabel;
    private JLabel menuLabel;
    private JPanel menuPanel;

    private final Controller controller;
    private final Map<String, JPanel> cardMap = new HashMap<>();
    private CardLayout cardLayout;

    public MainFrame(Controller controller) {
        this.controller = controller;

        setTitle("Hackathon.IO - Home");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(rootPanel);
        setLocationRelativeTo(null);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
