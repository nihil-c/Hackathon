package gui;

import controller.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JPanel rootPanel;
    private JPanel sidebarPanel;
    private JPanel cardPanel;
    private JPanel roundedDashboardPanel;
    private JLabel dashboardLabel;
    private JPanel roundedHackathonPanel;
    private JLabel hackathonLabel;
    private JPanel roundedTeamPanel;
    private JLabel teamLabel;
    private JPanel roundedLogoutPanel;
    private JLabel logoutLabel;
    private JLabel menuLabel;
    private JPanel menuPanel;
    private JPanel roundedManagePanel;
    private JLabel manageLabel;
    private JPanel containerPanel;

    private CardLayout cardLayout;
    private final Controller controller;

    public MainFrame(Controller controller) {
        this.controller = controller;

        setTitle("Hackathon.IO - Home");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(rootPanel);
        setLocationRelativeTo(null);
        
        setupCardPanel();
        customizeComponents();
    }

    private void setupCardPanel() {
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(new DashboardCardPanel(controller, this).getRootPanel(), "dashboard");
        // cardPanel.add(new HackathonCardPanel(controller).getRootPanel(), "hackathon");
        // cardPanel.add(new TeamCardPanel(controller).getRootPanel(), "team");
        // cardPanel.add(new ManageCardPanel(controller).getRootPanel(), "manage");

        cardLayout.show(cardPanel, "dashboard");
    }

    private void customizeComponents() {
        sidebarPanel.setBackground(UIColors.NIGHT_BLUE);
        menuLabel.setForeground(Color.WHITE);
        containerPanel.setBackground(UIColors.NIGHT_BLUE);
        menuPanel.setBackground(UIColors.NIGHT_BLUE);
        dashboardLabel.setForeground(Color.WHITE);
        hackathonLabel.setForeground(Color.WHITE);
        teamLabel.setForeground(Color.WHITE);
        manageLabel.setForeground(Color.WHITE);
        logoutLabel.setForeground(Color.WHITE);
    }

    private void createUIComponents() {
        roundedDashboardPanel = new RoundedPanel();
        roundedHackathonPanel = new RoundedPanel();
        roundedTeamPanel = new RoundedPanel();
        roundedManagePanel = new RoundedPanel();
        roundedLogoutPanel = new RoundedPanel();

        setupDashboardPanel();
        setupHackathonPanel();
        setupTeamPanel();
        setupManagePanel();
        setupLogoutPanel();
    }

    private void setupDashboardPanel() {
        roundedDashboardPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedDashboardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedDashboardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedDashboardPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(cardPanel, "dashboard");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedDashboardPanel.setCursor(Cursor.getDefaultCursor());
                roundedDashboardPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void setupHackathonPanel() {
        roundedHackathonPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedHackathonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedHackathonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedHackathonPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(cardPanel, "hackathon");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedHackathonPanel.setCursor(Cursor.getDefaultCursor());
                roundedHackathonPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void setupTeamPanel() {
        roundedTeamPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedTeamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedTeamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedTeamPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(cardPanel, "team");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedTeamPanel.setCursor(Cursor.getDefaultCursor());
                roundedTeamPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void setupManagePanel() {
        roundedManagePanel.setBackground(UIColors.NIGHT_BLUE);
        roundedManagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedManagePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedManagePanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(cardPanel, "manage");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedManagePanel.setCursor(Cursor.getDefaultCursor());
                roundedManagePanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void setupLogoutPanel() {
        roundedLogoutPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedLogoutPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedLogoutPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedLogoutPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Open auth frame and close main frame
                SwingUtilities.invokeLater(() -> {
                    new AuthFrame(controller).setVisible(true);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                    if (frame != null) frame.dispose();
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedLogoutPanel.setCursor(Cursor.getDefaultCursor());
                roundedLogoutPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
