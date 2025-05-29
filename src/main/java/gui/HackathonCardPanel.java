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

    public HackathonCardPanel(Controller controller) {
        this.controller = controller;

        customizeComponents();
    }

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

        //titleLabel.setForeground(Color.GRAY);
        //locationLabel.setForeground(Color.GRAY);
        //startDateLabel.setForeground(Color.GRAY);
        //endDateLabel.setForeground(Color.GRAY);
        //deadlineLabel.setForeground(Color.GRAY);
        //maxParticipantsLabel.setForeground(Color.GRAY);
        //maxTeamSizeLabel.setForeground(Color.GRAY);
        //organizerLabel.setForeground(Color.GRAY);

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

    private void setupScrollPanel() {
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);
    }

    private void setupRankingListPanel() {
        rankingListPanel.setLayout(new BoxLayout(rankingListPanel, BoxLayout.Y_AXIS));
        rankingListPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

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

    private void setupRPublishPanel() {
        rPublishPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rPublishPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!(controller.getCurrentUser().getRole() instanceof JudgeRole)) {
                    showErrorDialog("Only a Judge can perform this action.");
                } else {
                    if (controller.getCurrentUser().getHackathon().getRanking() == null) {
                        rankingInfoLabel.setText("The ranking is currently unavailable.");
                    } else {
                        List<Team> ranking = controller.getCurrentUser().getHackathon().getRanking();
                        rankingListPanel.removeAll();

                        int maxTeams = Math.min(10, ranking.size());
                        for (int i = 0; i < maxTeams; i++) {
                            Team team = ranking.get(i);
                            RoundedPanel rankingCard = createRankingCard(i + 1, team.getTeamName());
                            rankingListPanel.add(rankingCard);
                            rankingListPanel.add(Box.createVerticalStrut(10));
                        }

                        rankingListPanel.revalidate();
                        rankingListPanel.repaint();
                    }
                }
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

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
