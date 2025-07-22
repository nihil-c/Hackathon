package gui;

import controller.Controller;
import model.*;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Finestra principale dell'applicazione Hackathon.IO.
 * <p>
 * Gestisce la visualizzazione dei diversi pannelli tramite CardLayout e permette la navigazione tra dashboard,
 * hackathon, team, gestione e logout. Inizializza il menu e i pannelli principali dell'interfaccia utente.
 * </p>
 */
public class MainFrame extends JFrame {
    private JPanel rootPanel;
    private JPanel sidebarPanel;
    private JPanel cardPanel;
    private JPanel containerPanel;

    private JPanel rDashboardPanel;
    private JPanel rHackathonPanel;
    private JPanel rTeamPanel;
    private JPanel rLogoutPanel;
    private JPanel rManagePanel;

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

    /**
     * Costruttore della finestra principale.
     * @param controller controller principale dell'applicazione
     */
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

    /**
     * Inizializza il pannello centrale con i vari pannelli dell'applicazione e imposta il CardLayout.
     */
    private void setupCardPanel() {
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Crea e registra i pannelli nella mappa
        JPanel dashboardPanel = new DashboardCardPanel(controller).getRootPanel();
        JPanel hackathonPanel = new HackathonCardPanel(controller).getRootPanel();
        JPanel teamPanel = new TeamCardPanel(controller).getRootPanel();
        JPanel managePanel = new ManageCardPanel(controller).getRootPanel();

        cardMap.put("dashboard", dashboardPanel);
        cardMap.put("hackathon", hackathonPanel);
        cardMap.put("team", teamPanel);
        cardMap.put("manage", managePanel);

        // Aggiunge i pannelli al cardPanel con il nome chiave
        cardPanel.add(dashboardPanel, "dashboard");
        cardPanel.add(hackathonPanel, "hackathon");
        cardPanel.add(teamPanel, "team");
        cardPanel.add(managePanel, "manage");

        cardLayout.show(cardPanel, "dashboard");
    }

    /**
     * Personalizza i componenti grafici della sidebar e dei pannelli menu.
     */
    private void customizeComponents() {
        sidebarPanel.setBackground(UIColors.NIGHT_BLUE);
        menuLabel.setForeground(Color.WHITE);
        containerPanel.setBackground(UIColors.NIGHT_BLUE);
        menuPanel.setBackground(UIColors.NIGHT_BLUE);

        rDashboardPanel.setBackground(UIColors.NIGHT_BLUE);
        dashboardLabel.setForeground(Color.WHITE);
        scaleLabelIcon(dashboardLabel, 20, 20);

        rHackathonPanel.setBackground(UIColors.NIGHT_BLUE);
        hackathonLabel.setForeground(Color.WHITE);
        scaleLabelIcon(hackathonLabel, 20, 20);

        rTeamPanel.setBackground(UIColors.NIGHT_BLUE);
        teamLabel.setForeground(Color.WHITE);
        scaleLabelIcon(teamLabel, 20, 20);

        rManagePanel.setBackground(UIColors.NIGHT_BLUE);
        manageLabel.setForeground(Color.WHITE);
        scaleLabelIcon(manageLabel, 20, 20);

        rLogoutPanel.setBackground(UIColors.NIGHT_BLUE);
        logoutLabel.setForeground(Color.WHITE);
        scaleLabelIcon(logoutLabel, 20, 20);
    }

    /**
     * Ridimensiona l'icona di una JLabel alle dimensioni specificate.
     * @param label JLabel da modificare
     * @param width larghezza desiderata
     * @param height altezza desiderata
     */
    private void scaleLabelIcon(JLabel label, int width, int height) {
        Icon icon = label.getIcon();
        if (icon instanceof ImageIcon imageIcon) {
            Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));
        }
    }

    /**
     * Inizializza i componenti grafici custom e imposta i listener dei pannelli menu.
     */
    private void createUIComponents() {
        rDashboardPanel = new RoundedPanel();
        rHackathonPanel = new RoundedPanel();
        rTeamPanel = new RoundedPanel();
        rManagePanel = new RoundedPanel();
        rLogoutPanel = new RoundedPanel();

        setupRDashboardPanelListener();
        setupRHackathonPanelListener();
        setupRTeamPanelListener();
        setupRManagePanelListener();
        setupRLogoutPanelListener();
    }

    /**
     * Imposta il listener per il pannello Dashboard.
     */
    private void setupRDashboardPanelListener() {
        rDashboardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rDashboardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshCard("dashboard", new DashboardCardPanel(controller).getRootPanel());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rDashboardPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rDashboardPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Imposta il listener per il pannello Hackathon.
     */
    private void setupRHackathonPanelListener() {
        rHackathonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rHackathonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!(controller.getCurrentUser().getRole() instanceof DefaultRole)){
                    refreshCard("hackathon", new HackathonCardPanel(controller).getRootPanel());
                } else {
                    showErrorDialog("Please create or join an event to access this panel.");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rHackathonPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rHackathonPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Imposta il listener per il pannello Team.
     */
    private void setupRTeamPanelListener() {
        rTeamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rTeamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (controller.getCurrentUser().getRole() instanceof ParticipantRole) {
                    refreshCard("team", new TeamCardPanel(controller).getRootPanel());
                } else {
                    showErrorDialog("Only Participants have access to this panel.");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rTeamPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rTeamPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Imposta il listener per il pannello Manage.
     */
    private void setupRManagePanelListener() {
        rManagePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rManagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Role currentUserRole = controller.getCurrentUser().getRole();

                if (currentUserRole instanceof OrganizerRole || currentUserRole instanceof JudgeRole) {
                    refreshCard("manage", new ManageCardPanel(controller).getRootPanel());
                } else {
                    showErrorDialog("This tab is reserved to Organizers and Judges.");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rManagePanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rManagePanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Imposta il listener per il pannello Logout.
     */
    private void setupRLogoutPanelListener() {
        rLogoutPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rLogoutPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new AuthFrame(controller).setVisible(true);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                    if (frame != null) frame.dispose();
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rLogoutPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rLogoutPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Aggiorna il pannello visualizzato nel CardLayout con uno nuovo.
     * @param name nome del pannello da aggiornare
     * @param newPanel nuovo pannello da visualizzare
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

    /**
     * Mostra una finestra di errore con il messaggio specificato.
     * @param message messaggio di errore da visualizzare
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Restituisce il pannello principale della finestra.
     * @return rootPanel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
