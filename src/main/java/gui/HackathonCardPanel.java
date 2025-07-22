package gui;

import controller.Controller;
import model.Hackathon;
import model.JudgeRole;
import model.Team;
import model.User;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * Pannello grafico per la visualizzazione e gestione delle informazioni di un hackathon.
 * <p>
 * Mostra i dettagli dell'evento, la classifica dei team, il problema proposto e permette
 * l'interazione tramite pulsanti e pannelli personalizzati. Consente ai giudici di pubblicare la classifica
 * e agli utenti di visualizzare le informazioni principali dell'hackathon.
 * </p>
 */
public class HackathonCardPanel {
    private JPanel rootPanel;
    private JLabel hackathonLabel;
    private JLabel infoLabel;
    private JScrollPane scrollPanel;
    private JLabel overviewLabel;
    private JPanel rTitlePanel;
    private JLabel titleLabel;
    private JPanel rTitleContentPanel;
    private JPanel rLocationPanel;
    private JLabel locationLabel;
    private JPanel rLocationContentPanel;
    private JLabel titleContentLabel;
    private JLabel locationContentLabel;
    private JPanel rStartDatePanel;
    private JLabel startDateLabel;
    private JPanel rStartDateContentPanel;
    private JLabel startDateContentLabel;
    private JPanel rEndDatePanel;
    private JLabel endDateLabel;
    private JPanel rEndDateContentPanel;
    private JLabel endDateContentLabel;
    private JPanel rDeadlinePanel;
    private JLabel deadlineLabel;
    private JPanel rDeadlineContentPanel;
    private JLabel deadlineContentLabel;
    private JPanel rOrganizerPanel;
    private JLabel organizerLabel;
    private JPanel rOrganizerContentPanel;
    private JLabel organizerContentLabel;
    private JLabel problemStatementLabel;
    private JPanel rEditPanel;
    private JLabel editLabel;
    private JTextArea problemStatementTextArea;
    private JLabel rankingLabel;
    private JLabel publishLabel;
    private JPanel rPublishPanel;
    private JLabel rankingInfoLabel;
    private JPanel rankingListPanel;
    private JLabel maxParticipantsLabel;
    private JLabel maxTeamSizeLabel;
    private JPanel rMaxParticipantsPanel;
    private JPanel rMaxTeamSizePanel;
    private JPanel rMaxParticipantsContentPanel;
    private JPanel rMaxTeamSizeContentPanel;
    private JLabel maxParticipantsContentLabel;
    private JLabel maxTeamSizeContentLabel;

    private final Controller controller;

    /**
     * Costruttore del pannello hackathon.
     * @param controller controller principale dell'applicazione
     */
    public HackathonCardPanel(Controller controller) {
        this.controller = controller;

        customizeComponents();
    }

    /**
     * Personalizza i componenti grafici e imposta i colori.
     */
    private void customizeComponents() {
        User currentUser = controller.getCurrentUser();
        Hackathon currentHackathon = currentUser.getHackathon();

        setupScrollPanel();

        hackathonLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
        overviewLabel.setForeground(UIColors.CARMINE_RED);

        rTitlePanel.setBackground(UIColors.LIGHT_GRAY);
        rLocationPanel.setBackground(UIColors.LIGHT_GRAY);
        rStartDatePanel.setBackground(UIColors.LIGHT_GRAY);
        rEndDatePanel.setBackground(UIColors.LIGHT_GRAY);
        rDeadlinePanel.setBackground(UIColors.LIGHT_GRAY);
        rMaxParticipantsPanel.setBackground(UIColors.LIGHT_GRAY);
        rMaxTeamSizePanel.setBackground(UIColors.LIGHT_GRAY);
        rOrganizerPanel.setBackground(UIColors.LIGHT_GRAY);

        rTitleContentPanel.setBackground(Color.WHITE);
        rLocationContentPanel.setBackground(Color.WHITE);
        rStartDateContentPanel.setBackground(Color.WHITE);
        rEndDateContentPanel.setBackground(Color.WHITE);
        rDeadlineContentPanel.setBackground(Color.WHITE);
        rMaxParticipantsContentPanel.setBackground(Color.WHITE);
        rMaxTeamSizeContentPanel.setBackground(Color.WHITE);
        rOrganizerContentPanel.setBackground(Color.WHITE);

        if (currentHackathon != null) {
            infoLabel.setText("You are currently registered for the event: " + controller.getCurrentUser().getHackathon().getTitle());
            addHackathonInfo(currentHackathon);

            if (currentHackathon.getProblemStatement() == null) {
                problemStatementTextArea.setText("Problem statement is empty.");
            }
        } else {
            infoLabel.setText("You are currently not registered for an event.");
        }

        problemStatementLabel.setForeground(UIColors.CARMINE_RED);
        rEditPanel.setBackground(UIColors.NIGHT_BLUE);
        editLabel.setForeground(Color.WHITE);
        problemStatementTextArea.setBackground(null);
        problemStatementTextArea.setForeground(Color.GRAY);

        rankingLabel.setForeground(UIColors.CARMINE_RED);
        rankingInfoLabel.setForeground(Color.GRAY);

        rPublishPanel.setBackground(UIColors.NIGHT_BLUE);
        publishLabel.setForeground(Color.WHITE);

        setupRankingListPanel();

        SwingUtilities.invokeLater(() -> scrollPanel.getVerticalScrollBar().setValue(0));
    }

    /**
     * Imposta lo scroll e il layout del pannello principale.
     */
    private void setupScrollPanel() {
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);
    }

    /**
     * Imposta il layout e il bordo del pannello classifica.
     */
    private void setupRankingListPanel() {
        rankingListPanel.setLayout(new BoxLayout(rankingListPanel, BoxLayout.Y_AXIS));
        rankingListPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    /**
     * Crea una card grafica per la classifica di un team.
     * @param rankingNumber posizione in classifica
     * @param teamName nome del team
     * @return pannello grafico della card classifica
     */
    private RoundedPanel createRankingCard(int rankingNumber, String teamName) {
        RoundedPanel card = new RoundedPanel();

        card.setLayout(new BoxLayout(card, BoxLayout.X_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        card.setPreferredSize(new Dimension(0, 50));

        JLabel rankingLabel = new JLabel(rankingNumber + "°");
        JLabel teamNameLabel = new JLabel(teamName);

        rankingLabel.setForeground(UIColors.NIGHT_BLUE);
        rankingLabel.setFont(new Font(null, Font.BOLD, 14));

        teamNameLabel.setForeground(UIColors.CARMINE_RED);
        teamNameLabel.setFont(new Font(null, Font.BOLD, 14));

        card.add(rankingLabel);
        card.add(Box.createHorizontalStrut(10));
        card.add(teamNameLabel);

        return card;
    }

    /**
     * Aggiorna le informazioni dell'hackathon visualizzate.
     * @param hackathon hackathon da visualizzare
     */
    private void addHackathonInfo(Hackathon hackathon) {
        titleContentLabel.setText(hackathon.getTitle());
        locationContentLabel.setText(hackathon.getLocation());
        startDateContentLabel.setText(hackathon.getStartDate().toString());
        endDateContentLabel.setText(hackathon.getEndDate().toString());
        deadlineContentLabel.setText(hackathon.getRegistrationDeadline().toString());
        maxParticipantsContentLabel.setText(String.valueOf(hackathon.getMaxParticipants()));
        maxTeamSizeContentLabel.setText(String.valueOf(hackathon.getMaxTeamSize()));
        organizerContentLabel.setText("@" + hackathon.getOrganizer().getUsername());
    }

    /**
     * Inizializza i componenti grafici custom.
     */
    private void createUIComponents() {
        rTitlePanel = new RoundedPanel();
        rLocationPanel = new RoundedPanel();
        rStartDatePanel = new RoundedPanel();
        rEndDatePanel = new RoundedPanel();
        rDeadlinePanel = new RoundedPanel();
        rMaxParticipantsPanel = new RoundedPanel();
        rMaxTeamSizePanel = new RoundedPanel();
        rOrganizerPanel = new RoundedPanel();

        rTitleContentPanel = new RoundedPanel();
        rLocationContentPanel = new RoundedPanel();
        rStartDateContentPanel = new RoundedPanel();
        rEndDateContentPanel = new RoundedPanel();
        rDeadlineContentPanel = new RoundedPanel();
        rMaxParticipantsContentPanel = new RoundedPanel();
        rMaxTeamSizeContentPanel = new RoundedPanel();
        rOrganizerContentPanel = new RoundedPanel();

        rEditPanel = new RoundedPanel();
        rPublishPanel = new RoundedPanel();

        setupREditPanel();
        setupRPublishPanel();
    }

    /**
     * Imposta il listener per la modifica del problema.
     */
    private void setupREditPanel() {
        rEditPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rEditPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!(controller.getCurrentUser().getRole() instanceof JudgeRole)) {
                    showErrorDialog("Only a Judge can edit this field.");
                } else {
                    JTextArea textArea = new JTextArea(10, 40);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);

                    JScrollPane scrollPane = new JScrollPane(textArea);

                    int result = JOptionPane.showConfirmDialog(
                            null,
                            scrollPane,
                            "Enter new Problem Statement",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        String newStatement = textArea.getText().trim();
                        if (!newStatement.isEmpty()) {
                            controller.getCurrentUser().getHackathon().setProblemStatement(newStatement);
                            problemStatementTextArea.setText(newStatement);
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rEditPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rEditPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Imposta il listener per la pubblicazione della classifica.
     */
    private void setupRPublishPanel() {
        rPublishPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rPublishPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Solo i giudici possono pubblicare la classifica
                User currentUser = controller.getCurrentUser();

                if (!(currentUser.getRole() instanceof JudgeRole)) {
                    JOptionPane.showMessageDialog(null, "Only a Judge can publish the ranking.", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Hackathon hackathon = currentUser.getHackathon();

                List<Team> teams = new ArrayList<>(hackathon.getTeams());

                teams.sort((t1, t2) -> Integer.compare(t2.getScore(), t1.getScore()));
                rankingListPanel.removeAll();

                for (int i = 0; i < teams.size(); i++) {
                    model.Team team = teams.get(i);
                    RoundedPanel rankingCard = createRankingCard(i + 1, team.getTeamName());
                    rankingListPanel.add(rankingCard);
                    rankingListPanel.add(Box.createVerticalStrut(10));
                }

                rankingListPanel.revalidate();
                rankingListPanel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rPublishPanel.setBackground(UIColors.CARMINE_RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                rPublishPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
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
     * Restituisce il pannello principale.
     * @return rootPanel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
