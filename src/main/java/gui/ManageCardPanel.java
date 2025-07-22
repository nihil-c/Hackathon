package gui;

import controller.Controller;
import model.*;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Pannello per la gestione dei team e dei partecipanti nell'applicazione Hackathon.IO.
 * <p>
 * Permette agli organizzatori e ai giudici di visualizzare, promuovere e valutare i team e i partecipanti.
 * I giudici possono assegnare punteggi e feedback ai team tramite una finestra dedicata.
 * </p>
 */
public class ManageCardPanel {
    private JPanel rootPanel;
    private JList participantsList;
    private JList teamsList;
    private JLabel manageLabel;
    private JLabel infoLabel;
    private JLabel participantsLabel;
    private JLabel participantsInfoLabel;
    private JLabel teamsAndUploadsLabel;
    private JLabel teamsAndUploadsInfoLabel;
    private JScrollPane scrollPanel;
    private JScrollPane participantsListScrollPanel;
    private JPanel rParticipantListPanel;
    private JScrollPane teamsListScrollPanel;
    private JPanel rTeamsListPanel;

    private final Controller controller;

    /**
     * Costruttore del pannello di gestione.
     * @param controller controller principale dell'applicazione
     */
    public ManageCardPanel(Controller controller) {
        this.controller = controller;

        setupScrollPanel();
        customizeComponents();
        populateParticipantsList();
        populateTeamsList();
        setupParticipantsListClickListener();
        setupTeamsAndUploadsListClickListener();
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
     * Personalizza i componenti grafici e imposta i colori.
     */
    private void customizeComponents() {
        setupHeader();
        setupParticipantsSection();
        setupTeamsAndUploadsSection();
    }

    /**
     * Imposta le proprietà grafiche della sezione header.
     */
    private void setupHeader() {
        manageLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
    }

    /**
     * Imposta le proprietà grafiche della lista dei partecipanti.
     */
    private void setupParticipantsSection() {
        participantsLabel.setForeground(UIColors.CARMINE_RED);
        participantsInfoLabel.setForeground(Color.GRAY);
        rParticipantListPanel.setBackground(Color.WHITE);
        participantsListScrollPanel.setBorder(null);
        participantsListScrollPanel.setBackground(null);
        participantsList.setBorder(null);
        participantsList.setBackground(null);
    }

    /**
     * Imposta le proprietà grafiche della sezione team e upload.
     */
    private void setupTeamsAndUploadsSection() {
        teamsAndUploadsLabel.setForeground(UIColors.CARMINE_RED);
        teamsAndUploadsInfoLabel.setForeground(Color.GRAY);
        rTeamsListPanel.setBackground(Color.WHITE);
        teamsListScrollPanel.setBorder(null);
        teamsListScrollPanel.setBackground(null);
        teamsList.setBorder(null);
        teamsList.setBackground(null);
    }

    /**
     * Popola la lista dei partecipanti.
     */
    private void populateParticipantsList() {
        User currentUser = controller.getCurrentUser();

        if (currentUser.getHackathon() != null) {
            DefaultListModel<User> listModel = new DefaultListModel<>();

            for (User user : currentUser.getHackathon().getParticipants()) {
                if (user.getRole() instanceof ParticipantRole) {
                    listModel.addElement(user);
                }
            }

            participantsList.setModel(listModel);
        }
    }

    /**
     * Popola la lista dei team.
     */
    private void populateTeamsList() {
        User currentUser = controller.getCurrentUser();

        if (currentUser.getHackathon() != null) {
            DefaultListModel<Team> listModel = new DefaultListModel<>();

            for (Team team : currentUser.getHackathon().getTeams()) {
                listModel.addElement(team);
            }

            teamsList.setModel(listModel);
        }
    }

    /**
     * Imposta il listener per la lista dei partecipanti (promozione a giudice).
     */
    private void setupParticipantsListClickListener() {
        participantsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                User currentUser = controller.getCurrentUser();

                if (!(currentUser.getRole() instanceof OrganizerRole)) {
                    return;
                }

                int index = participantsList.locationToIndex(e.getPoint());

                if (index >= 0) { // Verifica che l'indice è valido
                    User selectedUser = (User) participantsList.getModel().getElementAt(index);
                    if (selectedUser.getRole() instanceof ParticipantRole pr) {
                        pr = (ParticipantRole) selectedUser.getRole();
                        if (pr.getTeam() != null) {
                            JOptionPane.showMessageDialog(null, "You cannot promote a participant who is part of a team.", "Promotion not allowed", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        int result = JOptionPane.showConfirmDialog(
                                null,
                                "Do you want to promote '" + selectedUser.getUsername() + "' to Judge?",
                                "Promote to Judge",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (result == JOptionPane.YES_OPTION) {
                            selectedUser.setRole(new model.JudgeRole());
                            populateParticipantsList();
                            JOptionPane.showMessageDialog(null, "User promoted to Judge!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    /**
     * Imposta il listener per la lista dei team: apre la finestra di valutazione se l'utente è giudice.
     */
    private void setupTeamsAndUploadsListClickListener() {
        teamsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = teamsList.locationToIndex(e.getPoint());

                if (index >= 0) {
                    Team selectedTeam = (Team) teamsList.getModel().getElementAt(index);
                    User currentUser = controller.getCurrentUser();
                    if (currentUser.getRole() instanceof JudgeRole) {
                        TeamEvaluationDialog dialogPanel = new TeamEvaluationDialog(selectedTeam);
                        dialogPanel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "You do not have permission to evaluate teams.", "Permission Denied", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * Inizializza i componenti grafici custom.
     */
    private void createUIComponents() {
        rParticipantListPanel = new RoundedPanel();
        rTeamsListPanel = new RoundedPanel();
    }

    /**
     * Restituisce il pannello principale.
     * @return rootPanel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
