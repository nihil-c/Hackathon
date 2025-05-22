package gui;

import controller.Controller;
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
    private JPanel roundedJoinTeamPanel;
    private JPanel roundedCreateTeamPanel;
    private JLabel membersLabel;
    private JLabel uploadsLabel;
    private JLabel addLabel;
    private JPanel roundedAddPanel;
    private JLabel uploadsInfoLabel;
    private JLabel membersInfoLabel;
    private JScrollPane membersScrollPanel;
    private JPanel membersListPanel;

    private final Controller controller;

    public TeamCardPanel(Controller controller) {
        this.controller = controller;

        customizeComponents();
        setupScrollPanel();
    }

    private void customizeComponents() {
        teamLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(Color.GRAY);
        createTeamLabel.setForeground(Color.WHITE);
        joinTeamLabel.setForeground(Color.WHITE);

        membersLabel.setForeground(UIColors.CARMINE_RED);
        membersInfoLabel.setForeground(Color.GRAY);

        uploadsLabel.setForeground(UIColors.CARMINE_RED);
        addLabel.setForeground(Color.WHITE);
        uploadsInfoLabel.setForeground(Color.GRAY);
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
        // updateMembersListPanel();
    }

    public void updateMembersListPanel() {
        membersListPanel.removeAll();

        for (int i = 0; i < 5; i++) {
            JPanel card = createMemberCard("sample_text");
            membersListPanel.add(card);
            membersListPanel.add(Box.createVerticalStrut(15));
        }

        membersListPanel.revalidate();
        membersListPanel.repaint();
    }

    private RoundedPanel createMemberCard(String name) {
        RoundedPanel card = new RoundedPanel();

        card.setLayout(new BoxLayout(card, BoxLayout.X_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        card.setPreferredSize(new Dimension(0, 50));

        JLabel nameLabel = new JLabel(name);

        nameLabel.setForeground(UIColors.CARMINE_RED);
        nameLabel.setFont(new Font(null, Font.BOLD, 14));

        card.add(nameLabel);

        return card;
    }

    private void createUIComponents() {
        roundedCreateTeamPanel = new RoundedPanel();
        roundedJoinTeamPanel = new RoundedPanel();
        roundedAddPanel = new RoundedPanel();

        setupCreateTeamPanel();
        setupJoinTeamPanel();
        setupAddPanel();
    }

    private void setupCreateTeamPanel(){
        roundedCreateTeamPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedCreateTeamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                roundedCreateTeamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedCreateTeamPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedCreateTeamPanel.setCursor(Cursor.getDefaultCursor());
                roundedCreateTeamPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void setupJoinTeamPanel() {
        roundedJoinTeamPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedJoinTeamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                roundedJoinTeamPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedJoinTeamPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedJoinTeamPanel.setCursor(Cursor.getDefaultCursor());
                roundedJoinTeamPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void setupAddPanel() {
        roundedAddPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedAddPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                roundedAddPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedAddPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedAddPanel.setCursor(Cursor.getDefaultCursor());
                roundedAddPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
