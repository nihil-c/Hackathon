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
    private JPanel membersListPanel;
    private JPanel uploadsListPanel;

    private final Controller controller;

    public TeamCardPanel(Controller controller) {
        this.controller = controller;

        customizeComponents();
        setupScrollPanel();
    }

    private void customizeComponents() {
        teamLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(Color.GRAY);

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

    private void setupInfoLabels() {
        User currentUser = controller.getCurrentUser();
        Role currentUserRole = currentUser.getRole();

        if (currentUserRole instanceof ParticipantRole participantRole) {
            if (participantRole.getTeam() != null) {
                infoLabel.setText("You are currently part of the team: " + participantRole.getTeam().getTeamName());
                membersInfoLabel.setVisible(false);
                uploadsInfoLabel.setVisible(false);
            } else {
                infoLabel.setText("You are currently not part of a team.");
                membersInfoLabel.setVisible(true);
                uploadsInfoLabel.setVisible(true);
            }
        }
    }

    private void setupScrollPanel() {
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);

        setupMembersListPanel();
    }

    private void setupMembersListPanel() {
        membersListPanel.setLayout(new BoxLayout(membersListPanel, BoxLayout.Y_AXIS));
        updateMembersListPanel();
    }

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

    private RoundedPanel createMemberCard(User user) {
        RoundedPanel card = new RoundedPanel();

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        card.setPreferredSize(new Dimension(0, 50));

        JLabel nameLabel = new JLabel(user.getUsername());
        JLabel emailLabel = new JLabel(user.getEmail());

        nameLabel.setForeground(UIColors.CARMINE_RED);
        nameLabel.setFont(new Font(null, Font.BOLD, 14));

        card.add(nameLabel);
        card.add(emailLabel);

        return card;
    }

    private void createUIComponents() {
        rCreateTeamPanel = new RoundedPanel();
        rJoinTeamPanel = new RoundedPanel();
        rAddPanel = new RoundedPanel();

        setupRCreateTeamPanelListener();
        setupRJoinTeamPanelListener();
        setupRAddPanelListener();
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
                joinTeamLabel.setForeground(null);
            }
        });
    }

    private void setupRAddPanelListener() {
        rAddPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rAddPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
