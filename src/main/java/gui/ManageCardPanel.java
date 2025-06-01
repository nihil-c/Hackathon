package gui;

import controller.Controller;
import model.ParticipantRole;
import model.Team;
import model.User;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;

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

    public ManageCardPanel(Controller controller) {
        this.controller = controller;

        setupScrollPanel();
        customizeComponents();
        populateParticipantsList();
        populateTeamsList();
    }

    private void setupScrollPanel() {
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);
    }

    private void customizeComponents() {
        setupHeader();
        setupParticipantsSection();
        setupTeamsAndUploadsSection();
    }

    private void setupHeader() {
        manageLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
    }

    private void setupParticipantsSection() {
        participantsLabel.setForeground(UIColors.CARMINE_RED);
        participantsInfoLabel.setForeground(Color.GRAY);
        rParticipantListPanel.setBackground(Color.WHITE);
        participantsListScrollPanel.setBorder(null);
        participantsListScrollPanel.setBackground(null);
        participantsList.setBorder(null);
        participantsList.setBackground(null);
    }

    private void setupTeamsAndUploadsSection() {
        teamsAndUploadsLabel.setForeground(UIColors.CARMINE_RED);
        teamsAndUploadsInfoLabel.setForeground(Color.GRAY);
        rTeamsListPanel.setBackground(Color.WHITE);
        teamsListScrollPanel.setBorder(null);
        teamsListScrollPanel.setBackground(null);
        teamsList.setBorder(null);
        teamsList.setBackground(null);
    }

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

    private void createUIComponents() {
        rParticipantListPanel = new RoundedPanel();
        rTeamsListPanel = new RoundedPanel();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
