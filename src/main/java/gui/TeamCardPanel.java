package gui;

import controller.Controller;
import model.ParticipantRole;
import model.Role;
import model.User;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JScrollPane membersScrollPanel;
    private JPanel membersListPanel;

    private final Controller controller;

    public TeamCardPanel(Controller controller) {
        this.controller = controller;

        customizeComponents();
    }

    private void customizeComponents() {
        User currentUser = controller.getCurrentUser();
        Role currentUserRole = currentUser.getRole();

        teamLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(Color.GRAY);

        if (currentUserRole instanceof ParticipantRole participantRole) {
            if (participantRole.getTeam() != null) {
                infoLabel.setText("You are currently part of the team: " + participantRole.getTeam().getTeamName());
            } else {
                infoLabel.setText("You are currently not part of a team.");
            }
        }

        rCreateTeamPanel.setBackground(UIColors.NIGHT_BLUE);
        createTeamLabel.setForeground(Color.WHITE);
        rJoinTeamPanel.setBackground(Color.WHITE);
        ((RoundedPanel) rJoinTeamPanel).setBorderColor(UIColors.NIGHT_BLUE);
        joinTeamLabel.setForeground(UIColors.NIGHT_BLUE);
        membersLabel.setForeground(UIColors.CARMINE_RED);
    }

    private void createUIComponents() {
        rCreateTeamPanel = new RoundedPanel();
        rJoinTeamPanel = new RoundedPanel();

        setupRCreateTeamPanelListener();
        setupRJoinTeamPanelListener();
    }

    private void setupRCreateTeamPanelListener() {
        rCreateTeamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rCreateTeamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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

    private void setupRJoinTeamPanelListener() {
        rJoinTeamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rJoinTeamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
