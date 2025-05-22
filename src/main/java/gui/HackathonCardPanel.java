package gui;

import controller.Controller;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HackathonCardPanel {
    private JPanel rootPanel;
    private JLabel hackathonLabel;
    private JLabel infoLabel;
    private JScrollPane scrollPanel;
    private JLabel overviewLabel;
    private JPanel roundedTitlePanel;
    private JLabel titleLabel;
    private JPanel roundedTitleContentPanel;
    private JPanel roundedLocationPanel;
    private JLabel locationLabel;
    private JPanel roundedLocationContentPanel;
    private JLabel titleContentLabel;
    private JLabel locationContentLabel;
    private JPanel roundedStartDatePanel;
    private JLabel startDateLabel;
    private JPanel roundedStartDateContentPanel;
    private JLabel startDateContentLabel;
    private JPanel roundedEndDatePanel;
    private JLabel endDateLabel;
    private JPanel roundedEndDateContentPanel;
    private JLabel endDateContentLabel;
    private JPanel roundedDeadlinePanel;
    private JLabel deadlineLabel;
    private JPanel roundedDeadlineContentPanel;
    private JLabel deadlineContentLabel;
    private JPanel roundedOrganizerPanel;
    private JLabel organizerLabel;
    private JPanel roundedOrganizerContentPanel;
    private JLabel organizerContentLabel;
    private JLabel problemStatementLabel;
    private JPanel roundedEditPanel;
    private JLabel editLabel;
    private JTextArea problemStatementTextArea;
    private JLabel rankingLabel;
    private JLabel publishLabel;
    private JPanel roundedPublishPanel;
    private JLabel rankingInfoLabel;
    private JPanel rankingListPanel;

    private final Controller controller;

    public HackathonCardPanel(Controller controller) {
        this.controller = controller;

        customizeComponents();
        setupScrollPanel();
    }

    private void customizeComponents() {
        hackathonLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(Color.GRAY);

        overviewLabel.setForeground(UIColors.CARMINE_RED);
        titleLabel.setForeground(Color.GRAY);
        locationLabel.setForeground(Color.GRAY);
        startDateLabel.setForeground(Color.GRAY);
        endDateLabel.setForeground(Color.GRAY);
        deadlineLabel.setForeground(Color.GRAY);
        organizerLabel.setForeground(Color.GRAY);

        problemStatementLabel.setForeground(UIColors.CARMINE_RED);
        editLabel.setForeground(Color.WHITE);

        rankingLabel.setForeground(UIColors.CARMINE_RED);
        publishLabel.setForeground(Color.WHITE);
        rankingInfoLabel.setForeground(Color.GRAY);
        // rankingInfoLabel.setVisible(false);
        rankingListPanel.setVisible(false);

        problemStatementTextArea.setBackground(null);
        problemStatementTextArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullamco laboriosam, nisi ut aliquid ex ea commodi consequatur. Duis aute irure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
    }

    private void setupScrollPanel() {
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);

        setupRankingListPanel();
    }

    private void setupRankingListPanel() {
        rankingListPanel.setLayout(new BoxLayout(rankingListPanel, BoxLayout.Y_AXIS));
        rankingListPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        for (int i = 1; i <= 10; i++) {
            RoundedPanel card = createRankingCard(i, "sample_text");
            rankingListPanel.add(card);
            rankingListPanel.add(Box.createVerticalStrut(15));
        }

        rankingListPanel.revalidate();
        rankingListPanel.repaint();
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

    private void createUIComponents() {
        roundedTitlePanel = new RoundedPanel();
        roundedLocationPanel = new RoundedPanel();
        roundedStartDatePanel = new RoundedPanel();
        roundedEndDatePanel = new RoundedPanel();
        roundedDeadlinePanel = new RoundedPanel();
        roundedOrganizerPanel = new RoundedPanel();

        roundedTitlePanel.setBackground(UIColors.LIGHT_GRAY);
        roundedLocationPanel.setBackground(UIColors.LIGHT_GRAY);
        roundedStartDatePanel.setBackground(UIColors.LIGHT_GRAY);
        roundedEndDatePanel.setBackground(UIColors.LIGHT_GRAY);
        roundedDeadlinePanel.setBackground(UIColors.LIGHT_GRAY);
        roundedOrganizerPanel.setBackground(UIColors.LIGHT_GRAY);

        roundedTitleContentPanel = new RoundedPanel();
        roundedLocationContentPanel = new RoundedPanel();
        roundedStartDateContentPanel = new RoundedPanel();
        roundedEndDateContentPanel = new RoundedPanel();
        roundedDeadlineContentPanel = new RoundedPanel();
        roundedOrganizerContentPanel = new RoundedPanel();

        roundedTitleContentPanel.setBackground(Color.WHITE);
        roundedLocationContentPanel.setBackground(Color.WHITE);
        roundedStartDateContentPanel.setBackground(Color.WHITE);
        roundedEndDateContentPanel.setBackground(Color.WHITE);
        roundedDeadlineContentPanel.setBackground(Color.WHITE);
        roundedOrganizerContentPanel.setBackground(Color.WHITE);

        roundedEditPanel = new RoundedPanel();
        roundedPublishPanel = new RoundedPanel();

        setupEditPanel();
        setupPublishPanel();
    }

    private void setupEditPanel() {
        roundedEditPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedEditPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                roundedEditPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedEditPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedEditPanel.setCursor(Cursor.getDefaultCursor());
                roundedEditPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void setupPublishPanel() {
        roundedPublishPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedPublishPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                roundedPublishPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedPublishPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedPublishPanel.setCursor(Cursor.getDefaultCursor());
                roundedPublishPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
