package gui;

import controller.Controller;
import exceptions.AlreadyPartOfATeamException;
import exceptions.BlankFieldException;
import model.ParticipantRole;
import model.Role;
import model.Team;
import model.User;
import model.Upload;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

/**
 * Pannello grafico per la gestione e visualizzazione del team nell'applicazione Hackathon.IO.
 * <p>
 * Permette ai partecipanti di creare o unirsi a un team, visualizzare i membri e gli upload,
 * e interagire con le funzionalità di upload e feedback. Gestisce la logica di interazione utente-team.
 * </p>
 */
public class TeamCardPanel {
    private JPanel rootPanel;
    private JLabel teamLabel;
    private JScrollPane scrollPanel;
    private JLabel infoLabel;
    private JLabel createTeamLabel;
    private JLabel joinTeamLabel;
    private JPanel rJoinTeamPanel;
    private JPanel rCreateTeamPanel;
    private JLabel membersLabel;
    private JLabel uploadsLabel;
    private JLabel addLabel;
    private JPanel rAddPanel;
    private JLabel uploadsInfoLabel;
    private JLabel membersInfoLabel;
    private JPanel membersListPanel;
    private JPanel uploadsListPanel;
    private JLabel accessCodeLabel;

    private final Controller controller;

    /**
     * Costruttore del pannello team.
     * @param controller controller principale dell'applicazione
     */
    public TeamCardPanel(Controller controller) {
        this.controller = controller;

        customizeComponents();
        setupScrollPanel();
    }

    /**
     * Personalizza i componenti grafici del pannello team.
     */
    private void customizeComponents() {
        teamLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
        accessCodeLabel.setForeground(Color.GRAY);
        accessCodeLabel.setVisible(false);

        rCreateTeamPanel.setBackground(UIColors.NIGHT_BLUE);
        createTeamLabel.setForeground(Color.WHITE);
        rJoinTeamPanel.setBackground(Color.WHITE);
        ((RoundedPanel) rJoinTeamPanel).setBorderColor(UIColors.NIGHT_BLUE);
        joinTeamLabel.setForeground(UIColors.NIGHT_BLUE);

        membersLabel.setForeground(UIColors.CARMINE_RED);
        membersInfoLabel.setForeground(Color.GRAY);

        uploadsLabel.setForeground(UIColors.CARMINE_RED);
        rAddPanel.setBackground(UIColors.NIGHT_BLUE);
        addLabel.setForeground(Color.WHITE);
        uploadsInfoLabel.setForeground(Color.GRAY);

        setupInfoLabels();
    }

    /**
     * Aggiorna le etichette informative del pannello team.
     */
    private void setupInfoLabels() {
        User currentUser = controller.getCurrentUser();
        Role currentUserRole = currentUser.getRole();

        if (currentUserRole instanceof ParticipantRole participantRole) {
            if (participantRole.getTeam() != null) {
                infoLabel.setText("You are currently part of the team: " + participantRole.getTeam().getTeamName());
                membersInfoLabel.setVisible(false);
                uploadsInfoLabel.setVisible(false);
                String accessCode = participantRole.getTeam().getAccessCode();
                updateAccessCodeLabel(accessCode);
            } else {
                infoLabel.setText("You are currently not part of a team.");
                membersInfoLabel.setVisible(true);
                uploadsInfoLabel.setVisible(true);
                accessCodeLabel.setVisible(false);
            }
        }
    }

    /**
     * Imposta lo scroll e il layout dei pannelli membri e upload.
     */
    private void setupScrollPanel() {
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);

        setupMembersListPanel();
        setupUploadsListPanel();
    }

    /**
     * Imposta il layout e aggiorna la lista dei membri del team.
     */
    private void setupMembersListPanel() {
        membersListPanel.setLayout(new BoxLayout(membersListPanel, BoxLayout.Y_AXIS));
        updateMembersListPanel();
    }

    /**
     * Aggiorna la lista dei membri del team.
     */
    public void updateMembersListPanel() {
        User currentUser = controller.getCurrentUser();
        Role currentUserRole = currentUser.getRole();

        membersListPanel.removeAll();

        if (currentUserRole instanceof ParticipantRole participantRole) {
            if (participantRole.getTeam() != null) {
                for (User u : participantRole.getTeam().getMembers()) {
                    RoundedPanel card = createMemberCard(u);
                    membersListPanel.add(card);
                    membersListPanel.add(Box.createVerticalStrut(15));
                }
            }
        }

        membersListPanel.revalidate();
        membersListPanel.repaint();
    }

    /**
     * Crea una card grafica per un membro del team.
     * @param user utente membro del team
     * @return pannello grafico del membro
     */
    private RoundedPanel createMemberCard(User user) {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorderColor(UIColors.LIGHT_GRAY);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(user.getUsername());
        JLabel emailLabel = new JLabel(user.getEmail());

        nameLabel.setForeground(UIColors.CARMINE_RED);
        nameLabel.setFont(new Font(null, Font.BOLD, 14));

        card.add(nameLabel);
        card.add(emailLabel);

        return card;
    }

    /**
     * Imposta il layout e aggiorna la lista degli upload del team.
     */
    private void setupUploadsListPanel() {
        uploadsListPanel.setLayout(new BoxLayout(uploadsListPanel, BoxLayout.Y_AXIS));
        updateUploadsListPanel();
    }

    /**
     * Aggiorna la lista degli upload del team.
     */
    public void updateUploadsListPanel() {
        User currentUser = controller.getCurrentUser();
        Role currentUserRole = currentUser.getRole();

        uploadsListPanel.removeAll();

        if (currentUserRole instanceof ParticipantRole participantRole) {
            if (participantRole.getTeam() != null) {
                for (Upload upload : participantRole.getTeam().getUploads()) {
                    RoundedPanel card = createUploadCard(upload);
                    uploadsListPanel.add(card, 0);
                    uploadsListPanel.add(Box.createVerticalStrut(15), 1);
                }
            }
        }

        uploadsListPanel.revalidate();
        uploadsListPanel.repaint();
    }

    /**
     * Crea una card grafica per un upload del team.
     * @param upload upload da visualizzare
     * @return pannello grafico dell'upload
     */
    private RoundedPanel createUploadCard(Upload upload) {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorderColor(UIColors.LIGHT_GRAY);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(upload.getTitle());
        JLabel urlLabel = new JLabel("URL: " + upload.getUrl());
        JLabel dateLabel = new JLabel("Date: " + upload.getCreationDate());
        String author = upload.getUploader() != null ? upload.getUploader().getUsername() : "Unknown";
        JLabel authorLabel = new JLabel("Uploader: @" + author);

        titleLabel.setForeground(UIColors.CARMINE_RED);
        titleLabel.setFont(new Font(null, Font.BOLD, 14));
        urlLabel.setForeground(Color.GRAY);
        dateLabel.setForeground(Color.GRAY);

        card.add(titleLabel);
        card.add(urlLabel);
        card.add(dateLabel);
        card.add(authorLabel);

        makeCardInteractive(card, upload);

        return card;
    }

    /**
     * Rende interattiva la card di un upload, mostrando eventuale feedback.
     * @param card pannello grafico dell'upload
     * @param upload upload associato
     */
    private void makeCardInteractive(RoundedPanel card, Upload upload) {
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (upload.getFeedback() == null) {
                    showInfoDialog("Feedback", "This upload has no feedback yet.");
                } else {
                    showInfoDialog("Feedback", upload.getFeedback());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(UIColors.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
        });
    }

    /**
     * Inizializza i componenti grafici custom e i listener.
     */
    private void createUIComponents() {
        rCreateTeamPanel = new RoundedPanel();
        rJoinTeamPanel = new RoundedPanel();
        rAddPanel = new RoundedPanel();

        setupRCreateTeamPanelListener();
        setupRJoinTeamPanelListener();
        setupRAddPanelListener();
    }

    /**
     * Imposta il listener per la creazione di un nuovo team.
     */
    private void setupRCreateTeamPanelListener() {
        rCreateTeamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rCreateTeamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                User currentUser = controller.getCurrentUser();
                Role currentUserRole = currentUser.getRole();

                if (((ParticipantRole) currentUserRole).getTeam() == null){
                    String accessCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

                    JTextField teamNameField = new JTextField();
                    JTextField accessCodeField = new JTextField(accessCode);
                    accessCodeField.setForeground(Color.GRAY);
                    accessCodeField.setEditable(false);

                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    JLabel teamNameLabel = new JLabel("Team name");
                    teamNameLabel.setForeground(Color.GRAY);
                    panel.add(teamNameLabel);
                    panel.add(teamNameField);
                    panel.add(Box.createVerticalStrut(10));

                    JLabel accessCodeLabel = new JLabel("Access code (auto-generated)");
                    accessCodeLabel.setForeground(Color.GRAY);
                    panel.add(accessCodeLabel);
                    panel.add(accessCodeField);
                    panel.add(Box.createVerticalStrut(10));

                    JLabel shareLabel = new JLabel("Share this code with your teammates.");
                    shareLabel.setForeground(UIColors.CARMINE_RED);
                    panel.add(shareLabel);

                    int result = JOptionPane.showConfirmDialog(
                            null,
                            panel,
                            "Create Team",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            String teamName = teamNameField.getText().trim();

                            if (teamName.isBlank()) throw new BlankFieldException();

                            ParticipantRole participantRole = (ParticipantRole) currentUserRole;
                            participantRole.createTeam(currentUser, currentUser.getHackathon(), teamName, accessCode);
                            setupInfoLabels();
                            updateMembersListPanel();
                            updateAccessCodeLabel(accessCode);
                            showInfoDialog("Success", "Team created successfully!\nShare the access code with your teammates.");
                        } catch (BlankFieldException | AlreadyPartOfATeamException ex) {
                            showErrorDialog(ex.getMessage());
                        }
                    }
                } else {
                    showErrorDialog("You are already part of a team.");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rCreateTeamPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rCreateTeamPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Imposta il listener per unirsi a un team esistente.
     */
    private void setupRJoinTeamPanelListener() {
        rJoinTeamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rJoinTeamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                User currentUser = controller.getCurrentUser();
                Role currentUserRole = currentUser.getRole();

                if (((ParticipantRole) currentUserRole).getTeam() != null) {
                    showErrorDialog("You are already part of a team.");
                    return;
                }

                JTextField teamNameField = new JTextField();
                JTextField accessCodeField = new JTextField();

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JLabel teamNameLabel = new JLabel("Team name");
                teamNameLabel.setForeground(Color.GRAY);
                panel.add(teamNameLabel);
                panel.add(teamNameField);
                panel.add(Box.createVerticalStrut(10));

                JLabel accessCodeLabel = new JLabel("Access code");
                accessCodeLabel.setForeground(Color.GRAY);
                panel.add(accessCodeLabel);
                panel.add(accessCodeField);
                panel.add(Box.createVerticalStrut(10));

                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "Join Team",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String teamName = teamNameField.getText().trim();
                    String accessCode = accessCodeField.getText().trim();

                    if (teamName.isBlank() || accessCode.isBlank()) {
                        showErrorDialog("Please fill in all fields.");
                        return;
                    }

                    var hackathon = currentUser.getHackathon();
                    Team foundTeam = null;
                    for (Team t : hackathon.getTeams()) {
                        if (t.getTeamName().equals(teamName) && t.getAccessCode().equals(accessCode)) {
                            foundTeam = t;
                            break;
                        }
                    }

                    if (foundTeam == null) {
                        showErrorDialog("Team not found or access code is incorrect.");
                        return;
                    }

                    try {
                        foundTeam.addMember(currentUser);
                        ((ParticipantRole) currentUserRole).setTeam(foundTeam);
                        setupInfoLabels();
                        updateMembersListPanel();
                        updateUploadsListPanel();
                        showInfoDialog("Success", "You have successfully joined the team!");
                    } catch (Exception ex) {
                        showErrorDialog("Unable to join the team: " + ex.getMessage());
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((RoundedPanel) rJoinTeamPanel).setBorderColor(UIColors.CARMINE_RED);
                joinTeamLabel.setForeground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((RoundedPanel) rJoinTeamPanel).setBorderColor(UIColors.NIGHT_BLUE);
                joinTeamLabel.setForeground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Imposta il listener per aggiungere un nuovo upload.
     */
    private void setupRAddPanelListener() {
        rAddPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rAddPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                User currentUser = controller.getCurrentUser();
                Role currentUserRole = currentUser.getRole();

                if (!(currentUserRole instanceof ParticipantRole participantRole)) {
                    showErrorDialog("Only participants can upload a project.");
                    return;
                }

                if (participantRole.getTeam() == null) {
                    showErrorDialog("You must be in a team to upload a project.");
                    return;
                }

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JTextField titleField = new JTextField();
                JTextField urlField = new JTextField();
                JLabel titleLabel = new JLabel("Title:");
                JLabel urlLabel = new JLabel("URL:");

                titleLabel.setForeground(Color.GRAY);
                titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                titleField.setAlignmentX(Component.LEFT_ALIGNMENT);
                urlLabel.setForeground(Color.GRAY);
                urlLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                urlField.setAlignmentX(Component.LEFT_ALIGNMENT);

                panel.add(titleLabel);
                panel.add(titleField);
                panel.add(Box.createVerticalStrut(10));
                panel.add(urlLabel);
                panel.add(urlField);
                panel.add(Box.createVerticalStrut(10));

                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "New Upload",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String title = titleField.getText().trim();
                    String url = urlField.getText().trim();

                    if (title.isBlank() || url.isBlank()) {
                        showErrorDialog("Please fill in all fields.");
                        return;
                    }

                    participantRole.upload(currentUser, title, url);
                    updateUploadsListPanel();
                    showInfoDialog("Success", "Upload successfully added!");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rAddPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rAddPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Aggiorna la label del codice di accesso del team.
     * @param accessCode codice di accesso
     */
    private void updateAccessCodeLabel(String accessCode) {
        accessCodeLabel.setText("Access Code: " + accessCode);
        accessCodeLabel.setVisible(true);
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
     * Mostra una finestra informativa con titolo e messaggio.
     * @param title titolo della finestra
     * @param message messaggio da visualizzare
     */
    private void showInfoDialog(String title, String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Restituisce il pannello principale del team.
     * @return rootPanel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
