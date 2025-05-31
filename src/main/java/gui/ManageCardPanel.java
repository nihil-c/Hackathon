package gui;

import controller.Controller;
import model.ParticipantRole;
import model.Team;
import model.User;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ManageCardPanel {
    private JPanel rootPanel;
    private JList participantsList;
    private JList teamsList;
    private JTable uploadsTable;
    private JLabel manageLabel;
    private JLabel infoLabel;
    private JLabel participantsLabel;
    private JLabel participantsInfoLabel;
    private JPanel rSearchPanel;
    private JLabel searchLabel;
    private JLabel teamsAndUploadsLabel;
    private JLabel teamsAndUploadsInfoLabel;
    private JScrollPane scrollPanel;

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
        participantsList.setBorder(null);
    }

    private void setupTeamsAndUploadsSection() {
        teamsAndUploadsLabel.setForeground(UIColors.CARMINE_RED);
        teamsAndUploadsInfoLabel.setForeground(Color.GRAY);
        teamsList.setBorder(null);
        uploadsTable.setBorder(null);
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

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
