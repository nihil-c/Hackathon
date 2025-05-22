package gui;

import controller.Controller;
import utils.*;

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

    // Mappa per gestire i pannelli (nome -> JPanel)
    private final Map<String, JPanel> cardMap = new HashMap<>();

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

        // Crea e registra i pannelli nella mappa
        JPanel dashboardPanel = new DashboardCardPanel(controller).getRootPanel();
        JPanel hackathonPanel = new HackathonCardPanel(controller).getRootPanel();
        JPanel teamPanel = new TeamCardPanel(controller).getRootPanel();
        // JPanel managePanel = new ManageCardPanel(controller).getRootPanel();

        cardMap.put("dashboard", dashboardPanel);
        cardMap.put("hackathon", hackathonPanel);
        cardMap.put("team", teamPanel);
        // cardMap.put("manage", managePanel);

        // Aggiunge i pannelli al cardPanel con il nome chiave
        cardPanel.add(dashboardPanel, "dashboard");
        cardPanel.add(hackathonPanel, "hackathon");
        cardPanel.add(teamPanel, "team");
        // cardPanel.add(managePanel, "manage");

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
            public void mouseExited(MouseEvent e) {
                roundedDashboardPanel.setCursor(Cursor.getDefaultCursor());
                roundedDashboardPanel.setBackground(UIColors.NIGHT_BLUE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(cardPanel, "dashboard");
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
            public void mouseExited(MouseEvent e) {
                roundedHackathonPanel.setCursor(Cursor.getDefaultCursor());
                roundedHackathonPanel.setBackground(UIColors.NIGHT_BLUE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Ricarica da zero il pannello "hackathon"
                refreshCard("hackathon", new HackathonCardPanel(controller).getRootPanel());
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
            public void mouseExited(MouseEvent e) {
                roundedTeamPanel.setCursor(Cursor.getDefaultCursor());
                roundedTeamPanel.setBackground(UIColors.NIGHT_BLUE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(cardPanel, "team");
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
            public void mouseExited(MouseEvent e) {
                roundedManagePanel.setCursor(Cursor.getDefaultCursor());
                roundedManagePanel.setBackground(UIColors.NIGHT_BLUE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(cardPanel, "manage");
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
            public void mouseExited(MouseEvent e) {
                roundedLogoutPanel.setCursor(Cursor.getDefaultCursor());
                roundedLogoutPanel.setBackground(UIColors.NIGHT_BLUE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Apri AuthFrame e chiudi MainFrame
                SwingUtilities.invokeLater(() -> {
                    new AuthFrame(controller).setVisible(true);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                    if (frame != null) frame.dispose();
                });
            }
        });
    }

    /**
     * Metodo per ricaricare un pannello specifico, sostituendolo con uno nuovo.
     *
     * @param name Nome del pannello nella CardLayout
     * @param newPanel Nuova istanza del JPanel da mostrare
     */
    private void refreshCard(String name, JPanel newPanel) {
        JPanel oldPanel = cardMap.get(name);
        if (oldPanel != null) {
            cardPanel.remove(oldPanel);
        }
        cardMap.put(name, newPanel);
        cardPanel.add(newPanel, name);
        cardLayout.show(cardPanel, name);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
